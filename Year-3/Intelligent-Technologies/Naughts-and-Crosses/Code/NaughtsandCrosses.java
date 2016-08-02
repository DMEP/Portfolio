package naughtsandcrosses;
/*
 * This file is the NaughtsandCrosses.java and contains all gui settings. 
 * If run it will start the program.
 * @author Daniel Elstob 
 * @version 1.0 23-03-2014
*/
//import java library
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class NaughtsandCrosses extends JFrame {
    // <editor-fold defaultstate="collapsed" desc="Variables">
    public static NaughtsandCrosses menu = new NaughtsandCrosses();
    public static Game gameMenu = new Game();
    //GUI components
    private JLabel title;
    private JButton start, instructions;
    private JPanel menuPanel;
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Methods">
    /* Class constructor - creation of the GUI Layout */
    public NaughtsandCrosses() {
        //set up the border layout
        setLayout(new BorderLayout(3, 1));
        //set up the main panel 
        menuPanel = new JPanel();
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));
        //create the title panel
        JPanel titlePanel = new JPanel();
        title = new JLabel();
        title.setText("Naughts and Crosses");
        titlePanel.add(title);
        menuPanel.add(titlePanel);
        //create the instruction panel
        JPanel instructionsPanel = new JPanel();
        //set up the buttons for starting the game
        instructions = new JButton("Instructions");
        //add action listener for button
        instructions.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Instructions.instructions.showIMenu();
                menu.dispose();
            }
        });
        instructionsPanel.add(instructions);
        menuPanel.add(instructionsPanel);
        //create the start panel
        JPanel startPanel = new JPanel();
        start = new JButton("Start Game");
        //add action listener for button
        start.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //set up new game
                Game.gameMenu.showGMenu();
                menu.dispose();
            }
        });
        startPanel.add(start);
        menuPanel.add(startPanel);
    }
    /* showMenu() - creation of the JFrame */
    public void showMenu() {
        //set frame attributes
        menu.setTitle("Naughts and Crosses");
        menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menu.setLayout(new BorderLayout());
        menu.add(menuPanel, BorderLayout.CENTER);
        menu.setSize(300, 300);
        menu.setResizable(false);
        menu.setLocationRelativeTo(null);
        menu.setVisible(true);
    }
    /* main() Initialize the tttRunner menu */
    public static void main(String [] args) {
        menu.showMenu();
    }
    // </editor-fold>
}

