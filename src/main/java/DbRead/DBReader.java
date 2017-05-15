package DbRead;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBReader {
	public static String className = "org.postgresql.Driver";
	public static Connection c = null;
	public static Statement stmt = null;
	public static ResultSet rs = null;

	private static DBReader myObj = null;

	private DBReader(){

	}

	private DBReader(String connectionURL, String userName, String password) throws SQLException{
		c = DriverManager.getConnection(connectionURL,userName, password);
		c.setAutoCommit(false);
	}

	public static DBReader getInstance()
	{
		myObj = new DBReader();
		return myObj;	
	}

	public static DBReader getInstance(String connectionURL, String userName, String password) throws SQLException
	{

		if(myObj == null){
			myObj = new DBReader(connectionURL, userName, password);
		}
		else{
			System.out.println("No objject created this time");
		}
		return myObj;
	}

	public ResultSet connectToDB(String query) throws SQLException
	{

		try 
		{
			Class.forName(className);
			System.out.println("Going to connect to database...");

			//			c = DriverManager.getConnection(connectionURL,userName, password);
			//			c.setAutoCommit(false);
			System.out.println("connected to database successfully");
			stmt = c.createStatement();
			if(query.contains("INSERT")){
				c.setAutoCommit(true);
				stmt.executeUpdate("SET datestyle = dmy;");
				System.out.println("set");
				stmt.executeUpdate(query);
				rs = null;
			}
			else
			{
				rs = stmt.executeQuery(query);
			}

		} catch (Exception e) {
			System.out.println("EXCEPTION");  
			System.out.println(e);
		}
		ResultSet r = rs;
		return r;
	}

}
