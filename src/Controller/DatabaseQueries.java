package Controller;

import Model.Student;
import Model.Book;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author
 */
public class DatabaseQueries extends Database {

    static PreparedStatement pst = null;
    static Statement st = null;
    static ResultSet rs = null;
    static Database db;

    /**
     * insert book to database
     *
     * @param book Book
     * @return true if inserted successfully, otherwise false.
     */
    public static boolean InsertBook(Book book) {
        try {
            String query = "INSERT INTO LIBRARY.BOOK VALUES(?,?,?,?);";
            pst = db.getConnection().prepareStatement(query);
            pst.setString(1, book.getIsbn());
            pst.setString(2, book.getTitle());
            pst.setString(3, book.getAuthor());
            pst.setInt(4, book.getNoOfCopies());

            pst.executeUpdate();
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        return false;
    }

    /**
     * delete book from database using ISBN
     *
     * @param ISBN
     * @return true if book deleted successfully, otherwise false.
     */
    public static boolean DeleteBook(String ISBN) {
        try {
            String query = "DELETE FROM LIBRARY.BOOK WHERE ISBN='" + ISBN + "'";
            st = db.getConnection().createStatement();
            st.executeUpdate(query);
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No Such Book exist");
        }
        return false;
    }

    /**
     * Insert new Student member to System
     *
     * @param student
     * @return with true if added successfully false otherwise
     */
    public static boolean InsertStudent(Student student) {
        try {
            String query = "INSERT INTO LIBRARY.Student VALUES(?,?,?,?);";

            pst = db.getConnection().prepareStatement(query);
            pst.setString(1, student.getFirstName());
            pst.setString(2, student.getLastName());
            pst.setString(3, student.getUserName());
            pst.setString(4, student.getPassword());

            pst.executeUpdate();
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No Such Student exist");
        }
        return false;
    }

    /**
     * select all students
     *
     * @return ArrayList with all students
     */
    public static ArrayList<Student> selectAllStudents() {
        try {
            String query = "SELECT * FROM LIBRARY.STUDENT";
            ArrayList<Student> students = new ArrayList<>();
            st = db.getConnection().createStatement();
            rs = st.executeQuery(query);
            while (rs.next()) {
                String firstname = rs.getString(1);
                String lastname = rs.getString(2);
                String username = rs.getString(3);
                String password = rs.getString(4);

                Student student = new Student(username, password, firstname, lastname);
                students.add(student);
            }
            return students;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * select all books
     *
     * @return ArrayList with all Books
     */
    public static ArrayList<Book> selectAllBooks() {
        try {
            String query = "SELECT * FROM LIBRARY.BOOK";
            ArrayList<Book> books = new ArrayList<>();
            st = db.getConnection().createStatement();
            rs = st.executeQuery(query);
            while (rs.next()) {
                String isbn = rs.getString(1);
                String title = rs.getString(2);
                String author = rs.getString(3);
                int copies = rs.getInt(4);
                String avilable = rs.getString(5);

                Book book = new Book(isbn, title, author, copies);
                books.add(book);
            }
            return books;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * select books using ISBN
     *
     * @param ISBN
     * @return Book with ISBN wanted or null if not found
     */
    public static Book searchBookByISBN(String ISBN) {
        try {
            String query = "SELECT title,author, copies "
                    + " FROM LIBRARY.BOOK"
                    + " WHERE ISBN='" + ISBN + "'";
            st = db.getConnection().createStatement();
            rs = st.executeQuery(query);
            if (rs.next()) {

                String title = rs.getString(1);
                String author = rs.getString(2);
                int copies = rs.getInt(3);

                Book book = new Book(ISBN, title, author, copies);
                return book;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * select books using title
     *
     * @param ISBN
     * @return Book with ISBN wanted or null if not found
     */
    public static Book searchBookByTitle(String title) {
        try {
            String query = "SELECT ISBN,author, copies "
                    + " FROM LIBRARY.BOOK"
                    + " WHERE title='" + title + "'";
            st = db.getConnection().createStatement();
            rs = st.executeQuery(query);
            if (rs.next()) {

                String ISBN = rs.getString(1);
                String author = rs.getString(2);
                int copies = rs.getInt(3);

                Book book = new Book(ISBN, title, author, copies);
                return book;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * update book using ISBN
     *
     * @param book Book
     * @return true if book updated successfully, otherwise false
     */
    public static boolean UpdateBook(Book book) {
        try {
            String query = "UPDATE LIBRARY.BOOK SET "
                    + "title = ?,"
                    + "author = ?,"
                    + "copies = ?"
                    + " WHERE ISBN = ? ";
            pst = db.getConnection().prepareStatement(query);
            pst.setString(1, book.getTitle());
            pst.setString(2, book.getAuthor());
            pst.setInt(3, book.getNoOfCopies());
            pst.setString(4, book.getIsbn());
            pst.executeUpdate();
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            ex.printStackTrace();
        }
        return false;
    }

}
