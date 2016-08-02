package LaVolpeAssignment;

import javax.swing.*;
import java.awt.*;
import java.io.*;


public class Register{
    JFrame f = new JFrame("Register");
    JLabel l1 = new JLabel("Username");
    JLabel l2 = new JLabel("Password");
    JTextField t1 = new JTextField(15);
    JTextField t2 = new JPasswordField(15);
    JButton b1 = new JButton("Confirm");
    String user;
    String pass;
          

      Register(){
      f.setResizable(false);
      f.setSize(250,100);
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
            public void actionPerformed(java.awt.event.ActionEvent aev) {
                user = t1.getText();
                pass = t2.getText();
                writeFile();
                System.out.println("Saved..");
                CustomerLogin thisCustomerLogin = new CustomerLogin();
            }
        });
      
      }

      
     public void writeFile() {
        try
        {
            FileWriter fw = new FileWriter("CL.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(user+", "+pass);
            bw.close();
            fw.close();




        }catch(IOException e)
        {}

        }
    
    }
