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
	static String name = "";

	public static void startDB() {
		try

		{
			// Connects to database
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url, user, passwrd);
			System.out.println("Made it here");
		} catch (Exception e) {
			System.out.println(e);
		}
	}

//	public static String getFirstName(String userID) {
//		System.out.println("In MySQL access " + userID);
//		try {
//			stmt = con.createStatement();
//			String queryString = "SELECT first_name FROM student where student_id = '" + userID + "';";
//			rs = stmt.executeQuery(queryString);
//
//			name = rs.getString(1);
//			System.out.println(name);
//
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return name;
//	}

	public static ArrayList<String> getUsername(String userType) {
		ArrayList<String> result = new ArrayList<String>();
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery("select username FROM " + userType + ";");
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

	public static ArrayList<String> getPassword() {
		ArrayList<String> result = new ArrayList<String>();
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery("select password FROM student;");
			while (rs.next()) {
				result.add(rs.getString(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}

	public static String getFirstName(String username, String userType) {
		System.out.println("In MySQL access " + username);
		try {
			stmt = con.createStatement();
			String queryString = "SELECT first_name FROM " + userType + " where username = '" + username + "';";
			rs = stmt.executeQuery(queryString);
			if (rs.next()) {
				name = rs.getString(1);
				System.out.println(name);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return name;
	}
}