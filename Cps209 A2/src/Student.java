/**
 * Name: Yong Kang He
 * Student # 500570639
 */
import java.util.ArrayList;


public class Student
{
  private String name;
  private String id;
  public  ArrayList<CreditCourse> courses;
  public double grade;
  
  
  public Student(String name, String id)
  {
	 this.name = name;
	 this.id   = id;
	 courses   = new ArrayList<CreditCourse>();
  }
  
  public String getId()
  {
	  return id;
  }
  
  public String getName()
  {
	  return name;
  }
  
  // adds a credit course to list of courses for this student
  public void addCourse(String courseName, String courseCode, String descr, String format,String sem, double grade)
  {
	  CreditCourse cc = new CreditCourse(courseName, courseCode, descr, format, sem, grade);
	  cc.setActive();
	  courses.add(cc);
	  this.grade = grade;
  }
  
  // gets the grades of each student that had their final grades set
  public double getGrade() {
	  for(CreditCourse cc: courses) {
		  return cc.grade;
	  }return 0;
  }
  
  
  
  // Prints a student transcript
  // Prints all completed (i.e. non active) courses for this student (course code, course name, 
  // semester, letter grade
  public void printTranscript()
  {
	  for(CreditCourse cc:courses) {
		  if (cc.getActive()==false) {
			  System.out.println(cc.displayGrade());
		  }
	  }
  }
  
  // Prints all active courses this student is enrolled in
  public void printActiveCourses()
  {
	  for(CreditCourse cc:courses) {
		  if (cc.getActive()==true) {
			  System.out.println(cc.getDescription());
		  }
	  }
  }
  
  // Drop a course (given by courseCode)
  // Find the credit course in courses arraylist above and remove it
  // only remove it if it is an active course
  public void removeActiveCourse(String courseCode)
  {
	  for(int i=0;i<courses.size();i++) {
		  CreditCourse cc =courses.get(i);
		  if (courseCode.equalsIgnoreCase(cc.getCode())) {
			  courses.remove(i);
		  }
	  }
  }
  
  public String toString()
  {
	  return "Student ID: " + id + " Name: " + name;
  }
  
  // override equals method inherited from superclass Object
  // if student names are equal *and* student ids are equal (of "this" student
  // and "other" student) then return true
  // otherwise return false
  public boolean equals(Object other)
  {
	  Student s = (Student)other;
	  if(this.getName().equalsIgnoreCase(s.getName()) && this.getId().equalsIgnoreCase(s.getId())) {
		  return true;
	  }
	  return false;
  }
  
}
