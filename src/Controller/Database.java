package Controller;

import static Controller.DB.DRIVER;
import static Controller.DB.PASSWORD;
import static Controller.DB.URL;
import static Controller.DB.USER_NAME;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class Database implements DB {

    private Statement st;
    private static Connection conn = null;

    public Database() {
        try {
            //load mysql driver
            Class.forName(DRIVER);
            //connect to mysql database with username and password
            conn = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
            st = conn.createStatement();
            createDataBase(); // create database
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Driver Not Found"); // show error if ar not added to project
            System.exit(0);
        } catch (SQLException ex) { // if database exist
            ex.printStackTrace();
        }
    }

    public static Connection getConnection() {
        return conn;
    }

    /**
     * This method is executed when user run program for first time only to
     * create database and tables
     */
    private void createDataBase() {
        try {
            //if no database named library then create it
            st.execute("CREATE DATABASE IF NOT EXISTS LIBRARY;");

            //create the table library
            st.execute("CREATE TABLE IF NOT EXISTS LIBRARY.STUDENT ("
                    + "firstname VARCHAR(50)  NOT NULL," + "lastname VARCHAR(50)  NOT NULL,"
                    + "username VARCHAR(50) PRIMARY KEY NOT NULL,"
                    + "password VARCHAR(50) NOT NULL);"
            );

            //create table for books if not exist
            st.execute(
                    "CREATE TABLE IF NOT EXISTS LIBRARY.BOOK ("
                    + "ISBN VARCHAR(50) PRIMARY KEY NOT NULL,"
                    + "title VARCHAR(50)  NOT NULL,"
                    + "author VARCHAR(50) NOT NULL,"
                    + "copies INTEGER NOT NULL);");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
