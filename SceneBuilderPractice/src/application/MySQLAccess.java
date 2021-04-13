package application;

//Adapted from http://www.vogella.com/tutorials/MySQLJava/article.html
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class MySQLAccess {
	/**
	 * for connecting to your database Change the database to match your database
	 * and your password and username for your MySQL // Also you will need to have
	 * either the command line client running or the shell in order to be able to
	 * connect java will not open MySQL for you but it will do everything after 
	 */

	static String url = "jdbc:mysql://localhost:3306/p2k_district?useSSL=false";
	static String user = "root";
	static String passwrd = "root";
	static Connection con;
	static Statement stmt;
	static ResultSet rs;
	static String name = "";
//	private static ResetPasswordView resetView;

	/**
	 * Must have the sql server started on pc before the code will work meaning you
	 * must open mysql before the java code will be able to look at the database
	 */
	public static void startDB() {
		try

		{
//			resetView = new ResetPasswordView();
			// Connects to database
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url, user, passwrd);
			System.out.println("Made it here");
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static ArrayList<String> getUsername() {
		ArrayList<String> result = new ArrayList<String>();
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery("select username from user");
			while (rs.next()) {
				result.add(rs.getString(1));
//				System.out.println(rs.getString(1));

			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return result;
	}

	public static ArrayList<String> getPassword() {
		ArrayList<String> result = new ArrayList<String>();
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery("select password FROM user");
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
	public static String getFirstName(String username) {
		System.out.println("In MySQL access " + username);
		try {
			stmt = con.createStatement();
			String queryString = "SELECT first_name FROM user where username = '" + username + "';";
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

	/**
	 * Thoughts here are you pass the input string in and the return statement
	 * requires the column numbers you want back like how many columns are you
	 * expecting in the answer to your query. It does the normal calls to the
	 * database and while there are objects in the answer it goes into for loop and
	 * brings back however many columns you needed back and appends the string once
	 * out of loop it appends the newLine and goes back into the while loop lather
	 * rinse repeat. Should return everything in line by line format we could just
	 * turn it back into a stringBuilder to format it or convert it into text for
	 * the views for formatting via javaFX
	 */

	public static String returnQuery(String query, int resultsNumber) {
		System.out.println("In sql query");
		StringBuilder returnResults = new StringBuilder("");
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				for (int i = 1; i <= resultsNumber; i++) {
					returnResults.append(rs.getString(i) + "\t");
					System.out.println(returnResults.toString());

				}
				returnResults.append("\n");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("returnQuery Maybe put a boolean here if triggered");
		}
		return returnResults.toString();
	}

	/** Same as above it with no return from database */
	public static void noReturnQuery(String query) {
		try {
			stmt = con.createStatement();
			stmt.executeUpdate(query);
			ResetPasswordController.flag = "Success";
		} catch (SQLException e) {
			e.printStackTrace();
			ResetPasswordController.flag = "Failure to Change";
			System.out.println("noReturnQuery Maybe put a boolean here if triggered");

		}
	}
}
/**
 * Old
 * 
 * 
 * 
 * This method here might still be of use for chagning the passsword via the new
 * method with the reseet view
 */
//	public static boolean changePassword(String newPassword, String oldPassword, String userType) {
//		String queryString = "";
//		boolean flag = false;
//		try {
//			stmt = con.createStatement();
//			if (userType.equals("Teacher")) {
//				queryString = "update teacher set password='" + newPassword + "' where password ='" + oldPassword
//						+ "';";
//				flag = true;
//			} else if (userType.equals("Student")) {
//				queryString = "update student set password ='" + newPassword + "' where password ='" + oldPassword
//						+ "';";
//				flag = true;
//			} else if (userType.equals("Faculty")) {
//				queryString = "update faculty set password ='" + newPassword + "' where password ='" + oldPassword
//						+ "';";
//				flag = true;
//			} else if (userType.equals("Guardian")) {
//				queryString = "update guardian set password ='" + newPassword + "' where password ='" + oldPassword
//						+ "';";
//				flag = true;
//			}
//
//			rs = stmt.executeQuery(queryString);
//
//		} catch (SQLException e) {
//			queryString = "Passwords didn't update";
//			e.printStackTrace();
//		}
//		return flag;
//	}

//	/**
//	 * Thoughts here are you pass the input string in and the return statement
//	 * requires the column numbers you want back like how many columns are you
//	 * expecting in the answer to your query. It does the normal calls to the
//	 * database and while there are objects in the answer it goes into for loop and
//	 * brings back however many columns you needed back and appends the string once
//	 * out of loop it appends the newLine and goes back into the while loop lather
//	 * rinse repeat. Should return everything in line by line format we could just
//	 * turn it back into a stringBuilder to format it or convert it into text for
//	 * the views for formatting via javaFX
//	 */
//
//	public static String returnQuery(String query, int resultsNumber) {
//		System.out.println("In sql query");
//		StringBuilder returnResults = new StringBuilder("");
//		try {
//			stmt = con.createStatement();
//			rs = stmt.executeQuery(query);
//			while (rs.next()) {
//				for (int i = 1; i <= resultsNumber; i++) {
//					returnResults.append(rs.getString(i)+ "\t");
//					System.out.println(returnResults.toString());
//
//				}
//				returnResults.append("\n");
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//			System.out.println("returnQuery Maybe put a boolean here if triggered");
//		}
//		return returnResults.toString();
//	}
//
//	/** Same as above it with no return from database */
//	public static void noReturnQuery(String query) {
//		try {
//			stmt = con.createStatement();
//			stmt.executeUpdate(query);
//			ResetPasswordController.flag = "Success";
//		} catch (SQLException e) {
//			e.printStackTrace();
//			ResetPasswordController.flag = "Failure to Change";
//			System.out.println("noReturnQuery Maybe put a boolean here if triggered");
//
//		}
//	}
/**
 * Went a different route
 * 
 * 
 * Starting of the password method have not updated the gui yet to reflect the
 * new idea of username - old pass - new pass but it should bring up the reset
 * view we already have made and we can make methods in the reset controller to
 * change and alter password
 */
//public static void changePassword() {
//	try {
//		resetView.start(ResetPasswordView.resetStage);
//	} catch (Exception e) {
//		e.printStackTrace();
//	}
//}