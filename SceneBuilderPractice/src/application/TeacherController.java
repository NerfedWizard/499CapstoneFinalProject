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
	private TextArea emailArea;
	private TextInputDialog emailPopup;
	private Main main;
	static String userType;
	private static String username;
	private ResetPasswordView rpv;
	static String sID = "";
	@FXML
	private AnchorPane gradeAnchor;
	@FXML
	private Button sendEmail;
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

	/** This is working because */
	public void listViewGrades() {
		textAreaLeft.clear();
//		labelPane.setVisible(false);
		ObservableList<String> names = FXCollections.observableArrayList();
		String str = MySQLAccess.returnQuery("SELECT username FROM user WHERE user_type = 'Student'", 1);
		for (String s : str.split("\\s+")) {
			System.out.println(s);
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

				MySQLAccess.noReturnQuery("UPDATE assignment set earned_points=" + ptsErnd.getText() + ", total_points = "
						+ totlPts.getText() + "where student_id = " + Integer.parseInt(stdID.getText())+";");
				String studentName = MySQLAccess.returnQuery(
						"SELECT username, first_name, last_name FROM user WHERE username = '" + stdUserN + "'", 3);
				textForFlowLeft.setText("Assignment Added for " + studentName);
				changeTextFlow(textForFlowLeft);
				System.out.println("Made it in the event handler");

			}
		});

	}

//	/**
//	 * This method is for altering the grades of assignments and will be an update
//	 * not insert
//	 */
//	public void changePoints() {
////		labelPane.setVisible(false);
//		courseNum.setVisible(false);
//		gradeAnchor.setVisible(true);
//
//	}

	/**
	 * Something here to get the object for materials and edit them maybe a
	 * ArrayList with string objects made in the main controller class or here but
	 * make it static so everyone can see it
	 */
	public void addMaterial() {
		textAreaLeft.setEditable(true);
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
//		labelPane.setVisible(false);
	}

	public void removeMaterial() {
//		textAreaLeft.clear();
		textAreaLeft.setEditable(false);
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
//					textAreaLeft.clear();
					textAreaLeft.setText("Material Removed");
				}
			}
		});
//		labelPane.setVisible(false);
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
				MySQLAccess.noReturnQuery("insert into  SET overview = '" + textAreaLeft.getText()
						+ "' WHERE course_id = " + courseText.getText());
				textAreaLeft.setText("Description Added");
			}
		});
	}

	public void changeTextFlow(Text textLeft) {
		textAreaLeft.setText(textLeft.getText());
	}

	public void checkEmail() {
		labelPane.setVisible(false);
		textForFlowLeft.setText(MySQLAccess.returnQuery(
				"select cast(message as NCHAR) from user_email where email ='" + username + "@p2k.com'", 1));
		changeTextFlow(textForFlowLeft);

	}

	public void sendMail() {
		labelPane.setVisible(false);
		anchor.setVisible(true);
		emailPopup = new TextInputDialog();
		emailPopup.setHeaderText("Enter the Username you would like to send message to.");
		emailArea = new TextArea();
		emailArea.setWrapText(true);
		emailArea.setPromptText("Enter Message Here");
		emailArea.setPrefSize(307, 376);
		anchor.getChildren().add(emailArea);
		emailPopup.showAndWait();
		sentUser = emailPopup.getEditor().getText();
		if (sentUser.length() > 8) {
			emailPopup.setHeaderText("This is not a valid Username Please Try Again");
			emailPopup.showAndWait();
		}

	}

	public void emailSent() {
		sentUser = sentUser + "@p2k.com";
		MySQLAccess.noReturnQuery("UPDATE user_email SET message ='" + emailArea.getText() + "\nFrom Username\n"
				+ username + "' WHERE email ='" + sentUser + "'");
		MySQLAccess.noReturnQuery("UPDATE user_email SET received = now() where email = '" + sentUser + "'");
		textForFlowLeft.setText("Email Sent\n" + sentUser);
		changeTextFlow(textForFlowLeft);
		emailArea.clear();
		anchor.setVisible(false);
	}
}
