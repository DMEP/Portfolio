package LaVolpeAssignment;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class StaffLogin{
 JFrame f = new JFrame("Staff Login");
 JLabel l1 = new JLabel("Username");
 JLabel l2 = new JLabel("Password");
 JTextField t1 = new JTextField(15);
 JTextField t2 = new JTextField(15);
 JButton b1 = new JButton("Submit");
 String user;
 String pass;
  
   
   public void StaffLogin(){
      f.setResizable(false);
      f.setSize(300,100);
      f.setVisible(true);
      f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      JPanel p = new JPanel(new GridLayout(3,1));
      p.setBackground(Color.WHITE);
      p.add(l1);
      p.add(t1);
      p.add(l2);
      p.add(t2);
      p.add(b1);
      f.add(p); 
      //Add listeners
      b1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent ae) {
	  user = t1.getText();
                pass = t2.getText();
                System.out.println("Submit Pressed...");
                
                if ((t1.getText().equals("SL.txt")) && (t2.getText().equals("SL.txt"))){
                    Account thisAccount = new Account();
                    //dispose();
}                
            }});

  }
      public void readFile() {
        try
        {
            FileInputStream fileIn = new FileInputStream("SL.txt");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            in.close();
            fileIn.close();



        }catch(IOException e)
        {}

    }
      

    public static void main(String[] args){
        StaffLogin thisStaffLogin = new StaffLogin();
  }
}
