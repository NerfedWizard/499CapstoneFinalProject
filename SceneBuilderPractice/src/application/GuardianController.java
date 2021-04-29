package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.AnchorPane;
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
	private MenuItem classList;
	@FXML
	private MenuItem checkMessage;
	@FXML
	private MenuItem sendMessage;
	@FXML
	private TextArea textAreaLeft;
	@FXML
	private AnchorPane anchor;
	@FXML
	private Button messBtn;
	@FXML
	private ListView<String> students = new ListView<String>();
	private String sentUser = "";
	private TextInputDialog messagePopup;
	private TextArea messageArea;
	private static String guardianUsername;
	private Text textForFlowLeft = new Text();// For Output to the user
	private static String gID = "";

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		rpv = new ResetPasswordView();
		main = new Main();
		anchor.setVisible(false);

	}

	public void getStudents() {
		ObservableList<String> studList = FXCollections.observableArrayList();
		String student = MySQLAccess.returnQuery(
				"select username from user, gsr where student_id = user_id and guardian_id ='" + gID + "'", 1);
		for (String s : student.split("\\s+")) {
			studList.add(s);

		}
		students.setItems(studList);
	}

	public static void setNameForTitle(String name) {

		guardianUsername = name;
		nameForTitle = MySQLAccess.getFirstName(name);
		gID = MySQLAccess.returnQuery("SELECT user_id from user where username ='" + guardianUsername + "'", 1);

	}

	public static String getUserGuardianNameForTitle() {
		return nameForTitle;
	}

	public void changeLogin() {
		try {
			ResetPasswordController.setUser(guardianUsername);
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
		String score = "Score";
		String grade = "Grade";
		textForFlowLeft.setText(String.format("%-4s %-3s\n", score, grade) + MySQLAccess
				.returnQuery("select score, grade from assignment,user where user_id = student_id  and username ='"
						+ students.getSelectionModel().getSelectedItem() + "'", 2));
		changeTextFlow(textForFlowLeft);
	}

	public void checkMessages() {
		String check = "\n" + MySQLAccess
				.returnQuery("SELECT date_received,cast(message_text as NCHAR) FROM message WHERE username ='"
						+ guardianUsername + "' order by date_received DESC limit 10", 2);
		textForFlowLeft.setText(check);
		changeTextFlow(textForFlowLeft);
	}

	public void sendMess() {
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
		String messageF = "FROM: " + guardianUsername + "\n" + messageArea.getText() + "\n\n";
		MySQLAccess.noReturnQuery(
				"insert into message (username,message_text) values('" + sentUser + "','" + messageF + "')");
		textForFlowLeft.setText("Message Sent\n" + sentUser);
		changeTextFlow(textForFlowLeft);
		messageArea.clear();
		anchor.setVisible(false);
	}

	/**
	 * Thoughts here is grab the course for selected student and maybe add their
	 * assignment with due date on itot
	 * 
	 * 
	 * Working
	 * 
	 * 
	 * 
	 * 
	 * Working but want the format to be better and still need to get all the
	 * comments added to the project
	 */
	public void courseList() {
		String course = MySQLAccess.returnQuery(
				"select course_name, assignment_name from assignment a, course c, user where student_id = user_id and c.course_id = a.course_id and username = '"
						+ students.getSelectionModel().getSelectedItem() + "'",
				2);

		ArrayList<String> splitCourse = new ArrayList<String>();
		for (String s : course.split("\\s+")) {
			splitCourse.add(s);
		}
		int i = splitCourse.get(1).length();
		System.out.println("Length of first entry: " + i + "\nThe string at index 0: " + splitCourse.get(0)
				+ "\nand the contents of course string: " + course + "\nSize of the list: " + splitCourse.size());
		textForFlowLeft.setText(course);
		changeTextFlow(textForFlowLeft);
	}

	/** Still needed for setting the text to user */
	public void changeTextFlow(Text textLeft) {
		textAreaLeft.setText(textLeft.getText());
	}

}
