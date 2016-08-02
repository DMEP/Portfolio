package LaVolpeAssignment;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


//create components 
public class CustomerLogin{
    JFrame f = new JFrame("Customer Login");
    JLabel l1 = new JLabel("Username");
    JLabel l2 = new JLabel("Password");
    JTextField t1 = new JTextField(15);
    JTextField t2 = new JPasswordField(15);
    JButton b1 = new JButton("New User");
    JButton b2 = new JButton("Submit");
    String user;
    String pass;

//add components to frame
      CustomerLogin(){
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
      p.add(b2);
      f.add(p);

      //Add listeners
      b1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent aev) {
                Register Register = new Register();
            }
        });
      b2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent ae) {
user = t1.getText();
                pass = t2.getText();
                System.out.println("Submit Pressed...");
                
                if ((t1.getText().equals("CL.txt")) && (t2.getText().equals("CL.txt"))){
                    Account thisAccount = new Account();
                    thisAccount.setVisible(true);
                    //dispose();
            }});
    
  }
      
      
      public void readFile() {
        try
        {
            FileInputStream fileIn = new FileInputStream("CL.txt");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            in.close();
            fileIn.close();



        }catch(IOException e)
        {}

    }


        public static void main(String[] args){
        CustomerLogin thisCustomerLogin = new CustomerLogin();
      }
}
