package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class MainController implements Initializable {
	@FXML
	private PasswordField passwordTextField;
	@FXML
	private TextField usernameTextField;
	@FXML
	private Button submitButton;
	@FXML
	private Label textShowLabel;
	@FXML
	private RadioButton forgotPasswordRadioButton;
	@FXML
	private MenuButton userDropDownMenuButton;
	@FXML
	private MenuItem teacherMenuItem;
	@FXML
	private MenuItem studentMenuItem;
	@FXML
	private MenuItem guardianMenuItem;
	@FXML
	private MenuItem adminMenuItem;
	@FXML
	private MenuItem resetPasswordMenuItem;
	private String userSelection = "";
	private StudentProfileView studentView;
	private AdminView adminView;
	private TeacherView teacherView;
	private ResetPasswordView resetPassUser;
	private boolean successfulLogin = false;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		studentView = new StudentProfileView();
		adminView = new AdminView();
		teacherView = new TeacherView();
		

		EventHandler<ActionEvent> event1 = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				setUserSelection(((MenuItem) e.getSource()).getText());
				userDropDownMenuButton.setText(getUserSelection());

			}
		};

		studentMenuItem.setOnAction(event1);
		guardianMenuItem.setOnAction(event1);
		teacherMenuItem.setOnAction(event1);
		adminMenuItem.setOnAction(event1);
//		resetPasswordMenuItem.setOnAction(event1);
//		MySQLAccess database = new MySQLAccess();
//		try {
//			database.readDataBase();
//		} catch (Exception e1) {
//			e1.printStackTrace();
//		}
		/** Announcements Unofficial */

	}

	public void userLogin() {
//		int wrongCount = 0;
		System.out.println(getUserSelection());
		
		/** Make something here that grabs the results and loops through checking the username and passwords 
		 * 
		 * 
		 * Not doing the command now gotta look into that 
		 * going to bed 
		 * */
		ArrayList<String> usernames = MySQLAccess.getUsername();
		ArrayList<String> passwords = MySQLAccess.getPassword();
		int count = usernames.size();
//		System.out.println(MySQLAccess.getUsername().get(1));
//		while(count != 0) {
		if (usernameTextField.getText().equals(MySQLAccess.getUsername().get(count))) {
			if (passwordTextField.getText().equals(passwords.get(count))) {
				textShowLabel.setText("Success!");
				if (getUserSelection().equals("Student")) {
					Stage stage = (Stage) submitButton.getScene().getWindow();
					try {
						studentView.start(stage);
					} catch (Exception e) {

						e.printStackTrace();
					}

				} else if (getUserSelection().equals("Admin")) {
					Stage stage = (Stage) submitButton.getScene().getWindow();
					try {
						adminView.start(stage);
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else if (getUserSelection().equals("Teacher")) {
					Stage stage = (Stage) submitButton.getScene().getWindow();
					try {
						teacherView.start(stage);
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {
					textShowLabel.setText("Please Select A User");
				}

			} else {
				textShowLabel.setText("Invalid Password");
			}
		} else {
			textShowLabel.setText("Invalid UserName");
//			wrongCount++;
//			int wrong = 5 - wrongCount;
//			textShowLabel.setText("Only " + wrong + " Tries left!");
//		}
		count--;
		}
		if (getUserSelection().equals("Reset Password")) {
			Stage stage = (Stage) submitButton.getScene().getWindow();
			try {
				resetPassUser.start(stage);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public String getUserSelection() {
		return userSelection;
	}

	public void setUserSelection(String userSelection) {
		this.userSelection = userSelection;
	}

	public boolean isSuccessfulLogin() {
		return successfulLogin;
	}

	public void setSuccessfulLogin(boolean successfulLogin) {
		this.successfulLogin = successfulLogin;
	}
}
