package travel.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CheckHotels extends JFrame implements Runnable {

    Thread t1;
    JLabel label = new JLabel();
    JLabel caption;

    ImageIcon[] originalImage = new ImageIcon[10];
    int currentIndex = 0;

    CheckHotels() {
        setBounds(500, 200, 800, 600);
        setLocationRelativeTo(null);
        setLayout(null);
        getContentPane().setBackground(Color.BLACK);

        caption = new JLabel();
        caption.setFont(new Font("Tahoma", Font.PLAIN, 40));
        caption.setForeground(Color.WHITE);
        add(caption);

        add(label); 
        setVisible(true);

        SwingUtilities.invokeLater(() -> {
            for (int i = 0; i < 10; i++) {
                originalImage[i] = new ImageIcon(ClassLoader.getSystemResource("icons/hotel" + (i + 1) + ".jpg"));
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

        if (originalImage[currentIndex] != null) {
            Image img = originalImage[currentIndex].getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH);
            label.setIcon(new ImageIcon(img));
            label.setBounds(0, 0, w, h);
        }

        caption.setBounds(50, h - 100, w - 100, 70);
    }

    public void run() {
        String[] text = new String[] {
            "JW Marriott Hotel", "Madarin Oriental Hotel", "Four Seasons Hotel",
            "Rassisson Blue Hotel", "Classio Hotel", "The Bay Club Hotel",
            "Breeze Blow Hotel", "The Taj Hotel", "Happy Morning Hotel", "River View Hotel"
        };

        try {
            while (true) {
                for (int i = 0; i < 10; i++) {
                    currentIndex = i;
                    updateImage();
                    caption.setText(text[i]);
                    Thread.sleep(2500);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new CheckHotels();
    }
}
