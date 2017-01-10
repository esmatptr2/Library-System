package View;

import Controller.DatabaseQueries;
import Model.Student;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
/*
 * Registration Gui to allow to new member to join database
 */

/**
 *
 * @author
 */
public class RegistrationFrame extends JFrame implements ActionListener {

    // Labels
    private JLabel firstName;
    private JLabel lastName;
    private JLabel userName;
    private JLabel password;
    //TextFields
    private JTextField fNameText;
    private JTextField lNameText;
    private JTextField uNameText;
    private JPasswordField pText;
    //Panels
    private JPanel informations;
    private JPanel submitPanel;
    //Buttons
    private JButton submit;
    private JButton back;

    private final Container container = getContentPane();

    /**
     * Constructor to initialize Frame
     */
    public RegistrationFrame() {
        super("Regestration Form"); // set title of Window
        setSize(500, 300);
        setLayout(new BorderLayout(10, 10)); // set layout as borderLayout (North-South-Center-Right-Left)
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// make default operation to close window
        //Start add components method
        addcomponents();
    }

    /**
     * Create and add components to window
     */
    private void addcomponents() {

        Font font = new Font(Font.SANS_SERIF, Font.BOLD, 14);
        //create lables 
        firstName = new JLabel("First Name");
        lastName = new JLabel("Last Name");
        userName = new JLabel("User Name");
        password = new JLabel("Password");
        // set labels font
        firstName.setFont(font);
        lastName.setFont(font);
        userName.setFont(font);
        password.setFont(font);

        //Create Text Fields 
        fNameText = new JTextField();
        lNameText = new JTextField();
        uNameText = new JTextField();
        pText = new JPasswordField();

        //create submit and back Buttons
        submit = new JButton("Submit");
        back = new JButton("Back");
        submit.setFont(new Font(Font.SERIF, Font.BOLD, 12));
        submit.setBackground(Color.green);
        back.setFont(new Font(Font.SERIF, Font.BOLD, 12));
        back.setBackground(Color.blue);
        submit.addActionListener(this);
        back.addActionListener(this);

        //create Pannels 
        informations = new JPanel(new GridLayout(4, 2, 5, 8));
        informations.setBorder(BorderFactory.createTitledBorder("Fill Information"));
        submitPanel = new JPanel(new FlowLayout());

        //add components to panels
        informations.add(firstName);
        informations.add(fNameText);
        informations.add(lastName);
        informations.add(lNameText);
        informations.add(userName);
        informations.add(uNameText);
        informations.add(password);
        informations.add(pText);
        submitPanel.add(submit);
        submitPanel.add(back);
        //add panels to window
        container.add(informations, BorderLayout.NORTH);
        container.add(submitPanel, BorderLayout.CENTER);

    }

    /**
     * Start do actions when buttons Pressed
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        // when submit button is pressed

        if (e.getSource() == submit) {
            // when submit button is pressed

            if (fNameText.getText().isEmpty()) { // if FirstName text is empty show message and force user to enter value
                JOptionPane.showMessageDialog(this, "First name Must be not empty !");
                return;
            } else {
                if (lNameText.getText().isEmpty()) { // if Last name text is empty show message and force user to enter value
                    JOptionPane.showMessageDialog(this, "Last name Must be not empty !");
                    return;
                } else {
                    if (uNameText.getText().isEmpty()) { // if User Name text is empty show message and force user to enter value
                        JOptionPane.showMessageDialog(this, "Username Must be not empty !");
                        return;
                    } else {
                        if (pText.getText().isEmpty()) { // if Password text is empty show message and force user to enter value
                            JOptionPane.showMessageDialog(this, "Password Must be not empty !");
                            return;
                        }
                    }
                }
            }

            Student student = new Student(uNameText.getText(), pText.getText(), fNameText.getText(), lNameText.getText());
            // check if user name is already exist in database
            ArrayList<Student> students = DatabaseQueries.selectAllStudents();
            for (Student student1 : students) {
                if (student1.getUserName().equals(uNameText.getText())) {
                    JOptionPane.showMessageDialog(this, "This user name is already Exist !");
                    return;
                }
            }
            //if not exist insert new member to database
            DatabaseQueries.InsertStudent(student);

            //reset all fields
            fNameText.setText("");
            lNameText.setText("");
            pText.setText("");
            uNameText.setText("");

            JOptionPane.showMessageDialog(this, "Student Added Succssefully !");
            this.dispose(); // kill window
            new HomeFrame().setVisible(true); // back to home window

        } else {

            if (e.getSource() == back) {
                // if back button is pressed
                this.dispose(); //kill this window
                new HomeFrame().setVisible(true); //back to pervious window
            }
        }
    }

}
