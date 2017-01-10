package View;

import Controller.DatabaseQueries;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
/*
 * Home Gui when user can login to system or sign up if he not a member 
 */

/**
 *
 * @author
 */
public class HomeFrame extends JFrame implements ActionListener {

    // labels  
    private JLabel welcome;
    private JLabel signUpLable;
    //buttons
    private JButton login;
    private JButton signUp;
    //panels
    private JPanel welcomePanel;
    private JPanel loginPanel;
    private JPanel signUpPanel;

    /**
     * create Database when program start
     */
    static {
        new DatabaseQueries();
    }

    private final Container container = getContentPane();

    /**
     * Constructor to initialize Frame
     */
    public HomeFrame() {
        super("Home"); // set frame title
        setSize(400, 200);
        setLayout(new BorderLayout()); // set layout as borderLayout (North-South-Center-Right-Left)
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // make default operation to close window
        addcomponents();
    }

    /**
     *
     */
    private void addcomponents() {

        //create welcome section
        welcome = new JLabel("Welcome To Library System ");
        welcome.setSize(20, 160);
        welcome.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
        welcome.setForeground(new Color(139, 34, 82));
        welcomePanel = new JPanel(new FlowLayout());
        welcomePanel.setBorder(BorderFactory.createEmptyBorder(3, 20, 20, 20));

        // create login section
        login = new JButton("Login");
        login.addActionListener(this);
        login.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 12));
        // login.setBackground(Color.BLUE);

        loginPanel = new JPanel(new FlowLayout());

        //create signUp Section 
        signUpLable = new JLabel(" OR SIGN UP NOW  ");
        signUp = new JButton("Sign Up");
        signUp.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 13));
        signUpLable.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 13));
        signUp.addActionListener(this);

        signUp.setBackground(Color.GREEN);

        signUpPanel = new JPanel(new GridLayout(2, 1, 5, 0));
        signUpPanel.setBorder(BorderFactory.createEmptyBorder(0, 130, 10, 130));

        //add welcome label to welcome pannel
        welcomePanel.add(welcome);
        // add welcom pannel to north of Frame
        container.add(welcomePanel, BorderLayout.NORTH);
        //add login button to login pannel 
        loginPanel.add(login);
        //add login panel to center of Frame 
        container.add(loginPanel, BorderLayout.CENTER);
        //add signup lable and button to signup panel
        signUpPanel.add(signUpLable);
        signUpPanel.add(signUp);
        // add Pannel to south of frame
        container.add(signUpPanel, BorderLayout.SOUTH);

    }

    /**
     * Start do actions when buttons Pressed
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == login) {
            //kill this window
            this.dispose();
            // show Login window
            new loginFrame().setVisible(true);

        } else {
            if (e.getSource() == signUp) {
                //kill this Window
                this.dispose();
                // show Registration Window
                new RegistrationFrame().setVisible(true);
            }
        }
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            // show message with Exception
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        // start Programe with Home Window
        new HomeFrame().setVisible(true);
    }
}
