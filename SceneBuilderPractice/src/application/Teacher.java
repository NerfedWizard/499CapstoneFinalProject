package application;

import java.util.ArrayList;

public class Teacher {
	private String username;
	private String name;
	private ArrayList<Student> students = new ArrayList<Student>();
	private ArrayList<Guardian> guardians = new ArrayList<Guardian>();
	private String schoolClass;
	private String facultyEmail;
	private String password;

	public Teacher(String username, String name, String facultyEmail, String password, String schoolClass) {
		this.setUsername(username);
		this.setName(name);
		this.setFacultyEmail(facultyEmail);
		this.setPassword(password);
		this.setSchoolClass(schoolClass);

	}
	public void addStudent(Student student) {
		this.students.add(student);
	}
	public void addGuardian(Guardian guardian) {
		this.guardians.add(guardian);
	}
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFacultyEmail() {
		return facultyEmail;
	}

	public void setFacultyEmail(String facultyEmail) {
		this.facultyEmail = facultyEmail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public ArrayList<Student> getStudents() {
		return students;
	}

	public void setStudents(ArrayList<Student> students) {
		this.students = students;
	}

	public ArrayList<Guardian> getGuardians() {
		return guardians;
	}

	public void setGuardians(ArrayList<Guardian> guardians) {
		this.guardians = guardians;
	}
	public String getSchoolClass() {
		return schoolClass;
	}
	public void setSchoolClass(String schoolClass) {
		this.schoolClass = schoolClass;
	}

}
