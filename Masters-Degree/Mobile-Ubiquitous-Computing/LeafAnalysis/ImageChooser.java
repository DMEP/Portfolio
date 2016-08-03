package leafAnalysis;

/*
 * This file is the ImageChooser.java which handles the user input when choosing
 * a suitable leaf image. @author Daniel Elstob @version 1.0 23-04-2015
 */
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JPanel;

public class ImageChooser extends JPanel implements ActionListener {
    static private String newline = "\n";
    private MainScreen ms;
    private JFileChooser fc;
    public File file;

    public ImageChooser(MainScreen mS) {
        super(new BorderLayout());
        ms = mS;
        //Set up the file chooser.
        if (fc == null) {
            fc = new JFileChooser();
            //Add the preview pane.
            fc.setAccessory(new ImagePreview(fc));
        }
        //Show it.
        int returnVal = fc.showDialog(ImageChooser.this,
                "Open");
        //Process the results.
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            file = fc.getSelectedFile();
            ms.cif = file;
            ms.openImage(file);
        } else {
            
        }
        //Reset the file chooser for the next time it's shown.
        fc.setSelectedFile(null);
        fc.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
    }
}
