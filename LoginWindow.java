import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Logger;
import java.util.logging.Level;

public class LoginWindow extends JFrame {
    private final JTextField usernameField;
    private final JPasswordField passwordField;
    private static final Logger logger = Logger.getLogger(LoginWindow.class.getName());

    public LoginWindow() {
        setTitle("Login");
        setSize(300, 150); setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 2));

        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField();
        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField();

        // Extracted method returning the loginButton
        JButton loginButton = createLoginButton();

        add(usernameLabel);
        add(usernameField);
        add(passwordLabel);
        add(passwordField);
        add(new JLabel()); // Empty label for spacing
        add(loginButton);

        setLocationRelativeTo(null); // Center the login window on the screen
    }

    private JButton createLoginButton() {
        JButton button = new JButton("Login");
        button.addActionListener(e -> {
            String username = usernameField.getText();
            char[] password = passwordField.getPassword();
            if (validateCredentials(username, password)) {
                // Close the login window
                dispose();
                // Open the main application window
                openMainApplicationWindow();
            } else { JOptionPane.showMessageDialog(null, "Incorrect username or password. Access denied.");
            }
            // Clear the password field after validation
            passwordField.setText("");
        });
        return button;
    }

    private boolean validateCredentials(String username, char[] password) {
        // Read the authorized accounts from a CSV file
        String csvFile = "C:\\Users\\REMENTAS FAMILY\\IdeaProjects\\MotorPH Employee App\\src\\authorized_accounts.csv";
        String line;
        String cvsSplitBy = ",";
        boolean isValid = false;

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            while ((line = br.readLine()) != null) {
                String[] account = line.split(cvsSplitBy);
                String storedUsername = account[0].trim();
                String storedPassword = account[1].trim();

                if (storedUsername.equals(username) && new String(password).equals(storedPassword)) {
                    isValid = true;
                    break;
                }
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "An error occurred", e);
        }

        return isValid;
    }

    private void openMainApplicationWindow() {
        MotorPHEmployeeAppGUI mainAppWindow = new MotorPHEmployeeAppGUI();
        mainAppWindow.setVisible(true);
    }
}
