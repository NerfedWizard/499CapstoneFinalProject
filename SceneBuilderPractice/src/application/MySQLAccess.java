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

	/**
	 * Must have the sql server started on pc before the code will work meaning you
	 * must open mysql before the java code will be able to look at the database
	 */
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

			e.printStackTrace();
		}
		return result;
	}

	public static ArrayList<String> getPassword(String userType) {
		ArrayList<String> result = new ArrayList<String>();
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery("select password FROM " + userType + ";");
			while (rs.next()) {
				result.add(rs.getString(1));
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return result;
	}

	/**
	 * Only something I made to the first name of the user for the title of the
	 * window
	 */
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

			e.printStackTrace();
		}
		return name;
	}

	public static boolean changePassword(String newPassword, String oldPassword, String userType) {
		String queryString = "";
		boolean flag = false;
		try {
			stmt = con.createStatement();
			if (userType.equals("Teacher")) {
				queryString = "update teacher set password='" + newPassword + "' where password ='" + oldPassword
						+ "';";
				flag = true;
			} else if (userType.equals("Student")) {
				queryString = "update student set password ='" + newPassword + "' where password ='" + oldPassword
						+ "';";
				flag = true;
			} else if (userType.equals("Faculty")) {
				queryString = "update faculty set password ='" + newPassword + "' where password ='" + oldPassword
						+ "';";
				flag = true;
			} else if (userType.equals("Guardian")) {
				queryString = "update guardian set password ='" + newPassword + "' where password ='" + oldPassword
						+ "';";
				flag = true;
			}

			rs = stmt.executeQuery(queryString);

		} catch (SQLException e) {
			queryString = "Passwords didn't update";
			e.printStackTrace();
		}
		return flag;
	}

	/**
	 * Needs the string for the query and a number for how many columns you want
	 * back
	 */
	public static void runQuery(String query) {
		
	}
}