package application;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class TestController implements Initializable {
	@FXML
	private PasswordField passwordTextField;
	@FXML
	private TextField usernameTextField;
	@FXML
	private Hyperlink forgotPasswordUsernameHyperLink;
	@FXML
	private Button submitButton;
	@FXML
	private TextField successFailTextField;
	@FXML
	private Label textShowLabel;
	@FXML
	private RadioButton forgotPasswordRadioButton;
	@FXML
	private Label wrongTryLabel;
	@FXML
	private MenuButton userDropDownMenuButton;
	@FXML
	private MenuItem teacherMenuItem;
	@FXML
	private MenuItem studentMenuItem;
	@FXML
	private MenuItem guardianMenuItem;
	@FXML
	private MenuItem resetPasswordMenuItem;
	private String userSelection = "";
	@FXML
	private Stage primaryStage;
	@FXML
	private Stage resetStage;
	@FXML
	private Scene resetScene;
	@FXML
	private Stage profileStage;
	@FXML
	private AnchorPane resetRoot;
	@FXML
	private AnchorPane profilePane;
	@FXML
	private Scene loginScene;
	@FXML
	private Scene profileScene;
	private boolean successfulLogin = false;

//	private ArrayList<String> userNames = new ArrayList<String>(); 

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		primaryStage = Main.getPrimaryStage();
		loginScene = Main.getScene();
		textShowLabel.setText("Enter Information");
		EventHandler<ActionEvent> event1 = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				setUserSelection(((MenuItem) e.getSource()).getText());
//				if(getUserSelection().equals("Reset Password")) {
//					try {
//						getNewStage("ResetPassword",e);
//					} catch (IOException e1) {
//						
//						e1.printStackTrace();
//					}
//				}
			}
		};
//		EventHandler<ActionEvent> resetEvent = new EventHandler<ActionEvent>() {
//			public void handle(ActionEvent e) {
//				 
//			}
//		};
//		
		studentMenuItem.setOnAction(event1);
		guardianMenuItem.setOnAction(event1);
		teacherMenuItem.setOnAction(event1);
	/** Announcements Unofficial */
//		resetPasswordMenuItem.setOnAction(event1);
	}

//	public void start(Stage resetStage){
//		resetStage.setTitle("Reset Username/Password");
//		try {
//			resetRoot = (AnchorPane)FXMLLoader.load(getClass().getResource("ResetPassword.fxml"));
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		resetScene = new Scene(resetRoot);
//		resetScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
//		resetStage.setScene(resetScene);
//		resetStage.show();
////		Parent home_page = FXMLLoader.load(getClass().getResource("ResetPassword.fxml"));
////		Stage app = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
////		app.setScene(new Scene(home_page, 300, 275));
////		app.show();
//	}
//	protected void resetPassword() {
//		loginStage = (Stage) submitButton.getScene().getWindow();

//		try {
//			resetStage = new Stage();
//			resetStage.setTitle("Reset Password/Username");
//			resetRoot = (AnchorPane) FXMLLoader.load(getClass().getResource("ResetPassword.fxml"));
//			newScene = new Scene(resetRoot);
//			newScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
//			resetStage.setScene(newScene);
//			resetStage.setOnHidden(x -> loginStage.show());
//			resetStage.show();
//			loginStage.hide();
//		} catch (Exception j) {
//			j.printStackTrace();
//		}

//	}
	public void userLogin() {

		if (usernameTextField.getText().equals("Loel")) {
			if (passwordTextField.getText().equals("123")) {
				textShowLabel.setText("Success!");
				profileView();
//				setSuccessfulLogin(true);

			} else {
				textShowLabel.setText("Invalid Password");

			}
		} else {
			textShowLabel.setText("Invalid UserName");
		}
//		if (isSuccessfulLogin()) {
//			profileView();
//		}
	}
//	public static void launchStage(String [] args) {
//		launch(args);
//	}
	public String getUserSelection() {
		return userSelection;
	}

	public void profileView() {
		
		StudentProfileView spv = new StudentProfileView();
		try {
			spv.start(profileStage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		Main.primaryStage.close();
//		profileStage = new Stage();
////		loginStage = Main.getPrimaryStage();
////		loginScene = Main.getScene();
//		try {
//			profileStage.setTitle(getUserSelection() + " Profile");
//			/**
//			 * Could use the getUserSelection to call specific fxml files for the different
//			 * scenes for the profiles, each user ie. Teacher Admin Guardian Student will
//			 * all have slightly different profile views Work on that later would also have
//			 * to change the different names for the pane and scene which with the amount
//			 * this controller class is should it be broke down and have separate
//			 * controllers for each view?
//			 */
//			profilePane = (AnchorPane) FXMLLoader.load(getClass().getResource("StudentProfileView.fxml"));
//			profileScene = new Scene(profilePane);
//			profileScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
//			primaryStage.setScene(profileScene);
////			primaryStage.sizeToScene();
//			primaryStage.show();
//		} catch (Exception e) {
//
//		}
//		try {
//			Main.primaryStage = primaryStage;
//			Main.primaryStage.setTitle("Login");
////			Parent root = (Parent) FXMLLoader.load(getClass().getResourceAsStream("FirstTest.fxml"));
//			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("FirstTest.fxml"));
//			scene = new Scene(root);
//			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
//			Main.primaryStage.setScene(scene);
//			Main.primaryStage.show();
//		} catch(Exception e) {
//			e.printStackTrace();
//		}
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
