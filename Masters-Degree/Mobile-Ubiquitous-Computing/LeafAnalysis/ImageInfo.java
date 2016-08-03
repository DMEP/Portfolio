package leafAnalysis;

/*
 * This file is the ImageInfo.java which handles image information such as 
 * thresholding. @author Daniel Elstob @version 1.0 23-04-2015
 */
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class ImageInfo {
    int i;

    // Create new form ImageInfo
    public ImageInfo()
            throws Exception {
    }

    public void init()
            throws Exception {
    }

    // Get the image width for rendering.
    public static int imageWidth(String file) {
        File f = new File(file);
        try {
            BufferedImage bi = ImageIO.read(f);
            return bi.getWidth();
        } catch (Exception e) {
        }
        return -1;
    }

    //Get the image width for edge detection.
    public static int imageWidth(BufferedImage image) {
        try {
            return image.getWidth();
        } catch (Exception e) {
        }
        return -1;
    }

    // Get the image height for rendering.
    public static int imageHeight(String file) {
        try {
            File f = new File(file);
            BufferedImage bi = ImageIO.read(f);
            return bi.getHeight();
        } catch (Exception e) {
        }
        return -1;
    }

    // Get the image height for edge detection.
    public static int imageHeight(BufferedImage image) {
        try {
            return image.getHeight();
        } catch (Exception e) {
        }
        return -1;
    }

    public static int[][][] readImage(BufferedImage bi, int x, int y, int h, int w) {
        int[][][] Pix = new int[3][w][h];
        try {
            for (int i = 0; i < w; i++) {
                for (int j = 0; j < h; j++) {
                    int rgba = bi.getRGB(x + i, y + j);
                    Pix[0][i][j] = (rgba >> 16) & 0xff;
                    Pix[1][i][j] = (rgba >> 8) & 0xff;
                    Pix[2][i][j] = (rgba) & 0xff;
                }
            }
            return Pix;
        } catch (Exception e) {
        }
        return null;
    }

    public static int[][] grayScale(int pix[][][]) {
        return grayScale(pix, .3, .59, .11);
    }

    public static int[][] grayScale(int pix[][][], double r, double g, double b) {
        double tot = r + g + b;
        r /= tot;
        g /= tot;
        b /= tot;
        int pix1[][] = new int[pix[0].length][pix[0][0].length];
        for (int i = 0; i < pix[0].length; i++) {
            for (int j = 0; j < pix[0][0].length; j++) {
                pix1[i][j] = (int) ((double) pix[0][i][j] * r + (double) pix[1][i][j] * g + (double) pix[2][i][j] * b);
            }
        }
        return pix1;
    }
}
