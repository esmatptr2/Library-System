package Model;
/*
 * Class person descripe any Person
 */

/**
 *
 * @author
 */
public class Person {

    private String firstName;
    private String lastName;

    /**
     * Default Constructor
     */
    public Person() {

    }

    /**
     * Constructor to initialize Person
     *
     * @param firstName
     * @param lastName
     */
    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
     * Return Person First name
     *
     * @return String
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Set Person First Name
     *
     * @param firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Return Person Last name
     *
     * @return String
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Set Person Last Name
     *
     * @param lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

}
