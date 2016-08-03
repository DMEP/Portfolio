package leafAnalysis;

/*
 * This file is the Edging.java which handles all the thresholding, edging,
 * plotting of apex and drawing the edge that will be shown in other GUI's.
 * @author Daniel Elstob @version 1.0 23-04-2015
 */
import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.*;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;

public class Edging {
    int i;
       
    public Edging()
            throws Exception {
        this.init();
    }

    public void init()
            throws Exception {
    }
    
    // Method for the creation of redered image  
    public static BufferedImage renderEdgeImage(Polygon[] shapes, int width, int height, boolean[] keep) {
        BufferedImage bi = null;
        try {
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            GraphicsDevice gs = ge.getDefaultScreenDevice();
            GraphicsConfiguration gc = gs.getDefaultConfiguration();
            // Create an image that supports transparent pixels
            bi = gc.createCompatibleImage(width, height, Transparency.OPAQUE);
            if (bi == null) {
                return null;
            }
            int size = shapes.length;
            for (int i = 0; i < size; i++) {
                if (keep[i]) {
                    for (int j = 0; j < shapes[i].npoints; j++) {
        //hexadecimal representation of pixel integer value using DirectColorModel
        //0xff000000 	alpha multiplier
        //0x00ff0000	red value (8bit)
        //0x0000ff00	green value (8 bit)
        //0x000000ff	blue value (8 bit)
                        if (shapes[i].xpoints[j] > 0 && shapes[i].xpoints[j] < width && shapes[i].ypoints[j] > 0 && shapes[i].ypoints[j] < height) {
                            bi.setRGB(shapes[i].xpoints[j], shapes[i].ypoints[j], 0xFF00FF00);
                        }
                    }
                }
            }
            Graphics g = bi.getGraphics();
            g.setColor(Color.red);
            for (int i = 0; i < size; i++) {
                if (keep[i]) {
                    Point2D.Double cp = findCentroid(extractCoordinates((Polygon) shapes[i]));
                    g.drawString(Integer.toString(i + 1), (int) cp.x, (int) cp.y);
                }
            }
            g.dispose();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return bi;
    }

    // Method to all the saving of comparison or rendered image
    public static boolean renderBufferedImage(BufferedImage bi, String format, String outFileName) {
        try {
            Iterator writers = ImageIO.getImageWritersByFormatName(format);
            if (!writers.hasNext()) {
                String[] formats = ImageIO.getWriterFormatNames();
            }
            ImageWriter writer = (ImageWriter) writers.next();
            File f1 = new File(outFileName);
            ImageOutputStream ios = ImageIO.createImageOutputStream(f1);
            writer.setOutput(ios);
            writer.write(bi);
            ios.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    // Method of thresholding the image.
    public static boolean isMargin(int i, int j, int[][] imagePix, int threshold) {
        boolean test = false;
        if (i <= 0 || j <= 0 || i >= imagePix.length || j >= imagePix[0].length) {
            return false;
        }
        if (imagePix[i][j] < threshold) {
            if (imagePix[i - 1][j] < threshold) {
                if (imagePix[i - 1][j - 1] < threshold || imagePix[i - 1][j + 1] < threshold) {
                    test = true;
                }
            }
            if (imagePix[i][j + 1] < threshold) {
                if (imagePix[i - 1][j + 1] < threshold || imagePix[i + 1][j + 1] < threshold) {
                    test = true;
                }
            }
            if (imagePix[i + 1][j] < threshold) {
                if (imagePix[i + 1][j - 1] < threshold || imagePix[i + 1][j + 1] < threshold) {
                    test = true;
                }
            }
            if (imagePix[i][j - 1] < threshold) {
                if (imagePix[i - 1][j - 1] < threshold || imagePix[i + 1][j - 1] < threshold) {
                    test = true;
                }
            }
        }
        if (imagePix[i - 1][j - 1] < threshold && imagePix[i - 1][j] < threshold && imagePix[i - 1][j + 1] < threshold && imagePix[i][j - 1] < threshold && imagePix[i][j] < threshold && imagePix[i][j + 1] < threshold && imagePix[i + 1][j - 1] < threshold && imagePix[i + 1][j] < threshold && imagePix[i + 1][j + 1] < threshold) {
            test = false;
        }
        return test;
    }
        
    public static boolean pointNearRegistered(int x, int y, int W, boolean[][] registered) {
        for (int i = -W; i <= W; i++) {
            for (int j = -W; j <= W; j++) {
                if (x + i >= 0 && y + j >= 0) {
                    if (x + 1 < registered.length && y + j < registered[0].length) {
                        if (registered[x + i][y + j]) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    // Method for marking the apex at the midvein of the leaf.
    public static boolean isApex(int i, int j, int threshold, boolean marked, int[][] imagePix) {
        if (i == 0 || j == 0 || i >= imagePix.length || j >= imagePix[0].length) {
            return false;
        }
        if (marked) {
            if (imagePix[i][j] == 0 && imagePix[i][j - 1] == 0 && imagePix[i][j + 1] == 0 && imagePix[i - 1][j] == 0 && imagePix[i + 1][j] == 0) {
                return true;
            }
        }
        if (!marked) {
            if (imagePix[i - 1][j] > threshold && imagePix[i][j] < threshold && imagePix[i][j - 1] > threshold && imagePix[i - 1][j - 1] > threshold && imagePix[i + 1][j - 1] > threshold) {
                if (imagePix[i][j + 1] < threshold || imagePix[i - 1][j + 1] < threshold || imagePix[i + 1][j + 1] < threshold) {
                    return true;
                }
            }
        }
        return false;
    }

    // Method to find and return edges
    public static Polygon[] findCoordinates(File image) {
        try {
            BufferedImage bi = ImageIO.read(image);
            int height = ImageInfo.imageHeight(bi);
            int width = ImageInfo.imageWidth(bi);
            return Edging.findCoordinates(ImageInfo.grayScale(ImageInfo.readImage(bi, 0, 0, height, width)), 220, 5, false);
        } catch (Exception e) {
        }
        return null;
    }
   
    // This Method returns a linked list of Polygons representing the shapes found in the image.
    // This will then be represented on the graphicEdge from EdgeInfo.java.
    public static Polygon[] findCoordinates(int[][] imagePix, int threshold, int spacing, boolean marked) {
        //imagePix is matrix of greyscaled 8-bit pixel values.
        //threshold is boundary pixel intensity for edge detection.
        //spacing is minimum distance in pixels between two objects in image. It should be greater than 5.
        //marked is true if apex (point where midvein exits leaf at top) is marked with a black '+'.
        //registry is a boolean maxtrix of dim[imagePix] indicating whether element i,j is in a polygon.

        LinkedList coords = new LinkedList();
        boolean[][] registry = new boolean[imagePix.length][imagePix[0].length];
        for (int i = 0; i < imagePix.length; i++) {
            for (int j = 0; j < imagePix[0].length; j++) {
                registry[i][j] = false;
            }
        }
        for (int j = 0; j < imagePix[0].length; j++) {
            for (int i = 0; i < imagePix.length; i++) {
                if (Edging.isApex(i, j, threshold, marked, imagePix)) {
                    if (Edging.isMargin(i, j, imagePix, threshold)) {
                        if (!Edging.pointNearRegistered(i, j, spacing, registry)) {
                            coords.add(Edging.findEdge(new Point(i, j), imagePix, registry, threshold));
                        }
                    }
                }
            }
        }
        Polygon[] out = new Polygon[coords.size()];
        for (int i = 0; i < coords.size(); i++) {
            out[i] = (Polygon) coords.get(i);
        }
        return out;
    }

    // <editor-fold defaultstate="collapsed" desc="Find Edge">
    // Traces the points of a single edge starting at point p.
    public static Polygon findEdge(Point p, int[][] imagePix, boolean[][] registry, int threshold) {
        int W = 0, passes = 0, state = 1, t, i;
        Polygon o = new Polygon();
        Point top = new Point(p.x, p.y), test = new Point(0, 0);
        boolean yes = false;
        o.addPoint(p.x, p.y);
        while (W <= 5) {
            while (passes <= 4) {
                yes = false;
                if (state == 1) {
                    for (t = 0; t <= W - 1; t++) {
                        for (i = 0; i <= W - 1; i++) {
                            if (!yes) {
                                test.x = p.x - i;
                                test.y = p.y + t - 1;
                                if (test.x > 0 && test.y > 0) {
                                    if (Edging.isMargin(test.x, test.y, imagePix, threshold) && !registry[test.x][test.y]) {
                                        p.x = test.x;
                                        p.y = test.y;
                                        o.addPoint(p.x, p.y);
                                        registry[p.x][p.y] = true;
                                        passes = 0;
                                        yes = true;
                                        W = 1;
                                    }
                                }
                            }
                        }
                    }
                    if (!yes) {
                        state = 2;
                        passes++;
                    }
                } 	//end State 1
                if (state == 2) {
                    for (i = 0; i <= W - 1; i++) {
                        for (t = 0; t <= W - 1; t++) {
                            if (!yes) {
                                test.x = p.x + i - 1;
                                test.y = p.y + t;
                                if (test.x > 0 && test.y > 0) {
                                    if (Edging.isMargin(test.x, test.y, imagePix, threshold) && !registry[test.x][test.y]) {
                                        p.x = test.x;
                                        p.y = test.y;
                                        o.addPoint(p.x, p.y);
                                        registry[p.x][p.y] = true;
                                        passes = 0;
                                        yes = true;
                                        W = 1;
                                    }
                                }
                            }
                        }
                    }
                    if (!yes) {
                        state = 3;
                        passes++;
                    }
                } 	//end State 2
                if (state == 3) {
                    for (t = 0; t <= W - 1; t++) {
                        for (i = 0; i <= W - 1; i++) {
                            if (!yes) {
                                test.x = p.x + i;
                                test.y = p.y - t + 1;
                                if (test.x > 0 && test.y > 0) {
                                    if (Edging.isMargin(test.x, test.y, imagePix, threshold) && !registry[test.x][test.y]) {
                                        p.x = test.x;
                                        p.y = test.y;
                                        o.addPoint(p.x, p.y);
                                        registry[p.x][p.y] = true;
                                        passes = 0;
                                        yes = true;
                                        W = 1;
                                    }
                                }
                            }
                        }
                    }
                    if (!yes) {
                        state = 4;
                        passes++;
                    }
                }	//end State 3
                if (state == 4) {
                    for (i = 0; i <= W - 1; i++) {
                        for (t = 0; t <= W - 1; t++) {
                            if (!yes) {
                                test.x = p.x + i - 1;
                                test.y = p.y - t;
                                if (test.x > 0 && test.y > 0) {
                                    if (Edging.isMargin(test.x, test.y, imagePix, threshold) && !registry[test.x][test.y]) {
                                        p.x = test.x;
                                        p.y = test.y;
                                        o.addPoint(p.x, p.y);
                                        registry[p.x][p.y] = true;
                                        passes = 0;
                                        yes = true;
                                        W = 1;
                                    }
                                }
                            }
                        }
                    }
                    if (!yes) {
                        state = 1;
                        passes++;
                    }
                }	//end State 4
                if (Math.abs(p.x - top.x) <= 2 && Math.abs(p.y - top.y) <= 2 && o.npoints >= 10) {
                    passes = 5;
                    W = 5;
                }
            }	//end passes loop
            passes = 0;
            W++;
        }		//end W loop
        return o;
    }
    // </editor-fold>
    
    // This method is used for comparing to scale the shape.
    public static double[][] scalePoints(double centroidDistance, double[][] coordinates) {
        int npoints = coordinates[0].length;
        double[][] temp = new double[2][npoints];
        System.arraycopy(coordinates[0], 0, temp[0], 0, npoints);
        System.arraycopy(coordinates[1], 0, temp[1], 0, npoints);
        for (int i = 0; i < npoints; i++) {
            temp[0][i] = coordinates[0][i] / centroidDistance;
            temp[1][i] = coordinates[1][i] / centroidDistance;
        }
        return temp;
    }

    //This method find the area of proposed shape.
    public static int findArea(Polygon shape) {
        Rectangle r = shape.getBounds();
        int area = 0;
        for (int i = r.x; i <= r.x + r.width; i++) {
            for (int j = r.y; j < r.y + r.height; j++) {
                if (shape.contains(i, j)) {
                    area++;
                }
            }
        }
        return area;
    }

    // This method extracts the coordinates of the edge.
    public static double[][] extractCoordinates(Polygon p) {
        if (p == null) {
            return null;
        }
        double[][] coordinates = new double[2][p.npoints];
        for (int i = 0; i < p.npoints; i++) {
            coordinates[0][i] = (double) p.xpoints[i];
            coordinates[1][i] = (double) p.ypoints[i];
        }
        return coordinates;
    }

    // Extract the coordinate of the edge that will be kept.
    public static double[][][] extractCoordinates(Polygon[] p, int nPoints, boolean[] keep) {
        int kn = 0;
        for (int i = 0; i < keep.length; i++) {
            if (keep[i]) {
                kn++;
            }
        }
        if (p == null) {
            return null;
        }
        double[][][] coordinates = new double[kn][2][nPoints];
        int cnt = 0;
        for (int j = 0; j < p.length; j++) {
            if (keep[j]) {
                int nPts = p[j].npoints;
                float interval = (float) nPts / (float) nPoints;
                for (int i = 0; i < nPoints; i++) {
                    coordinates[cnt][0][i] = p[j].xpoints[Math.max(0, Math.round(interval * (float) i) - 1)];
                    coordinates[cnt][1][i] = p[j].ypoints[Math.max(0, Math.round(interval * (float) i) - 1)];
                }
                cnt++;
            }
        }
        return coordinates;
    }

    // A method to find the centroid distance.
    public static double findCentroidDistance(double[][] coordinates) {
        Point2D.Double pd = new Point2D.Double();
        pd = Edging.findCentroid(coordinates);
        int npoints = coordinates[0].length;
        double dist = 0.0;
        for (int i = 0; i < npoints; i++) {
            dist += Math.sqrt((coordinates[0][i] - pd.x) * (coordinates[0][i] - pd.x) + (coordinates[1][i] - pd.y) * (coordinates[1][i] - pd.y));
        }
        dist = dist / (double) npoints;
        return dist;
    }

    public static Point2D.Double findCentroid(double coordinates[][]) {
        int npoints = coordinates[0].length;
        double xave = 0, yave = 0;
        for (int i = 0; i < npoints; i++) {
            xave += coordinates[0][i];
            yave += coordinates[1][i];
        }
        return (new Point2D.Double(xave / (double) npoints, yave / (double) npoints));
    }

    // The method that will translate the coordinates.
    public static double[][] translateCoordinates(double coordinates[][], double x, double y) {
        int npoints = coordinates[0].length;
        double[][] temp = new double[2][npoints];
        System.arraycopy(coordinates[0], 0, temp[0], 0, npoints);
        System.arraycopy(coordinates[1], 0, temp[1], 0, npoints);
        for (int i = 0; i < npoints; i++) {
            temp[0][i] = coordinates[0][i] + x;
            temp[1][i] = coordinates[1][i] + y;
        }
        return temp;
    }

    // Return coordinates for species and main screen use
    public static double[][] translateCoordinates(double[][] coordinates, Point2D.Double p) {
        return Edging.translateCoordinates(coordinates, -1 * p.x, -1 * p.y);
    }

    // Method that is used for rotation of edges in compare.
    public static double[][] samplePoints(double coordinates[][], int nPoints) {
        double[][] out = new double[2][nPoints];
        int nPts = coordinates[0].length;
        float interval = (float) nPts / (float) nPoints;
        for (int i = 0; i < nPoints; i++) {
            out[0][i] = coordinates[0][Math.max(0, Math.round(interval * (float) i) - 1)];
            out[1][i] = coordinates[1][Math.max(0, Math.round(interval * (float) i) - 1)];
        }
        return out;
    }
    
    // Method to get the Harmonics.
    // Requires input coordinates to be in a radial function.
    public static double[][] getHarmonics(double[][] radialFunction, int harmnum) {
        int margnum = radialFunction[0].length;
        double[] a = new double[harmnum];
        double[] b = new double[harmnum];
        double[][] out = new double[2][harmnum];
        double lint = 0, hint = 0;
        for (int z = 0; z <= harmnum - 1; z++) {
            a[z] = 0.0;
            b[z] = 0.0;
            for (int t = 0; t <= margnum - 1; t++) {
                if (t == 0) {
                    lint = -Math.PI;
                    hint = 2 * Math.PI * (radialFunction[0][t] + .5 * (radialFunction[0][t + 1] - radialFunction[0][t] - radialFunction[0][0])) / radialFunction[0][margnum - 1] - Math.PI;
                } else if (t != margnum - 1) {
                    lint = 2 * Math.PI * (radialFunction[0][t] + .5 * (radialFunction[0][t - 1] - radialFunction[0][t] - radialFunction[0][0])) / radialFunction[0][margnum - 1] - Math.PI;
                    hint = 2 * Math.PI * (radialFunction[0][t] + .5 * (radialFunction[0][t + 1] - radialFunction[0][t] - radialFunction[0][0])) / radialFunction[0][margnum - 1] - Math.PI;
                }
                if (t == margnum - 1) {
                    lint = 2 * Math.PI * (radialFunction[0][t] + .5 * (radialFunction[0][t - 1] - radialFunction[0][t] - radialFunction[0][0])) / radialFunction[0][margnum - 1] - Math.PI;
                    hint = Math.PI;
                }
                if (z == 0) {
                    a[z] += (1 / Math.PI) * radialFunction[1][t] * (hint - lint);
                }
                if (z > 0) {
                    a[z] += (1 / Math.PI) * (1 / (double) z) * radialFunction[1][t] * (Math.sin((double) z * hint) - Math.sin((double) z * lint));
                    b[z] += (1 / Math.PI) * (1 / (double) z) * radialFunction[1][t] * (Math.cos((double) z * lint) - Math.cos((double) z * hint));
                }
            }
        }
        out[0] = a;
        out[1] = b;
        return out;
    }
    
    // Method to get edge points that will be used by compare method.
    public static Polygon toPolygon(double[][] coordinates, double scale) {
        if (coordinates == null) {
            return null;
        }
        int[] xpoints = new int[coordinates[0].length];
        int[] ypoints = new int[coordinates[0].length];
        for (int i = 0; i < coordinates[0].length; i++) {
            xpoints[i] = (int) Math.round(coordinates[0][i] * scale);
            ypoints[i] = (int) Math.round(coordinates[1][i] * scale);
        }
        return new Polygon(xpoints, ypoints, coordinates[0].length);
    }

    
// Method to optimally superimpose coords1 (database image) onto coords2 
// (analysed image. Assumes coordinates already translated.
    public static double[][] superimposeCoordinates(double[][] coords1, double[][] coords2) {
        try {
            coords2 = Edging.optimallyRotate(coords1, coords2);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return coords2;
    }

    // Method to calcualte average which is used in Species.java and by the compare method
    public static double[][] calculateAverageCoordinates(EdgeList edgeList, int nPoints) throws Exception {
        double[][] aveCoords = new double[2][nPoints];
        while (edgeList.previous != null) {
            edgeList = edgeList.previous;
        }
        if (edgeList.next == null) {
            System.arraycopy(edgeList.coordinatesSST[0], 0, aveCoords[0], 0, nPoints);
            System.arraycopy(edgeList.coordinatesSST[1], 0, aveCoords[1], 0, nPoints);
            return aveCoords;
        }
        //set the leaf against which all others will be superimposed
        int cnt = 0;
        while (edgeList.next != null) {
            if (edgeList.coordinatesSST == null) {
                throw new Exception("Coordinates should be sampled, scaled, and translated before being superimposed");
            }
            for (int i = 0; i < nPoints; i++) {
                if (cnt == 0) {
                    aveCoords[0][i] = edgeList.coordinatesSST[0][i];
                    aveCoords[1][i] = edgeList.coordinatesSST[1][i];
                } else {
                    aveCoords[0][i] += edgeList.coordinatesSST[0][i];
                    aveCoords[1][i] += edgeList.coordinatesSST[1][i];
                }
            }
            cnt++;
            edgeList = edgeList.next;
        }
        cnt++;
        if (edgeList.coordinatesSST == null) {
            throw new Exception("Coordinates should be sampled, scaled, and translated before being superimposed");
        }
        for (int i = 0; i < nPoints; i++) {
            aveCoords[0][i] += edgeList.coordinatesSST[0][i];
            aveCoords[1][i] += edgeList.coordinatesSST[1][i];
            aveCoords[0][i] /= cnt;
            aveCoords[1][i] /= cnt;
        }
        return aveCoords;
    }

    // Method that calculates average coordiinates.
    //assumes already superimposed coordinates if that is what is desired.
    public static double[][] calculateAverageCoordinates(double[][][] coordinates) {
        int margnum = coordinates[0][0].length;
        int numObs = coordinates.length;
        double[][] aveCoords = new double[2][margnum];
        for (int i = 0; i < numObs; i++) {
            for (int t = 0; t < margnum; t++) {
                if (i == 0 && t == 0) {
                    aveCoords[0][t] = coordinates[i][0][t];
                    aveCoords[1][t] = coordinates[i][1][t];
                } else {
                    aveCoords[0][t] += coordinates[i][0][t];
                    aveCoords[1][t] += coordinates[i][1][t];
                }
            }
        }
        for (int t = 0; t < margnum; t++) {
            aveCoords[0][t] /= (double) margnum;
            aveCoords[1][t] /= (double) margnum;
        }
        return aveCoords;
    }

    // Convergence is a number [0,1] that tells how close the superimposition is to optimality.
    // Closer to 1 is faster but lower accuracy.
    // For now, simply superimposes over first leaf.
    public static double[][][] superimposeCoordinates(double[][][] coordinates, double convergence) {
        int numObs = coordinates.length;
        int margnum = coordinates[0][0].length;
        double[][] ave1 = new double[2][margnum];
        System.arraycopy(coordinates[0][0], 0, ave1[0], 0, margnum);
        System.arraycopy(coordinates[0][1], 0, ave1[1], 0, margnum);
        for (int i = 0; i < numObs; i++) {
            Edging.superimposeCoordinates(ave1, coordinates[i]);
        }
        return coordinates;
    }

    // Calcualte the procrustes Distance.
    public static double calculateProcrustesDistance(double[][] coords1, double[][] coords2, boolean superimposed) throws Exception {
        double avedist = 0;
        int margnum = coords1[0].length;
        if (coords1[0].length != coords2[0].length) {
            return -1;
        }
        if (!superimposed) {
            coords2 = Edging.superimposeCoordinates(coords1, coords2);
        }
        for (int t = 0; t <= margnum - 1; t++) {
            avedist += (coords2[0][t] - coords1[0][t]) * (coords2[0][t] - coords1[0][t]) + (coords2[1][t] - coords1[1][t]) * (coords2[1][t] - coords1[1][t]);
        }
        avedist = Math.sqrt(avedist) / (double) margnum;
        return avedist;
    }

    // Otimally rotates coords1 onto coords2.
    public static double[][] optimallyRotate(double[][] coords1, double[][] coords2) throws Exception {
        double B;
        double C;
        double lamb1, lamb2;
        double v1, v2, v3, v4;
        double u1, u2, u3, u4;
        double a = 0, b = 0, c = 0, d = 0;
        double[][] A = new double[2][2];
        double[][] Q = new double[2][2];
        int margnum = coords1[0].length;
        if (margnum != coords2[0].length) {
            throw new Exception("Coordinate matrices differed in dimension during rotation");
        }
        for (int t = 0; t <= margnum - 1; t++) {
            a += coords2[0][t] * coords1[0][t];
            b += coords2[0][t] * coords1[1][t];
            c += coords2[1][t] * coords1[0][t];
            d += coords2[1][t] * coords1[1][t];
        }
        // from the characteristic polynomial in this case the quadritic is used
        B = -(a * a + b * b + c * c + d * d);
        C = a * a * d * d + c * c * b * b - 2 * a * b * c * d;

        // calculated the eigenvalues for A^t A
        lamb1 = (-B + Math.sqrt(B * B - 4 * C)) / 2;
        lamb2 = (-B - Math.sqrt(B * B - 4 * C)) / 2;

        // V = [v1 v2] [v3 v4]
        // Start with v1 = v2 = 1; solve for v3, v4; normalize v3, v4; solve for
        // v1, v2 using the normalized values.
         
        v3 = -(a * a + a * b + c * c + c * d - lamb1) / (a * b + c * d + b * b + d * d - lamb1);
        v4 = -(a * a + a * b + c * c + c * d - lamb2) / (a * b + c * d + b * b + d * d - lamb2);

        v3 = v3 / (Math.sqrt(1 + v3 * v3));
        v4 = v4 / (Math.sqrt(1 + v4 * v4));

        v1 = -(a * b + c * d + b * b + d * d - lamb1) * v3 / (a * a + a * b + c * c + c * d - lamb1);
        v2 = -(a * b + c * d + b * b + d * d - lamb2) * v4 / (a * a + a * b + c * c + c * d - lamb2);

        // Solve for U
        u1 = (a * v1 + b * v3) / Math.sqrt(lamb1);
        u2 = (a * v2 + b * v4) / Math.sqrt(lamb2);
        u3 = (c * v1 + d * v3) / Math.sqrt(lamb1);
        u4 = (c * v2 + d * v4) / Math.sqrt(lamb2);

        Q[0][0] = u1 * v1 + u2 * v2;
        Q[0][1] = u1 * v3 + u2 * v4;
        Q[1][0] = u3 * v1 + u4 * v2;
        Q[1][1] = u3 * v3 + u4 * v4;
        double t1 = 0, t2 = 0;
        for (int t = 0; t <= margnum - 1; t++) {
            t1 = coords2[0][t] * Q[0][0] + coords2[1][t] * Q[1][0];
            t2 = coords2[0][t] * Q[0][1] + coords2[1][t] * Q[1][1];
            coords2[0][t] = t1;
            coords2[1][t] = t2;
        }
        return coords2;
    }

    // Calculate the fourier coefficients
    public static double[][] calculateFourierCoefficients(double[][] coords1, int harmnum) {
        int margnum = coords1[0].length;
        double[][] rf1 = new double[2][margnum];
        Edging.makeRadialFunction(coords1, rf1);
        double[][] out = Edging.getHarmonics(rf1, harmnum);
        return out;
    }

    // Requires edges be size-normalized (centroid-scaled) and translated so that centroid is at origin.
    public static double calculateFourierDistance(double[][] coords1, double[][] coords2, int harmnum) {
        int margnum = coords1[0].length;
        double[][] rf1 = new double[2][margnum], rf2 = new double[2][margnum];
        double[] a1 = new double[harmnum], b1 = new double[harmnum], a2 = new double[harmnum], b2 = new double[harmnum];
        Edging.makeRadialFunction(coords1, rf1);
        Edging.makeRadialFunction(coords2, rf2);
        double[][] tmp = Edging.getHarmonics(rf1, harmnum);
        a1 = tmp[0];
        b1 = tmp[1];
        tmp = Edging.getHarmonics(rf2, harmnum);
        a2 = tmp[0];
        b2 = tmp[1];
        return Edging.calculateFourierDistance(a1, b1, a2, b2);
    }

    public static double calculateFourierDistance(double[] a1, double[] b1, double[] a2, double[] b2) {
        double dist = 0.0;
        int harmnum = a1.length;
        for (int z = 0; z <= harmnum - 1; z++) {
            dist += (a1[z] - a2[z]) * (a1[z] - a2[z]) + (b1[z] - b2[z]) * (b1[z] - b2[z]);
        }
        dist = Math.sqrt(dist);
        return dist;
    }

    public static double calculateCombinedDistanceScore(double[][] coords1, double[][] coords2, int harmnum, boolean superimposed) throws Exception {
        return (Edging.calculateFourierDistance(coords1, coords2, harmnum) + 9.4 * Edging.calculateProcrustesDistance(coords1, coords2, superimposed)) / 2;
    }

    public static double calculateCombinedDistanceScore(double[] a1, double[] b1, double[] a2, double[] b2, double[][] coords1, double[][] coords2, int harmnum, boolean superimposed) throws Exception {
        return (Edging.calculateFourierDistance(a1, b1, a2, b2) + 9.4 * Edging.calculateProcrustesDistance(coords1, coords2, superimposed)) / 2;
    }

    public static double calculateAverageFourierDistance(double[] avea, double[] aveb, double[][][] coordinates, int harmnum) {
        double aveFD = 0;
        int numObs = coordinates.length;
        double[] a, b;
        for (int i = 0; i < numObs; i++) {
            a = new double[harmnum];
            b = new double[harmnum];
            double[][] temp = new double[2][harmnum];
            temp = Edging.getHarmonics(coordinates[i], harmnum);
            a = temp[0];
            b = temp[1];
            aveFD += Edging.calculateFourierDistance(avea, aveb, a, b);
        }
        aveFD /= (double) numObs;
        return aveFD;
    }

    public static double calculateAverageProcrustesDistance(double[][] coords1, double[][][] coordinates, boolean superimposed) throws Exception {
        double avePD = 0;
        int numObs = coordinates.length;
        for (int i = 0; i < numObs; i++) {
            avePD += Edging.calculateProcrustesDistance(coords1, coordinates[i], superimposed);
        }
        avePD /= (double) numObs;
        return avePD;
    }

    public static double[][][] convertPolygons(Polygon[] p, int nPoints, boolean[] keep) {
        return Edging.extractCoordinates(p, nPoints, keep);
    }

    // Sort the scaled,sampled and transformed edges into the takon format that the database will use.
    public static Hashtable sortSSTEdgesByTaxon(Hashtable speciesDatabase) {
        Hashtable out = new Hashtable();
        Vector t = new Vector();
        Set keys = speciesDatabase.keySet();
        Iterator iter = keys.iterator();
        Vector output = new Vector();
        while (iter.hasNext()) {
            t = new Vector();
            Species t1 = (Species) speciesDatabase.get(iter.next());
            while (t1.edges.previous != null) {
                t1.edges = t1.edges.previous;
            }
            while (t1.edges.next != null) {
                t.add(t1.edges.coordinatesSST);
                t1.edges = t1.edges.next;
            }
            t.add(t1.edges.coordinatesSST);
            out.put(t1.name, t);
        }
        return out;
    }
    
    // Coordinate information
    public static String coordinateInformation(EdgeList el) {
        String output = new String();
        output += el.taxon + "\t";
        output += el.label + "\t";
        output += el.part_name + "\t";
        output += el.number + "\t";
        output += el.dimensionality + "\t";
        output += el.centered_normalized + "\t";
        output += el.pixelArea + "\t";
        output += el.pixelPerCentimeter + "\t";
        output += el.imageFile + "\t";
        for (int j = 0; j < el.number; j++) {
            for (int i = 0; i < el.dimensionality; i++) {
                output += el.coordinates[i][j] + "\t";
            }
        }
        return output + "\n";
    }

    // Requires translation of the centroid to the origin before calling this function.
    public static void makeRadialFunction(double[][] coords, double radialFunction[][]) {
        int margnum = coords[0].length;
        for (int t = 0; t <= margnum - 1; t++) {
            if (t == 0) {
                radialFunction[0][t] = Math.sqrt((coords[0][margnum - 1] - coords[0][t]) * (coords[0][margnum - 1] - coords[0][t]) + (coords[1][margnum - 1] - coords[1][t]) * (coords[1][margnum - 1] - coords[1][t]));
            }
            if (t > 0) {
                radialFunction[0][t] = radialFunction[0][t - 1] + Math.sqrt((coords[0][t - 1] - coords[0][t]) * (coords[0][t - 1] - coords[0][t]) + (coords[1][t - 1] - coords[1][t]) * (coords[1][t - 1] - coords[1][t]));
            }
            radialFunction[1][t] = Math.sqrt(coords[0][t] * coords[0][t] + coords[1][t] * coords[1][t]);
        }
    }
    
    // Calculate the average harmonics.
    public static double[][] calculateAverageHarmonics(EdgeList harmonics, int nHarm) {
        double[][] aveH = new double[2][nHarm];
        while (harmonics.previous != null) {
            harmonics = harmonics.previous;
        }
        if (harmonics.next == null) {
            return harmonics.coordinates;
        }
        int cnt = 0;
        while (harmonics.next != null) {
            for (int i = 0; i < nHarm; i++) {
                if (cnt == 0) {
                    aveH[0][i] = harmonics.coordinates[0][i];
                    aveH[1][i] = harmonics.coordinates[1][i];
                } else {
                    aveH[0][i] += harmonics.coordinates[0][i];
                    aveH[1][i] += harmonics.coordinates[1][i];
                }
            }
            cnt++;
            harmonics = harmonics.next;
        }
        cnt++;
        for (int i = 0; i < nHarm; i++) {
            aveH[0][i] += harmonics.coordinates[0][i];
            aveH[1][i] += harmonics.coordinates[1][i];
            aveH[0][i] /= cnt;
            aveH[1][i] /= cnt;
        }
        return aveH;
    }
}