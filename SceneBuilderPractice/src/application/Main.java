package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application {

	static Scene scene;
	static Stage primaryStage;
	
//	static String url = "jdbc:mysql://localhost:3306/Portal_To_Knowledge?useSSL=false";
//	static String user = "root";
//	static String passwrd = "root";

	@Override
	public void start(Stage primaryStage) {
		try {

			primaryStage.setTitle("Login");
			AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("FirstTest.fxml"));
			scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		MySQLAccess.startDB();
//		try
//
//		{
//			Class.forName("com.mysql.jdbc.Driver");
//			Connection con = DriverManager.getConnection(url,
//					user, passwrd);
//			// here sonoo is database name, root is username and password
//			Statement stmt = con.createStatement();
//			ResultSet rs = stmt.executeQuery("select first_name from teacher");
//			while (rs.next())
//				System.out.println(rs.getString(1));
//			con.close();
//		} catch (Exception e) {
//			System.out.println(e);
//		}
		launch(args);
//		MySQLAccess database = new MySQLAccess();
//		database.newQuery();
//		database.readDataBase();
	}
}
