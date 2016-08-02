
package qlearningagent;

import java.awt.*;
import java.awt.event.*;
import java.text.*;
import javax.swing.*;
import javax.swing.text.DefaultCaret;

/*
   This program demonstrates Reinforcement Learning using Q-Learning.
   It is designed to show the agent (blue dot) learn and navigate a maze. 
   
   This file is the Gui.java and contains all Gui settings. If run it will start the program.
   
   @author Daniel Elstob 
   @version 5.0 23-03-2014
 */

public class Gui extends Canvas implements Runnable
{
    // <editor-fold defaultstate="collapsed" desc="Variables">
    private static final long serialVersionUID = 1L;
    Environment environment = new Environment();
    Controller controller = new Controller(environment);
    
    public final int width = 250;
    public final int height = 250;
    public final int scale = 3;
    public String name = "MAZE Solving AI";
    public Dimension frameDimension = new Dimension(width, height);
    
    public static JTextField discountField;
    public static JTextField initialValueField;
    public static JTextField greedyField;
    public static JTextField moveField;
    public static JTextField alphaField;
    public static JLabel stepsLabel;
    public static JLabel rewardsLabel;
    public final int strutSize=15;
    public final int sqSize = 50; // Size of the tiles
    public final int oDA = 3; // Size of the optimal direction arrows
    DecimalFormat decimalFormate = new DecimalFormat("0.##");
    Dimension gridDimension = new Dimension(sqSize*10,sqSize*10);
    public double brightness = 1.0;
    public boolean running = false;
    //public JTextArea textArea;
    GridPanel graphPanel;
    JFrame frame;
    // </editor-fold>
    
    
    // <editor-fold defaultstate="collapsed" desc="Methods">
    
   public Gui()
   {
    	graphPanel = new GridPanel();
	JPanel optionsPanel = new JPanel();
        optionsPanel.setPreferredSize(new Dimension(450, 300));
	optionsPanel.setLayout(new BoxLayout(optionsPanel,BoxLayout.Y_AXIS));
        
        
        // <editor-fold defaultstate="collapsed" desc="tracePanel">
        /*
           The panel for the trace check box which if selected outputs actions
        */
        JPanel tracePanel = new JPanel();
        final JCheckBox tracingCheckBox = new JCheckBox("Trace on console");
	tracingCheckBox.addActionListener(new ActionListener()
	    {
		public void actionPerformed(ActionEvent event)
		{
		    controller.tracing = tracingCheckBox.isSelected();
		    environment.tracing = tracingCheckBox.isSelected();
                }
	    });
        tracePanel.add(tracingCheckBox);
        optionsPanel.add(Box.createVerticalGlue());
        optionsPanel.add(tracePanel);
	// </editor-fold>
        
        // <editor-fold defaultstate="collapsed" desc="qValuePanel">
        /*
           The panel for the qValue check box which if selected shows tile 
           qValues
        */
        JPanel qValuePanel = new JPanel();
        final JCheckBox qValueCheckBox = new JCheckBox("Show the tile qValue");
	qValueCheckBox.addActionListener(new ActionListener()
	    {
		public void actionPerformed(ActionEvent event)
		{
                    controller.qValue = qValueCheckBox.isSelected();
                    environment.qValue = qValueCheckBox.isSelected();
                }
	    });
        qValuePanel.add(qValueCheckBox);
        optionsPanel.add(Box.createVerticalGlue());
        optionsPanel.add(qValuePanel);
	// </editor-fold>
        
        // <editor-fold defaultstate="collapsed" desc="discountPanel">
	JPanel discountPanel = new JPanel();
	discountPanel.add(new JLabel("Discount"));

	JButton decrement = new JButton("-");
	discountPanel.add(decrement);
	decrement.addActionListener(new ActionListener()
	    {
		public void actionPerformed(ActionEvent event)
		{
		    discountField.setText(decimalFormate.format(
                            Double.parseDouble(discountField.getText())-0.1)); 
		    frame.repaint();
		}
	    }
				    );

	discountField = new JTextField(Double.toString(controller.discount),3);
	discountPanel.add(discountField);
        
        
	JButton increment = new JButton("+");
	discountPanel.add(increment);
	increment.addActionListener(new ActionListener()
	    {
		public void actionPerformed(ActionEvent event)
		{
		    discountField.setText(decimalFormate.format(Double.parseDouble
                            (discountField.getText())+0.1)); 
		    frame.repaint();
		}
	    }
				    );

	optionsPanel.add(discountPanel);
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc="greedyPanel">
	JPanel greedyPanel = new JPanel();
	greedyPanel.add(new JLabel("Greedy Exploit"));
	greedyField = new  JTextField("80",2);
	greedyPanel.add(greedyField);
	greedyPanel.add(new JLabel("%"));
	optionsPanel.add(greedyPanel);
        // </editor-fold>
        
        // <editor-fold defaultstate="collapsed" desc="alphaPanel">
	JPanel alphaPanel = new JPanel();
	final JCheckBox alphaCheckBox = new JCheckBox("Fixed alpha =");
	alphaCheckBox.addActionListener(new ActionListener()
	    {
		public void actionPerformed(ActionEvent event)
		{
		    controller.alphaFixed = alphaCheckBox.isSelected();
		}
	    });

	alphaPanel.add(alphaCheckBox);
	alphaField = new JTextField("0.4",3);
	alphaPanel.add(alphaField);
	optionsPanel.add(alphaPanel);
        // </editor-fold>
 
        // <editor-fold defaultstate="collapsed" desc="movePanel">
        JPanel movePanel= new JPanel();
	JButton move = new JButton("Move");
	movePanel.add(move);
	moveField = new JTextField("1",7);
        movePanel.add(moveField);
	move.setFont(new Font("Serif",1,20));
	move.addActionListener(new ActionListener()
	    {
		public void actionPerformed(ActionEvent event)
		{
		    movements(Integer.parseInt(moveField.getText()));
		    frame.repaint();
		}
	    }
				);
	optionsPanel.add(movePanel);
        // </editor-fold>
        
        // <editor-fold defaultstate="collapsed" desc="resetPanel">
	JPanel resetPanel = new JPanel();
        JButton reset = new JButton("Reset");
	reset.setFont(new Font("Serif",1,20));
	reset.addActionListener(new ActionListener()
	    {
		public void actionPerformed(ActionEvent event)
		{
		    reset();
		    frame.repaint();
		}
	    }
				);

	resetPanel.add(reset);
	optionsPanel.add(resetPanel);
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc="initValPanel">
	JPanel initialValuePanel = new JPanel();
	initialValuePanel.add(new JLabel("Initial Value"));
	initialValueField = new JTextField("0.0",3);
	initialValuePanel.add(initialValueField);
	optionsPanel.add(initialValuePanel);
        // </editor-fold>
            
        // <editor-fold defaultstate="collapsed" desc="stepsTakenPanel">
	JPanel reportStepsPanel = new JPanel();
	stepsLabel = new JLabel("Steps Taken: 0");
        reportStepsPanel.add(stepsLabel);
	optionsPanel.add(reportStepsPanel);
        // </editor-fold>
        
        // <editor-fold defaultstate="collapsed" desc="cumulativeRewardsPanel">
	JPanel cumulativeRewardsPanel = new JPanel();
	rewardsLabel = new JLabel("Cumulative Reward: 0");
        cumulativeRewardsPanel.add(rewardsLabel);
	optionsPanel.add(cumulativeRewardsPanel);
        // </editor-fold>
        
        // <editor-fold defaultstate="collapsed" desc="outputConsolePanel">
        JPanel outputConsolePanel = new JPanel();
        controller.textArea = new JTextArea(9,1);
        outputConsolePanel.add(controller.textArea);
        
        DefaultCaret caret = (DefaultCaret)controller.textArea.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        
        
        JScrollPane sp = new JScrollPane(controller.textArea);
        sp.setViewportView(controller.textArea);
        optionsPanel.add(sp, BorderLayout.CENTER);
        // </editor-fold>
                
        // <editor-fold defaultstate="collapsed" desc="frame Settings">
	setMinimumSize(frameDimension);
        setMaximumSize(frameDimension);
        setPreferredSize(frameDimension);
        
        frame = new JFrame(name);
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        
        frame.add(this, BorderLayout.CENTER);
        frame.getContentPane().add(optionsPanel,"West");
        frame.getContentPane().add(graphPanel);
        reset();
	frame.repaint();  
        frame.pack();
        
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        // </editor-fold>        
	  
    }
    public void setTextArea(JTextArea aTextArea)
    {
        controller.textArea = aTextArea;        
    }
    
    private class GridPanel extends JPanel
    {
	public GridPanel()
	{
	    setPreferredSize(gridDimension);
	}

        public void paintComponent(Graphics graphics)
	{     
            for (int xValue = 0; xValue < 10; xValue++) {
		for (int yValue = 0; yValue < 10; yValue++) {
                    double value = controller.value(xValue, yValue);
                    if (value >= 0.0)
                        /* Turn high value tiles green */
			graphics.setColor(new Color(0, Math.min((int)(255.0* 
                                Math.pow(value / 10.0, brightness)), 255) ,0));
                    
		    else
                        /* Turn negative value tiles red */
			graphics.setColor(new Color(Math.min((int)(255.0* 
                                Math.pow(-value / 10.0, brightness)), 255), 0, 0));
		               
                    graphics.fillRect(xValue * sqSize, yValue * sqSize, sqSize, sqSize);      
                    
                    /* Optimal Direction Arrows code */
		    graphics.setColor(Color.yellow);
		    /* Up arrows */
                    if (value == controller.qValues[xValue][yValue][0]){
			int upTriangleX[] = {xValue * sqSize + sqSize / 2 - oDA,
					xValue * sqSize + sqSize / 2 + oDA,
					xValue * sqSize + sqSize / 2};
			int upTriangleY[] = {yValue * sqSize + sqSize / 2,
					yValue * sqSize + sqSize / 2,
					yValue * sqSize};
			graphics.fillPolygon(upTriangleX, upTriangleY, 3);
		    }
                    /* Right arrows */
		    if (value == controller.qValues[xValue][yValue][1]){
			int upTriangleY[] = {yValue *sqSize + sqSize / 2 -oDA,
					yValue *sqSize + sqSize / 2 +oDA,
					yValue *sqSize + sqSize / 2};
			int upTriangleX[] = {xValue *sqSize + sqSize / 2,
					xValue *sqSize + sqSize / 2,
					(xValue + 1) * sqSize};
			graphics.fillPolygon(upTriangleX, upTriangleY, 3);
		    }
                    /* Down arrows */
		    if (value == controller.qValues[xValue][yValue][2]){
			int upTriangleX[] = {xValue * sqSize + sqSize / 2 - oDA,
					xValue * sqSize + sqSize / 2 + oDA,
					xValue * sqSize + sqSize / 2};
			int upTriangleY[] = {yValue * sqSize + sqSize / 2,
					yValue * sqSize + sqSize / 2,
					(yValue + 1) * sqSize};
			graphics.fillPolygon(upTriangleX, upTriangleY, 3);
		    }
                    /* Left arrows */
		    if (value == controller.qValues[xValue][yValue][3]){
			int upTriangleY[] = {yValue * sqSize + sqSize / 2 - oDA,
					yValue * sqSize + sqSize / 2 + oDA,
					yValue * sqSize + sqSize / 2};
			int upTriangleX[] = {xValue * sqSize + sqSize / 2,
					xValue * sqSize + sqSize / 2,
					xValue * sqSize};
			graphics.fillPolygon(upTriangleX, upTriangleY, 3);
		    }
                    /* This if constructor is for showing the qValues on the 
                       tiles */
                    if (controller.qValue) {
                    /* Up value */
		    graphics.setColor(Color.white);
		    graphics.drawString(decimalFormate.format(controller.qValues
                            [xValue][yValue][0]),
                            xValue * sqSize + sqSize / 3, yValue * sqSize 
                            + sqSize / 3);
                    /* Right value */
                    graphics.setColor(Color.white);
		    graphics.drawString(decimalFormate.format(controller.qValues
                            [xValue][yValue][1]),
                            xValue * sqSize + 2 + sqSize / 2, yValue * sqSize 
                            + 2 *sqSize / 3);
                    /* Down value */
                    graphics.setColor(Color.white);
		    graphics.drawString(decimalFormate.format(controller.qValues
                            [xValue][yValue][2]),
                            xValue * sqSize + sqSize / 3,(yValue + 1) * 
                            sqSize - 1);
                    /* Left value */
                    graphics.setColor(Color.white);
		    graphics.drawString(decimalFormate.format(controller.qValues
                            [xValue][yValue][3]),
                            xValue * sqSize + 2, yValue * sqSize + 2 *sqSize 
                            / 3);
                    }
                };
	    };
            /* Left and Up graph border color */
            graphics.setColor(Color.black);
	    graphics.drawLine(0, 0, 0, 10 * sqSize);
	    graphics.drawLine(0, 0, 10 * sqSize, 0);
	    graphics.drawLine(10 * sqSize, 0, 10 * sqSize, 10 * sqSize);
	    graphics.drawLine(0, 10 * sqSize, 10 * sqSize, 10 * sqSize);
            /* Grid, Right and Down border color */
	    graphics.setColor(Color.black);
	    for (int counter = 1 ; counter <= 10; counter++) {
		graphics.drawLine(sqSize * counter, 0, sqSize * counter, 10 * sqSize);
		graphics.drawLine(0, sqSize * counter, 10 * sqSize, sqSize * counter);
	    }
            /* Agent color and position on graph code */
	    graphics.setColor(Color.blue);
            graphics.fillOval(environment.currentX * sqSize + sqSize / 3, 
                    environment.currentY * sqSize+sqSize / 3, sqSize - 2 *
                    (sqSize / 3), sqSize - 2 *(sqSize / 3));
	    stepsLabel.setText("Number of moves: " + environment.numberOfMovements);
	    rewardsLabel.setText("Cumulative Reward: " + 
                    environment.cumulativeReward);
	}
    }
    /* Reset button method */
    public void reset() 
    {     
	environment.reset();
	controller.reset(Double.parseDouble(initialValueField.getText()));
        
    }
    /* Move button method explaing in Environment.java line 24 */
    public void movement(int action)
    {
	controller.movement(action, Double.parseDouble(discountField.getText()),
                Double.parseDouble(alphaField.getText()));
    }
    /* Move button method explaind in Controller.java line 103 */
    private void movements(int count)
    {
	controller.movements(count,Double.parseDouble(greedyField.getText()) / 
                100.0,Double.parseDouble(discountField.getText()),
                Double.parseDouble(alphaField.getText()));
        frame.repaint();
    }
    
    public synchronized void start()
    {
        running = true;
    }
    
    public void stop()
    {
        running = false;
    }
    
    public void run()
    {
        while (running){
        }
    } 
    // </editor-fold>
}
