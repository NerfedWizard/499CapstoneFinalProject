package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/** Controller to be used for the student profile */
public class StudentController implements Initializable {
@FXML ImageView backgroundImage  = new ImageView();
@FXML Image image = new Image("disneyP.jpg");//Image that is needed to swap for current.
@FXML MenuItem materialsMenuItem;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		

	}
	public void changeImage() {
		backgroundImage.setImage(image);
	}
	

}
