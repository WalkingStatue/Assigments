/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Infam
 */
import javax.swing.*;
import java.awt.*;

public class StudentInfoDisplay extends JFrame {

    public JPanel panel;

    public StudentInfoDisplay() {
        // Initialize the JFrame
        super("Student Information");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // ScrollPane in case information exceeds window size
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        add(scrollPane, BorderLayout.CENTER);

        // Display the student information
        displayStudentInfo();
    }

    private void displayStudentInfo() {
        Student student = new Student();
        // Setting student details (this could be dynamic or from user input)
        student.setName("Dhruv Saija");
        student.setDateOfBirth("08-08-2003");
        student.setEmailAddress("saijadhruv8803@gmail.com");
        student.setCity("Ahmedabad");
        student.setRollNo(3159);
        student.setCollegeName("KSSBMIT");

        // Displaying details in text area
        JLabel headingLabel = new JLabel("Student Information");
        headingLabel.setFont(new Font("Arial", Font.BOLD, 20));
        panel.add(headingLabel);

        panel.add(new JLabel("Name: " + student.getName()));
        panel.add(new JLabel("Date of Birth: " + student.getDateOfBirth()));
        panel.add(new JLabel("Email Address: " + student.getEmailAddress()));
        panel.add(new JLabel("City: " + student.getCity()));
        panel.add(new JLabel("Roll No: " + student.getRollNo()));
        panel.add(new JLabel("College Name: " + student.getCollegeName()));
    }

    public static void main(String[] args) {
        StudentInfoDisplay frame = new StudentInfoDisplay();
        frame.setVisible(true);
    }
}
