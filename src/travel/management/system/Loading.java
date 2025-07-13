package travel.management.system;

import javax.swing.*;
import java.awt.*;

public class Loading extends JFrame implements Runnable {
    Thread t;
    JProgressBar bar;
    String username;

    public void run() {
        try {
            for (int i = 1; i <= 101; i++) {
                int max = bar.getMaximum();
                int value = bar.getValue();

                if (value < max) {
                    bar.setValue(bar.getValue() + 1);
                } else {
                    setVisible(false);
                    new Dashboard(username);
                }
                Thread.sleep(50);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    Loading(String username) {
        this.username = username;

        t = new Thread(this);
        setSize(800, 500);  
        setLocationRelativeTo(null);  
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        
        setLayout(new GridBagLayout());
        getContentPane().setBackground(new Color(245, 245, 255)); 

        
        JPanel content = new JPanel();
        content.setLayout(null);
        content.setPreferredSize(new Dimension(700, 400));
        content.setBackground(new Color(245, 245, 255)); 

        
        JLabel text = new JLabel("Travel and Tourism Application");
        text.setBounds(50, 30, 700, 50);
        text.setForeground(new Color(54, 33, 89));
        text.setFont(new Font("Raleway", Font.BOLD, 36));
        content.add(text);

        
        bar = new JProgressBar();
        bar.setBounds(200, 120, 300, 40);
        bar.setStringPainted(true);
        bar.setForeground(new Color(102, 153, 255)); 
        bar.setBackground(Color.WHITE);
        content.add(bar);

        
        JLabel loading = new JLabel("Loading, please wait...");
        loading.setBounds(260, 170, 300, 30);
        loading.setForeground(new Color(255, 102, 102)); 
        loading.setFont(new Font("Raleway", Font.BOLD, 18));
        content.add(loading);

        
        JLabel lblusername = new JLabel("Welcome " + username);
        lblusername.setBounds(20, 320, 500, 40);
        lblusername.setForeground(new Color(0, 153, 153)); 
        lblusername.setFont(new Font("Raleway", Font.BOLD, 18));
        content.add(lblusername);

       
        add(content);
        setVisible(true);

        t.start();
    }

    public static void main(String[] args) {
        new Loading("User");
    }
}
