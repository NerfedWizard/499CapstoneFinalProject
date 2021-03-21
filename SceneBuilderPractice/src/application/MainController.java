package application;

import java.net.URL;
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
	private boolean successfulLogin = false;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		studentView = new StudentProfileView();
		adminView = new AdminView();
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
		/** Announcements Unofficial */

	}

	public void userLogin() {

		if (usernameTextField.getText().equals("Loel")) {
			if (passwordTextField.getText().equals("123")) {
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
				}

			} else {
				textShowLabel.setText("Invalid Password");

			}
		} else {
			textShowLabel.setText("Invalid UserName");
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
