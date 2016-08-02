
package qlearningagent;

import javax.swing.JTextArea;

/*
   This program demonstrates Reinforcement Learning using Q-Learning.
   It is designed to show the agent (blue dot) learn and navigate a maze. 
   
   This file is the Controller.java and contains the methods that control 
   movements and outputs of the program.
   
   @author Daniel Elstob 
   @version 5.0 23-03-2014
 */

public class Controller
{
    // <editor-fold defaultstate="collapsed" desc="Variables">
    Environment environment; // The environment in which the controller operates
    /* The Q values: Q[xPos,yPos,action] */
    public double qValues[][][] = new double[10][10][4];
    /* The number of times the agent has been at (xPos,yPos) and done action */
    public int visits[][][] = new int[10][10][4];
    /* The discount factor(gamma) determines the importance of future rewards.
       A factor of 0 (short sighted) makes the agent consider current rewards, 
       factor of 1 (long sighted) makes it strive for long-term high rewards. 
       If discount meets or exceeds 1 the action values may diverge. */
    public double discount = 0.9;
    /* The learning rate(alpha) determines to what extent the newly aquired 
      information will overide the old information. A factor of 0 will make the 
      agent not learn anything. A factor of 1 will make the agent consider only 
      the most recent information. */
    double alpha;
    /* alphaFixed fixes the learning rate to a specific value */    
    public boolean alphaFixed = false;
    /* Tacing is a feature implemented to outupt the actions the agent takes */
    public boolean tracing = false;  
    /* QValue is a feature implemented to show the qValues of the tiles */
    public boolean qValue = false;
    
    public JTextArea textArea;
    
    // </editor-fold>
    
    
    // <editor-fold defaultstate="collapsed" desc="Methods">
    Controller(Environment env)
    {
	environment = env;
    }
    
    /* reset method resets the Q-values, sets all of the Q-values to initVal, 
       and all of the visit counts to 0. initVal is the initial value to set 
       all values to. 
    */
    public void reset(double initialValue)
    {      
	for (int xValue = 0 ; xValue < 10; xValue++) {
	    for (int yValue = 0; yValue < 10; yValue++) {
		for (int i = 0; i < 4; i++) {
		    qValues[xValue][yValue][i] = initialValue;
		    visits[xValue][yValue][i] = 0;
                    textArea.setText(" ");
                }
            }
	}
    }
    /* movement method carries out the action, and sets the discount 
       and the alpha value. newdiscount is the discount to use, action is the 
       action that the agent does and alphaFieldValue is the alpha value to use.
     */
    public void movement(int action, double newDiscount,double alphaFieldValue)
    { 
	discount = newDiscount;
	alpha = alphaFieldValue;
	movement(action);
    }
    /* movement method carries out the action which the agent does. */
    public void movement(int action) 
    { 
        int oldX = environment.currentX; // oldX is the old tile X co-ordinate 
        int oldY = environment.currentY; // oldY is the old tile Y co-ordinate 
	double reward = environment.movement(action); // reward is the value of the movement
        int newX = environment.currentX; // newX is the new tile X co-ordinates
	int newY = environment.currentY; // newY is the new tile Y co-ordinate
        double newValue = value(newX, newY); // update Q values of the tile
	double newDatum = reward + discount * newValue; // Datum(a piece of information)
	
        visits[oldX][oldY][action]++;
	if (!alphaFixed)
	    alpha = 1.0 / visits[oldX][oldY][action];
		
	/* If tracing option has been selected then output the old co-ordinates,
           the action, the reward, the new co-ordinates and the datum. Also
           print out the old Q value, the visits to the tile and the new Q value
        */
        if (tracing) {
	    textArea.append("(" + oldX + "," + oldY + ") A="+ action + " R=" + reward + " "
                    + "(" + newX + "," + newY + ") newDatum=" + newDatum);
	    textArea.append("     Qold=" + qValues[oldX][oldY][action] + " Visits="
                    + visits[oldX][oldY][action]);
	}
        qValues[oldX][oldY][action] = (1 - alpha) * qValues[oldX][oldY][action] + alpha * newDatum;
	if (tracing) {
            textArea.append(" Qnew=" + qValues[oldX][oldY][action] + "\n");
        }
        
        if (qValue) {
            qValue = true;
        }
    }
    /* value method determines the value of a location and the value is the 
       maximum for all actions of the q-value. xVal is the x-coordinate value
       and yVal the y-coordinate value. */
    public double value(int xValue, int yValue)
    {
	double value = qValues[xValue][yValue][3];
	for (int i = 2; i >= 0; i--) {
	    if(qValues[xValue][yValue][i] > value) {
		value = qValues[xValue][yValue][i];
	    }
	}
	return value;
    }
    /* movements method counts the number of steps the agent takes and whether the
       step is random or greedy(calculated by greedyProb(the probability that
       the step is greedy)). newdiscount is the discount to use, action is the 
       action that the agent does and alphaFieldValue is the alpha value to use.
    */
    public void movements(int count, double greedyProbability, double newDiscount,
            double alphaFieldValue)
    {
	discount = newDiscount;
	alpha = alphaFieldValue;
	for(int i = 0; i < count; i++)
	    {
		double random = Math.random();
		if (random < greedyProbability) // act greedily
		    {
			int startDirection = (int) (Math.random() * 4);
			double bestValue = qValues[environment.currentX]
                                [environment.currentY][startDirection];
			int bestDirection = startDirection;
			for (int direction = 1; direction < 4; direction++)
			    {
				startDirection = (startDirection + 1) % 4;
				if (qValues[environment.currentX]
                                        [environment.currentY][startDirection] 
                                        > bestValue)
				    {
					bestValue = qValues[environment.currentX]
                                                [environment.currentY]
                                                [startDirection];
					bestDirection = startDirection;
				    }
			    }
			movement(bestDirection);
		    }
                else // act randomly
		    { 
			movement((int) (Math.random() * 4)); 
		    }
	    }
    }
    // </editor-fold>
}