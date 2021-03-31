package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;

public class TeacherController implements Initializable {
	static String nameForTitle = "";
	private ResetPasswordView rpv;
	private Main main;
	@FXML
	private MenuItem changeLogin;
	@FXML
	private MenuItem logout;
	@FXML
	private MenuItem addGrades;
	@FXML
	private MenuItem removeGrades;
	@FXML
	private MenuItem addMaterial;
	@FXML
	private MenuItem removeMaterial;
	@FXML
	private MenuItem addAssignment;
	@FXML
	private MenuItem removeAssignment;
	@FXML
	private MenuItem checkEmail;
	@FXML
	private MenuItem sendEmail;
	@FXML
	private MenuItem manageMeetings;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		rpv = new ResetPasswordView();
		main = new Main();

	}

	public static void setNameForTitle(String name, String userType) {
		System.out.println(name + "   In the student controller");
		nameForTitle = MySQLAccess.getFirstName(name, userType);
	}

	public static String getUserTeacherNameForTitle() {
		return nameForTitle;
	}

	public void changeLogin() {
		try {
			rpv.start(Main.logStage);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void teacherLogout() {
		try {
			main.start(Main.logStage);// shows you can go to any view from any view if needed
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** Need sql statement for adding grades to the users */
	public void addGrades() {

	}

	public void removeGrades() {

	}

	public void addMaterial() {

	}

	public void removeMaterial() {

	}

	public void addAssignment() {

	}

	public void removeAssignment() {

	}

	public void checkEmail() {

	}

	public void sendEmail() {

	}

	public void manageMeeting() {

	}
}
