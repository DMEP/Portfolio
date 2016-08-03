package leafAnalysis;

/*
 * This file is the Utils.java which handles all the file types that will be
 * used for saving images or data @author Daniel Elstob @version 1.0 23-04-2015
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Utils {

    public final static String jpeg = "jpeg";
    public final static String jpg = "jpg";
    public final static String gif = "gif";
    public final static String png = "png";
    public final static String odb = "odb";
   
    // Get the extension of a file.
    public static String getExtension(File f) {
        String ext = new String();
        String s = f.getName();
        int i = s.lastIndexOf('.');
        if (i > 0 && i < s.length() - 1) {
            ext = s.substring(i + 1).toLowerCase();
        }
        return ext;
    }

    public static int getLineCount(String file) {
        int tot = 0;
        try {
            BufferedReader in = new BufferedReader(new FileReader(file));
            tot = 0;
            String l = new String();
            while (l != null) {
                l = in.readLine();
                tot++;
            }
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tot;
    }

    public String getGif() {
        return gif;
    }
}
