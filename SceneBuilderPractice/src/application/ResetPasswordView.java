package application;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ResetPasswordView extends Application{
	@FXML
	private AnchorPane root;
	@FXML
	private Scene scene;
	@Override
	public void start(Stage primaryStage) throws Exception {
		try {
			primaryStage.setTitle("Reset Password Username");
			AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("StudentProfileView.fxml"));
			scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}

		
	}


}
