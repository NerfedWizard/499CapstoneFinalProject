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
import javafx.scene.layout.AnchorPane;
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
	private MenuItem changePass;
	@FXML
	private Menu courseMenu;
	@FXML
	private Button sendMessage;
	@FXML
	private AnchorPane anchor;

	private String sentUser = "";
	static String firstName;
	static String userType;
	static String sID = "";
	private static String studUsername;

	private TextArea messageArea;
	private TextInputDialog messagePopup;
	private Text textForFlowLeft = new Text();// For Output to the user

	private Main main;
	private ResetPasswordView rpv;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		main = new Main();
		rpv = new ResetPasswordView();
		anchor.setVisible(false);
	}

	public void updateLogin() {
		try {
			ResetPasswordController.setUser(studUsername);
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
		StudentController.firstName = MySQLAccess
				.returnQuery("SELECT first_name FROM user where username='" + userName + "'", 1);
	}

	public void checkMessages() {
		String check = "\n" + MySQLAccess
				.returnQuery("SELECT date_received,cast(message_text as NCHAR) FROM message WHERE username ='"
						+ studUsername + "' order by date_received DESC limit 10", 2);
		textForFlowLeft.setText(check);
		changeTextFlow(textForFlowLeft);
	}

	/*
	 * Need a way to send it to the database instead of keep checking maybe add a
	 * button on the tilepane
	 **/
	public void sendMessage() {
		anchor.setVisible(true);
		messagePopup = new TextInputDialog();
		messagePopup.setHeaderText("Enter the Username you would like to send message to.");
		messageArea = new TextArea();
		messageArea.setWrapText(true);
		messageArea.setPromptText("Enter Message Here");
		messageArea.setPrefSize(307, 376);
		anchor.getChildren().add(messageArea);
		messagePopup.showAndWait();
		sentUser = messagePopup.getEditor().getText();
		if (sentUser.length() > 8 || sentUser.length() < 8) {
			messagePopup.setHeaderText("This is not a valid Username Please Try Again");
			messagePopup.showAndWait();
		}

	}

	public void messageSent() {
//		sentUser = sentUser + "@p2k.com";
		String messageF = "FROM: " + studUsername + "\n" + messageArea.getText() + "\n\n";
		MySQLAccess.noReturnQuery(
				"insert into message (username,message_text) values('" + sentUser + "','" + messageF + "')");
		textForFlowLeft.setText("Message Sent\n" + sentUser);
		changeTextFlow(textForFlowLeft);
		messageArea.clear();
		anchor.setVisible(false);
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
		anchor.setVisible(false);
		String score = "Score";
		String grade = "Grade";
		textForFlowLeft.setText(String.format("%-4s %-3s\n", score, grade)
				+ MySQLAccess.returnQuery("select score, grade from assignment a, course c where a.student_id ='" + sID
						+ "' and a.course_id = c.course_id", 2));
		changeTextFlow(textForFlowLeft);
	}

	public void getAssignments() {
		String name = "Name";
		String dueDate = "Due Date";
		anchor.setVisible(false);
		textForFlowLeft.setText(String.format("%-15s %4s\n", name, dueDate) + MySQLAccess
				.returnQuery("select assignment_name,deadline from assignment where student_id ='" + sID + "'", 2));
		changeTextFlow(textForFlowLeft);
	}

	/**
	 * Make this a string object instead of accessing the database
	 */
	public void getMaterials() {
		String x = course();
		String materials = MySQLAccess
				.returnQuery("select cast(overview as NCHAR) from course where course_name = '" + x + "'", 1);
		textForFlowLeft.setText(course() + " Course Description:\n" + materials);
		changeTextFlow(textForFlowLeft);

	}

	public static String getUsername() {
		return studUsername;
	}

	public static void setUsername(String username) {
		StudentController.studUsername = username;
		sID = MySQLAccess.returnQuery("SELECT user_id from user where username ='" + username + "'", 1);
	}

	public String course() {
		String courseQ = MySQLAccess.returnQuery(
				"select course_name from course c, assignment a where c.course_id = a.course_id and student_id = "
						+ StudentController.sID,
				1);
//		System.out.println(courseQ.length());
//		System.out.println(courseQ.trim().length());  
		return courseQ.trim();
	}

	public void checkCourse() {
		String courseQ = "select course_name from course c, assignment a where c.course_id = a.course_id and student_id = "
				+ StudentController.sID;
		courseMenu.setOnShown(new EventHandler<Event>() {
			@Override
			public void handle(Event arg0) {
				courseMenu.setText(MySQLAccess.returnQuery(courseQ, 1));
			}
		});
//		textForFlowLeft.setText(MySQLAccess.returnQuery(courseQ, 1));
	}
}
