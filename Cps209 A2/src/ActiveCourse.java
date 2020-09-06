/**
 * Name: Yong Kang He
 * Student # 500570639
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

// Active University Course

public class ActiveCourse extends Course
{
	private ArrayList<Student> students; 
	private String             semester;
	private int lectureStart, lectureDuration;
	private String lectureDay;

	// Constructor to initialize super class and a copy of the student active courses
	public ActiveCourse(String name, String code, String descr, String fmt,String semester,ArrayList<Student> students)
	{
		super(name,code,descr,fmt);
		this.semester =semester;
		this.students=(ArrayList<Student>)students.clone();
	}
	
	//sets the lecture Start time
	public void setTime(int start) {
		lectureStart=start;
	}
	// gets the start time
	public int getTime() {
		return lectureStart;
	}
	//sets the duration of lecture
	public void setDuration(int duration) {
		lectureDuration = duration;
	}
	//get the duration
	public int getDuration() {
		return lectureDuration;
	}
	//sets the lecture day
	public void setDay(String day) {
		lectureDay=day;
	}
	//gets the lecture day
	public String getDay() {
		return lectureDay;
	}
	
	// get the students array list
	public ArrayList<Student>getStudents(){
		return students;
	}

	public String getSemester()
	{
		return semester;
	}

	// Prints the students in this course  (name and student id)
	public void printClassList()
	{
		for (int i =0;i<students.size();i++) {
			System.out.println(students.get(i));
		}
	}

	// Prints the grade of each student in this course (along with name and student id)
	// 
	public void printGrades()
	{
		for(Student s:students) {
			System.out.println(s.toString() + " Grade: "+ s.getGrade());
		}
	}

	// Returns the numeric grade in this course for this student
	// If student not found in course, return 0 
	public double getGrade(String studentId)
	{
		// Search the student's list of credit courses to find the course code that matches this active course
		// return the grade stored in the credit course object
		for(int i=0;i<students.size();i++) {
			Student s=students.get(i);
			if(s.getId().equalsIgnoreCase(studentId)) {
				return s.getGrade();
			}
		}
		return 0; 
	}

	// Returns a String containing the course information as well as the semester and the number of students 
	// enrolled in the course
	// override method in the superclass Course and use super class method getDescription()
	public String getDescription()
	{
		return super.getDescription() + " " + this.semester + " Enrollment: " + students.size() + "\n";

	}
	//gets just the description of the class
	public String getClassDescription() {
		for(Student s: students) {
			return super.getClassDescription();
		}
		return "";
	}


	// Sort the students in the course by name using the Collections.sort() method with appropriate arguments
	public void sortByName()
	{
		Collections.sort(students,new NameComparator());
	}


	// This class is used to compare two Student objects based on student name
	private class NameComparator implements Comparator<Student>
	{
		//overrides the compare method to compare 2 student names
		public int compare(Student s1,Student s2) {
			return s1.getName().compareTo(s2.getName());
		}
	}

	// Sort the students in the course by student id using the Collections.sort()
	public void sortById()
	{
		Collections.sort(students,new IdComparator());
	}


	// This class is used to compare two Student objects based on student id
	private class IdComparator implements Comparator<Student>
	{
		//overrides the compareTo method to compare the Id
		public int compare(Student s1, Student s2) {
			return s1.getId().compareTo(s2.getId());
		}
	}
}
