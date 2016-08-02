package AssignmentFrame;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.*;
import javax.swing.JButton;
import javax.swing.JFrame;

public class MingFortuneCookie extends JFrame implements ActionListener, ItemListener {
    MingEvent thisMingEvent = new MingEvent();
    // set up row 1
    JPanel row1 = new JPanel();
    JLabel NameLabel = new JLabel("Name: ", JLabel.LEFT);
    JTextField NameField = new JTextField("<Enter Name Here>", 21);
    // set up row 2
    JPanel row2 = new JPanel();
    JLabel DOBLabel = new JLabel("Enter Date Of Birth: ", JLabel.LEFT);
    JComboBox day = new JComboBox();
    JComboBox month = new JComboBox();
    JComboBox year = new JComboBox();
    // set up row 3
    JPanel row3 = new JPanel();
    JButton reset = new JButton("Reset");
    JButton enter = new JButton("Enter");
    // set up row 4
    JPanel row4 = new JPanel();
    JLabel answerLabel = new JLabel("Your Answers Are: ", JLabel.LEFT);
    JTextArea answer = new JTextArea ();


    //todays date
    public void dateVal(){
        Date todaysDate = new Date();
        SimpleDateFormat dayFormat = new SimpleDateFormat("dd");
        SimpleDateFormat monthFormat = new SimpleDateFormat ("MM");
        SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
        String todayDay = dayFormat.format(todaysDate);
        String todayMonth = monthFormat.format(todaysDate);
        String todayYear = yearFormat.format (todaysDate);
        String user = NameField.getText();
        
        System.out.println(todayDay);
        System.out.println(day.getSelectedItem().toString());
        System.out.println(todayMonth);
        System.out.println(month.getSelectedItem().toString());
        System.out.println(todayYear);
        System.out.println(year.getSelectedItem().toString());

        todayYear = todayYear.substring(2, 4);

        String dayString ="";

        if (day.getSelectedItem().toString().length() < 2){
            dayString = "0" + day.getSelectedItem().toString();
        }
        else{
            dayString = day.getSelectedItem().toString();
        }
        String letters = "abcdefghijklmnopqrstuvwxyz";
        String myChar = NameField.getText().substring(0, 1);



        if (todayDay.equals(dayString) && letters.toLowerCase().contains(myChar.toLowerCase())){
            if (todayMonth.equals(month.getSelectedItem().toString()))
            {
                JOptionPane.showMessageDialog(this, "Happy Birthday " + user 
                        + ". \nHere is a 15% discount code that can be used in our restaurant: "
                        + todayYear + todayMonth + todayDay);
            }
        }
        System.out.println("Today : " + todaysDate);
    }
    
    //enter button ActionPerformed
    private void enterActionPerformed(ActionEvent evt) {
        String user = NameField.getText();

        int chosenFoodNum  = day.getSelectedIndex();
        String chosenFood = thisMingEvent.food.get(chosenFoodNum).toString();
        int chosenCarNum  = month.getSelectedIndex();
        String chosenCar = thisMingEvent.car.get(chosenCarNum).toString();

        answer.setText( user + " \nYour lucky food is : " + chosenFood
                  + " \nYour lucky car is :" + chosenCar);
        
        String letters = "abcdefghijklmnopqrstuvwxyz";
        String myChar = NameField.getText().substring(0, 1);
        System.out.println(letters.toLowerCase());
        System.out.println(myChar.toLowerCase());

        if (letters.toLowerCase().contains(myChar.toLowerCase())){
            System.out.println("First letter is a character");
        }
        else{
            System.out.println("Not character");
            answer.setText("");
            JOptionPane.showMessageDialog(this, "First letter of Name must be a character");
        }
        
            dateVal();
          
    }

    //reset button ActionPerformed
    private void resetActionPerformed(ActionEvent evt){
            NameField.setText("<Enter Name Here>");

            day.setSelectedItem("1");
            month.setSelectedItem("January");
            year.setSelectedItem("1900");
            
            answer.setText("");
        }
        
    // Frame Appearance
    public MingFortuneCookie() {
        super("Ming Fortune Cookie");
        MingFortuneCookie.setDefaultLookAndFeelDecorated(true);
        JDialog.setDefaultLookAndFeelDecorated(true);
        setSize(400, 350);
        setDefaultCloseOperation(MingFortuneCookie.EXIT_ON_CLOSE);
        GridLayout layout = new GridLayout(4, 1, 0, 0);
        setLayout(layout);

        //Add listeners
        enter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enterActionPerformed(evt);
            }
        });

        reset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetActionPerformed(evt);
            }
        });

        // Layouts
        //row 1
        FlowLayout layout1 = new FlowLayout(FlowLayout.CENTER, 1, 10);
        row1.setLayout(layout1);
        row1.add(NameLabel);
        row1.add(NameField);
        add(row1);
        //row 2      
        FlowLayout layout2 = new FlowLayout(FlowLayout.CENTER, 1, 1);
        row2.setLayout(layout2);
        row2.add(DOBLabel);
        day = new JComboBox(thisMingEvent.day31);
        row2.add(day);
        month = new JComboBox(thisMingEvent.month);
        row2.add(month);
        year = new JComboBox(thisMingEvent.year);
        row2.add(year);
        add(row2);
        // row 3
        FlowLayout layout3 = new FlowLayout(FlowLayout.CENTER, 1, 1);
        row3.setLayout(layout3);
        row3.add(reset);
        row3.add(enter);
        add(row3);
        //row 4
        FlowLayout layout4 = new FlowLayout(FlowLayout.CENTER, 1, 1);
        row4.setLayout(layout4);
        row4.add(answerLabel);
        answer.setEditable(false);
        answer.setColumns(30);
        answer.setRows(3);
        row4.add(answer);
        add(row4);
        
        setVisible(true);
    }

    // Appearance continued
    public void setDefaultLookAndFeelDecorated(){
        try{
         UIManager.setLookAndFeel(
                 "com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel"
         );
     } catch (Exception exc){
         //ignore error
     }
    }
       
    public static void main(String[] args){
        MingFortuneCookie thisMingFortuneCookie = new MingFortuneCookie();
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
