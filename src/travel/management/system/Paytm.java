package travel.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URI;

public class Paytm extends JFrame implements ActionListener {

    JButton back, pay;

    Paytm() {
        setTitle("Paytm Payment Page");
        setSize(800, 600);
        setLocationRelativeTo(null); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        
        JLabel info = new JLabel("Click Pay button to continue to Paytm");
        info.setFont(new Font("Tahoma", Font.PLAIN, 24));
        info.setBounds(250, 40, 500, 30);

       
        ImageIcon imgIcon = new ImageIcon(ClassLoader.getSystemResource("icons/paytm.jpg"));
        Image img = imgIcon.getImage().getScaledInstance(350, 350, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(img);
        JLabel imgLabel = new JLabel(scaledIcon);
        imgLabel.setBounds(250, 90, 300, 300); 

        
        pay = new JButton("Pay");
        pay.setBounds(200, 450, 120, 40);
        pay.setBackground(Color.BLACK);
        pay.setForeground(Color.WHITE);
        pay.addActionListener(this);

       
        back = new JButton("Back");
        back.setBounds(480, 450, 120, 40);
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.addActionListener(this);

        
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setLayout(null);
        layeredPane.setBounds(0, 0, 800, 600);

        layeredPane.add(info, Integer.valueOf(1));
        layeredPane.add(imgLabel, Integer.valueOf(1));
        layeredPane.add(pay, Integer.valueOf(2));
        layeredPane.add(back, Integer.valueOf(2));

        setContentPane(layeredPane);

        
        addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                int width = getWidth();
                int height = getHeight();
                info.setBounds((width - 500) / 2, 40, 500, 30);
                imgLabel.setBounds((width - 300) / 2, 90, 300, 300);
                pay.setBounds((width / 2) - 160, height - 100, 120, 40);
                back.setBounds((width / 2) + 40, height - 100, 120, 40);
            }
        });

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == pay) {
            try {
                Desktop.getDesktop().browse(new URI("https://paytm.com/"));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Failed to open Paytm. Please check your connection.");
            }
        } else if (ae.getSource() == back) {
            setVisible(false);
            new Payment(); // 
        }
    }

    public static void main(String[] args) {
        new Paytm();
    }
}
