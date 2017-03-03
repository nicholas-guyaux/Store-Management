package database_console;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;



public class DBConnect {
	
	public static void main(String[] args) throws Exception
	{
		
		Class.forName("com.mysql.jdbc.Driver");
		
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/store_management","username","password");
		
		PreparedStatement statement = con.prepareStatement("select * from products");
		
		ResultSet result = statement.executeQuery();
		
		System.out.println("ProductID  Description  Price  Quantity     ExpDate    ProviderID");
		
		while(result.next())
		{
			System.out.println(" " + result.getString(1) + "     " + result.getString(2)+ "      $" + result.getString(3)+ "   " + result.getString(4)+ "        " + result.getString(5)+ "    " + result.getString(6));
		}
	}

}