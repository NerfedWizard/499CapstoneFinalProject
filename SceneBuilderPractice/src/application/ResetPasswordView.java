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
	public void start(Stage resetStage) throws Exception {
		try { 
			resetStage.setTitle("Reset Password Username");
			AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("ResetPassword.fxml"));
			scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			resetStage.setScene(scene);
			resetStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}

		
	}
	public static void main(String[] args) {
		launch(args);
	}


}
