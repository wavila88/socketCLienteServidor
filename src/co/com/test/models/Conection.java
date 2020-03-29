package co.com.test.models;

import com.mysql.jdbc.Connection;

import java.sql.DriverManager;
import java.sql.SQLException;

public class Conection {

	public static Connection getConnection()
	    {
		 Connection connection = null;
		   String url = "jdbc:mysql://localhost:3306/socketDB";
	        
	        String username = "root";
	        String password = "1234";
	        
	        // try to establish the connection to the database
	        try {
	        	 connection = (Connection) DriverManager.getConnection(url, username, password);
	        	System.out.println("Connection established successfully!");
	        }
	        // if the connection couldn't be established raise an exception
	        catch (SQLException e) {
	        	throw new IllegalStateException("Unable to connect to the database. " + e.getMessage());
	        }
	        return connection;
	    }

}
