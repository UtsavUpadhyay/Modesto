package dbconnection;

import java.sql.Connection;
import java.sql.DriverManager;

import java.lang.Exception;

public class DBconnect {
	
	Connection con;
	public Connection dbCon(){
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			String url="jdbc:mysql://localhost:3306/modesto database";
			String username="root";
			String passwrd="hello";
			con=DriverManager.getConnection(url, username, passwrd);
			if(con!=null)
				System.out.println("connection dn!!");
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return con;
	}
}
