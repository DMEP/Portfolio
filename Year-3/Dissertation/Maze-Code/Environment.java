
package qlearningagent;

/*
   This program demonstrates Reinforcement Learning using Q-Learning.
   It is designed to show the agent (blue dot) learn and navigate a maze. 
   
   This file is the Environment.java and contains the setting for what happens 
   to the agent in the envronment.
   
   @author Daniel Elstob 
   @version 5.0 23-03-2014
 */

public class Environment
{
    // <editor-fold defaultstate="collapsed" desc="Variables">
    public int numberOfMovements = 0; // Set movements taken to 0
    public double cumulativeReward = 0.0; // Set the agents cumulative reward to 0.0 
    public int currentX = (int) (Math.random() * 10); // Set the current X position
    public int currentY = (int) (Math.random() * 10); // Set the current Y position 
    public boolean tracing = false; // Set the tacing option to off
    public boolean qValue = false;
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Methods">
    /* Resets the steps and the reward */
    public void reset()
    {     
	numberOfMovements = 0;
	cumulativeReward = 0.0;
    }
    /* movement method carries out the action, and sets the discount 
       and the alpha value. newdiscount is the discount to use, action is the 
       action that the agent does and alphaFieldValue is the alpha value to use
    */
    public double movement(int action) 
    { 
	int actualDirection;
	double reward;
        int newX;
        int newY;
	int random = (int) (Math.random() * 10); 
	if (random < 4) {
            actualDirection = random;
        }
        else {
            actualDirection = action;
        }
	reward = 0.0;
	if ((currentX == 8 && currentY == 7) || (currentX == 7 && 
                currentY == 2)) {
            if (currentX== 8) {
		reward = 10.0; // Give the agent +10 reward
            }
            else {
                reward = 3.0; // Give the agent +3 reward
            }
	    /* Agent's position is set to a random corner */
            newX = ((int) (Math.random() * 2)) * 9;
	    newY = ((int) (Math.random() * 2)) * 9;
	}
	else 
	    {
		if (currentX == 3 && currentY == 4)
		    reward = -5.0; // Give the agent -5 reward
		else if (currentX == 3 && currentY == 7) 
		   reward = -10.0; // Give the agent -10 reward
                /* Create new position and get hitting-wall rewards */
		switch (actualDirection) {
		case 0: /* Up */
		    if (currentY == 0) {
			newY = currentY;
			newX = currentX;
			reward = -1.0;}
		    else {
			newY = currentY -1;
			newX = currentX;}
		    break;
		case 1: /* Right */
		    if (currentX == 9) {
			newY = currentY;
			newX = currentX;
			reward = -1.0;}
		    else {
			newY = currentY;
			newX = currentX + 1;}
		    break;
		case 2: /* Down */
		    if (currentY == 9) {
			newY = currentY;
			newX = currentX;
			reward = -1.0;}
		    else {
			newY = currentY + 1;
			newX = currentX;}
		    break;
		case 3: /* Left */
		    if (currentX == 0) {
			newY = currentY;
			newX = currentX;
			reward = -1.0;}
		    else {
			newY = currentY;
			newX = currentX -1;}
		    break;
		default:   
		    {
			newX = 0;
			newY = 0;
			reward = 0.0;
		    }
		}
	    }
        numberOfMovements++;
	cumulativeReward += reward;
        currentX = newX;
	currentY = newY;
	return reward;
    }
    //</editor-fold>
}