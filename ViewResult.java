import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

class ViewResult extends JFrame implements ActionListener {
    JLabel l1, l2, l3, l4, l5, l6, l7;
    JTextField tf1;
    JButton btn1, btn2;

    ViewResult() {
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("VIEW ");

        l1 = new JLabel("Entry Number:");
        l2 = new JLabel("Patname");
        l3 = new JLabel("age");
        l4 = new JLabel("bloodtype");
        l5 = new JLabel("EntryType");
        l6 = new JLabel("Contact");
        l7 = new JLabel("Residence");

        l1.setForeground(Color.blue);
        l1.setFont(new Font("Serif", Font.BOLD, 20));

        tf1 = new JTextField();
        btn1 = new JButton("Submit");
        btn2 = new JButton("Back");

        btn1.addActionListener(this);
        btn2.addActionListener(this);

        l1.setBounds(100, 30, 400, 30);
        tf1.setBounds(100, 70, 200, 30);
        l2.setBounds(100, 110, 400, 30);
        l3.setBounds(100, 150, 400, 30);
        l4.setBounds(100, 190, 400, 30);
        l5.setBounds(100, 230, 400, 30);
        l6.setBounds(100, 270, 400, 30);
        l7.setBounds(100, 310, 400, 30);
        btn1.setBounds(50, 350, 100, 30);
        btn2.setBounds(170, 350, 100, 30);

        add(l1);
        add(tf1);
        add(l2);
        add(l3);
        add(l4);
        add(l5);
        add(l6);
        add(l7);
        add(btn1);
        add(btn2);

        setVisible(true);
        setSize(700, 400);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btn1) {
            String regno = tf1.getText();
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                
                String connectionUrl = "jdbc:mysql://localhost:3306/college";
                String dbUser = "root";
                String dbPwd = "thaiyal16";
                Connection conn = DriverManager.getConnection(connectionUrl, dbUser, dbPwd);

                String queryString = "select * from hospital where EntryType=?";
                PreparedStatement stmt = conn.prepareStatement(queryString);
                stmt.setString(1, regno);
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    l2.setText("Patientname=" + rs.getString("PatName"));
                    l3.setText("Age=" + rs.getInt("age"));
                    l4.setText("BloodGroup=" + rs.getInt("bloodtype"));
                    l5.setText("EntryNumber=" + rs.getInt("EntryType"));
                    l6.setText("ContactNumber=" + rs.getInt("Contact"));
                    l7.setText("Residence=" + rs.getInt("Residence"));
                } else {
                    // If no result found, reset labels
                    l2.setText("Patient name=");
                    l3.setText("Age=");
                    l4.setText("Blood Group=");
                    l5.setText("Entry Number=");
                    l6.setText("Contact Number=");
                    l7.setText("Residence=");
                }
                conn.close();
            } catch (ClassNotFoundException | SQLException ex) {
                System.out.println("Error: " + ex);
                ex.printStackTrace();
            }
        } else {
            new Homepage();
        }
    }
}