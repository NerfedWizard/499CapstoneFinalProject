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
	private Button sndMes;
	@FXML
	private Button mssMess;
	@FXML
	private TextFlow textFlow;
	@FXML
	private TextArea textAreaLeft;
	@FXML
	private Label userLabel;
	@FXML
	private Button usrBT;

	private String sentUser = "";
	private TextArea messageArea;
	private TextInputDialog messagePopup;
	private Text textForFlowLeft = new Text();
	private ResetPasswordView rpv;
	private Main main;
	private static String adminUsername;
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
			ResetPasswordController.setUser(adminUsername);
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
		adminUsername = name;
		nameForTitle = MySQLAccess.getFirstName(adminUsername);
	}

	public static String getUserAdminNameForTitle() {
		return nameForTitle;
	}

	public void checkMessages() {
		String check = "\n" + MySQLAccess
				.returnQuery("SELECT date_received,cast(message_text as NCHAR) FROM message WHERE username ='"
						+ adminUsername + "' order by date_received DESC limit 10", 2);
		textForFlowLeft.setText(check);
		changeTextFlow(textForFlowLeft);
	}

	/*
	 * Need a way to send it to the database instead of keep checking maybe add a
	 * button on the tilepane
	 **/
	public void sendMessage() {
		anchor.setVisible(true);
		sndMes.setVisible(true);
		mssMess.setVisible(false);
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

	/** Get the list of names from the database in an ArrayList just like log in */
	public void sendMassMessage() {
		anchor.setVisible(true);
		sndMes.setVisible(false);
		mssMess.setVisible(true);
		messageArea = new TextArea();
		messageArea.setWrapText(true);
		messageArea.setPromptText("Enter Message Here");
		messageArea.setPrefSize(307, 376);
		anchor.getChildren().add(messageArea);
	}

	public void massSent() {
		for (String s : MySQLAccess.getUsername()) {
			String users = s;
			String emailF = "FROM: " + adminUsername
					+ "\n" + messageArea.getText() + "\n";
			MySQLAccess.noReturnQuery(
					"INSERT INTO message (username, message_text) VALUES ('" + users + "','" + emailF + "')");

		}
		textForFlowLeft.setText("Message Sent To All Users\n");
		changeTextFlow(textForFlowLeft);
		messageArea.clear();
		anchor.setVisible(false);
	}

	public void messageSent() {
//		sentUser = sentUser + "@p2k.com";
		String emailF = "FROM: " + adminUsername + "\n" + messageArea.getText() + "\n\n";
		MySQLAccess.noReturnQuery(
				"insert into message (username,message_text) values('" + sentUser + "','" + emailF + "')");
		textForFlowLeft.setText("Message Sent\n" + sentUser);
		changeTextFlow(textForFlowLeft);
		messageArea.clear();
		anchor.setVisible(false);
	}

	public void changeTextFlow(Text textLeft) {
		textAreaLeft.setText(textLeft.getText());
	}

}
