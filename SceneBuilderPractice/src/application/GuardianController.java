package application;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.property.Property;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
/**
 * @Version 1.0
 * @Date 03/29/2021
 */

/** Controller to be used for the Guardian profile */
public class GuardianController implements Initializable {
	/**
//	 * Properites for the guardian view.  @FXML is need to so scene builder can
//	 * read properties for code assignment, can also hard code.
//	 */
//	// Normal variables:
	private static String nameForTitle = "";
//	ObservableList list = FXCollections.observableArrayList();
////	ObservableList<Student> StudentList = FXCollections.observableArrayList();
//	private Main main;
//
//	// TableView properties:
//	@FXML private TableView<Student> tableView;
//	@FXML private TableColumn<Student, String> studentIDColumn;
//	@FXML private TableColumn<Student, String> firstNameColumn;
//	@FXML private TableColumn<Student, String> lastNameColumn;
//	@FXML private TableColumn<Student, Integer> gradeColumn;
////	@FXML private TableColumn<Student, String> guardian1Column;
//
//	// ListView Properties:
//	@FXML private ListView<String> listViewContent;
//
//	// Menu Properties:
	@FXML private MenuItem courseMenuItem;
//	@FXML private MenuItem messageMenuItem;
//	@FXML private MenuItem logoutMenuItem;
//	@FXML private MenuItem gradesMenuItem;
//	@FXML private MenuItem courseMaterialMenuItem;
//	@FXML private MenuItem courseMaterialAssignmentMenuItem;
//	@FXML private MenuItem courseMaterialMaterialMenuItem;
//	@FXML private MenuItem messageSendEmailMenuItem;
//	@FXML private MenuItem messageCheckEmailMenuItem;
//	@FXML private MenuItem manageMeetingMenuItem;
//	@FXML private MenuItem changePasswordMenuItem;
//
	@Override
	 public void initialize(URL arg0, ResourceBundle arg1) {
		
	}
//		System.out.println("Started Guardian Initialize method.");
//		main = new Main();
//
//		// Load placeholder data for left side content ListView.
//		listViewLoadData();
//
//		// Sets up the columns in the tableView.
//		firstNameColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("firstName"));
//		lastNameColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("lastName"));
//		studentIDColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("studentID"));
////		guardian1Column.setCellValueFactory(new PropertyValueFactory<Student, String>("Guardian1"));
//		gradeColumn.setCellValueFactory(new PropertyValueFactory<Student, Integer>("grade"));
//
//		// Load data for tableView.
////		tableView.setItems(getStudents());
//	}
//
//	/**
//	 * This method will return an ObserveableList of Student objects.
//	 * Dummy data for tableview.
//	 * @return students
//	 *  A list of students.
//	 */
//	public ObservableList<Student> getStudents() {
//		ObservableList<Student> students = FXCollections.observableArrayList();
//		students.add(new Student("Luke", "Pha", "123456",  12));
//		students.add(new Student("Loel", "test", "100001",  11));
//		students.add(new Student("Adnan", "test2", "100002",  10));
//		students.add(new Student("Cedar", "test3", "100003",  9));
//		return students;
//
//	}
//
//	/**
//	 * This method is for when Logout is clicked.
//	 */
//	 private void logoutAction() {
//		System.out.println("Student logout button clicked.");
//		try {
//			main.start(Main.logStage);// shows you can go to any view from any view if needed
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	/**
//	 * This method is for when grade is clicked.
//	 */
//	private void gradeAction() {
//		System.out.println("Grade tab clicked.");
////		tableView.setItems(getStudents());
//	}
//
//
//	/**
//	 * This method is for when course material is clicked.
//	 */
//	 private void courseMaterialAction() {
//		System.out.println("courseMaterialMessage clicked.");
//		this.list.removeAll();
//		this.listViewContent.getItems().removeAll();
//		this.listViewContent.getItems().add("Table of Contents");
//		this.listViewContent.getItems().add("Introductions");
//		this.listViewContent.getItems().add("Syllabus");
//		this.listViewContent.getItems().add("Resources");
//		this.listViewContent.getItems().add("Session 1");
//		this.listViewContent.getItems().add("Session 2");
//	}
//
//
//	/**
//	 * This method is for when send email is clicked.
//	 */
//	 private void sendEmailAction() {
//		System.out.println("Send email action clicked.");
//	}
//
//
//	/**
//	 * This method is for when checked email is clicked.
//	 */
//	 private void checkEmailAction() {
//		System.out.println("Check email action clicked.");
//	}
//
//
//	/**
//	 * This method is for when manage meeting is clicked.
//	 */
//	 private void manageMeetingAction() {
//		System.out.println("Manage meeting action clicked.");
//	}
//
//
//	/**
//	 * This method is for when change password is clicked.
//	 */
//	 private void changePasswordAction() {
//		System.out.println("Change password action clicked.");
//	}
//
//	/**
//	 * This method changes the left side of the border pane for guardian view.
//	 * This adds the content data to the content sections.
//	 * @note Link for help: http://tutorials.jenkov.com/javafx/listview.html
//	 *
//	 */
//	 private void listViewLoadData(){
//		list.removeAll(list);
//		this.listViewContent.getItems().removeAll();
//		this.listViewContent.getItems().add("Course not chosen");
//		this.listViewContent.getItems().add("Course not chosen");
//		this.listViewContent.getItems().add("Course not chosen");
//
//	}
//
	 public static void setNameForTitle(String name, String userType) {
		System.out.println(name + "   In the guardian controller");
		nameForTitle = MySQLAccess.getFirstName(name, userType);
	}

}