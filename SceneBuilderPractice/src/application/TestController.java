package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
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
	private Stage loginStage;
	@FXML
	private Stage resetStage;
	@FXML
	private Scene resetScene;
	@FXML
	private AnchorPane resetRoot;
//	private ArrayList<String> userNames = new ArrayList<String>(); 

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
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
}
