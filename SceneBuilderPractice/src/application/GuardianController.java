package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;

/** @author LoelN Copy the stuff from the other sent/check email */
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
//	@FXML
//	private TextArea textArtextForeaG;
	@FXML
	private TextArea textAreaLeft;
	private static String username;
	private Text textForFlowLeft = new Text();// For Output to the user
	private static String gID = "";

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		rpv = new ResetPasswordView();
		main = new Main();

	}

//	public void setUsername() {
//		
//	}
	public void getStudent() {
		String [] student = MySQLAccess.returnQuery("SELECT student_id from gsr where guardian_id =", 1).split("\\s+");
	}

	public static void setNameForTitle(String name) {
//		System.out.println(name + "   In the Guardian controller");
		username = name;
		nameForTitle = MySQLAccess.getFirstName(name);
		gID = MySQLAccess.returnQuery("SELECT user_id from user where username ='" + username + "'", 1);
		System.out.println(gID);
	}

	public static String getUserGuardianNameForTitle() {
		return nameForTitle;
	}

	public void changeLogin() {
		try {
			ResetPasswordController.setUser(username);
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
		String check = "\n"
				+ MySQLAccess.returnQuery("SELECT date_received,cast(message as NCHAR) FROM user_email WHERE email ='"
						+ username + "@p2k.com' order by date_received DESC limit 5", 2);
		textForFlowLeft.setText(check);
		changeTextFlow(textForFlowLeft);
	}

	public void sendEmail() {

	}

	public void manageMeeting() {

	}

	public void checkAttendance() {

	}

	public void classList() {

	}

	/** Still needed for setting the text to user */
	public void changeTextFlow(Text textLeft) {
		textAreaLeft.setText(textLeft.getText());
	}

}
