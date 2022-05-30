package com.sdet34l1.genericLibrary;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.mysql.cj.jdbc.Driver;

/**
 * This class contains all the common action related to database
 * @author Chetna K
 *
 */
public class DatabaseLibrary {

	static Connection connection;
	static Statement statement;
	/**
	 * This method is used to open the database connection and initialize the connection 
	 * @param dBUrl
	 * @param dBUsername
	 * @param dBPassword
	 * @throws SQLException
	 */
	public static void openDBConnection(String dBUrl, String dBUsername, String dBPassword) throws SQLException {
		Driver d= new Driver();
		DriverManager.registerDriver(d);
		Connection connection = DriverManager.getConnection(dBUrl, dBUsername, dBPassword);
		statement = connection.createStatement();
	}

	/**
	 * This method is used to fetch the data from the data base and do the ddl actions on the database
	 * @param dBUrl
	 * @param dBUsername
	 * @param dBPassword
	 * @param columnName
	 * @param query
	 * @return
	 * @throws SQLException
	 */
	public static ArrayList<String>  getDataFromDataBase( String columnName , String query) throws SQLException {
		ArrayList<String> list= new ArrayList<String>();
		ResultSet result = statement.executeQuery(query);
		while(result.next())
		{
			list.add(result.getString(columnName));
		}
		return list;
	}

	/**
	 * This method is used to validate the data whether it is present in database or not 
	 * @param dBUr
	 * 21l
	 * @param dBUsername
	 * @param dBPassword
	 * @param columnName
	 * @param query
	 * @param data
	 * @return
	 */
	public static boolean validateTheDataInDatabase( String query, String columnName ,String expectedData) {
		ArrayList<String> list= new ArrayList<String>();
		boolean flag = false;
		for(String actualData: list)
		{
			if(actualData.equalsIgnoreCase(expectedData)) {
				flag = true;
				break;
			}
		}
		return flag;
	}

	/**
	 * This method is used to store/insert/modify/delete the data in the data base / to do the DDL/DML actions on database 
	 * @param dBUrl
	 * @param dBUsername
	 * @param dBPassword
	 * @param query
	 * @throws SQLException
	 */
	public static void  setDataInDataBase(String query) throws SQLException {
		int result = statement.executeUpdate(query);
		if(result>=1) {
			System.out.println("Data entered/modified successfully");
		}
	}

	/**
	 * This method is used to close the Database connection 
	 */
	public static void closeDBConnection() {
		try {
			connection.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.getMessage());
			System.out.println("While closing the Database connection we got exception");
		}
	}
}