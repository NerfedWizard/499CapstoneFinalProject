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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
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
	@FXML
	private MenuItem facultyMenuItem;
	private KeyCode keycode;
	private String userSelection = "";
	private StudentProfileView studentView;
	private AdminView adminView;
	private TeacherView teacherView;
	private ResetPasswordView resetPassUser;
	private GuardianView guardianView;
	private FacultyView facultyView;
	private boolean successfulLogin = false;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		studentView = new StudentProfileView();
		adminView = new AdminView();
		teacherView = new TeacherView();
		resetPassUser = new ResetPasswordView();
		guardianView = new GuardianView();
		facultyView = new FacultyView();

		EventHandler<ActionEvent> event1 = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				setUserSelection(((MenuItem) e.getSource()).getText());
				userDropDownMenuButton.setText(getUserSelection());

			}
		};
		passwordTextField.setOnKeyPressed(new EventHandler<KeyEvent>() {
		    @Override
		    public void handle(KeyEvent ke) {
		        if (ke.getCode().equals(KeyCode.ENTER)) {
		            userLogin();
		        }
		    }
		}); 

		studentMenuItem.setOnAction(event1);
		guardianMenuItem.setOnAction(event1);
		teacherMenuItem.setOnAction(event1);
		adminMenuItem.setOnAction(event1);
		facultyMenuItem.setOnAction(event1);
		resetPasswordMenuItem.setOnAction(event1);

	}

	/**
	 * For logging in the user of any type, but they have to select the user type
	 * before able to move forward with login or it will tell them to select a user
	 * type
	 * 
	 * 
	 * For testing all but the admin can log in with 'root' 'password'
	 */
	@FXML public void onEnter(ActionEvent ae) {
		System.out.println("Test for enter");
	}
//	passwordTextField.setOnKeyPressed(new EventHandler<KeyEvent>() {
//	    @Override
//	    public void handle(KeyEvent ke) {
//	        if (ke.getCode().equals(KeyCode.ENTER)) {
//	            userLogin();
//	        }
//	    }
//	});
	public void userLogin() {
		System.out.println(getUserSelection());// Just Testing things
		Stage stage = (Stage) submitButton.getScene().getWindow();
		if (getUserSelection().equals("Reset Password")) {

			try {
				resetPassUser.start(stage);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else if (getUserSelection().equals("")) {
			textShowLabel.setText("Please select the user from dropdown");
		} else {
			for (String s : MySQLAccess.getUsername(getUserSelection())) {
				for (String p : MySQLAccess.getPassword(getUserSelection())) {
					if (usernameTextField.getText().equals(s)) {
						if (passwordTextField.getText().equals(p)) {
							textShowLabel.setText("Success!");
							if (getUserSelection().equals("Student")) {
								StudentController.setNameForTitle(s, getUserSelection());
//								Stage stage = (Stage) submitButton.getScene().getWindow();
								try {
									studentView.start(stage);
								} catch (Exception e) {

									e.printStackTrace();
								}

							} else if (getUserSelection().equals("Admin")) {
								AdminController.setNameForTitle(s, getUserSelection());
//								Stage stage = (Stage) submitButton.getScene().getWindow();
								try {
									adminView.start(stage);
								} catch (Exception e) {
									e.printStackTrace();
								}
							} else if (getUserSelection().equals("Teacher")) {
								TeacherController.setNameForTitle(s, getUserSelection());
//								Stage stage = (Stage) submitButton.getScene().getWindow();
								try {
									teacherView.start(stage);
								} catch (Exception e) {
									e.printStackTrace();
								}
							} else if (getUserSelection().equals("Faculty")) {
								FacultyController.setNameForTitle(s, getUserSelection());
								try {
									facultyView.start(stage);
								} catch (Exception e) {

									e.printStackTrace();
								}
							} else if (getUserSelection().equals("Guardian")) {
								GuardianController.setNameForTitle(s, getUserSelection());
								try {
									guardianView.start(stage);
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
					}
				}
			}
		}
	}
//		if (getUserSelection().equals("Reset Password")) {
//			Stage stage = (Stage) submitButton.getScene().getWindow();
//			try {
//				resetPassUser.start(stage);
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//
//	}

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
