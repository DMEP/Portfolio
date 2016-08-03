package leafAnalysis;

/*
 * This file is the EdgeList.java which will handle the calculating and tempory
 * storing of the edges and their information. @author Daniel Elstob @version
 * 1.0 23-04-2015
 */

public class EdgeList extends Object {
    EdgeList next = null;
    EdgeList previous = null;
    int number = -1;
    String label = null;
    String part_name = null;
    String taxon = null;
    String imageFile = null;
    double centroidDistance;
    int pixelArea;
    double pixelPerCentimeter;
    int centered_normalized = 0;
    int dimensionality = 0;
    double[][] coordinates = null;
    double[][] coordinatesSST = null;     //coordinates that are scaled, sampled, and translated

    // Create new form for EdgeList
    public EdgeList() {
    }

    // Information of the analysed image
    public EdgeList(String Taxon, String imageFile, double centroidDistance, int pixelArea, double pixelPerCentimeter, String Label, int Number, String Part_name, int Centered_normalized, int Dimensionality, double[][] Coordinates) {
        next = null;
        previous = null;
        taxon = Taxon;
        this.imageFile = imageFile;
        this.centroidDistance = centroidDistance;
        this.pixelArea = pixelArea;
        this.pixelPerCentimeter = pixelPerCentimeter;
        label = Label;
        number = Number;
        part_name = Part_name;
        centered_normalized = Centered_normalized;
        dimensionality = Dimensionality;
        coordinates = new double[dimensionality][number];
        for (int i = 0; i < dimensionality; i++) {
            System.arraycopy(Coordinates[i], 0, coordinates[i], 0, number);
        }
    }
}
