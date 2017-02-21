package database_console;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBConnect {

		public static void main(String[] args)
		{
			try {
				String host = "jdbc:derby://localhost:1527/Employees";
				String uName = "username";
				String uPass = "password";
			
				Connection con = DriverManager.getConnection( host, uName, uPass );
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}
			
			
		}
}
