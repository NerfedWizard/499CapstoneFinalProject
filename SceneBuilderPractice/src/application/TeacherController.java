package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class TeacherController implements Initializable {
	static String firstName = "";
	private String sentUser = "";
	private TextArea messageArea;
	private TextInputDialog messagePopup;
	private Main main;
	static String userType;
	private static String teachUsername;
	private ResetPasswordView rpv;
	static String sID = "";
	@FXML
	private AnchorPane gradeAnchor;
	@FXML
	private Button sendMessage;
	@FXML
	private AnchorPane anchor;
	@FXML
	private MenuItem changeLogin;
	@FXML
	private TextArea textAreaLeft;
	@FXML
	private ListView<String> addGradeView = new ListView<String>();
	@FXML
	private ListView<String> removeGradeView = new ListView<String>();
	@FXML
	private ListView<String> remAssign = new ListView<String>();
	@FXML
	private TextField courseNum;
	@FXML
	private TextField assignName;
	@FXML
	private TextField dueDate;
	@FXML
	private TextField stdID;
	@FXML
	private TextField ptsErnd;
	@FXML
	private TextField totlPts;
	@FXML
	private Button gradeBtn;
	@FXML
	private Label courseMat;
	@FXML
	private Pane labelPane;
	@FXML
	private Button matButn;
	@FXML
	private TextField courseText;

	private Text textForFlowLeft = new Text();
	static ArrayList<String> materials = new ArrayList<String>();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		rpv = new ResetPasswordView();
		main = new Main();
		anchor.setVisible(false);
		gradeAnchor.setVisible(false);
		labelPane.setVisible(false);
	}

	public static void setNameForTitle(String userName) {

		setUsername(userName);
		firstName = MySQLAccess.returnQuery("SELECT first_name from user where username ='" + userName + "'", 1);
	}

	public static String getUserTeacherNameForTitle() {
		return firstName;
	}

	public static void setUsername(String username) {
		TeacherController.teachUsername = username;
	}

	public void changeLogin() {
		try {
			ResetPasswordController.setUser(teachUsername);
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

	/** This is working because */
	public void listViewGrades() {
		textAreaLeft.clear();
		ObservableList<String> names = FXCollections.observableArrayList();
		String str = MySQLAccess.returnQuery("SELECT username FROM user WHERE user_type = 'Student'", 1);
		for (String s : str.split("\\s+")) {
//			System.out.println(s);
			names.add(s);
		}
		addGradeView.setItems(names);
		removeGradeView.setItems(names);

	}

	/**
	 * Need sql statement for adding grades to the users This method is for
	 * inserting a new assignment into the database and assigning the points for
	 * each student
	 * 
	 * 
	 * Change this to add student grade to assignment and have the other add the
	 * assignment
	 * 
	 * Make part invisible and only show the Assignment Name StudentID pts erned tot
	 * pts
	 */
	public void addAssign() {
//		labelPane.setVisible(false);
		courseNum.setVisible(false);
		dueDate.setVisible(false);

		gradeAnchor.setVisible(true);
		String stdUserN = addGradeView.getSelectionModel().getSelectedItem();
		stdID.setText(MySQLAccess.returnQuery("SELECT user_id FROM user WHERE username ='" + stdUserN + "'", 1));
		assignName.setText(MySQLAccess
				.returnQuery("select assignment_name from assignment where student_id =" + stdID.getText(), 1));
//		System.out.println(query);

		gradeBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
//				String query = "UPDATE assignment set earned_points=" + ptsErnd.getText() + ", total_points ="
//						+ totlPts.getText() + "where student_id = " + stdID.getText();

				MySQLAccess
						.noReturnQuery("UPDATE assignment set earned_points=" + ptsErnd.getText() + ", total_points = "
								+ totlPts.getText() + "where student_id = " + Integer.parseInt(stdID.getText()) + ";");
				String studentName = MySQLAccess.returnQuery(
						"SELECT username, first_name, last_name FROM user WHERE username = '" + stdUserN + "'", 3);
				textForFlowLeft.setText("Assignment Added for " + studentName);
				changeTextFlow(textForFlowLeft);
				System.out.println("Made it in the event handler");

			}
		});

	}

	/**
	 * Something here to get the object for materials and edit them maybe a
	 * ArrayList with string objects made in the main controller class or here but
	 * make it static so everyone can see it
	 */
	public void addMaterial() {
		textAreaLeft.setEditable(true);
		gradeAnchor.setVisible(false);
		courseMat.setVisible(true);
		matButn.setVisible(true);
		textAreaLeft.setText("Here");
		labelPane.setVisible(true);
		matButn.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent o) {
				MySQLAccess.noReturnQuery("UPDATE course SET overview = '" + textAreaLeft.getText()
						+ "' WHERE course_id = " + courseText.getText());
				textAreaLeft.setText("Description Added");
			}
		});
	}

	public void removeMaterial() {
		textAreaLeft.setEditable(false);
		gradeAnchor.setVisible(false);
		courseMat.setVisible(false);
		labelPane.setVisible(true);
		matButn.setVisible(false);
		textAreaLeft.setText("Enter the Course ID to Remove Material and Press Enter");
		courseText.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent ke) {
				if (ke.getCode().equals(KeyCode.ENTER)) {
					System.out.println("Remove Material Enter Handle");
					MySQLAccess.noReturnQuery(
							"UPDATE course SET overview = 'TBD' WHERE course_id = " + courseText.getText());
					textAreaLeft.setText("Material Removed");
				}
			}
		});
	}

	/**
	 * For adding a new ungraded assignment with everything but student_id and
	 * earned points
	 */
	public void addNewAssignment() {
		labelPane.setVisible(false);
		gradeAnchor.setVisible(true);
		ObservableList<String> assign = FXCollections.observableArrayList();
		String str = MySQLAccess.returnQuery("SELECT course_name from course", 1);
		for (String s : str.split("\\n+")) {
			assign.add(s);
		}
		remAssign.setItems(assign);
		String courseSel = remAssign.getSelectionModel().getSelectedItem();
		courseNum.setText(
				MySQLAccess.returnQuery("SELECT course_id from course where course_name = '" + courseSel + "'", 1));

		gradeBtn.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent o) {
				MySQLAccess.noReturnQuery("insert into   SET overview = '" + textAreaLeft.getText()
						+ "' WHERE course_id = " + courseText.getText());
				textAreaLeft.setText("Description Added");
			}
		});
	}

	public void changeTextFlow(Text textLeft) {
		textAreaLeft.setText(textLeft.getText());
	}

	public void checkMessages() {
		labelPane.setVisible(false);
		gradeAnchor.setVisible(false);
		String check = "\n" + MySQLAccess
				.returnQuery("SELECT date_received,cast(message_text as NCHAR) FROM message WHERE username ='"
						+ teachUsername + "' order by date_received DESC limit 10", 2);
		textForFlowLeft.setText(check);
		changeTextFlow(textForFlowLeft);
	}

	public void sendMessage() {
		labelPane.setVisible(false);
		anchor.setVisible(true);
		gradeAnchor.setVisible(false);
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
		String messgeF = "FROM: " + teachUsername + "\n" + messageArea.getText() + "\n\n";
		MySQLAccess.noReturnQuery(
				"insert into message (username,message_text) values('" + sentUser + "','" + messgeF + "')");
		textForFlowLeft.setText("Message Sent\n" + sentUser);
		changeTextFlow(textForFlowLeft);
		messageArea.clear();
		anchor.setVisible(false);
	}
}
