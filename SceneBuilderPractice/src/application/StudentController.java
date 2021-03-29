package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

/**
 * Controller to be used for the student profile
 * 
 * Had to go with a login button outside of the menu for going back to the login
 * view now inside the menu it will have change password and change user name
 */
public class StudentController implements Initializable {

	@FXML
	private MenuItem materialsMenuItem;
	@FXML
	private TextFlow textFlow;
	@FXML
	private TextArea textAreaLeft;
	@FXML
	private TextArea textAreaRight;
	@FXML
	private Button logoutButton;
	@FXML
	private MenuItem changePass;
	private Text textForFlowLeft = new Text();// This is going to have to connect to the database for setting the
												// queries to text but in a formatted output
	private Text textForFlowRight = new Text();
	static String nameForTitle;
	private Main main;
	static String userType;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		main = new Main();// This was the culprit for going back to login forgot to initialize it. ARGH!!
	}

	public void studentLogout() {
		try {
			main.start(Main.logStage);// shows you can go to any view from any view if needed
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void setNameForTitle(String name, String userType) {
//		StudentController.userType = userType;
		System.out.println(name + "   In the student controller");
		nameForTitle = MySQLAccess.getFirstName(name, userType);
	}

	public static String getUserStudentNameForTitle() {
		return nameForTitle;
	}

	public void changeTextFlow(Text textLeft, Text textRight) {
		textAreaLeft.setText(textLeft.getText());
		textAreaRight.setText(textRight.getText());
	}

	/** Needs connecting to database for grade pulls */
	public void getGrades() {
		textForFlowLeft.setText("You are Failing");
		textForFlowRight.setText("F-");
		changeTextFlow(textForFlowLeft, textForFlowRight);
	}

	public void getAssignments() {
		textForFlowLeft.setText("Design a Dyson Sphere and harness the power of the sun ");
		textForFlowRight.setText("Due Date is tomorrow");
		changeTextFlow(textForFlowLeft, textForFlowRight);
	}

	public void getMaterials() {
		textForFlowLeft.setText("Loel Nelson\r\n" + "Beverly Hill \r\n" + "LING 316-01\r\n" + "November 29th, 2018\r\n"
				+ "\tBilingual: Greek, English and More\r\n"
				+ "Bilingual is defined by the Oxford dictionary as having or characterized by two languages.  To get a better handle on the ins and outs of bilinguals I interviewed a Greek family who run a local restaurant I work part time at.  Marcos and Athanasia Chouliouris were born in Greece and moved to the Untied States in the early 80s.  Marcos speaks Greek and English along with Turkish, Russian and some Spanish.  Athanasia or Sula as she likes to go by speaks only Greek and English and she has the heavier accent.  Shortly after moving they were married and had two children.  Anastasia or Stacy and Efstathios or Steven their children are both fluent in multiple languages.  Stacy speaks English, Greek, Spanish and French fluently and was always able to pick up languages faster.  Steven is fluent in Greek, English, Spanish and is in the process of learning Japanese.  Bilingual children have the potential to learn languages faster than monolingual children by having been exposed to it early on in childhood.  By speaking and understanding more than one language has opened many doors for Stacy and Steven in their adulthood.  Is learning two or more languages early on in childhood a benefit? Do all multilingual households end up having bilingual children? \r\n"
				+ "\tGrowing up Stacy and Steven learned both Greek and English at different rates.  Stacy who is the oldest learned younger both languages Sula says this was because they were able to spend more time with her.  Sula as a young mom spent her time with Stacy while Marcos worked.  They moved to Rosemount, MN and opened a Greek restaurant T.O.P.S pizza and gyros and it wasn’t until then when Steven was born.  Steven being the youngest spent a lot of time with his sister while his parents ran the restaurant.  This might be the cause of Stacy progressing faster than Steven when learning both languages.  Steven wasn’t speaking much Greek until around the age of five and what he did speak was very broken between the two.  I notice this in the restaurant, code switching or going from one language to another mid-sentence.  This is not to say Steven was learning to speak slower than other monolingual children, he was having trouble picking up on both.  I asked what language they spoke most often in the household and the answer was they weren’t at home much and would speak both equally.  \r\n"
				+ "\tHaving grown up speaking dual languages Stacy and Steven were able to pick up on new languages with ease.  Stacy fluently speaks English, Greek, French and Spanish.  Steven speaks English, Spanish, Greek and is in the process of learning Japanese and although not fluent yet he is picking it up for just starting.  Sula told me a story of Stacy going to Greece for the summer where all she spoke was Greek and when the summer was over, she called her mom and said she had forgotten how to speak English.  Both children went to English speaking schools, in fact, I am the same age as Stacy and wasn’t aware she even knew Greek until seeing her at T.O.P.S. years after high school.  Now, in there adult years both Stacy and Steven have children with English speaking spouses, but their children can only understand Greek and only know few words.\r\n"
				+ "\tSo, it isn’t always the case that multilingual households will have bilingual children.  This is also shown in a friend of Marcos and Sula, Argiris, who has three children with only one speaking fluent Greek.  Argiris is married to an English-speaking wife, while the oldest son speaks Greek the youngest, as his dad says, never had any interest in speaking Greek.  This shows to me that although the option is there the children must have a want to learn the language it doesn’t just happen. \r\n"
				+ "\tStacy having multiple languages under her belt has made great career advancements.  Steven being a computer science professional has also used his knowledge of other languages to further his career.  He also said being able to pick up on new languages made learning coding that much easier.  This shows that knowing multiple languages doesn’t hurt in the long run but rather helps open more doors.  Doors that might not have opened for monolinguals.  \r\n"
				+ "\tTalking with Marcos and Sula, I asked them about code switching and if speaking both languages confused the children at times.  Marcos said this didn’t have any effect on the children, but Sula had a different view.  She went on to tell me about Steven having issues in school with making friends.  The kids at school would make fun of Steven when he would slip up and speak Greek.  As I stated before, code switching is prevalent with Marcos and Sula at the restaurant as well even when talking to customers.  More than code switching I see a lot of “Marco (followed by Greek)” which is Sula asking a certain word in English and getting the answer back.  Marcos does seem to know more English than Sula, but with any culture you did not grow up in the idioms fall a little short most of the time.  The latest I heard was supposed to be “Close but no cigar” but when Marcos said it “not close enough for cigar” we all knew what he meant but I still must correct him.  The little things like that and the accent are reminders they speak English as a second language.  \r\n"
				+ "\tIn closing, bilinguals from what I have gathered from the Chouliouris family do learn new languages faster than monolinguals.  Anastasia and Efstathios are prime examples of the benefits of being bilingual.  There was evidence however in Argiris’s family that not all members of multilingual households pick up the dual languages.  The members of the family that do pick up both the native language and the language spoken by parents do pick up more languages and have more options when choosing life’s path.  Quick story, Argiris is Greek and his wife, although American, has a French family history so they call their kids Freeks or French Greeks.  That story makes me laugh every time Argiris tells it.  The other big joke it seems from all the Greeks I have met in the restaurant is, “I’m not a damn Greek God only a goddamn Greek”.\r\n"
				+ "\r\n" + "\r\n" + "Work Cited\r\n" + "\r\n"
				+ " Chouliouris, Athanasia and Marcos. Personal interview. 27 November 2018.\r\n"
				+ "“Bilingual, Multilingual, Monolingual.” Oxford English Dictionary, online ed., 2018.\r\n"
				+ "	\r\n" + "	\r\n" + "");
		textForFlowRight.setText("");
		changeTextFlow(textForFlowLeft, textForFlowRight);
	}

	public void changePassword() {
		String newPassword = "";
		String oldPassword = "";
		String passwordsForReset = "";
		textForFlowLeft.setText("Enter New Password and Old Password seperated by - ");
		textAreaRight.setText(textForFlowLeft.getText());
		textForFlowRight.setText("Enter Passwords Here!");
		textForFlowRight.setText("");
		while (textForFlowRight.getText().equals("")) {
			passwordsForReset = textForFlowRight.getText();
		}
		int indexPass = passwordsForReset.indexOf("-");
		int endStr = passwordsForReset.length() - 1;
		if (indexPass != -1) {
			newPassword = passwordsForReset.substring(0, indexPass);
			oldPassword = passwordsForReset.substring(indexPass + 1, endStr);
		}
		System.out.println(newPassword);
		System.out.println(oldPassword);

	}
}
