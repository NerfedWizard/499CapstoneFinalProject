package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;

public class GuardianController implements Initializable {
	static String nameForTitle = "";
	private ResetPasswordView rpv;
	private Main main;
	@FXML
	private MenuItem logout;
	@FXML
	private MenuItem changLogin;
	@FXML
	private MenuItem checkGrades;
	@FXML
	private MenuItem checkAttendance;
	@FXML
	private MenuItem classList;
	@FXML
	private MenuItem checkEmail;
	@FXML
	private MenuItem sendEmail;
	@FXML
	private MenuItem scheduleMeeting;
	@FXML
	private TextArea textAreaG;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		rpv = new ResetPasswordView();
		main = new Main();

	}

	public static void setNameForTitle(String name) {
		System.out.println(name + "   In the guardian controller");
		nameForTitle = MySQLAccess.getFirstName(name);
	}

	public static String getUserGuardianNameForTitle() {
		return nameForTitle;
	} 

	public void changeLogin() {
		try {
			rpv.start(Main.logStage);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void logout() {
		try {
			main.start(Main.logStage);// shows you can go to any view from any view if needed
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** Need sql statement for adding grades to the users */
	public void checkGrades() {

	}

	public void checkEmail() {

	}

	public void sendEmail() {

	}

	public void manageMeeting() {

	}

	public void checkAttendance() {

	}

	public void classList() {

	}

}
