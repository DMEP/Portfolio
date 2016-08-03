package leafAnalysis;

/*
 * This file is the Species.java which handles how the gather information about
 * the image and the user input will be put together and stored to the database.
 * @author Daniel Elstob @version 1.0 23-04-2015
 */
import java.awt.Polygon;

public class Species {
    public String name;
    public int nPoints;
    public int nHarm;
    public EdgeList edges = null;
    public EdgeList harmonics = null;
    public double[][] aveHarmonics = null;
    public double[][] aveCoords = null;
    public boolean sampledScaledTranslated = false;

    // Creates a new instance of Species.
    public Species(String taxonName, double[][] coordinates, MainScreen m) { //primary constructor
        init(taxonName, coordinates, m);
    }

    public Species(String taxonName, double[][] coordinates, int nPoints, int nHarm) {
        init(taxonName, coordinates, nPoints, nHarm);
    }

    public Species(String taxonName, Polygon p, MainScreen m) {
        //convert polygon to coordinates and initialize
        init(taxonName, Edging.extractCoordinates(p), m);
    }

    private void init(String taxonName, double[][] coordinates, int nPoints, int nHarm) {
        name = taxonName;
        this.nPoints = nPoints;
        this.nHarm = nHarm;
        this.edges = new EdgeList(taxonName, "unknown", 0.0, 0, 0.0, "unknown", coordinates[0].length, "unknown", 0, 2, coordinates);
        //scale, sample, and translate as needed
        scaleTranslateSampleDatabase();
        double[][] temp = Edging.calculateFourierCoefficients(edges.coordinatesSST, nHarm);
        harmonics = new EdgeList(taxonName, "unknown", 0.0, 0, 0.0, "unknown", temp[0].length, "unknown", 0, 2, temp);
        sampledScaledTranslated = false;
    }

    private void init(String taxonName, double[][] coordinates, MainScreen m) {
        name = taxonName;
        nPoints = m.nPoints;
        nHarm = m.nHarm;
        System.out.println(nPoints + " " + nHarm);
        edges = new EdgeList(taxonName, "unknown", 0.0, 0, 0.0, "unknown", coordinates[0].length, "unknown", 0, 2, coordinates);
        scaleTranslateSampleDatabase();
        harmonics = new EdgeList(taxonName, "unknown", 0.0, 0, 0.0, "unknown", nHarm, "unknown", 0, 2, Edging.calculateFourierCoefficients(edges.coordinatesSST, nHarm));
        sampledScaledTranslated = false;
    }

    // Add new image inforamtion and edges to currently opened database.
    public void appendCoordinates(String taxonName, double[][] coordinates) {
        //add unscaled, untranslated coordinates to edges list
        if (edges == null) {
            edges = new EdgeList(taxonName, "unknown", 0.0, 0, 0.0, "unknown", coordinates[0].length, "unknown", 0, 2, coordinates);
        } else {
            EdgeList eL = new EdgeList(taxonName, "unknown", 0.0, 0, 0.0, "unknown", coordinates[0].length, "unknown", 0, 2, coordinates);
            edges.next = eL;
            eL.previous = edges;
            edges = edges.next;
        }
        //scale, sample, and translate as needed
        scaleTranslateSampleDatabase();
        //calculate Fourier coefficients and add them to harmonics list
        if (harmonics == null) {
            harmonics = new EdgeList(taxonName, "unknown", 0.0, 0, 0.0, "unknown", nHarm, "unknown", 0, 2, Edging.calculateFourierCoefficients(edges.coordinatesSST, nHarm));
        } else {
            EdgeList hosl = new EdgeList(taxonName, "unknown", 0.0, 0, 0.0, "unknown", nHarm, "unknown", 0, 2, Edging.calculateFourierCoefficients(edges.coordinatesSST, nHarm));
            harmonics.next = hosl;
            hosl.previous = harmonics;
            harmonics = harmonics.next;
        }
        //set sampledScaledTranslated to false
        sampledScaledTranslated = true;

    }

    public void prepareCoordinates() throws Exception {
        //scale, sample, and translate as needed
        scaleTranslateSampleDatabase();
        //assume harmonics are calculated for each leaf
        //calculate average harmonics
        aveHarmonics = Edging.calculateAverageHarmonics(harmonics, nHarm);
        //superimpose leaves
        optimallyRotateCoordinates();
        //calculate average coordinates
        aveCoords = Edging.calculateAverageCoordinates(edges, nPoints);
        sampledScaledTranslated = true;
    }
    
    // Method for superimposing leaves.
    private void optimallyRotateCoordinates() throws Exception {
        while (edges.previous != null) {
            edges = edges.previous;
        }
        if (edges.next == null) {
            return;
        }
        //set the leaf against which all others will be superimposed
        double[][] standard = new double[2][nPoints];
        System.arraycopy(edges.coordinatesSST[0], 0, standard[0], 0, nPoints);
        System.arraycopy(edges.coordinatesSST[1], 0, standard[1], 0, nPoints);
        edges = edges.next;
        while (edges.next != null) {
            if (edges.coordinatesSST == null || edges.centered_normalized != 2) {
                throw new Exception("Coordinates should be sampled, scaled, and translated before being superimposed");
            }
            edges.coordinatesSST = Edging.optimallyRotate(standard, edges.coordinatesSST);
            edges = edges.next;
        }
        if (edges.coordinatesSST == null || edges.centered_normalized != 2) {
            throw new Exception("Coordinates should be sampled, scaled, and translated before being superimposed");
        }
        edges.coordinatesSST = Edging.optimallyRotate(standard, edges.coordinatesSST);
    }

    // Method to scale, sample and translate the leaf edge.
    private void scaleTranslateSampleDatabase() {
        while (edges.previous != null) {
            edges = edges.previous;
        }
        while (edges.next != null) {
            if (edges.centered_normalized != 2 || edges.coordinatesSST == null) {
                edges.centroidDistance = Edging.findCentroidDistance(edges.coordinates);
                edges.coordinatesSST = Edging.scalePoints(edges.centroidDistance, edges.coordinates);
                System.out.println("After scaling, the centroid distance is " + Edging.findCentroidDistance(edges.coordinatesSST));
                edges.coordinatesSST = Edging.translateCoordinates(edges.coordinatesSST, Edging.findCentroid(edges.coordinatesSST));
                edges.coordinatesSST = Edging.samplePoints(edges.coordinatesSST, nPoints);
                edges.centered_normalized = 2;
            }
            edges = edges.next;
        }
        if (edges.centered_normalized != 2 || edges.coordinatesSST == null) {
            edges.centroidDistance = Edging.findCentroidDistance(edges.coordinates);
            edges.coordinatesSST = Edging.scalePoints(edges.centroidDistance, edges.coordinates);
            edges.coordinatesSST = Edging.translateCoordinates(edges.coordinatesSST, Edging.findCentroid(edges.coordinatesSST));
            edges.coordinatesSST = Edging.samplePoints(edges.coordinatesSST, nPoints);
            edges.centered_normalized = 2;
        }
    }
}
