import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PatientRegistrationPage {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/college";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "thaiyal16";

    private JFrame frame;
    private JTextField nameField;
    private JTextField ageField;
    private JTextField bloodtypeField;
    private JTextField entryTypeField;
    private JTextField contactField;
    private JTextField residenceField;

    public PatientRegistrationPage() {
        frame = new JFrame("Patient Registration");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        JPanel panel = new JPanel();
        frame.add(panel);

        JLabel nameLabel = new JLabel("Name:");
        panel.add(nameLabel);
        nameField = new JTextField(20);
        panel.add(nameField);

        JLabel ageLabel = new JLabel("Age:");
        panel.add(ageLabel);
        ageField = new JTextField(5);
        panel.add(ageField);

        JLabel genderLabel = new JLabel("bloodtype:");
        panel.add(bloodtypeLabel);
        genderField = new JTextField(10);
        panel.add(bloodtypeField);

        JLabel diagnosisLabel = new JLabel("entryType:");
        panel.add(entryTypeLabel);
        diagnosisField = new JTextField(20);
        panel.add(entryTypeField);

        JLabel diagnosisLabel = new JLabel("contact:");
        panel.add(contactLabel);
        diagnosisField = new JTextField(20);
        panel.add(contactField);

        JLabel diagnosisLabel = new JLabel("residence:");
        panel.add(residenceLabel);
        diagnosisField = new JTextField(20);
        panel.add(residenceField);

        JButton submitButton = new JButton("Submit");
        panel.add(submitButton);
        submitButton.addActionListener(new SubmitButtonListener());

        frame.setVisible(true);
    }

    private class SubmitButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = nameField.getText();
            String age = ageField.getText();
            String bloodtype = bloodtypeField.getText();
            String entryType = entryTypeField.getText();
            String contact = contactField.getText();
            String residence = residenceField.getText();

            try {
                Connection connection = DriverManager.getConnection(jdbc:mysql://localhost:3306/college,"root","thaiyal16");
                insertPatient(connection, name, age, bloodtype, entryType,contact,residence);
                connection.close();
                JOptionPane.showMessageDialog(frame, "Patient details saved successfully.");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    private void insertPatient(Connection connection, String name, String age, String gender, String diagnosis) throws SQLException {
        String insertQuery = "INSERT INTO patients (name, age, bloodtype, entryType,contact,residence) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, age);
            preparedStatement.setString(3, bloodtype);
            preparedStatement.setString(4, entryType);
            preparedStatement.setString(4, contact);
            preparedStatement.setString(4, residence);
            preparedStatement.executeUpdate();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PatientRegistrationPage());
    }
}