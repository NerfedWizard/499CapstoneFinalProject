package application;

import java.net.URL;
import java.util.ResourceBundle;

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
//	private String userSelection = "";
	private StudentProfileView studentView;
	private AdminView adminView;
	private TeacherView teacherView;
	private ResetPasswordView resetPassUser;
	private GuardianView guardianView;
	private ForgotInfoView forgot;
//	private FacultyView facultyView;
//	private boolean successfulLogin = false;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		studentView = new StudentProfileView();
		adminView = new AdminView();
		teacherView = new TeacherView();
		resetPassUser = new ResetPasswordView();
		guardianView = new GuardianView();
		forgot = new ForgotInfoView();
//		facultyView = new FacultyView();

//		EventHandler<ActionEvent> event1 = new EventHandler<ActionEvent>() {
//			public void handle(ActionEvent e) {
//				setUserSelection(((MenuItem) e.getSource()).getText());
//				userDropDownMenuButton.setText(getUserSelection());
//
//			}
//		};
		passwordTextField.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent ke) {
				if (ke.getCode().equals(KeyCode.ENTER)) {
					userLogin();
				}
			}
		});

	}

	/**
	 * This is working and sending you back to login after but instead of when
	 * successful it is always Also this is not the one we need this is for changing
	 * password and noth the forgot info view so need to change that but this is how
	 * we can add the functionality to the other views like student and what not for
	 * it to go back to login after changing the password
	 */
	public void forgotInfo() {
		Stage stage = (Stage) submitButton.getScene().getWindow();
//		if (getUserSelection().equals("Forgot Password")) {
//
		try {
			forgot.start(stage);
		} catch (Exception e) {
			e.printStackTrace();
		}
//
//		}
	}

	/**
	 * For logging in the user of any type, but they have to select the user type
	 * before able to move forward with login or it will tell them to select a user
	 * type
	 * 
	 * 
	 * For testing all but the admin can log in with 'root' 'password'
	 */

	public void userLogin() {
//		System.out.println(getUserSelection());// Just Testing things
		Stage stage = (Stage) submitButton.getScene().getWindow();

		for (String s : MySQLAccess.getUsername()) {
//			System.out.println(s);
			for (String p : MySQLAccess.getPassword()) {
				if (usernameTextField.getText().equals(s)) {
					if (passwordTextField.getText().equals(p)) {
						textShowLabel.setText("Success!");
						String userType = MySQLAccess
								.returnQuery("select user_type from user where username ='" + s + "'", 1).trim();
//						int userNum = userNum(userType);
//						System.out.println(userType.length());
//							setUserSelection(userType);
						if (userType.equals("Student")) {
							StudentController.setNameForTitle(s);
//							System.out.println(userType.length());
//								Stage stage = (Stage) submitButton.getScene().getWindow();
							try {
								studentView.start(stage);
							} catch (Exception e) {
								e.printStackTrace();
//									System.out.println("In user login Exception");
							}
						} else if (userType.equals("Admin")) {
							AdminController.setNameForTitle(s);
							System.out.println(userType.length());
//								Stage stage = (Stage) submitButton.getScene().getWindow();
							try {
								adminView.start(stage);
							} catch (Exception e) {
								e.printStackTrace();
							}
						} else if (userType.equals("Teacher")) {
							TeacherController.setNameForTitle(s);
							System.out.println(userType.length());
//								Stage stage = (Stage) submitButton.getScene().getWindow();
							try {
								teacherView.start(stage);
							} catch (Exception e) {
								e.printStackTrace();
							}
						} else if (userType.equals("Guardian")) {
							GuardianController.setNameForTitle(s);
							System.out.println(userType.length());
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
//	}

//	public String getUserSelection() {
//		return this.userSelection;
//	}
//
//	public void setUserSelection(String userSelection) {
//		this.userSelection = userSelection;
//	}

//	public int userNum(String type) {
//		int usertype = 0;
//		if (type.equals("Student")) {
//			usertype = 2;
//		} else if (type.equals("Admin")) {
//			usertype = 0;
//		} else if (type.equals("Teacher")) {
//			usertype = 1;
//		} else if (type.equals("Guardian")) {
//			usertype = 3;
//		} else {
//			usertype = 9;
//		}
//		return usertype;
//	}

//	public boolean isSuccessfulLogin() {
//		return successfulLogin;
//	}
//
//	public void setSuccessfulLogin(boolean successfulLogin) {
//		this.successfulLogin = successfulLogin;
//	}
}
