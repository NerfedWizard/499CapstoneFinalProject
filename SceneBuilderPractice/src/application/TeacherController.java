package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;


public class TeacherController implements Initializable {
	static String nameForTitle = "";

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		
	}
	public static void setNameForTitle(String name,String userType) {
		System.out.println(name + "   In the student controller");
		nameForTitle = MySQLAccess.getFirstName(name,userType);
	}
	public static String getUserTeacherNameForTitle() {
		return nameForTitle;
	}

}
