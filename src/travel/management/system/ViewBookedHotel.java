package travel.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class ViewBookedHotel extends JFrame implements ActionListener {

    JButton back;
    JLabel labelusername, labelname, labelpersons, labeldays, labelac, labelfood, labelid, labelnumber, labelphone, labelcost;
    String username;

    ViewBookedHotel(String username) {
        this.username = username;

        setBounds(400, 150, 1050, 600);
        getContentPane().setBackground(Color.WHITE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel text = new JLabel("VIEW BOOKED HOTEL DETAILS");
        text.setFont(new Font("Tahoma", Font.BOLD, 22));
        text.setBounds(50, 10, 400, 30);
        add(text);

        JLabel lblusername = new JLabel("Username");
        lblusername.setBounds(30, 60, 150, 25);
        lblusername.setFont(new Font("Tahoma", Font.PLAIN, 14));
        add(lblusername);

        labelusername = new JLabel(username);
        labelusername.setBounds(180, 60, 180, 25);
        labelusername.setFont(new Font("Tahoma", Font.PLAIN, 14));
        add(labelusername);

        int y = 110, labelX = 30, valueX = 180, h = 25, gap = 40;

        addLabel("Hotel Name", y, labelX, valueX);
        labelname = createValueLabel(y, valueX);
        y += gap;

        addLabel("Total Persons", y, labelX, valueX);
        labelpersons = createValueLabel(y, valueX);
        y += gap;

        addLabel("Total Days", y, labelX, valueX);
        labeldays = createValueLabel(y, valueX);
        y += gap;

        addLabel("AC/Non-AC", y, labelX, valueX);
        labelac = createValueLabel(y, valueX);
        y += gap;

        addLabel("Food Included", y, labelX, valueX);
        labelfood = createValueLabel(y, valueX);
        y += gap;

        addLabel("Id", y, labelX, valueX);
        labelid = createValueLabel(y, valueX);
        y += gap;

        addLabel("Number", y, labelX, valueX);
        labelnumber = createValueLabel(y, valueX);
        y += gap;

        addLabel("Phone", y, labelX, valueX);
        labelphone = createValueLabel(y, valueX);
        y += gap;

        addLabel("Total Cost", y, labelX, valueX);
        labelcost = createValueLabel(y, valueX);
        y += gap;

        back = new JButton("Back");
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.setBounds(150, y, 100, 25);
        back.addActionListener(this);
        add(back);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/bookedDetails.jpg"));
        Image i2 = i1.getImage().getScaledInstance(450, 400, Image.SCALE_SMOOTH);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(500, 80, 500, 400);
        add(image);

        
        setVisible(true);
        SwingUtilities.invokeLater(() -> fetchBookedHotelDetails());
    }

    private void addLabel(String title, int y, int labelX, int valueX) {
        JLabel lbl = new JLabel(title);
        lbl.setBounds(labelX, y, 150, 25);
        lbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
        add(lbl);
    }

    private JLabel createValueLabel(int y, int x) {
        JLabel l = new JLabel();
        l.setBounds(x, y, 200, 25);
        l.setFont(new Font("Tahoma", Font.PLAIN, 14));
        add(l);
        return l;
    }

    private void fetchBookedHotelDetails() {
        try {
            if (username == null || username.trim().isEmpty()) {
                clearLabels();
                SwingUtilities.invokeLater(() ->
                        JOptionPane.showMessageDialog(null, "No customer is logged in or no hotel booking found."));
                return;
            }

            Conn c = new Conn();
            String query = "SELECT * FROM bookhotel WHERE username = '" + username + "'";
            ResultSet rs = c.s.executeQuery(query);

            if (rs.next()) {
                labelid.setText(rs.getString("id"));
                labelnumber.setText(rs.getString("number"));
                labelname.setText(rs.getString("name"));
                labelcost.setText(rs.getString("price"));
                labelphone.setText(rs.getString("phone"));
                labelpersons.setText(rs.getString("persons"));
                labelfood.setText(rs.getString("food"));
                labelac.setText(rs.getString("ac"));
                labeldays.setText(rs.getString("days"));
            } else {
                clearLabels();
                SwingUtilities.invokeLater(() ->
                        JOptionPane.showMessageDialog(null, "No customer is logged in or no hotel booking found."));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void clearLabels() {
        labelid.setText("");
        labelnumber.setText("");
        labelname.setText("");
        labelcost.setText("");
        labelphone.setText("");
        labelpersons.setText("");
        labelfood.setText("");
        labelac.setText("");
        labeldays.setText("");
    }

    public void actionPerformed(ActionEvent ae) {
        setVisible(false);
    }

    public static void main(String[] args) {
        new ViewBookedHotel(" "); 
    }
}
