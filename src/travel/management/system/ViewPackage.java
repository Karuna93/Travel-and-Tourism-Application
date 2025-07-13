package travel.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class ViewPackage extends JFrame implements ActionListener {

    JButton back;
    JLabel labelusername, labelpackage, labelpersons, labelid, labelnumber, labelcountry, labelphone, labelprice;

    ViewPackage(String username) {
        setBounds(300, 100, 1000, 550);
        getContentPane().setBackground(Color.WHITE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel text = new JLabel("VIEW PACKAGE DETAILS");
        text.setFont(new Font("Tahome", Font.BOLD, 22));
        text.setBounds(60, 10, 300, 30);
        add(text);

        int labelX = 30;
        int valueX = 220;
        int startY = 70;
        int gapY = 35;

        JLabel lblusername = new JLabel("Username");
        lblusername.setBounds(labelX, startY, 150, 25);
        add(lblusername);

        labelusername = new JLabel();
        labelusername.setBounds(valueX, startY, 200, 25);
        add(labelusername);

        JLabel lblpackage = new JLabel("Package");
        lblpackage.setBounds(labelX, startY + gapY, 150, 25);
        add(lblpackage);

        labelpackage = new JLabel();
        labelpackage.setBounds(valueX, startY + gapY, 200, 25);
        add(labelpackage);

        JLabel lblpersons = new JLabel("Total Persons");
        lblpersons.setBounds(labelX, startY + 2 * gapY, 150, 25);
        add(lblpersons);

        labelpersons = new JLabel();
        labelpersons.setBounds(valueX, startY + 2 * gapY, 200, 25);
        add(labelpersons);

        JLabel lblid = new JLabel("Id");
        lblid.setBounds(labelX, startY + 3 * gapY, 150, 25);
        add(lblid);

        labelid = new JLabel();
        labelid.setBounds(valueX, startY + 3 * gapY, 200, 25);
        add(labelid);

        JLabel lblnumber = new JLabel("Number");
        lblnumber.setBounds(labelX, startY + 4 * gapY, 150, 25);
        add(lblnumber);

        labelnumber = new JLabel();
        labelnumber.setBounds(valueX, startY + 4 * gapY, 200, 25);
        add(labelnumber);

        JLabel lblcountry = new JLabel("Country");
        lblcountry.setBounds(labelX, startY + 5 * gapY, 150, 25);
        add(lblcountry);

        labelcountry = new JLabel();
        labelcountry.setBounds(valueX, startY + 5 * gapY, 200, 25);
        add(labelcountry);

        JLabel lblphone = new JLabel("Phone");
        lblphone.setBounds(labelX, startY + 6 * gapY, 150, 25);
        add(lblphone);

        labelphone = new JLabel();
        labelphone.setBounds(valueX, startY + 6 * gapY, 200, 25);
        add(labelphone);

        JLabel lblprice = new JLabel("Price");
        lblprice.setBounds(labelX, startY + 7 * gapY, 150, 25);
        add(lblprice);

        labelprice = new JLabel();
        labelprice.setBounds(valueX, startY + 7 * gapY, 200, 25);
        add(labelprice);

        back = new JButton("Back");
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.setBounds(150, startY + 8 * gapY + 10, 100, 25);
        back.addActionListener(this);
        add(back);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/bookedDetails.jpg"));
        Image i2 = i1.getImage().getScaledInstance(400, 400, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(550, 80, 400, 400); 
        add(image);

        setVisible(true);

        
        SwingUtilities.invokeLater(() -> fetchUserPackageDetails(username));
    }

    private void fetchUserPackageDetails(String username) {
        if (username == null || username.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "No user is currently logged in.");
            return;
        }

        try {
            Conn c = new Conn();
            String query = "SELECT * FROM bookpackage WHERE username = '" + username + "'";
            ResultSet rs = c.s.executeQuery(query);

            if (rs.next()) {
                labelusername.setText(rs.getString("username"));
                labelpackage.setText(rs.getString("package"));
                labelpersons.setText(rs.getString("persons"));
                labelid.setText(rs.getString("id"));
                labelnumber.setText(rs.getString("number"));
                labelphone.setText(rs.getString("phone"));
                labelprice.setText(rs.getString("price"));
                labelcountry.setText("India"); 
            } else {
                JOptionPane.showMessageDialog(null, "No package found for logged-in user.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == back) {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new ViewPackage(" "); 
    }
}
