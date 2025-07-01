import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class StudentInformationSystem {

    public static void main(String[] args) {
        new StudentInformationSystem().createGUI();
    }

    // Table model to store student records
    DefaultTableModel tableModel;

    // GUI components
    JTextField nameField, rollField, courseField;
    JTable studentTable;

    public void createGUI() {
        // Frame setup
        JFrame frame = new JFrame("Student Information System");
        frame.setSize(700, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Panel for input fields
        JPanel inputPanel = new JPanel(new GridLayout(2, 4, 10, 10));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Student Details"));

        nameField = new JTextField();
        rollField = new JTextField();
        courseField = new JTextField();

        inputPanel.add(new JLabel("Name:"));
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("Roll No:"));
        inputPanel.add(rollField);
        inputPanel.add(new JLabel("Course:"));
        inputPanel.add(courseField);

        // Buttons
        JButton addButton = new JButton("Add");
        JButton updateButton = new JButton("Update");
        JButton deleteButton = new JButton("Delete");

        inputPanel.add(addButton);
        inputPanel.add(updateButton);
        inputPanel.add(deleteButton);

        // Table for displaying student data
        String[] columns = {"Name", "Roll No", "Course"};
        tableModel = new DefaultTableModel(columns, 0);
        studentTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(studentTable);

        frame.add(inputPanel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Add button action
        addButton.addActionListener(e -> {
            String name = nameField.getText();
            String roll = rollField.getText();
            String course = courseField.getText();

            if (!name.isEmpty() && !roll.isEmpty() && !course.isEmpty()) {
                tableModel.addRow(new Object[]{name, roll, course});
                clearFields();
            } else {
                JOptionPane.showMessageDialog(frame, "Please fill all fields");
            }
        });

        // Update button action
        updateButton.addActionListener(e -> {
            int selectedRow = studentTable.getSelectedRow();
            if (selectedRow != -1) {
                tableModel.setValueAt(nameField.getText(), selectedRow, 0);
                tableModel.setValueAt(rollField.getText(), selectedRow, 1);
                tableModel.setValueAt(courseField.getText(), selectedRow, 2);
                clearFields();
            } else {
                JOptionPane.showMessageDialog(frame, "Select a row to update");
            }
        });

        // Delete button action
        deleteButton.addActionListener(e -> {
            int selectedRow = studentTable.getSelectedRow();
            if (selectedRow != -1) {
                tableModel.removeRow(selectedRow);
                clearFields();
            } else {
                JOptionPane.showMessageDialog(frame, "Select a row to delete");
            }
        });

        // Row selection to auto-fill fields
        studentTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = studentTable.getSelectedRow();
                nameField.setText(tableModel.getValueAt(row, 0).toString());
                rollField.setText(tableModel.getValueAt(row, 1).toString());
                courseField.setText(tableModel.getValueAt(row, 2).toString());
            }
        });

        // Show GUI
        frame.setVisible(true);
    }

    // Utility method to clear input fields
    private void clearFields() {
        nameField.setText("");
        rollField.setText("");
        courseField.setText("");
    }
}
