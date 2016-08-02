package LaVolpeAssignment;

import javax.swing.*;
import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class Password{
    JFrame f = new JFrame("Change Password");
    JLabel l1 = new JLabel("Current Password");
    JLabel l2 = new JLabel("New Password");
    JLabel l3 = new JLabel("Confirm Password");
    JTextField t1 = new JPasswordField(15);
    JTextField t2 = new JPasswordField(15);
    JTextField t3 = new JPasswordField(15);
    JButton b1 = new JButton("Update");
    String pass1;
    String pass2;
    String pass3;
    
      Password(){
      f.setResizable(false);
      f.setSize(300,150);
      f.setVisible(true);
      f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      JPanel p = new JPanel(new GridLayout(4,1));
      p.setBackground(Color.WHITE);
      p.add(l1);
      p.add(t1);
      p.add(l2);
      p.add(t2);
      p.add(l3);
      p.add(t3);
      p.add(b1);
      f.add(p); 
      
      //Add listeners
      b1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent aev) {
                pass1 = t1.getText();
                pass2 = t2.getText();
                pass3 = t3.getText();
                System.out.println("Update Pressed...");
                
writeFile();
                System.out.println("Saved..");
                Account thisAccount = new Account();
            }
        });
      
      }

      
     public void writeFile() {
        try
        {
            FileWriter fw = new FileWriter("CL.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(pass1+", "+pass2+", "+pass3);
            bw.close();
            fw.close();



        }catch(IOException e)
        {}

    }
 }
