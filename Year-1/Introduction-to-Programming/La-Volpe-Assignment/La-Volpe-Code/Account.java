package LaVolpeAssignment;

import javax.swing.*;
import java.awt.*;

public class Account{
    JFrame f = new JFrame("Account");
    JLabel l1 = new JLabel("Account", JLabel.CENTER);
    JButton b1 = new JButton("Order");
    JButton b2 = new JButton("Update Profile");
    JButton b3 = new JButton("Change Password");
    JButton b4 = new JButton("View Orders");
    
      Account(){
      f.setResizable(false);
      f.setSize(300,150);
      f.setVisible(true);
      f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      JPanel p = new JPanel(new GridLayout(5,1));
      p.setBackground(Color.WHITE);
      p.add(l1);
      p.add(b1);
      p.add(b2);
      p.add(b3);
      p.add(b4);
      f.add(p); 
      
      //Add listeners
      b1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent aev) {
                Starters thisStarter = new Starters();
            }
        });
      b2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent aev) {
                Profile thisProfileChange = new Profile();
            }
        });
      b3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent aev) {
                Password thisPassword = new Password();
            }
        });
      b4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent aev) {
              //  ViewOrders thisViewOrders = new ViewOrders();
            }
        });
     
    }
      
}
