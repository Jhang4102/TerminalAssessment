import javax.swing.*;
import java.awt.*;
import javax.swing.table.DefaultTableModel;
import java.util.Scanner;

public class EmployeeUI extends JPanel {
    private final JTextField employeeNumberField;
    private final JTextField sssField;
    private final JTextField philHealthField;
    private final JTextField pagIbigField;
    private final JTextField tinField;
    private final JButton submitButton;
    private DefaultTableModel employeeTableModel;

    private class Employee {
        private int employeeNumber;
        private String firstName;
        private String lastName;
        private String position;
        private double basicSalary;
        private String sssNumber;
        private String philHealthNumber;
        private String pagIbigNumber;
        private String tinNumber;

        // Constructor, getters, and setters for the Employee class
    }

    public EmployeeUI() {
        setLayout(new GridLayout(6, 2));

        JLabel employeeNumberLabel = new JLabel("Employee Number:");
        employeeNumberField = new JTextField(34);

        JLabel sssLabel = new JLabel("SSS Number:");
        sssField = new JTextField(34);
        JLabel philHealthLabel = new JLabel("PhilHealth Number:");
        philHealthField = new JTextField(34);
        JLabel pagIbigLabel = new JLabel("Pag-IBIG Number:");
        pagIbigField = new JTextField(34);
        JLabel tinLabel = new JLabel("TIN Number:");
        tinField = new JTextField(34);

        submitButton = new JButton("Submit");

        add(employeeNumberLabel);
        add(employeeNumberField);
        add(sssLabel);
        add(new JLabel());
        add(philHealthLabel);
        add(new JLabel());
        add(pagIbigLabel);
        add(new JLabel());
        add(tinLabel);
        add(new JLabel());
        add(submitButton);
    }

    public JButton getSubmitButton() {
        return submitButton;
    }

    public JTextField getEmployeeNumberField() {
        return employeeNumberField;
    }

    public void displayEmployeeInfo(Employee employee) {
        // Display employee information on the user interface
        System.out.println("Employee Information:");
        System.out.println("Employee Number: " + employee.employeeNumber);
        System.out.println("Name: " + employee.firstName + " " + employee.lastName);
        System.out.println("Position: " + employee.position);
        System.out.println("Basic Salary: " + employee.basicSalary);
        System.out.println("SSS Number: " + employee.sssNumber);
        System.out.println("Philhealth Number: " + employee.philHealthNumber);
        System.out.println("Pag-IBIG Number: " + employee.pagIbigNumber);
        System.out.println("TIN Number: " + employee.tinNumber);
    }

    public void captureUserInput() {
        // Capture user input for employee data
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter Employee Number:");
        int employeeNumber = scanner.nextInt();
        System.out.println("Enter First Name:");
        String firstName = scanner.next();
        System.out.println("Enter Last Name:");
        String lastName = scanner.next();
        System.out.println("Enter Position:");
        String position = scanner.next();
        System.out.println("Enter Basic Salary:");
        double basicSalary = scanner.nextDouble();
        System.out.println("Enter SSS Number:");
        String sssNumber = scanner.next();
        System.out.println("Enter PhilHealth Number:");
        String philHealthNumber = scanner.next();
        System.out.println("Enter Pag-IBIG Number:");
        String pagIbigNumber = scanner.next();
        System.out.println("Enter TIN Number:");
        String tinNumber = scanner.next();

        // Create an Employee object with the captured user input
        Employee newEmployee = new Employee();
        newEmployee.employeeNumber = employeeNumber;
        newEmployee.firstName = firstName;
        newEmployee.lastName = lastName;
        newEmployee.position = position;
        newEmployee.basicSalary = basicSalary;
        newEmployee.sssNumber = sssNumber;
        newEmployee.philHealthNumber = philHealthNumber;
        newEmployee.pagIbigNumber = pagIbigNumber;
        newEmployee.tinNumber = tinNumber;

        // Display the information of the new employee
        displayEmployeeInfo(newEmployee);
    }

    public void performAction() {
        getSubmitButton().addActionListener(e -> {
            // Retrieve the user input from the text fields
            int employeeNumber = Integer.parseInt(getEmployeeNumberField().getText());
            String sssNumber = sssField.getText();
            String philHealthNumber = philHealthField.getText();
            String pagIbigNumber = pagIbigField.getText();
            String tinNumber = tinField.getText();

            // Create a new Employee object with the entered data
            Employee newEmployee = new Employee();
            newEmployee.employeeNumber = employeeNumber;
            newEmployee.sssNumber = sssNumber;
            newEmployee.philHealthNumber = philHealthNumber;
            newEmployee.pagIbigNumber = pagIbigNumber;
            newEmployee.tinNumber = tinNumber;

            // Display the information of the new employee
            displayEmployeeInfo(newEmployee);
        });
    }
}
