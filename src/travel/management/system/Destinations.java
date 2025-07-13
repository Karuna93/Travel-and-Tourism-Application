package travel.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Destinations extends JFrame implements Runnable {

    Thread t1;
    JLabel label = new JLabel(); 
    ImageIcon[] images = new ImageIcon[10];
    int currentIndex = 0;

    Destinations() {
        setSize(800, 600); 
        setLocationRelativeTo(null);
        setLayout(null);
        getContentPane().setBackground(Color.BLACK);

        add(label); 

        setVisible(true);

        SwingUtilities.invokeLater(() -> {
            for (int i = 0; i < 10; i++) {
                images[i] = new ImageIcon(ClassLoader.getSystemResource("icons/dest" + (i + 1) + ".jpg"));
            }

            updateImage(); 

            addComponentListener(new ComponentAdapter() {
                public void componentResized(ComponentEvent e) {
                    updateImage(); 
                }
            });

            t1 = new Thread(this);
            t1.start();
        });
    }

    public void updateImage() {
        int w = getWidth();
        int h = getHeight();

        if (images[currentIndex] != null) {
            Image img = images[currentIndex].getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH);
            label.setIcon(new ImageIcon(img));
            label.setBounds(0, 0, w, h);
        }
    }

    public void run() {
        try {
            while (true) {
                for (int i = 0; i < 10; i++) {
                    currentIndex = i;
                    updateImage();
                    Thread.sleep(2500);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Destinations();
    }
}
