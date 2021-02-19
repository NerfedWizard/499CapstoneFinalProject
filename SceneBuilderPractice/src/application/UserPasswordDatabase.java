package application;

import java.util.ArrayList;

public class UserPasswordDatabase {
//	private static String[] userNames = {"Erin","Cedar","Adnan","Luke","Loel"};
//	private static String[] passwords = {"111","222","333","444","555"};
//	private static String[] email = new String[5];
	private ArrayList<Student> studentArchive = new ArrayList<Student>();
	private ArrayList<Guardian> guardianArchive = new ArrayList<Guardian>();
	private ArrayList<Teacher> teacherArchive = new ArrayList<Teacher>();

	public UserPasswordDatabase() {

	}

	public void addStudent(Student student, int id) {
		this.studentArchive.add(id,student);
	}

	public void addGuardian(Guardian guardian, int id) {
		this.guardianArchive.add(id, guardian);
	}

	public void addTeacher(Teacher teacher, int id) {
		this.teacherArchive.add(id,teacher);
	}
	public Student getStudentArchive(int id) {
		return studentArchive.get(id);
	}
	public Guardian getGuardianArchive(int id) {
		return guardianArchive.get(id);
	}
	public Teacher getTeacherArchive(int id) {
		return teacherArchive.get(id);
	}
//	public static String[] getLoginInfo() {
//		return userNames;
//	}
//
//	public static String[] getPasswords() {
//		return passwords;
//	}
//
//	public static String[] getEmail() {
//		return email;
//	}

}
