package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application {

	static Scene scene;
	static Stage primaryStage;

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
		
		launch(args);
//		MySQLAccess database = new MySQLAccess();
//		database.readDataBase();
	}
}
