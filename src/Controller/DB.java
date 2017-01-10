package Controller;

public interface DB {

    /**
     * Main database constants for connection - for ease of access and change
     */
    public static final String USER_NAME = "root";
    public static final String PASSWORD = "abcdef12345";
    public static final String URL = "jdbc:mysql://localhost/";
    public static final String DB_NAME = "LIBRARY";
    public static final String DRIVER = "com.mysql.jdbc.Driver";
}
