package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class Main extends Application {
	
	static Scene scene;
	static Stage primaryStage;
	@Override
	public  void start(Stage primaryStage) {
		try {
			Main.primaryStage = primaryStage;
			Main.primaryStage.setTitle("Login");
//			Parent root = (Parent) FXMLLoader.load(getClass().getResourceAsStream("FirstTest.fxml"));
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("FirstTest.fxml"));
			scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			Main.primaryStage.setScene(scene);
//			Main.primaryStage.initModality(Modality.APPLICATION_MODAL);
			Main.primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static Scene getScene() {
		return scene;
	}
	public static Stage getPrimaryStage() {
		return primaryStage;
	}
	public static void main(String[] args) {
	launch(args);
	}
}
