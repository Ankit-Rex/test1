package libraryMgmt;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
	public class Connection4Jdbc {

		private static Connection con;
		public static Connection getConnection() {
			try {
				if(con==null) {
				Class.forName("com.mysql.cj.jdbc.Driver");
				System.out.println("Class loaded");
				con=DriverManager.getConnection("jdbc:mysql://localhost:3306/librarymgmt","root","4444");
				System.out.println("connecton estabb");
				}
				
			}catch(Exception e) {
				e.printStackTrace();
			}
			return con;
		}
		}