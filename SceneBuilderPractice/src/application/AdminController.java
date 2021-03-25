package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.SubScene;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * I think making a text field for the database stuff is the way to go
 */
public class AdminController implements Initializable {
	@FXML
	private MenuButton adminMenuButton;

	ImageView backgroundImage;

	Image image;// Image that is needed to swap for current.
	@FXML
	MenuItem materialsMenuItem;
	@FXML
	SubScene subscene;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
//		image = new Image("disneyP.jpg");
//		image = new Image("Metropolitan_State_Library_and_Learning_Center.jpg");

	}

	public void changeImage() {
		TextArea textArea = new TextArea("Hello");
		textArea.setEditable(false);
		textArea.setText("Hello World");

//		System.out.println("Hety");
//		backgroundImage.setImage(image);
	}

}
