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

	private static String nameForTitle = "";

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	}

	public static void setNameForTitle(String name, String userType) {
		System.out.println(name + "   In the student controller");
		nameForTitle = MySQLAccess.getFirstName(name, userType);
	}

	public static String getUserAdminNameForTitle() {
		return nameForTitle;
	}

}
