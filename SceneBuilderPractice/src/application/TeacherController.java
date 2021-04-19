package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;

public class TeacherController implements Initializable {
	static String firstName = "";
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
	static String username;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		rpv = new ResetPasswordView();
		main = new Main();
 
	}

	public static void setNameForTitle(String userName) {
		System.out.println(userName + "   In the Teacher controller");
		setUsername(userName);
		firstName = MySQLAccess.returnQuery("SELECT first_name from user where username ='" + userName + "'", 1);
	}

	public static String getUserTeacherNameForTitle() {
		return firstName;
	}
	public static void setUsername(String username) {
		TeacherController.username = username;
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
//MySQLAccess.noReturnQuery();
	}

	public void removeMaterial() {

	}

	public void addAssignment() {

	}

	public void removeAssignment() {

	}

	public void checkEmail() {
//TextArea.set(MySQLAccess.returnQuery(query, resultsNumber);
	}

	public void sendEmail() {

	}

	public void manageMeeting() {

	}
}
