package travel.management.system;

import javax.swing.*;
import java.awt.*;

public class Splash extends JFrame implements Runnable{
    Thread thread;
    Splash() {
  
         ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/splash.jpg"));
         Image i2 = i1.getImage().getScaledInstance(1200, 600, Image.SCALE_DEFAULT); 
         ImageIcon i3 = new ImageIcon(i2);
         JLabel image = new JLabel(i3);
         add(image);
        
        setVisible(true);
        thread = new Thread(this);
        thread.start();
        
    }
    
    public void run() {
        try {
            Thread.sleep(7000);
           setVisible(false);
        } catch (Exception e) {
            
        }
        
    }
    
    public static void main(String[] args) {
        Splash frame = new Splash();
        
        int x = 1;
        for (int i = 1; i <= 500; x += 7, i += 6) {
            frame.setLocation(680 - (x + i)/2, 360 - (i/2));  
            frame.setSize(x + i, i);
            try {
                Thread.sleep(10);
            } catch (Exception e) {}
        }
    }       
}
