/**
 * Name: Yong Kang He
 * Student # 500570639
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

public class Registry
{
	private TreeMap<String,Student>      students = new TreeMap<String,Student>();
	private TreeMap<String,ActiveCourse> courses = new TreeMap<String,ActiveCourse>(String.CASE_INSENSITIVE_ORDER);

	public Registry() throws FileNotFoundException
	{
		String line = studentList("students.txt");
		Scanner input = new Scanner(line);
		try {
		while (input.hasNext()) {
			String student = input.nextLine();
			Scanner scanner = new Scanner(student);
			String name = scanner.next();
			String id = scanner.next();
			Student s = new Student (name,id);
			students.put(id,s);
		}input.close();
		}catch (Exception e) {
			System.out.println("Bad File Format students.txt");
		}

		// Add some students
		// in A2 we will read from a file
		Student s1 = new Student("JohnOliver", "34562");
		Student s2 = new Student("HarryWindsor", "38467");
		Student s3 = new Student("SophieBrown", "98345");
		Student s4 = new Student("FaisalQuereshi", "57643");
		Student s5 = new Student("GenghisKhan", "25347");
		Student s6 = new Student("SherryTu", "46532");
		students.put(s1.getId(),s1);
		students.put(s2.getId(),s2);
		students.put(s3.getId(),s3);
		students.put(s4.getId(),s4);
		students.put(s5.getId(),s5);
		students.put(s6.getId(),s6);

		ArrayList<Student> list = new ArrayList<Student>();
		// CPS209
		String courseName = "Computer Science II";
		String courseCode = "CPS209";
		String descr = "Learn how to write complex programs!";
		String format = "3Lec 2Lab";
		list.add(s2); list.add(s3); list.add(s4);
		courses.put(courseCode,new ActiveCourse(courseName,courseCode,descr,format,"W2020",list));
		// Add some active courses with students
		list.add(s2); list.add(s3); list.add(s4);	  
		// Add course to student list of courses
		s2.addCourse(courseName,courseCode,descr,format,"W2020", 0); 
		s3.addCourse(courseName,courseCode,descr,format,"W2020", 0); 
		s4.addCourse(courseName,courseCode,descr,format,"W2020", 0);

		// CPS511
		list.clear();
		courseName = "Computer Graphics";
		courseCode = "CPS511";
		descr = "Learn how to write cool graphics programs";
		format = "3Lec";
		list.add(s1); list.add(s5); list.add(s6);
		courses.put(courseCode,new ActiveCourse(courseName,courseCode,descr,format,"W2020",list));
		s1.addCourse(courseName,courseCode,descr,format,"W2020", 0); 
		s5.addCourse(courseName,courseCode,descr,format,"W2020", 0); 
		s6.addCourse(courseName,courseCode,descr,format,"W2020", 0);

		// CPS643
		list.clear();
		courseName = "Virtual Reality";
		courseCode = "CPS643";
		descr = "Learn how to write extremely cool virtual reality programs";
		format = "3Lec 2Lab";
		list.add(s1); list.add(s2); list.add(s4); list.add(s6);
		courses.put(courseCode,new ActiveCourse(courseName,courseCode,descr,format,"W2020",list));
		s1.addCourse(courseName,courseCode,descr,format,"W2020", 0); 
		s2.addCourse(courseName,courseCode,descr,format,"W2020", 0); 
		s4.addCourse(courseName,courseCode,descr,format,"W2020", 0); 
		s6.addCourse(courseName,courseCode,descr,format,"W2020", 0); 

		// CPS706
		list.clear();
		courseName = "Computer Networks";
		courseCode = "CPS706";
		descr = "Learn about Computer Networking";
		format = "3Lec 1Lab";
		courses.put(courseCode,new ActiveCourse(courseName,courseCode,descr,format,"W2020",list));

		// CPS616
		list.clear();
		courseName = "Algorithms";
		courseCode = "CPS616";
		descr = "Learn about Algorithms";
		format = "3Lec 1Lab";
		courses.put(courseCode,new ActiveCourse(courseName,courseCode,descr,format,"W2020",list));
		// sort the students alphabetically - see class Student 

	}

	//helper method for reading a text file with student name and ID
	//creates a Student object
	public static String studentList(String studentFiles) throws FileNotFoundException{
		String file="";
			Scanner scanner = new Scanner(new File(studentFiles));
			while (scanner.hasNextLine()) {
				file += scanner.nextLine() +"\n";
			}

		return file;
	}

	// returns the active courses for scheduler
	public TreeMap<String,ActiveCourse> getCourses(){
		return courses;
	}
	
	// Add new student to the registry (students arraylist above) 
	public boolean addNewStudent(String name, String id)
	{
		// Creates a new student object
		// checks to ensure student is not already in registry
		Student s = new Student(name,id);
		if (students.containsKey(id)) {
			System.out.println( s + " already registered");
			return false;
		}
		// if not, add them and return true
		students.put(id, s);
		System.out.println( s + " has been registered");
		return true;
	}
	// Remove student from registry 
	public boolean removeStudent(String studentId)
	{
		// Finds student in students map
		if(students.containsKey(studentId)== false) {
			System.out.println("Student " + studentId + " is not currently registered");
			return false;
		}
		// If found, remove this student and return true
		students.remove(studentId);
		System.out.println("Student " + studentId + " is no longer registered");
		return true;
	}

	// Print all registered students
	public void printAllStudents()
	{
		for(Student s: students.values()) {
			System.out.println("ID: " + s.getId() + " Name: " + s.getName() );
		}

	}

	// Given a studentId and a course code, add student to the active course
	public void addCourse(String studentId, String courseCode)
	{
		boolean taken = false;
		boolean sFound = false;
		Student newKid =null;
		boolean taking = false;
		try {
			// Find student object in registry
			if(students.containsKey(studentId)) {
				newKid = students.get(studentId);
				sFound = true;
			}else {
				throw new Exception();
			}
			// Check if student has already taken this course in the past

			for (CreditCourse cc: newKid.courses) {
				if(cc.getCode().equalsIgnoreCase(courseCode) && cc.getActive()==false) {
					taken = true;
					throw new Exception();
				}
			}
			// If not, then find the active course in courses array list using course code
			if(taken== false && sFound==true) {
				if(courses.containsKey(courseCode)) {
					// If active course found then check to see if student already enrolled in this course
					for(Student s: courses.get(courseCode).getStudents()) {
						if (s.equals(newKid)) {
							taking=true;
							throw new Exception();
						}
					}
				}
			}
			// If not already enrolled
			//   add student to the active course
			//   add course to student list of credit courses with initial grade of 0
			if (taking == false) {
				courses.get(courseCode).getStudents().add(newKid);
				newKid.addCourse(courses.get(courseCode).getName(), courses.get(courseCode).getCode(), courses.get(courseCode).getClassDescription(), courses.get(courseCode).getFormat(), courses.get(courseCode).getSemester(), 0);
				System.out.println(studentId+" has been added to "+courseCode);
			}
		}catch (Exception e) {
			if (!sFound)System.out.println(studentId+" is not registered");
			if(taken)System.out.println(studentId+" has already completed "+courseCode );
			if(taking)System.out.println(studentId+" is currently taking "+courseCode);
		}


	}

	// Given a studentId and a course code, drop student from the active course
	public void dropCourse(String studentId, String courseCode)
	{
		boolean active= false;
		boolean sFound = false;
		try {
			// Find the active course
			if(courses.containsKey(courseCode))active=true;
			// Find the student in the list of students for this course
			if (!active)throw new Exception();
			Student student = null;
			if(active) {
				for(Student s: courses.get(courseCode).getStudents()) {
					if(s.getId().equals(studentId)) {
						student =s;
						sFound =true;
					}
				}
			}
			if (!sFound)throw new Exception();
			// If student found:
			//   remove the student from the active course
			//   remove the credit course from the student's list of credit courses
			if(sFound) {
				courses.get(courseCode).getStudents().remove(student);
				student.removeActiveCourse(courseCode);
			}
		}catch (Exception e) {
			if(!active)System.out.println(courseCode+" is not an active course");
			if(!sFound)System.out.println(studentId+" is not a student in the course");

		}
	}

	// Print all active courses
	public void printActiveCourses()
	{
		for(ActiveCourse ac:courses.values()) {
			System.out.println(ac.getDescription());
		}
	}

	// Print the list of students in an active course
	public void printClassList(String courseCode)
	{
		for(ActiveCourse ac: courses.values()) {
			if (ac.getCode().equalsIgnoreCase(courseCode)) {
				ac.printClassList();
			}
		}
	}

	// Given a course code, find course and sort class list by student name
	public void sortCourseByName(String courseCode)
	{
		for(ActiveCourse ac: courses.values()) {
			if (ac.getCode().equalsIgnoreCase(courseCode)) {
				ac.sortByName();
			}
		}
	}

	// Given a course code, find course and sort class list by student name
	public void sortCourseById(String courseCode)
	{
		for(ActiveCourse ac: courses.values()) {
			if (ac.getCode().equalsIgnoreCase(courseCode)) {
				ac.sortById();
			}
		}
	}

	// Given a course code, find course and print student names and grades
	public void printGrades(String courseCode)
	{
		for(ActiveCourse ac: courses.values()) {
			if (ac.getCode().equalsIgnoreCase(courseCode)) {
				ac.printGrades();
			}
		}
	}

	// Given a studentId, print all active courses of student
	public void printStudentCourses(String studentId)
	{
		for(Student s: students.values()) {
			if(s.getId().equalsIgnoreCase(studentId)) {
				s.printActiveCourses();
			}
		}
	}

	// Given a studentId, print all completed courses and grades of student
	public void printStudentTranscript(String studentId)
	{
		for(Student s: students.values()) {
			if(s.getId().equalsIgnoreCase(studentId)) {
				s.printTranscript();
			}
		}
	}

	// Given a course code, student id and numeric grade
	// set the final grade of the student
	public void setFinalGrade(String courseCode, String studentId, double grade)
	{
		boolean sFound = false;
		boolean acFound = false;
		Student s = null;
		try {
		// find the active course
			for (ActiveCourse ac : courses.values()) {
				if(ac.getCode().equalsIgnoreCase(courseCode)) {
					acFound = true;
				}
			}
			
		// If found, find the student in class list
			if(acFound) {
				for(Student student: courses.get(courseCode).getStudents()) {
					if(student.getId().equalsIgnoreCase(studentId)) {
						sFound=true;
						s= student;
					}
				}
			}else {
				throw new Exception();
			}
		// then search student credit course list in student object and find course
			if(sFound) {
				for(CreditCourse cc: s.courses) {
					if(cc.getCode().equalsIgnoreCase(courseCode)) {
						cc.setGrade(grade);
						cc.setInactive();
					}
				}
			}else {
				throw new Exception();
			}
		// set the grade in credit course and set credit course inactive
		} catch (Exception e) {
			if(!acFound)System.out.println(courseCode+" is not an active course");
			if (!sFound) System.out.println(studentId+" is not currently taking "+courseCode);
		}

	}

}
