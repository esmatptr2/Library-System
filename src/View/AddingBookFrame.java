package View;

import Controller.DatabaseQueries;
import Model.Book;
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
import javax.swing.JTextField;
/*
 * Adding new Book Gui Allow to Admin to Adding new Book to System 
 */

/**
 *
 * @author
 */
public class AddingBookFrame extends JFrame implements ActionListener {

    // labels
    private JLabel title;
    private JLabel author;
    private JLabel noOfCopies;
    private JLabel ISBN;
    // TextFields
    private JTextField titleText;
    private JTextField authorText;
    private JTextField noOfCopiesText;
    private JTextField ISBNTEXT;
    //Buttons
    private JButton submit;
    private JButton back;
    //Panels
    private JPanel informations;
    private JPanel submitPanel;
    // contanier to hold panels
    private final Container container = getContentPane();

    /**
     * Constructor to initialize Frame
     */
    public AddingBookFrame() {
        super("Adding Book Information"); // set title of Window
        setSize(500, 300);
        setLayout(new BorderLayout(10, 10)); // set layout as borderLayout (North-South-Center-Right-Left)
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // make default operation to close window
        addcomponents();
    }

    /**
     * Create and add components to window
     */
    private void addcomponents() {
        //create new Font
        Font font = new Font(Font.SANS_SERIF, Font.BOLD, 14);
        //create lables 
        title = new JLabel("Title");
        author = new JLabel("Author Name");
        noOfCopies = new JLabel("Number Of Copies");
        ISBN = new JLabel("ISBN");
        // set labels Font
        title.setFont(font);
        author.setFont(font);
        noOfCopies.setFont(font);
        ISBN.setFont(font);
        //Create Text Fields 
        titleText = new JTextField();
        authorText = new JTextField();
        noOfCopiesText = new JTextField();
        ISBNTEXT = new JTextField();

        //create submit Button
        submit = new JButton("Submit");
        submit.setFont(new Font(Font.SERIF, Font.BOLD, 12));
        submit.setBackground(Color.GREEN);
        submit.addActionListener(this);
        // create back button
        back = new JButton("Back");
        back.setFont(new Font(Font.SERIF, Font.BOLD, 12));
        back.setBackground(Color.blue);
        back.addActionListener(this);

        //create Pannels 
        informations = new JPanel(new GridLayout(5, 2, 5, 8));
        informations.setBorder(BorderFactory.createTitledBorder("Fill Information"));
        submitPanel = new JPanel(new FlowLayout());

        //add components to pannels
        informations.add(ISBN);
        informations.add(ISBNTEXT);
        informations.add(title);
        informations.add(titleText);
        informations.add(author);
        informations.add(authorText);
        informations.add(noOfCopies);
        informations.add(noOfCopiesText);

        submitPanel.add(submit);
        submitPanel.add(back);
        // add panels to Frame
        container.add(informations, BorderLayout.NORTH); // add information panel to north of window
        container.add(submitPanel, BorderLayout.CENTER); // add submit panel to Center of window

    }

    /**
     * Start do actions when buttons Pressed
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submit) {
            // when submit button is pressed
            try {
                if (ISBNTEXT.getText().isEmpty()) { // if isbn text is empty show message and force user to enter value
                    JOptionPane.showMessageDialog(this, "ISBN Must be not empty !");
                    return;
                } else {  // if title text is empty show message and force user to enter value
                    if (titleText.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(this, "Title Must be not empty !");
                        return;
                    } else { // if Author name text is empty show message and force user to enter value
                        if (authorText.getText().isEmpty()) {
                            JOptionPane.showMessageDialog(this, "Author name Must be not empty !");
                            return;
                        } else { // if noOfCopies text is empty show message and force user to enter value
                            if (noOfCopiesText.getText().isEmpty()) {
                                JOptionPane.showMessageDialog(this, "no OF Copies Must be not empty !");
                                return;
                            }
                        }
                    }
                }
                int noOfCopie = Integer.parseInt(noOfCopiesText.getText());
                // check if this book is already exist in the system
                if (DatabaseQueries.searchBookByISBN(ISBNTEXT.getText()) != null) {
                    JOptionPane.showMessageDialog(this, "this ISBN is already Exist !");
                    return;
                }
                // if not so add it to the database
                Book book = new Book(ISBNTEXT.getText(), titleText.getText(), authorText.getText(), noOfCopie);
                DatabaseQueries.InsertBook(book);

            } catch (NumberFormatException nx) { // if not valid input enterd to no- of copies text show alert
                JOptionPane.showMessageDialog(this, "Enter Valid number Of Copies !");
                return;
            } catch (Exception sx) { // handle any other exception
                JOptionPane.showMessageDialog(this, sx.getMessage());
                return;
            }

            //reset all fields
            ISBNTEXT.setText("");
            titleText.setText("");
            authorText.setText("");
            noOfCopiesText.setText("");
            // if added succefully show message
            JOptionPane.showMessageDialog(this, "Book Added Succssefully !");
            this.dispose(); // kil this window
            new AdminView().setVisible(true); // back to pervious window

        } else {
            if (e.getSource() == back) {
                // if back button is pressed
                this.dispose(); //kill this window
                new View.AdminView().setVisible(true); //back to pervious window
            }
        }
    }

}
