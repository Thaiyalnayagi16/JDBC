import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NewUser extends JFrame implements ActionListener {
    JLabel l1, l2, l3, mark1, mark2, mark3, mark4, mark5;
    JTextField tf1, tf2, tf3, tf4, tf5 ,tf6;
    JButton submit, btn2;

    NewUser() {
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Registration Form");

        l1 = new JLabel("HOSPITAL MANAGEMENT:");
        l1.setForeground(Color.RED);
        l1.setFont(new Font("Serif", Font.BOLD, 27));

        l2 = new JLabel("Patient Name:");
        l3 = new JLabel("Age:");
        mark1 = new JLabel("Blood Type:");
        mark2 = new JLabel("Entry Type:");
        mark3 = new JLabel("Contact:");
        mark4 = new JLabel("Residence:");

        tf1 = new JTextField();
        tf2 = new JTextField();
        tf3 = new JTextField();
        tf4 = new JTextField();
        tf5 = new JTextField();
        tf6 = new JTextField();

        submit = new JButton("Submit");
        btn2 = new JButton("Back");

        submit.addActionListener(this);
        btn2.addActionListener(this);

        l1.setBounds(100, 30, 400, 30);
        l2.setBounds(80, 70, 200, 30);
        l3.setBounds(80, 110, 200, 30);
        mark1.setBounds(80, 150, 200, 30);
        mark2.setBounds(80, 190, 200, 30);
        mark3.setBounds(80, 230, 200, 30);
        mark4.setBounds(80, 270, 200, 30);

        tf1.setBounds(300, 70, 200, 30);
        tf2.setBounds(300, 110, 200, 30);
        tf3.setBounds(300, 150, 200, 30);
        tf4.setBounds(300, 190, 200, 30);
        tf5.setBounds(300, 230, 200, 30);
        tf6.setBounds(300, 270, 200, 30);

        submit.setBounds(50, 320, 100, 30);
        btn2.setBounds(200, 320, 100, 30);

        add(l1);
        add(l2);
        add(tf1);
        add(l3);
        add(tf2);
        add(mark1);
        add(tf3);
        add(mark2);
        add(tf4);
        add(mark3);
        add(tf5);
        add(mark4);
        add(tf6);
        add(submit);
        add(btn2);

        setSize(600, 400);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submit) {
            String name = tf1.getText();
            int age = Integer.parseInt(tf2.getText());
            String bloodType = tf3.getText();
            String entryType = tf4.getText();
            String contact = tf5.getText();
            String residence = tf6.getText();

            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/college", "root", "thaiyal16");
                PreparedStatement st = conn.prepareStatement("insert into hospital (Patname, age, bloodtype, EntryType, Contact, Residence) values (?, ?, ?, ?, ?, ?)");
                st.setString(1, name);
                st.setInt(2, age);
                st.setString(3, bloodType);
                st.setString(4, entryType);
                st.setString(5, contact);
                st.setString(6, residence);
                st.executeUpdate();

                if (conn != null) {
                    conn.close();
                }
                System.out.println("Patient details inserted successfully");
            } catch (SQLException sqle) {
                System.out.println("SQL Exception thrown:" + sqle);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(NewUser.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            new Homepage();
        }
    }
}