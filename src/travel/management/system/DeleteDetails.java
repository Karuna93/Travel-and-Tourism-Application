package travel.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class DeleteDetails extends JFrame implements ActionListener {
    JButton delete, back;
    JLabel labelusername, labelid, labelnumber, labelname, labelgender, labelcountry,
           labeladdress, labelphone, labelemail;

    DeleteDetails(String username) {
        setBounds(450, 180, 900, 650);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        setLocationRelativeTo(null);

        JLabel lbluser = new JLabel("Username");
        lbluser.setBounds(30, 20, 150, 25);
        add(lbluser);

        labelusername = new JLabel();
        labelusername.setBounds(220, 20, 150, 25);
        add(labelusername);

        JLabel lblid = new JLabel("Id");
        lblid.setBounds(30, 60, 150, 25);
        add(lblid);

        labelid = new JLabel();
        labelid.setBounds(220, 60, 150, 25);
        add(labelid);

        JLabel lblnumber = new JLabel("Number");
        lblnumber.setBounds(30, 100, 150, 25);
        add(lblnumber);

        labelnumber = new JLabel();
        labelnumber.setBounds(220, 100, 150, 25);
        add(labelnumber);

        JLabel lblname = new JLabel("Name");
        lblname.setBounds(30, 140, 150, 25);
        add(lblname);

        labelname = new JLabel();
        labelname.setBounds(220, 140, 150, 25);
        add(labelname);

        JLabel lblgender = new JLabel("Gender");
        lblgender.setBounds(30, 180, 150, 25);
        add(lblgender);

        labelgender = new JLabel();
        labelgender.setBounds(220, 180, 150, 25);
        add(labelgender);

        JLabel lblcountry = new JLabel("Country");
        lblcountry.setBounds(500, 20, 150, 25);
        add(lblcountry);

        labelcountry = new JLabel();
        labelcountry.setBounds(690, 20, 150, 25);
        add(labelcountry);

        JLabel lbladdress = new JLabel("Address");
        lbladdress.setBounds(500, 60, 150, 25);
        add(lbladdress);

        labeladdress = new JLabel();
        labeladdress.setBounds(690, 60, 150, 25);
        add(labeladdress);

        JLabel lblphone = new JLabel("Phone");
        lblphone.setBounds(500, 100, 150, 25);
        add(lblphone);

        labelphone = new JLabel();
        labelphone.setBounds(690, 100, 150, 25);
        add(labelphone);

        JLabel lblemail = new JLabel("Email");
        lblemail.setBounds(500, 140, 150, 25);
        add(lblemail);

        labelemail = new JLabel();
        labelemail.setBounds(690, 140, 150, 25);
        add(labelemail);

        delete = new JButton("Delete");
        delete.setBackground(Color.BLACK);
        delete.setForeground(Color.WHITE);
        delete.setBounds(350, 250, 100, 25);
        delete.addActionListener(this);
        add(delete);

        back = new JButton("Back");
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.setBounds(470, 250, 100, 25);
        back.addActionListener(this);
        add(back);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/viewall.jpg"));
        Image i2 = i1.getImage().getScaledInstance(600, 200, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(20, 330, 600, 200);
        add(image);

        ImageIcon i4 = new ImageIcon(ClassLoader.getSystemResource("icons/viewall.jpg"));
        Image i5 = i4.getImage().getScaledInstance(600, 200, Image.SCALE_DEFAULT);
        ImageIcon i6 = new ImageIcon(i5);
        JLabel image2 = new JLabel(i6);
        image2.setBounds(600, 330, 600, 200);
        add(image2);

        if (username != null && !username.equals("")) {
            labelusername.setText(username);
            fetchCustomerData(username);
        }

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == delete) {
            String username = labelusername.getText();

            if (username.equals("")) {
                JOptionPane.showMessageDialog(null, "No user loaded.");
                return;
            }

            int confirm = JOptionPane.showConfirmDialog(null,
                    "Are you sure you want to delete details for username: " + username + "?",
                    "Confirm Deletion", JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                try {
                    Conn c = new Conn();
                    c.s.executeUpdate("DELETE FROM account WHERE username = '" + username + "'");
                    c.s.executeUpdate("DELETE FROM customer WHERE username = '" + username + "'");
                    c.s.executeUpdate("DELETE FROM bookpackage WHERE username = '" + username + "'");
                    c.s.executeUpdate("DELETE FROM bookhotel WHERE username = '" + username + "'");

                    JOptionPane.showMessageDialog(null, "Customer details deleted successfully.");
                    clearLabels();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else if (ae.getSource() == back) {
            setVisible(false);
        }
    }

    private void fetchCustomerData(String username) {
        try {
            Conn c = new Conn();
            String query = "SELECT * FROM customer WHERE username = '" + username + "'";
            ResultSet rs = c.s.executeQuery(query);

            if (rs.next()) {
                labelid.setText(rs.getString("id"));
                labelnumber.setText(rs.getString("number"));
                labelname.setText(rs.getString("name"));
                labelgender.setText(rs.getString("gender"));
                labelcountry.setText(rs.getString("country"));
                labeladdress.setText(rs.getString("address"));
                labelphone.setText(rs.getString("phone"));
                labelemail.setText(rs.getString("email"));
            } else {
                JOptionPane.showMessageDialog(null, "No customer found with this username");
                clearLabels();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void clearLabels() {
        labelid.setText("");
        labelnumber.setText("");
        labelname.setText("");
        labelgender.setText("");
        labelcountry.setText("");
        labeladdress.setText("");
        labelphone.setText("");
        labelemail.setText("");
    }

    public static void main(String[] args) {
        new DeleteDetails(""); 
    }
}
