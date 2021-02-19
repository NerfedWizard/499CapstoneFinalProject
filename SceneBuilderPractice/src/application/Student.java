package application;

import java.util.ArrayList;

/***/
public class Student {
	private int id = 0;
	private String username;
	private String name;
	private ArrayList<Teacher> teachers = new ArrayList<Teacher>();
	private ArrayList<String> classes = new ArrayList<String>();
	private String studentEmail;
	private String password;
	private Guardian guardian;
	private String attendance;

	public Student(String username, String name, String studentEmail, String password, Guardian guardian) {
		this.setId(this.id);
		this.setUsername(username);
		this.setName(name);
		this.setFacultyEmail(studentEmail);
		this.setPassword(password);
		this.setGuardian(guardian);
		

	}

	public String getAttendance() {
		return this.attendance;
	}

	public void setAttendance(String status) {
		this.attendance = status;
	}

	public void addTeacher(Teacher teacher) {
		this.teachers.add(teacher);
	}

//	public void addGuardian(Guardian guardian) {
//		this.guardian = guardian;
//	}
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFacultyEmail() {
		return this.studentEmail;
	}

	public void setFacultyEmail(String facultyEmail) {
		this.studentEmail = facultyEmail;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public ArrayList<Teacher> getTeachers() {
		return this.teachers;
	}

//	public void setTeachers(ArrayList<Student> students) {
//		this.teachers = students;
//	}

	public ArrayList<String> getClasses() {
		return this.classes;
	}

	public void addClasses(String classTaken) {
		this.classes.add(classTaken);
	}

//	public ArrayList<String> getClasses() {
//		return classes;
//	}
//	public void setClasses(ArrayList<String> classes) {
//		this.classes = classes;
//	}
	public Guardian getGuardian() {
		return this.guardian;
	}

	public void setGuardian(Guardian guardian) {
		this.guardian = guardian;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
		this.id+=1;
	}

}
