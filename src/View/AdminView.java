package View;

import Controller.DatabaseQueries;
import Model.Book;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
/*
 * Admin View Gui to choose add new book or remove book 
 */

/**
 *
 * @author
 */
public class AdminView extends JFrame implements ActionListener {

    // Buttons
    private JButton addBook;
    private JButton removeBook;
    private JButton back;
    // panel
    private final JPanel panel = new JPanel(new BorderLayout(10, 20));
    // create new Font 
    private final Font font = new Font(Font.SANS_SERIF, Font.BOLD, 15);

    /**
     * Constructor to initialize Frame
     */
    public AdminView() {
        super("Admin Section"); // set title of Window
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
        addBook = new JButton("Add new Book");
        removeBook = new JButton("Remove Book");
        back = new JButton("Back");
        // set buttons font  and colors
        addBook.setFont(font);
        removeBook.setFont(font);
        addBook.setBackground(Color.green);
        back.setFont(font);
        removeBook.setBackground(Color.red);
        back.setBackground(Color.blue);
        // add action listiners
        addBook.addActionListener(this);
        removeBook.addActionListener(this);
        back.addActionListener(this);
        // add buttons to panels
        panel.add(addBook, BorderLayout.NORTH);
        panel.add(removeBook, BorderLayout.CENTER);
        panel.add(back, BorderLayout.SOUTH);
        // create empty border to make space 
        panel.setBorder(BorderFactory.createTitledBorder(""));
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
        // when addBook button is pressed
        if (e.getSource() == addBook) {

            this.dispose(); // kill this window
            new AddingBookFrame().setVisible(true); // run adding new book frame 
        } else {
            // when removeBook button is pressed
            if (e.getSource() == removeBook) {
                String Isbn = JOptionPane.showInputDialog(null, "Enter ISBN of Book to Delete it ",
                        "Removing Book", JOptionPane.INFORMATION_MESSAGE);

                Book book = DatabaseQueries.searchBookByISBN(Isbn); // get book from database

                if (book == null) { // if not found in data base 
                    JOptionPane.showMessageDialog(null, "No book with this isbn", "Error not Found !", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                // if found delete it 
                DatabaseQueries.DeleteBook(Isbn);
                JOptionPane.showMessageDialog(null, "Book Deleted Succssefully ", "Deleted", JOptionPane.INFORMATION_MESSAGE);
            } else {
                // when back button is pressed
                if (e.getSource() == back) {
                    this.dispose(); // kill this window
                    new HomeFrame().setVisible(true); // show home Frame 
                }
            }
        }
    }
}
