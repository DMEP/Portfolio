package naughtsandcrosses;
/*
 * This file is the Game.java and contains all gui settings. 
 * @author Daniel Elstob 
 * @version 1.0 23-03-2014
*/
//import java library
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Game extends JFrame implements ActionListener {
    // <editor-fold defaultstate="collapsed" desc="Variables">
    public static BoardLogic boardLogic = new BoardLogic();
    public static Game gameMenu = new Game();
    //current scores accumulator
    private int scoreX = 0, scoreY = 0;
    //array of buttons
    private JButton [][] buttons = new JButton[3][3];
    //GUI components
    private JButton restart, mainmenu;
    private JTextPane curScore;
    // </editor-fold
    
    // <editor-fold defaultstate="collapsed" desc="Methods">
    /* Class constructor - creation of the game board Layout */
    public Game() {
        //set up the grid layout
        setLayout(new GridLayout(4, 3));
        //set up the buttons and action events
        initButtons();
        //return to main menu (NaughtsandCrosses.java)
        mainmenu = new JButton("<< Back to main menu");
        mainmenu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gameMenu.dispose();
                NaughtsandCrosses.menu.showMenu();
            }
        });
        add(mainmenu);
        //show the current player and score
        curScore = new JTextPane();
        curScore.setText("X's turn\n\nScore\n============\nX:\t " + scoreX + "\nO:\t " + scoreY);
        curScore.setEditable(false);
        add(curScore);
        //restart the game
        restart = new JButton("Restart Game");
        restart.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) {
                //restart of the same type of game and reset all the buttons
                boardLogic = new BoardLogic();
                resetButtons();
            }
        });
        add(restart);
    }
    /* showGMenu() - creation of the JFrame */
    public void showGMenu() {
        //set frame attributes
        gameMenu.setTitle("Naughts and Crosses");
        gameMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameMenu.setSize(500, 600);
        gameMenu.setResizable(false);
        gameMenu.setLocationRelativeTo(null);
        gameMenu.setVisible(true);
    }
    /* Override actionPerformed() - Assigns all methods and events that must be completed 
     * upon clicking a button on the playing grid. 
    */
    public void actionPerformed(ActionEvent e) {
        //loop through the buttons to determine which was clicked
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                //buttons[i][j] was clicked
                if(e.getSource()==buttons[i][j]) {
                    //play the user move
                    boardLogic.makeMove(i, j, boardLogic.XAI);
                    //update the GUI to display the user's symbol (always an X)
                    buttons[i][j].setText("X");
                    //update the label showing the current player
                    String label = curScore.getText();
                    label = String.valueOf("X's turn " + label.substring(8));
                    curScore.setText(String.valueOf(label));
                    //disable the button, not allowing the user to play this cell again
                    buttons[i][j].setEnabled(false);
                    //check for a winner (at this point, would be the user/X)
                    boolean finishedGame = determineWinner();
                    //no winner means the computer can make its move
                    if(!finishedGame) {
                        //determine where to play
                        spot compMove = boardLogic.getCompMove(boardLogic.OAI);
                        //play the determined move
                        boardLogic.makeMove(compMove.x,compMove.y, boardLogic.OAI);
                        //show the move in the GUI
                        showAIMove(compMove.x, compMove.y);
                        //check again for winner
                        if(boardLogic.winner() != boardLogic.EMPTYAI) {
                            determineWinner();
                        }
                    }
                }
            }
        }
    }
    /* determineWinner() - Determine if there is a winner, and who that player is
     * return boolean true if there has been a winner or a tie otherwise false
    */
    public boolean determineWinner() {
        String label;
        //user winner
        if(boardLogic.winner() == BoardLogic.XAI) {
            //update scoreboard
            scoreX++;
            label = curScore.getText();
            //set the winner on the screen
            label = String.valueOf("You won this round! " + label.substring(8));
            curScore.setText(String.valueOf(label));
            //alert the winner
            boardLogic.printWinner();
            //remove the current player and just show the score since the game is over
            curScore.setText("Score\n============\nX:\t " + scoreX + "\nO:\t " + scoreY);
            //disable all the buttons to indicated game over
            disableAllButtons();
            return true;
        }
        //computer winner
        else if(boardLogic.winner() == BoardLogic.OAI) {
            //update scoreboard
            scoreY++;
            label = curScore.getText();
            //set the winner on the screen
            label = String.valueOf("The computer won this round! " + label.substring(8));
            curScore.setText(String.valueOf(label));
            //alert the winner
            boardLogic.printWinner();
            //remove the current player and just show the score since the game is over
            curScore.setText("Score\n============\nX:\t " + scoreX + "\nO:\t " + scoreY);
            //disable all the buttons to indicated game over
            disableAllButtons();
            return true;
        }
        //tie
        else if(boardLogic.getNumMoves() == 9) {
            label = curScore.getText();
            //set the winner on the screen
            label = String.valueOf("This round was a tie! " + label.substring(8));
            curScore.setText(String.valueOf(label));
            //alert the winner
            boardLogic.printWinner();
            //remove the current player and just show the score since the game is over
            curScore.setText("Score\n============\nX:\t " + scoreX + "\nO:\t " + scoreY);
            //disable all the buttons to indicated game over
            disableAllButtons();
            return true;
        }
        return false;
    }
    /* initButtons() -  Assigns all methods and events that must be completed 
     * upon clicking a button on the playing grid.
    */
    public void initButtons() {
        //loop through 3x3 array
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                //create button, add to GUI, add action listener
                buttons[i][j] = new JButton("");
                add(buttons[i][j]);
                buttons[i][j].addActionListener(this);
            }
        }
    }
    /* resetButtons() - Reset all buttons to default state by:
     *      - setting the text to empty
     *      - enabling clicking
     *      - defaulting the scoreboard to be X's turn
    */
    public void resetButtons() {
        //reset text
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
            }
        }
        //make buttons clickable again
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                buttons[i][j].setEnabled(true);
            }
        }
        //reset scoreboard text
        curScore.setText("X's turn\n\nScore\n============\nX:\t " + scoreX + "\nO:\t " + scoreY);
    }
    /* disableAllButtons() - Disable all buttons on the 3x3 game grid
     * Used when game has been won
    */
    public void disableAllButtons() {
        //disable all buttons
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                buttons[i][j].setEnabled(false);
            }
        }
    }
    /* showAIMove() - Disable all buttons on the 3x3 game grid
     * Used when game has been won
    */
    public void showAIMove(int x, int y) {
        //set the computer's move in the GUI
        buttons[x][y].setText("O");
        //disable the button
        buttons[x][y].setEnabled(false);
    }
    // </editor-fold>
}

