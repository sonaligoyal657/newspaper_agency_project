package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectToDatabase 
{
	public static Connection getConnection()
	{
		Connection con=null;			
		try {
			con=DriverManager.getConnection("jdbc:mysql://localhost/java2020",  "root",  "");
			//System.out.println("Connected");
		} 
		catch (SQLException e) {
			
			e.printStackTrace();
		}
		return con;
	}
	public static void main(String args[])
	{
		Connection conRef=getConnection();
		if(conRef==null)
			System.out.println("error");
		else
			System.out.println("Connected...");
		
	}
}
