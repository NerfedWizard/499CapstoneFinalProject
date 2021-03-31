package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class ResetPasswordController implements Initializable {
	@FXML
	private Label oldEntry;
	@FXML
	private Label newEntry;
	@FXML
	private Label newEntry2;
	@FXML
	private Label titleText;
	@FXML
	private TextField toBeChanged;
	@FXML
	private TextField newInsert;
	@FXML
	private TextField newInsert2;
	@FXML
	private Button submit;
	@FXML
	private MenuButton dropDownMenu;
	@FXML
	private MenuItem usernameItem;
	@FXML
	private MenuItem passwordItem;
	private String query = "";
	private int choice = 0;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	}

	/** Might need a boolean check to make sure the update performed */
	public void changePassword() {
		oldEntry.setText("Old Password");
		newEntry.setText("New Password (8 characters)");
		dropDownMenu.setText("Password");
		this.choice = 1;
		System.out.println(StudentController.getUsername());
	}

	public void changeUsername() {
		dropDownMenu.setText("Username");
		System.out.println(StudentController.getUsername());
		oldEntry.setText("Old Username");
		newEntry.setText("New Username");
		this.choice = 2;

	}

	public void buttonSubmit() {
		MainController mC = new MainController();
		System.out.println(mC.getUserSelection());
		if (this.choice == 1) {
			this.query = "UPDATE student SET password ='" + newInsert.getText() + "'where password ='"
					+ toBeChanged.getText() + "';";
		} else if (this.choice == 2) {
			this.query = "UPDATE student SET username ='" + newInsert.getText() + "'where username ='"
					+ toBeChanged.getText() + "';";
		}
		if (this.query.equals("")) {
			oldEntry.setTextFill(Color.color(1, 0, 0));
			newEntry.setTextFill(Color.color(1, 0, 0));
			oldEntry.setText("Required");
			newEntry.setText("Required");
		} else {
			MySQLAccess.noReturnQuery(this.query);
		}
	}
}
