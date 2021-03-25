package application;

//Adapted from http://www.vogella.com/tutorials/MySQLJava/article.html
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class MySQLAccess {
	// for connecting to my database the first part is right but not sure what your
	// db is
	static String url = "jdbc:mysql://localhost:3306/Portal_To_Knowledge?useSSL=false";
	static String user = "root";
	static String passwrd = "root";
	static Connection con;
	static Statement stmt;
	static ResultSet rs;

	public static void startDB() {
		try

		{
			// Connects to database
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url, user, passwrd);
			System.out.println("Made it here");
//			stmt = con.createStatement();
//			rs = stmt.executeQuery(query);
//			while (rs.next())
//				System.out.println(rs.getString(1));
//			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static ArrayList<String> getUsername() {
		ArrayList<String> result = new ArrayList<String>();
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery("select username FROM user_info;");
			while (rs.next()) {
				result.add(rs.getString(1));
				System.out.println(rs.getString(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	public static ArrayList<String> getPassword(){
		ArrayList<String> result = new ArrayList<String>();
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery("select password FROM user_info;");
			while (rs.next()) {
				result.add(rs.getString(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
}