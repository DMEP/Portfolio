package leafAnalysis;

/*
 * This file is the Display.java and will be used by MainScreen.java to diplay
 * messages during senarios such as the database creation. @author Daniel Elstob
 * @version 1.0 23-04-2015
 */
public class Display extends javax.swing.JInternalFrame {
    // <editor-fold defaultstate="collapsed" desc="Display">
    //Creates new form Display
    public Display(String title, String filler) {
        super(title,
                false, //resizable
                true, //closable
                false, //maximizable
                true);//iconifiablesuper(title);
        initComponents();
        txtFocal.setText(filler);
        txtFocal.setEditable(false);
    }
    // </editor-fold>

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        focalTextScrollPane = new javax.swing.JScrollPane();
        txtFocal = new javax.swing.JTextArea();

        setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txtFocal.setColumns(20);
        txtFocal.setRows(5);
        focalTextScrollPane.setViewportView(txtFocal);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(focalTextScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 529, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(focalTextScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane focalTextScrollPane;
    private javax.swing.JTextArea txtFocal;
    // End of variables declaration//GEN-END:variables
}
