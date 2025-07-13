package travel.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Payment extends JFrame implements ActionListener {

    JButton pay, back;
    JLabel imageLabel;
    Image originalImage;

    Payment() {
        setSize(800, 600);
        setLocationRelativeTo(null);
        setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/paytm.jpg"));
        originalImage = i1.getImage();

        imageLabel = new JLabel();
        imageLabel.setLayout(null);
        add(imageLabel);

        pay = new JButton("Pay");
        pay.setBounds(420, 0, 80, 40);
        pay.setBackground(Color.BLACK);
        pay.setForeground(Color.WHITE);
        pay.addActionListener(this);
        imageLabel.add(pay);

        back = new JButton("Back");
        back.setBounds(520, 0, 80, 40);
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.addActionListener(this);
        imageLabel.add(back);

        addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                updateBackground();
            }
        });

        updateBackground(); 

        setVisible(true);
    }

    public void updateBackground() {
        int width = getWidth();
        int height = getHeight();
        Image scaledImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        imageLabel.setIcon(new ImageIcon(scaledImage));
        imageLabel.setBounds(0, 0, width, height);

        
        pay.setBounds((int)(width * 0.52), 0, 80, 40);
        back.setBounds((int)(width * 0.66), 0, 80, 40);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == pay) {
            setVisible(false);
            new Paytm(); 
        } else {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new Payment();
    }
}
