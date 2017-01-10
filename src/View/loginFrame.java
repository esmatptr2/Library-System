package View;

import Controller.DatabaseQueries;
import Model.Student;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
/*
 * Login Gui to Login as a member or Admin 
 */

/**
 *
 * @author
 */
public class loginFrame extends JFrame implements ActionListener {

    // labels
    private final JLabel lablname = new JLabel("User Name");
    private final JLabel lablpass = new JLabel("Password");
    //TExtFields
    private final JTextField textname = new JTextField();
    private final JPasswordField pass = new JPasswordField();
    //Buttons
    private final JButton okb = new JButton("login");
    private final JButton cancelb = new JButton("Cancel");
    //Panel
    private final JPanel pannel = new JPanel();
    // Create new Font
    private final Font font = new Font(Font.SANS_SERIF, Font.BOLD, 12);

    /**
     * Constructor to initialize Frame
     */
    public loginFrame() {
        super("Login Box"); // set title of Window
        setSize(310, 155);
        setLayout(new BorderLayout()); // set layout as borderLayout (North-South-Center-Right-Left)
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // make default operation to close window
        //Start add components method
        addcomp();
    }

    /**
     * Create and add components to window
     */
    private void addcomp() {

        pannel.setLayout(null);

        // set labels Position and Font 
        lablname.setBounds(13, 18, 180, 30);
        lablname.setFont(font);
        lablpass.setBounds(13, 58, 180, 30);
        lablpass.setFont(font);
        //add labels to panel
        pannel.add(lablname);
        pannel.add(lablpass);

        // set textFields Position
        textname.setBounds(100, 20, 180, 30);
        pass.setBounds(100, 55, 180, 30);
        //add them to panel
        pannel.add(textname);
        pannel.add(pass);

        //set Buttons Position and Font 
        cancelb.setBounds(30, 90, 100, 30);
        cancelb.setBackground(Color.RED);
        okb.setBounds(150, 90, 100, 30);
        okb.setBackground(Color.GREEN);
        cancelb.addActionListener(this);
        okb.addActionListener(this);
        // Buttons to Panel
        pannel.add(okb);
        pannel.add(cancelb);
        //add panel to Center of the Window
        add(pannel, BorderLayout.CENTER);
    }

    /**
     * Start do actions when buttons Pressed
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == okb) {

            //check if he is admin 
            if (textname.getText().equalsIgnoreCase("admin") && pass.getText().equalsIgnoreCase("admin")) {
                this.dispose();
                new AdminView().setVisible(true);
            } else {
                ArrayList<Student> students = DatabaseQueries.selectAllStudents(); // get all students from database
                String username = textname.getText(); // get username entered
                String password = pass.getText(); // get password entered
                //check if username or password are empty
                if (username.trim().isEmpty() || password.trim().isEmpty()) { // if empty
                    JOptionPane.showMessageDialog(null, "Username or Password is Empty"); // show message
                    return; // end method
                }

                for (Student student : students) {
                    if (student.getUserName().equals(username) && student.getPassword().equals(password)) {
                        JOptionPane.showMessageDialog(this, "Login Succesfull"); // then show meesage
                        new StudentView().setVisible(true); // open the home page
                        this.setVisible(false); //set this frame visible
                        this.dispose(); // close this window
                        return;
                    }
                }
                //if no match found then show message for not found
                JOptionPane.showMessageDialog(this, "Username and Password either not found.");
            }

        } else {
            if (e.getSource() == cancelb) {
                this.dispose(); // kill this window
                new HomeFrame().setVisible(true); // back to Home Window
            }
        }
    }
}
