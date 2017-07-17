/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package multibank.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author client
 */
public class MyConnection {
    public static Connection getConnection() throws SQLException, ClassNotFoundException{
        Class.forName("com.mysql.jdbc.Driver");
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/multibank", "root", "admin");
    }
}
