package application;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class FacultyView extends Application {
	@FXML
	private Scene scene;
	@FXML
	private AnchorPane root;

	@Override
	public void start(Stage facView) throws Exception {
		try {
			facView.setTitle(AdminController.getUserAdminNameForTitle());
			AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("FacultyView.fxml"));
			scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			facView.setScene(scene);
			facView.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

}
