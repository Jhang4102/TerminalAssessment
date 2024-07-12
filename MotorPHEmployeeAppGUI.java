import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import javax.swing.table.TableModel;
import javax.swing.event.ListSelectionEvent;
import java.util.ArrayList;
import java.util.List;
import java.io.*;
import java.util.Scanner;

public class MotorPHEmployeeAppGUI extends JFrame {
    private final List<Employee> employeeDatabase;
    private final JTable employeeTable;
    private final JTextField lastNameField;
    private final JTextField firstNameField;
    private final JTextField sssField;
    private final JTextField philHealthField;
    private final JTextField tinField;
    private final JTextField pagibigField;
    private static final String CSV_FILE_PATH = "C:\\Users\\REMENTAS FAMILY\\IdeaProjects\\MotorPH Employee App\\src\\employee_data.csv";

    public MotorPHEmployeeAppGUI() {
        setTitle("MotorPH Employee App GUI");
        setSize(1200, 400); setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("MotorPH Employee App"); titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(titleLabel, BorderLayout.NORTH);

        // Initialize the Employee Database by reading from CSV file
        employeeDatabase = readEmployeeDataFromCSV(CSV_FILE_PATH);

        // Create a DefaultTableModel and JTable for employee records
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("Employee Number");
        tableModel.addColumn("Last Name");
        tableModel.addColumn("First Name");
        tableModel.addColumn("SSS No.");
        tableModel.addColumn("PhilHealth No.");
        tableModel.addColumn("TIN");
        tableModel.addColumn("Pagibig No.");

        for (Employee employee : employeeDatabase) {
            tableModel.addRow(new Object[]{ employee.getEmployeeNumber(), employee.getLastName(), employee.getFirstName(), employee.getSSSNumber(), employee.getPhilHealthNumber(), employee.getTinNumber(), employee.getPagibigNumber(),});
        }

        // Create JScrollPane with JTable for employee records
        employeeTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(employeeTable);
        add(scrollPane, BorderLayout.CENTER);

        lastNameField = new JTextField(10);
        firstNameField = new JTextField(10);
        sssField = new JTextField(10);
        philHealthField = new JTextField(10);
        tinField = new JTextField(10);
        pagibigField = new JTextField(10);

        // Add ListSelectionListener to update TextFields when row is selected
        ListSelectionModel selectionModel = employeeTable.getSelectionModel(); selectionModel.addListSelectionListener((ListSelectionEvent e) -> {
            int selectedRow = employeeTable.getSelectedRow();
            if (selectedRow != -1) {
                TableModel model = employeeTable.getModel(); lastNameField.setText(model.getValueAt(selectedRow, 1).toString()); firstNameField.setText(model.getValueAt(selectedRow, 2).toString()); sssField.setText(model.getValueAt(selectedRow, 3).toString()); philHealthField.setText(model.getValueAt(selectedRow, 4).toString()); tinField.setText(model.getValueAt(selectedRow, 5).toString()); pagibigField.setText(model.getValueAt(selectedRow, 6).toString());
            }
        });

        add(createUpdatePanel(), BorderLayout.EAST);
        add(createButtonPanel(), BorderLayout.WEST);
        add(createBottomPanel(), BorderLayout.SOUTH);

        setVisible(true);
    }

    private List<Employee> readEmployeeDataFromCSV(String filePath) {
        List<Employee> employees = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(filePath))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] data = line.split(",");

                int employeeNumber = Integer.parseInt(data[0]);
                String lastName = data[1];
                String firstName = data[2];
                String position = data[3];
                double basicSalary = Double.parseDouble(data[4]);
                double riceSubsidy = Double.parseDouble(data[5]);
                double phoneAllowance = Double.parseDouble(data[6]);
                double clothingAllowance = Double.parseDouble(data[7]);
                String sssNumber = data[8];
                String philHealthNumber = data[9];
                String pagIbigNumber = data[10];
                String tinNumber = data[11];

                employees.add(new Employee(employeeNumber, lastName, firstName, position, basicSalary, riceSubsidy, phoneAllowance, clothingAllowance, sssNumber, philHealthNumber, pagIbigNumber, tinNumber));
            }
        } catch (FileNotFoundException e) {
            System.err.println("CSV file not found." + e.getMessage());
        }
        return employees;
    }

    private JPanel createUpdatePanel() {
        JPanel updatePanel = new JPanel();
        updatePanel.setLayout(new GridLayout(0, 2));
        updatePanel.add(new JLabel("LastName:"));
        updatePanel.add(lastNameField);
        updatePanel.add(new JLabel("First Name:"));
        updatePanel.add(firstNameField);
        updatePanel.add(new JLabel("SSS No.:"));
        updatePanel.add(sssField);
        updatePanel.add(new JLabel("PhilHealth No.:"));
        updatePanel.add(philHealthField);
        updatePanel.add(new JLabel("TIN:"));
        updatePanel.add(tinField);
        updatePanel.add(new JLabel("Pagibig No.:"));
        updatePanel.add(pagibigField);
        return updatePanel;
    }

    private JButton createDeleteButton() {
        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(e -> {
            int selectedRow = employeeTable.getSelectedRow();
            if (selectedRow != -1) {
                DefaultTableModel model = (DefaultTableModel) employeeTable.getModel();
                int employeeNumber = (int) model.getValueAt(selectedRow, 0);

                // Remove the employee from the employeeDatabase list
                Employee employeeToDelete = findEmployeeByNumber(employeeNumber);
                if (employeeToDelete != null) { employeeDatabase.remove(employeeToDelete);
                }

                // Remove the row from the JTable
                model.removeRow(selectedRow);
            } else {
                JOptionPane.showMessageDialog(null, "Please select a row to delete.");
            }
        });
        return deleteButton;
    }

    private JButton createLeaveApplicationButton() {
        JButton leaveApplicationButton = new JButton("Leave Application");
        leaveApplicationButton.addActionListener(e -> {
            // Display a dialog for leave application form
            JTextField startDateField = new JTextField(10);
            JTextField endDateField = new JTextField(10);
            JTextField reasonField = new JTextField(20);

            JPanel leaveApplicationPanel = new JPanel();
            leaveApplicationPanel.setLayout(new GridLayout(0,2));
            leaveApplicationPanel.add(new JLabel("Start Date:"));
            leaveApplicationPanel.add(startDateField);
            leaveApplicationPanel.add(new JLabel("End Date:"));
            leaveApplicationPanel.add(endDateField);
            leaveApplicationPanel.add(new JLabel("Reason for Leave:"));
            leaveApplicationPanel.add(reasonField);

            int result = JOptionPane.showConfirmDialog(null, leaveApplicationPanel, "Leave Application Form", JOptionPane.OK_CANCEL_OPTION);

            if (result == JOptionPane.OK_OPTION) {
                String startDate = startDateField.getText();
                String endDate = endDateField.getText();
                String reason = reasonField.getText();

                // Process the leave application (e.g., save data, send notification, etc.)
                // Add leave application processing logic here
                System.out.println("Leave Application Submitted:");
                System.out.println("Start Date: " + startDate);
                System.out.println("End Date: " + endDate);
                System.out.println("Reason: " + reason);

                JOptionPane.showMessageDialog(null, "Leave Application Submitted:\nStartDate: " + startDate + "\nEnd Date: " + endDate + "\nReason: " + reason);
            }
        });
        return leaveApplicationButton;
    }

    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(0, 1));

        JButton leaveApplicationButton = createLeaveApplicationButton();
        JButton updateButton = createUpdateButton();
        JButton deleteButton = createDeleteButton();
        JButton refreshButton = createRefreshButton();
        JButton calculateSalaryButton = createCalculateSalaryButton();

        buttonPanel.add(leaveApplicationButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(refreshButton);
        buttonPanel.add(calculateSalaryButton);

        return buttonPanel;
    }

    private JButton createRefreshButton(){
        JButton refreshButton = new JButton("Refresh");
        refreshButton.addActionListener(e -> {
            // Clear the existing data in the JTable
            DefaultTableModel model = (DefaultTableModel) employeeTable.getModel();
            model.setRowCount(0);

            // Read the updated employee data from the CSV file
            employeeDatabase.clear(); employeeDatabase.addAll(readEmployeeDataFromCSV(CSV_FILE_PATH));

            // Update the JTable with the refreshed employee data
            for (Employee employee : employeeDatabase) {
                model.addRow(new Object[] {employee.getEmployeeNumber(), employee.getLastName(), employee.getFirstName(), employee.getSSSNumber(), employee.getPhilHealthNumber(), employee.getTinNumber(), employee.getPagibigNumber()});
            }
        });
        return refreshButton;
    }

    private JButton createUpdateButton() {
        JButton updateButton = new JButton("Update");
        updateButton.addActionListener(e -> {
            int selectedRow = employeeTable.getSelectedRow();
            if (selectedRow != -1) {
                DefaultTableModel model = (DefaultTableModel) employeeTable.getModel();
                int employeeNumber = (int) model.getValueAt(selectedRow, 0);

                Employee selectedEmployee = findEmployeeByNumber(employeeNumber);
                if (selectedEmployee != null) {
                    // Update the selected employee's information based on the data in the text fields
                    selectedEmployee.setLastName(lastNameField.getText()); selectedEmployee.setFirstName(firstNameField.getText()); selectedEmployee.setSSSNumber(sssField.getText()); selectedEmployee.setPhilHealthNumber(philHealthField.getText()); selectedEmployee.setTinNumber(tinField.getText()); selectedEmployee.setPagibigNumber(pagibigField.getText());

                    // Update the corresponding row in the JTable
                    model.setValueAt(selectedEmployee.getLastName(), selectedRow, 1); model.setValueAt(selectedEmployee.getFirstName(), selectedRow, 2); model.setValueAt(selectedEmployee.getSSSNumber(), selectedRow, 3); model.setValueAt(selectedEmployee.getPhilHealthNumber(), selectedRow, 4); model.setValueAt(selectedEmployee.getTinNumber(), selectedRow, 5); model.setValueAt(selectedEmployee.getPagibigNumber(), selectedRow, 6);
                }
            }
        });
        return updateButton;
    }

    private JButton createCalculateSalaryButton() {
        JButton calculateSalaryButton = new JButton("Calculate Salary");
        calculateSalaryButton.addActionListener(e -> {
            int selectedRow = employeeTable.getSelectedRow();
            if (selectedRow != -1) {
                int employeeNumber = (int) employeeTable.getValueAt(selectedRow, 0); openEmployeeDetailsFrame(employeeNumber);
            } else {
                JOptionPane.showMessageDialog(null, "Please select an employee from the table.");
            }
        });
        return calculateSalaryButton;
    }

    private JPanel createBottomPanel() {
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(0, 1));
        JButton viewEmployeeButton = new JButton("View Employee");
        viewEmployeeButton.addActionListener(e -> {
            int selectedRow = employeeTable.getSelectedRow();
            if (selectedRow != -1) {
                int employeeNumber = (int) employeeTable.getValueAt(selectedRow, 0); displayEmployeeDetails(employeeNumber);
            } else {
                JOptionPane.showMessageDialog(null, "Please select and employee from the table");
            }
        });

        bottomPanel.add(viewEmployeeButton);

        return bottomPanel;
    }

    private void displayEmployeeDetails(int employeeNumber) {
        Employee selectedEmployee = findEmployeeByNumber(employeeNumber);
        if (selectedEmployee != null) {
            JOptionPane.showMessageDialog(null, "Employee Details:\nEmployee Number: " + selectedEmployee.getEmployeeNumber() + "\nLast Name: " + selectedEmployee.getLastName() + "\nFirstName: " + selectedEmployee.getFirstName());
        } else {
            JOptionPane.showMessageDialog(null, "Employee not found in the database");
        }
    }

    private void openEmployeeDetailsFrame(int employeeNumber) {
        JFrame employeeDetailsFrame = new JFrame("Employee Details");
        employeeDetailsFrame.setSize(400, 200);
        employeeDetailsFrame.setLayout(new FlowLayout());

        JLabel monthLabel = new JLabel("Select Month");
        String[] months = {"January", "Febuary", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        JComboBox<String> monthComboBox = new JComboBox<>(months);
        JButton computeButton = new JButton("Compute");

        // Add action listener to the Compute button
        computeButton.addActionListener(e -> {
            String selectedMonth = (String) monthComboBox.getSelectedItem();
            Employee selectedEmployee = findEmployeeByNumber(employeeNumber);

            if (selectedEmployee != null) {
                double salary = calculateSalaryForMonth(selectedEmployee, selectedMonth); JOptionPane.showMessageDialog(null, "Salary details for Employee #" + selectedEmployee.getEmployeeNumber() + " in" + selectedMonth + ": PHP " + salary);
            } else {JOptionPane.showMessageDialog(null, "Employee not found in the database.");
            }
        });

        employeeDetailsFrame.add(monthLabel); employeeDetailsFrame.add(monthComboBox); employeeDetailsFrame.add(computeButton);

        employeeDetailsFrame.setVisible(true);
    }

    private Employee findEmployeeByNumber(int employeeNumber) {
        for (Employee employee : employeeDatabase) {
            if (employee != null && employee.getEmployeeNumber() == employeeNumber) {
                return employee;
            }
        }
        return null;
    }

    private double calculateSalaryForMonth(Employee employee, String month) {
        double basicSalary = employee.getBasicSalary();
        double riceSubsidy = employee.getRiceSubsidy();
        double phoneAllowance = employee.getPhoneAllowance();
        double clothingAllowance = employee.getClothingAllowance();

        double totalDeductions = riceSubsidy + phoneAllowance + clothingAllowance;
        double additionalDeductions = calculateAdditionalDeductions(month);

        return basicSalary - totalDeductions - additionalDeductions;
    }

    private double calculateAdditionalDeductions(String month) {
        double additionalDeductions = 0.0;

        switch (month.toLowerCase()) {
            case "december":
                additionalDeductions = 1000.0;
                break;
            case "january":
                additionalDeductions = 500.0;
                break;
        }

        return additionalDeductions;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MotorPHEmployeeAppGUI::new);
    }
}
