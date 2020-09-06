/**
 * Name: Yong Kang He
 * Student # 500570639
 */
public class CreditCourse  extends Course
{
	private String semester;
	public boolean active;
	public double grade;
	//// Constructor to initialize super class and semester and grades
	public CreditCourse(String name, String code, String descr, String fmt,String semester, double grade)
	{
		super(name,code,descr,fmt);
		this.semester=semester;
		this.grade=grade;
	}
	public void setGrade(double grade) {
		this.grade=grade;
	}
	//returns the status of the course(complete or ongoing)
	public boolean getActive()
	{
		
		return active;
	}
	// set the course as the students ongoing course
	public void setActive()
	{
		active=true;
	}
	//sets the course as the student get their final grade
	public void setInactive()
	{
		active=false;
	}
	//returns the current grade of student
	public String displayGrade()
	{
		// print out info about this course plus which semester and the grade achieved
		return super.getInfo()+" "+semester+" "+super.convertNumericGrade(grade);
	}
	
}