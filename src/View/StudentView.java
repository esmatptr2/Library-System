package View;

import Controller.DatabaseQueries;
import Model.Book;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
/*
 * Student View Gui to choose Explore book or PickUp book 
 */

/**
 *
 * @author
 */
public class StudentView extends JFrame implements ActionListener {

    //Buttons
    private JButton Explore;
    private JButton PickUp;
    private JButton Exit;
    // panel
    private final JPanel panel = new JPanel(new GridLayout(3, 1, 4, 10));
    // creat new Font
    private final Font font = new Font(Font.SANS_SERIF, Font.BOLD, 15);

    /**
     * Constructor to initialize Frame
     */
    public StudentView() {
        super("Student Section"); // set title of Window
        setSize(400, 200);
        setLayout(new BorderLayout()); // set layout as borderLayout (North-South-Center-Right-Left)
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // make default operation to close window
        //Start add components method
        addcomponents();
    }

    /**
     * Create and add components to window
     */
    private void addcomponents() {

        //Create Buttons 
        Explore = new JButton("Explore");
        PickUp = new JButton("PICK UP");
        Exit = new JButton("Exit");
        // set buttons font and colors
        Explore.setFont(font);
        Explore.setBackground(Color.yellow);
        PickUp.setFont(font);
        PickUp.setBackground(Color.green);
        Exit.setFont(font);
        Exit.setBackground(Color.red);
        // add listiners
        Explore.addActionListener(this);
        PickUp.addActionListener(this);
        Exit.addActionListener(this);
        // add buttons to panel
        panel.add(Explore);
        panel.add(PickUp);
        panel.add(Exit);
        // create empty border to make space 
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        // add panel to window 
        add(panel);
    }

    /**
     * Start do actions when buttons Pressed
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == Explore) {
            // when Explore button is pressed
            try {
                // choose explore method 
                int choice = Integer.parseInt(JOptionPane.showInputDialog(this, " To find Book by ISBN enter 1 \n To find Book by title enter 2"));
                //if choice not correct 
                if (choice != 1 && choice != 2) {
                    JOptionPane.showMessageDialog(this, "not valid input !");
                    return;
                }
                if (choice == 1) { // if choose to search by ISBN 
                    String ISBN = JOptionPane.showInputDialog(this, "Enter Book ISBN");
                    // get book if exist
                    Book book = DatabaseQueries.searchBookByISBN(ISBN);
                    //if not exist show alert message
                    if (book == null) {
                        JOptionPane.showMessageDialog(this, "NO Book with this ISBN ", "Not Exist!", JOptionPane.INFORMATION_MESSAGE);
                        return;
                    }
                    // show information of book 
                    JOptionPane.showMessageDialog(this, book.toString(), "Book Details", JOptionPane.INFORMATION_MESSAGE);
                } else { // if choose to search by title
                    String title = JOptionPane.showInputDialog(this, "Enter Book title");
                    // get book if exist
                    Book book = DatabaseQueries.searchBookByTitle(title);
                    //if not exist show alert message
                    if (book == null) {
                        JOptionPane.showMessageDialog(this, "NO Book with this title ", "Not Exist!", JOptionPane.INFORMATION_MESSAGE);
                        return;
                    }
                    // show information of book 
                    JOptionPane.showMessageDialog(this, book.toString(), "Book Details", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "not valid input !");

            }

        } else {
            if (e.getSource() == PickUp) {
                // when PickUp button is pressed
                try {
                    // choose explore method 
                    int choice = Integer.parseInt(JOptionPane.showInputDialog(this, " To Pick UP Book by ISBN enter 1 \n To Pick UP Book by title enter 2"));
                    //if choice not correct 
                    if (choice != 1 && choice != 2) {
                        JOptionPane.showMessageDialog(this, "not valid input !");
                        return;
                    }
                    if (choice == 1) { // if choose to search by ISBN 
                        String ISBN = JOptionPane.showInputDialog(this, "Enter Book ISBN");
                        // get book if exist
                        Book book = DatabaseQueries.searchBookByISBN(ISBN);
                        //if not exist show alert message
                        if (book == null) {
                            JOptionPane.showMessageDialog(this, "NO Book with this ISBN ", "Not Exist!", JOptionPane.INFORMATION_MESSAGE);
                            return;
                        }
                        //if there is available copies of book
                        if (book.getNoOfCopies() > 0) {
                            // show information of book 
                            JOptionPane.showMessageDialog(this, book.toString(), "Book Details", JOptionPane.INFORMATION_MESSAGE);
                            //decrase number of copies by 1
                            book.decreaseNoOfCopies();
                            //update number of copies of this book in database
                            DatabaseQueries.UpdateBook(book);

                            JOptionPane.showMessageDialog(this, "Book Picked UP succefully!");

                        } else {
                            JOptionPane.showMessageDialog(this, "No available Copies Of this Book !", "No more Copies", JOptionPane.ERROR_MESSAGE);
                        }

                    } else { // if choose to search by title
                        String title = JOptionPane.showInputDialog(this, "Enter Book title");
                        // get book if exist
                        Book book = DatabaseQueries.searchBookByTitle(title);
                        //if not exist show alert message
                        if (book == null) {
                            JOptionPane.showMessageDialog(this, "NO Book with this title ", "Not Exist!", JOptionPane.INFORMATION_MESSAGE);
                            return;
                        }
                        // show information of book 
                        JOptionPane.showMessageDialog(this, book.toString(), "Book Details", JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "not valid input !");

                }

            } else {
                // when Exit button is pressed
                if (e.getSource() == Exit) {
                    this.dispose(); // kill this window
                    new HomeFrame().setVisible(true); // back to pervious window
                }
            }
        }
    }
}
