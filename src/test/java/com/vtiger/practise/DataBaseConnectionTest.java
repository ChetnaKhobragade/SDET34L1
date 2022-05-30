package com.vtiger.practise;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class DataBaseConnectionTest {
	static Connection connection ;
	public static void main(String[] args) throws SQLException {
		try {
		//step 1: Create object for implementation class
		Driver driver = new Driver();
		//step 2:Register the Driver with JDBC
		DriverManager.registerDriver(driver);
		//step 3:Establish the connection
		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/projects", "root", "root");
		//step 4:create the statement
		Statement statement = connection.createStatement();
		//step 5:execute script
		ResultSet result = statement.executeQuery("select * from emp;");
		//step 6:validate based on test case
		while(result.next())
		{
			System.out.println(result.getString(2));
			
		}
		}
		
		finally {
			System.out.println("Before close");
		//step 7:close the connection
		connection.close();

		System.out.println("after close");
		}
	}

}
