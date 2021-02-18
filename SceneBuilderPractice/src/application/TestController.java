package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

public class TestController implements Initializable {
	@FXML
	private Label loginLabel;
	@FXML
	private PasswordField passwordTextField;
	@FXML
	private TextField usernameTextField;
	@FXML
	private CheckBox studentCheckBox;
	@FXML
	private CheckBox parentCheckBox;
	@FXML
	private Hyperlink forgotPasswordUsernameHyperLink;
	@FXML
	private Button loginButton;
	@FXML
	private TextField successFailTextField;
	@FXML
	private Label textShowLabel;
	@FXML
	CheckBox teacherCheckBox;
	@FXML
	private RadioButton forgotPasswordRadioButton;
	@FXML
	private Label wrongTryLabel;
	private int failedCounter = 0;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
//		loginLabel.setText("User Login");
		textShowLabel.setText("Enter Information");
//		loginButton.isPressed();
//		if (forgotPasswordUsernameHyperLink.isHover()) {
//			textShowLabel.setText("Sent to email");
//		}
//		if (forgotPasswordUsernameHyperLink.isPressed()) {
//			forgotInfoHyperText();
//		}
	}

	public void login() {
		if (studentCheckBox.isSelected()) {
//			String username = "";
//			String password = "";
////			username = usernameTextField.getText();
//			password = passwordTextField.getText();

			if (usernameTextField.getText().equals("Loel")) {
				if (passwordTextField.getText().equals("123")) {
					textShowLabel.setText("Successful Login");

				}
			} else if (!usernameTextField.getText().equals("Loel")) {
				failedCounter += 1;
			}
			if (failedCounter > 3) {
				wrongTryLabel.setText("Select Button To Reset Username/Password");
			}
		}

		if (parentCheckBox.isSelected()) {
			String username = "";
			String password = "";

			username = usernameTextField.getText().toString();
			password = passwordTextField.getText();

			if ("Loel" == username) {
				if ("123" == password) {
					textShowLabel.setText("Successful Login");

				}
			}
		}
//		if (forgotPasswordRadioButton.isSelected()) {
//			
//		}
	}

//	private void successfulStudentLogin() {
////		loginLabel.setText("Success");
//		successFailTextField.setText("Successful Login");
//	}

	public void forgotInfoHyperText() {
//		FailTextField.appendText("A new login is sent to email");
		wrongTryLabel.setText("Email is sent to reset login");
//		if (forgotPasswordUsernameHyperLink.isHover()) {
//			successFailTextField.appendText("Try again..:");
//		}

	}

}
