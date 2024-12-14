import javax.swing.*;
import java.awt.event.*;

class Homepage extends JFrame implements ActionListener {
    JButton reg,view;

    Homepage() {
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("HomePage");
        reg = new JButton("Registration");
        view = new JButton("View Result");
        reg.addActionListener(this);
        view.addActionListener(this);
        reg.setBounds(100, 30, 200, 30);
        view.setBounds(100, 70, 200, 30);
        add(reg);
        add(view);
        setVisible(true);
        setSize(400, 400);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == reg) {
            // Corrected constructor call
            new NewUser(); // Assuming NewUpload is a class name
        } else {
            // Corrected constructor call
            new ViewResult(); // Assuming ViewRecord is a class name
        }
    }

    public static void main(String args[]) {
        new Homepage();
    }
}