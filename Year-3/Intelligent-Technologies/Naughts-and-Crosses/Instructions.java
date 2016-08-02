package naughtsandcrosses;
/*
 * This file is the Instructions.java and contains all gui settings. 
 * @author Daniel Elstob 
 * @version 1.0 23-03-2014
*/
//import java library
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Instructions extends JFrame {
    // <editor-fold defaultstate="collapsed" desc="Variables">
    public static Instructions instructions = new Instructions();
    //GUI components
    private JTextArea instructionsText;
    private JButton mainMenu;
    private JPanel menuPanel;
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Methods">
    /* Class constructor - creation of the GUI Layout */
    public Instructions() {
        //set up the border layout
        setLayout(new BorderLayout(3, 1));
        //set up the main panel 
        menuPanel = new JPanel();
        //create the text panel
        JPanel textPanel = new JPanel();
        instructionsText = new JTextArea();
        instructionsText.setRows(6);
        instructionsText.setSize(50, 150);
        //show the instructions about the game
        instructionsText.setText("This is a game of Naughts and Crosses\n"
                + "The aim of this game is to have 3 in a row, column "
                + "or diagonally\nAfter playing click 'Restart Game' "
                + "button to start a new game");
        instructionsText.setEditable(false);
        textPanel.add(instructionsText);
        menuPanel.add(textPanel);        
        //set up the buttons and action events
        JPanel backPanel = new JPanel();
        //return to main menu (NaughtsandCrosses.java)
        mainMenu = new JButton("<< Back to main menu");
        //add action listener for button
        mainMenu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                instructions.dispose();
                NaughtsandCrosses.menu.showMenu();
            }
        });
        backPanel.add(mainMenu);
        menuPanel.add(backPanel);
    }
    /* showIMenu() - creation of the JFrame */
    public void showIMenu() {
        //set frame attributes
        instructions.setTitle("Naughts and Crosses");
        instructions.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        instructions.setLayout(new BorderLayout());
        instructions.add(menuPanel, BorderLayout.CENTER);
        instructions.setSize(400, 200);
        instructions.setResizable(false);
        instructions.setLocationRelativeTo(null);
        instructions.setVisible(true);
    }
    // </editor-fold>
}

