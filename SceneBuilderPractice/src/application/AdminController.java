package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;

/**
 * I think making a text field for the database stuff is the way to go
 */
public class AdminController implements Initializable {

	private static String nameForTitle = "";

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	}

	public static void setNameForTitle(String name) {
		System.out.println(name + "   In the student controller");
		nameForTitle = MySQLAccess.getFirstName(name);
	}

	public static String getUserAdminNameForTitle() {
		return nameForTitle;
	}

}
