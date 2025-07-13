package travel.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.regex.*;

public class Signup extends JFrame implements ActionListener {

    JButton create, back;
    JTextField tfname, tfusername, tfanswer;
    JPasswordField tfpassword;
    JCheckBox showPassword;
    Choice security;
    JPanel p1;
    JLabel image;

    Signup() {
        setBounds(350, 200, 900, 360);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        p1 = new JPanel();
        p1.setBackground(new Color(133, 193, 233));
        p1.setBounds(0, 0, 500, 400);
        p1.setLayout(null);
        add(p1);

        JLabel lblusername = new JLabel("Username");
        lblusername.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblusername.setBounds(50, 20, 125, 25);
        p1.add(lblusername);

        tfusername = new JTextField();
        tfusername.setBounds(190, 20, 180, 25);
        tfusername.setBorder(BorderFactory.createEmptyBorder());
        p1.add(tfusername);

        JLabel lblname = new JLabel("name");
        lblname.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblname.setBounds(50, 60, 125, 25);
        p1.add(lblname);

        tfname = new JTextField();
        tfname.setBounds(190, 60, 180, 25);
        tfname.setBorder(BorderFactory.createEmptyBorder());
        p1.add(tfname);

        JLabel lblpassword = new JLabel("Password");
        lblpassword.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblpassword.setBounds(50, 100, 125, 25);
        p1.add(lblpassword);

        tfpassword = new JPasswordField();
        tfpassword.setBounds(190, 100, 180, 25);
        tfpassword.setBorder(BorderFactory.createEmptyBorder());
        p1.add(tfpassword);

        showPassword = new JCheckBox("Show Password");
        showPassword.setFont(new Font("Tahoma", Font.PLAIN, 12));
        showPassword.setBackground(new Color(133, 193, 233));
        showPassword.setBounds(190, 125, 150, 20);
        showPassword.addActionListener(e -> {
            if (showPassword.isSelected()) {
                tfpassword.setEchoChar((char) 0);
            } else {
                tfpassword.setEchoChar('•');
            }
        });
        p1.add(showPassword);

        JLabel lblsecurity = new JLabel("Security Question");
        lblsecurity.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblsecurity.setBounds(50, 160, 125, 25);
        p1.add(lblsecurity);

        security = new Choice();
        security.add("Fav Character from Harry Potter");
        security.add("Fav Marvel superhero");
        security.add("Your Fav Sports");
        security.add("your fav Cricketer");
        security.setBounds(190, 160, 180, 25);
        p1.add(security);

        JLabel lblanswer = new JLabel("Answer");
        lblanswer.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblanswer.setBounds(50, 200, 125, 25);
        p1.add(lblanswer);

        tfanswer = new JTextField();
        tfanswer.setBounds(190, 200, 180, 25);
        tfanswer.setBorder(BorderFactory.createEmptyBorder());
        p1.add(tfanswer);

        create = new JButton("Create");
        create.setBackground(Color.WHITE);
        create.setForeground(new Color(133, 193, 233));
        create.setFont(new Font("Tahoma", Font.BOLD, 14));
        create.setBounds(80, 250, 100, 30);
        create.addActionListener(this);
        p1.add(create);

        back = new JButton("Back");
        back.setBackground(Color.WHITE);
        back.setForeground(new Color(133, 193, 233));
        back.setFont(new Font("Tahoma", Font.BOLD, 14));
        back.setBounds(250, 250, 100, 30);
        back.addActionListener(this);
        p1.add(back);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/signup.png"));
        Image i2 = i1.getImage().getScaledInstance(250, 250, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        image = new JLabel(i3);
        image.setBounds(580, 50, 250, 250);
        add(image);

        
        addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                int width = getWidth();
                int height = getHeight();

                p1.setBounds(0, 0, width / 2, height);
                image.setBounds((width / 2) + (width / 4 - 125), height / 2 - 125, 250, 250);
            }
        });

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == create) {
            String username = tfusername.getText().trim();
            String name = tfname.getText().trim();
            String password = new String(tfpassword.getPassword()).trim();
            String question = security.getSelectedItem();
            String answer = tfanswer.getText().trim();

            if (username.isEmpty() || name.isEmpty() || password.isEmpty() || answer.isEmpty()) {
                JOptionPane.showMessageDialog(null, "All fields are required", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (username.equalsIgnoreCase(name)) {
                JOptionPane.showMessageDialog(null, "Username and name cannot be the same.", "Validation Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!isValidPassword(password)) {
                JOptionPane.showMessageDialog(null,
                        "Password must be at least 8 characters long and include at least two of the following:\n- Uppercase\n- Lowercase\n- Underscore\n- Special Character",
                        "Invalid Password",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                Conn c = new Conn();
                String checkQuery = "SELECT * FROM account WHERE username = '" + username + "'";
                ResultSet rs = c.s.executeQuery(checkQuery);
                if (rs.next()) {
                    JOptionPane.showMessageDialog(null, "Account with this username already exists", "Duplicate Account", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String query = "insert into account values('" + username + "', '" + name + "', '" + password + "', '" + question + "', '" + answer + "')";
                c.s.executeUpdate(query);

                JOptionPane.showMessageDialog(null, "Account Created Successfully");

                setVisible(false);
                new Login();
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else if (ae.getSource() == back) {
            setVisible(false);
            new Login();
        }
    }

    private boolean isValidPassword(String password) {
        if (password.length() < 8) return false;

        boolean hasUpper = false, hasLower = false, hasSpecial = false, hasUnderscore = false;
        for (char ch : password.toCharArray()) {
            if (Character.isUpperCase(ch)) hasUpper = true;
            else if (Character.isLowerCase(ch)) hasLower = true;
            else if (ch == '_') hasUnderscore = true;
            else if (!Character.isLetterOrDigit(ch)) hasSpecial = true;
        }

        int count = 0;
        if (hasUpper) count++;
        if (hasLower) count++;
        if (hasSpecial) count++;
        if (hasUnderscore) count++;

        return count >= 2;
    }

    public static void main(String[] args) {
        new Signup();
    }
}
