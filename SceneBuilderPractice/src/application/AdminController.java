package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

/**
 * I think making a text field for the database stuff is the way to go
 * 
 */
public class AdminController implements Initializable {

	private static String nameForTitle = "";
	@FXML
	private AnchorPane anchor;
	@FXML
	private AnchorPane userAnchor;
	@FXML
	private TextField newUser;
	@FXML
	private TextField newPass;
	@FXML
	private TextField newFirst;
	@FXML
	private TextField newMI;
	@FXML
	private TextField newLast;
	@FXML
	private TextField newType;
	@FXML
	private TextField newPhone;
	@FXML
	private TextField newAddr;
	@FXML
	private TextField newSchool;
	@FXML
	private Button sndEmBt;
	@FXML
	private Button mssEmal;
	@FXML
	private TextFlow textFlow;
	@FXML
	private TextArea textAreaLeft;
	@FXML
	private Label userLabel;
	@FXML
	private Button usrBT;

	private String sentUser = "";
	private TextArea emailArea;
	private TextInputDialog emailPopup;
	private Text textForFlowLeft = new Text();
	private ResetPasswordView rpv;
	private Main main;
	private static String username;
	private int choice = 0;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		anchor.setVisible(false);
		userAnchor.setVisible(false);
		main = new Main();
		rpv = new ResetPasswordView();
	}

	public void createUser() { 
		userAnchor.setVisible(true);
		userLabel.setText("Enter information to create user");
		newUser.setVisible(true);
		newPass.setVisible(true);
		newSchool.setVisible(true);
		newAddr.setVisible(true);
		newPhone.setVisible(true);
		newType.setVisible(true);
		newLast.setVisible(true);
		newMI.setVisible(true);
		newFirst.setVisible(true);
		choice = 1;
	}

	public void userChanges() {
		textAreaLeft.clear();
		if (choice == 1) {
			MySQLAccess.noReturnQuery("INSERT INTO user VALUES (NULL,'" + newUser.getText() + "','" + newPass.getText()
					+ "','" + newFirst.getText() + "','" + newMI.getText() + "','" + newLast.getText() + "','"
					+ newType.getText() + "','" + newPhone.getText() + "','" + newAddr.getText() + "','"
					+ newSchool.getText() + "')");
			textForFlowLeft.setText("User: " + newUser.getText() + " added to database");
			changeTextFlow(textForFlowLeft);
		} else if (choice == 2) {
			MySQLAccess.noReturnQuery("DELETE FROM user WHERE username = '" + newUser.getText() + "'");
			textForFlowLeft.setText("User: " + newUser.getText() + " removed from database");
			changeTextFlow(textForFlowLeft);
		}
	}

	public void removeUser() {
		userAnchor.setVisible(true);
		userLabel.setText("Enter username to remove user");
		newUser.setVisible(true);
		newUser.clear();
		newPass.setVisible(false);
		newSchool.setVisible(false);
		newAddr.setVisible(false);
		newPhone.setVisible(false);
		newType.setVisible(false);
		newLast.setVisible(false);
		newMI.setVisible(false);
		newFirst.setVisible(false);
		choice = 2;
	}

	public void updateLogin() {
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

	public static void setNameForTitle(String name) {
		username = name;
		nameForTitle = MySQLAccess.getFirstName(username);
	}

	public static String getUserAdminNameForTitle() {
		return nameForTitle;
	}

	public void checkEmail() {
		String check = "\n"
				+ MySQLAccess.returnQuery("SELECT date_received,cast(message as NCHAR) FROM user_email WHERE email ='"
						+ username + "@p2k.com' order by date_received DESC limit 5", 2);
		textForFlowLeft.setText(check);
		changeTextFlow(textForFlowLeft);
	}

	/*
	 * Need a way to send it to the database instead of keep checking maybe add a
	 * button on the tilepane
	 **/
	public void sendMail() {
		anchor.setVisible(true);
		sndEmBt.setVisible(true);
		mssEmal.setVisible(false);
		emailPopup = new TextInputDialog();
		emailPopup.setHeaderText("Enter the Username you would like to send message to.");
		emailArea = new TextArea();
		emailArea.setWrapText(true);
		emailArea.setPromptText("Enter Message Here");
		emailArea.setPrefSize(307, 376);
		anchor.getChildren().add(emailArea);
		emailPopup.showAndWait();
		sentUser = emailPopup.getEditor().getText();
		if (sentUser.length() > 8 || sentUser.length() < 8) {
			emailPopup.setHeaderText("This is not a valid Username Please Try Again");
			emailPopup.showAndWait();
		}

	}

	/** Get the list of names from the database in an ArrayList just like log in */
	public void sendMassMail() {
		anchor.setVisible(true);
		sndEmBt.setVisible(false);
		mssEmal.setVisible(true);
		emailArea = new TextArea();
		emailArea.setWrapText(true);
		emailArea.setPromptText("Enter Message Here");
		emailArea.setPrefSize(307, 376);
		anchor.getChildren().add(emailArea);
	}

	public void massSent() {
		for (String s : MySQLAccess.getUsername()) {

			String email = s + "@p2k.com";
			String emailF = "FROM: " + username + "@p2k.com\n" + emailArea.getText() + "\n";
			MySQLAccess
					.noReturnQuery("INSERT INTO user_email (email, message) VALUES ('" + email + "','" + emailF + "')");

		}
		textForFlowLeft.setText("Email Sent To All Users\n");
		changeTextFlow(textForFlowLeft);
		emailArea.clear();
		anchor.setVisible(false);
	}

	public void emailSent() {
		sentUser = sentUser + "@p2k.com";
		String emailF = "FROM: " + username + "@p2k.com\n" + emailArea.getText() + "\n\n";
		MySQLAccess.noReturnQuery("insert into user_email (email,message) values('" + sentUser + "','" + emailF + "')");
		textForFlowLeft.setText("Email Sent\n" + sentUser);
		changeTextFlow(textForFlowLeft);
		emailArea.clear();
		anchor.setVisible(false);
	}

	public void changeTextFlow(Text textLeft) {
		textAreaLeft.setText(textLeft.getText());
	}

}
