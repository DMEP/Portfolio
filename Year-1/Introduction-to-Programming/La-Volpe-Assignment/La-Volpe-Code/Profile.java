package LaVolpeAssignment;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.*;

public class Profile {
        
    JFrame f = new JFrame("Change Profile Details");
    JLabel l = new JLabel("Full Name");
    JLabel l1 = new JLabel("Address");
    JLabel l2 = new JLabel("City");
    JLabel l3 = new JLabel("Postcode");
    JTextField name = new JTextField(10);
    JTextField add = new JTextField(10);
    JTextField city = new JTextField(10);
    JTextField post = new JTextField(10);
    JButton b1 = new JButton("Confirm");
    String FullName;
    String Address;
    String City1;
    String Postcode;
    
    Profile()
    {
       f.setResizable(false);
       f.setSize(300,150);
       f.setVisible(true);
       f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       JPanel p = new JPanel(new GridLayout(5,1));
       p.setBackground(Color.WHITE);
       p.add(l); 
       p.add(name);
       p.add(l1);
       p.add(add);
       p.add(l2);
       p.add(city);
       p.add(l3);
       p.add(post);
       p.add(b1);
       
       f.add(p);
       
       //Add listeners
       b1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent aev) {
                FullName = name.getText();
                Address = add.getText();
                City1 = city.getText();
                Postcode = post.getText();
                writeFile();
                System.out.println("Saved..");
            }
        });

      }


     public void writeFile() {
        try
        {
            FileWriter fw = new FileWriter("Profile.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(FullName+", "+Address+", "+City1+", "+Postcode);
            bw.close();
            fw.close();




        }catch(IOException e)
        {}
       }
         
           
}
