package application;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class TeacherView extends Application {
	@FXML
	private Stage teacherView;
	
	@FXML
	private AnchorPane root;
	@FXML
	private Scene scene;

	public TeacherView() {
		
		
	}

	@Override
	public void start(Stage teacherView) throws Exception {
		try {
			teacherView.setTitle(TeacherController.getUserTeacherNameForTitle());
			teacherView.setFullScreen(true);  
			AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("TeacherView.fxml"));
			scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			teacherView.setScene(scene);
			teacherView.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
public static void main(String [] args) {
	launch(args);
}
}
