package travel.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class About extends JFrame implements ActionListener {

    About() {
        setTitle("About");
        setExtendedState(JFrame.MAXIMIZED_BOTH); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(800, 600)); 

       
        JPanel wrapper = new JPanel(new BorderLayout());
        wrapper.setBorder(BorderFactory.createEmptyBorder(30, 60, 30, 60));
        wrapper.setBackground(Color.WHITE);

        
        JPanel container = new JPanel(new BorderLayout());
        container.setOpaque(false);

     
        JPanel header = new JPanel(new FlowLayout(FlowLayout.LEFT));
        header.setBackground(Color.WHITE);

        
        ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("icons/dashboard.png")); 
        Image img = icon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        JLabel iconLabel = new JLabel(new ImageIcon(img));
        header.add(iconLabel);

        JLabel title = new JLabel("ABOUT");
        title.setFont(new Font("Tahoma", Font.BOLD, 30));
        title.setForeground(new Color(220, 20, 60)); 
        header.add(title);

        container.add(header, BorderLayout.NORTH);

        
        String s = " About Travel and Tourism Management System\n\n"
                + "The objective of the Travel and Tourism Management System "
                + "project is to develop a system that automates the processes "
                + "and activities of a travel and the purpose is to design a "
                + "system using which one can perform all operations related to "
                + "traveling.\n\n"
                + "This application will help in accessing the information related "
                + "to the travel to the particular destination with great ease. "
                + "The users can track the information related to their tours with "
                + "great ease through this application. The travel agency information "
                + "can also be obtained through this application.\n\n"
                + "Advantages of Project:\n"
                + "• Gives accurate information\n"
                + "• Simplifies the manual work\n"
                + "• It minimizes the documentation related work\n"
                + "• Provides up to date information\n"
                + "• Friendly Environment by providing warning messages\n"
                + "• Travelers details can be provided\n"
                + "• Booking confirmation notification";

        JTextArea area = new JTextArea(s);
        area.setEditable(false);
        area.setFont(new Font("Tahoma", Font.PLAIN, 16));
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
        area.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GRAY, 1),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));

        JScrollPane scrollPane = new JScrollPane(area);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
        scrollPane.setPreferredSize(new Dimension(400, 300));
        container.add(scrollPane, BorderLayout.CENTER);

        
        JPanel bottom = new JPanel();
        bottom.setBackground(Color.WHITE);
        JButton back = new JButton("Back");
        back.setPreferredSize(new Dimension(100, 30));
        back.setFocusPainted(false);
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.addActionListener(this);
        bottom.add(back);

        container.add(bottom, BorderLayout.SOUTH);

        wrapper.add(container, BorderLayout.CENTER);
        add(wrapper);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        setVisible(false);
    }

    public static void main(String[] args) {
        new About();
    }
}
