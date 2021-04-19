package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.TilePane;
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

	@FXML
	private Button logoutButton;
	@FXML
	private MenuItem changePass;
	@FXML
	private Menu courseMenu;
	@FXML
	private TilePane tilePane;
	private TextArea emailArea;
	private TextInputDialog emailPopup;
	private Text textForFlowLeft = new Text();// For Output to the user

	static String firstName;
	private Main main;
	static String userType;
	private static String username;
	private ResetPasswordView rpv;
	static String sID = "";

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		main = new Main();
		rpv = new ResetPasswordView();
		textAreaLeft.setVisible(false);
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
	public static void setNameForTitle(String userName) {
		StudentController.setUsername(userName);
		System.out.println(userName + "   In the student controller");
		StudentController.firstName = MySQLAccess
				.returnQuery("SELECT first_name FROM user where username='" + userName + "'", 1);
	}

	public void checkEmail() {
		textAreaLeft.setVisible(true);
//		emailArea.setVisible(false);

		textForFlowLeft.setText(MySQLAccess.returnQuery(
				"select cast(message as NCHAR) from user_email where email ='" + username + "@p2k.com'", 1));
		changeTextFlow(textForFlowLeft);
	}

	public void sendMail() {
//		textAreaLeft.setVisible(false);
		emailPopup = new TextInputDialog();

		emailPopup.setHeaderText("Enter the Username you would like to send message to.");
//		emailPopup.set("This is a test");
		emailArea = new TextArea();
		emailArea.setPromptText("Enter Message Here");
		emailArea.setPrefSize(307, 376);
		emailArea.setVisible(true);
		tilePane.getChildren().add(emailArea);
		emailPopup.showAndWait();
		String recUser = emailPopup.getEditor().getText();
		if (recUser.length() > 8) {
			emailPopup.setHeaderText("This is not a valid Username Please Try Again");
			emailPopup.showAndWait();
		}
//		textAreaLeft.setVisible(true);
//		textForFlowLeft.setText(recUser);
//		changeTextFlow(textForFlowLeft);
//		emailArea.setVisible(false);
	}

	/**
	 * for setting the first name instead of username this and the above can
	 * probably be changed now with the 2 mysql methods
	 */
	public static String getUserStudentNameForTitle() {
		return StudentController.firstName;
	}

	/** Still needed for setting the text to user */
	public void changeTextFlow(Text textLeft) {
		textAreaLeft.setText(textLeft.getText());
	}

	/** Works for getting the grade of user */
	public void getGrades() {
		textAreaLeft.setVisible(true);
//		emailArea.setVisible(false);
		String course = "Course";
		String score = "Score";
		String grade = "Grade";
//		StringBuilder sb = new StringBuilder();
		textForFlowLeft.setText(String.format("%-15s %4s %-3s\n", course, score, grade) + MySQLAccess
				.returnQuery("select course_name, score, grade from assignment a, course c where a.student_id ='" + sID
						+ "' and a.course_id = c.course_id", 3));

		changeTextFlow(textForFlowLeft);
	}

	public void getAssignments() {
//		emailArea.setVisible(false);
		textAreaLeft.setVisible(true);
		textForFlowLeft.setText("Name\t\tDue Date\n" + MySQLAccess
				.returnQuery("select assignment_name,deadline from assignment where student_id ='" + sID + "'", 2));

		changeTextFlow(textForFlowLeft);
	}

	/**
	 * Make this a string object instead of accessing the database
	 */
	public void getMaterials() {
//		emailArea.setVisible(false);
		textAreaLeft.setVisible(true);
		textForFlowLeft.setText(
				"Materials needed:\n Million tons of steel\nSpacesuit\nRocket Ship\nSteelworker Knowledge\nPermission slip to build Dyson Sphere around the Sun");
		changeTextFlow(textForFlowLeft);
	}

	public static String getUsername() {
		return username;
	}

	public static void setUsername(String username) {
		StudentController.username = username;
		sID = MySQLAccess.returnQuery("SELECT user_id from user where username ='" + username + "'", 1);
	}

	public void checkCourse() {
//		emailArea.setVisible(false);
		textAreaLeft.setVisible(true);
		String courseQ = "select course_name from course c, assignment a where c.course_id = a.course_id and student_id = "
				+ StudentController.sID;
		courseMenu.setOnShown(new EventHandler<Event>() {
			@Override
			public void handle(Event arg0) {
				courseMenu.setText(MySQLAccess.returnQuery(courseQ, 1));
			}
		});
		textForFlowLeft.setText(MySQLAccess.returnQuery(courseQ, 1));
	}
}
