package application;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class GuardianView extends Application {
	@FXML
	private Scene scene;
	@FXML
	private BorderPane root;

	public GuardianView() {

	}

	@Override
	public void start(Stage guardStage) throws Exception {
		try {
			guardStage.setTitle("Guardian View");
			 BorderPane root = (BorderPane) FXMLLoader.load(getClass().getResource("GuardianView.fxml"));
			scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			guardStage.setScene(scene);
			guardStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}