package Model;
/*
 *  Student Class Describe Students
 */

/**
 *
 * @author
 */
public class Student extends Person {

    private String userName;
    private String password;

    /**
     * Constructor to initialize Student
     *
     * @param userName
     * @param password
     * @param firstName
     * @param lastName
     */
    public Student(String userName, String password, String firstName, String lastName) {
        super(firstName, lastName);
        this.userName = userName;
        this.password = password;
    }

    /**
     * Return Student User Name
     *
     * @return String
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Set Student User Name
     *
     * @param userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Get Password
     *
     * @return String
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set Password
     *
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

}
