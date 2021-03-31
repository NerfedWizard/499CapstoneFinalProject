package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

/**
 * Controller to be used for the student profile
 * 
 * Had to go with a login button outside of the menu for going back to the login
 * view now inside the menu it will have change password and change user name
 */
public class StudentController implements Initializable {

	@FXML
	private MenuItem materialsMenuItem;
	@FXML
	private TextFlow textFlow;
	@FXML
	private TextArea textAreaLeft;
//	@FXML
//	private TextArea textAreaRight;
	@FXML
	private Button logoutButton;
	@FXML
	private MenuItem changePass;
	private Text textForFlowLeft = new Text();// For Output to the user
//	private Text textForFlowRight = new Text();
	static String nameForTitle;
	private Main main;
	static String userType;
	private static String username;
	private ResetPasswordView rpv;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
//		textAreaRight.setPromptText("Enter text here:");
		main = new Main();
		rpv = new ResetPasswordView();
	}

	public void updateLogin() {
		try {
			rpv.start(Main.logStage);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void studentLogout() {
		try {
			main.start(Main.logStage);// shows you can go to any view from any view if needed
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** Setting the title for the Students window */
	public static void setNameForTitle(String name, String userType) {
		StudentController.setUsername(name);
		System.out.println(name + "   In the student controller");
		StudentController.nameForTitle = MySQLAccess.getFirstName(name, userType);
	}

	public void checkEmail() {
		textForFlowLeft.setText("Al Capone\n\tAppointment Reminder-" + getUserStudentNameForTitle() + ".......");
		changeTextFlow(textForFlowLeft);
	}

	/**
	 * for setting the first name instead of username this and the above can
	 * probably be changed now with the 2 mysql methods
	 */
	public static String getUserStudentNameForTitle() {
		return StudentController.nameForTitle;
	}

	/** Still needed for setting the text to user */
	public void changeTextFlow(Text textLeft) {
		textAreaLeft.setText(textLeft.getText());
	}

	/** Works for getting the grade of user */
	public void getGrades() {
		textForFlowLeft.setText("Course\t\t\tScore\tGrade\n" + MySQLAccess
				.returnQuery("select course_name, score, grade from assignment a, course c where a.username ='"
						+ getUsername() + "' and a.course_id = c.course_id", 3));

		changeTextFlow(textForFlowLeft);
	}

	public void getAssignments() {
		textForFlowLeft.setText("Name\t\tDue Date\n" + MySQLAccess.returnQuery(
				"select assignment_name,deadline from assignment where username ='" + getUsername() + "'", 2));

		changeTextFlow(textForFlowLeft);
	}

	public void getMaterials() {
		textForFlowLeft.setText(
				"Materials needed:\n Million tons of steel\nSpacesuit\nRocket Ship\nSteelworker Knowledge\nPermission slip to build Dyson Sphere around the Sun");
		changeTextFlow(textForFlowLeft);
	}

	public static String getUsername() {
		return username;
	}

	public static void setUsername(String username) {
		StudentController.username = username;
	}
}
