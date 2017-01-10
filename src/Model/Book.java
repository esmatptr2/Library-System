package Model;
/*
 *  Class Book to define Books
 */

/**
 *
 * @author
 */
public class Book {

    private String isbn;
    private String title;
    private String author;
    private int noOfCopies;

    /**
     * Constructor to initialize Book
     *
     * @param isbn
     * @param title
     * @param author
     * @param noOfCopies
     */
    public Book(String isbn, String title, String author, int noOfCopies) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.noOfCopies = noOfCopies;
    }

    /**
     * Get title of book
     *
     * @return String
     */
    public String getTitle() {
        return title;
    }

    /**
     * Set title of book
     *
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Get author name of book
     *
     * @return String
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Set Author name of Book
     *
     * @param author
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * Get no of copies of book
     *
     * @return String
     */
    public int getNoOfCopies() {
        return noOfCopies;
    }

    /**
     * Set no of copies of book
     *
     * @param noOfCopies
     */
    public void setNoOfCopies(int noOfCopies) {
        this.noOfCopies = noOfCopies;
    }

    /**
     * Decrease no of copies of book by 1
     */
    public void decreaseNoOfCopies() {
        this.noOfCopies--;
    }

    /**
     * Return Book ISBN
     *
     * @return String
     */
    public String getIsbn() {
        return isbn;
    }

    /**
     * Set Isbn
     *
     * @param isbn
     */
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    /**
     * Print All information about Book Object
     *
     * @return String
     */
    @Override
    public String toString() {
        return "Book Details \n ------------------------------- \n ISBN : " + this.isbn + "\n Title : " + this.title
                + "\n Author : " + this.author + "\n Number Of Copies : " + this.noOfCopies;
    }

}
