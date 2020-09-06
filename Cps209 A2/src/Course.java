/**
 * Name: Yong Kang He
 * Student # 500570639
 */
public class Course 
{
	private String code;
	private String name;
	private String description;
	private String format;

	public Course()
	{
		this.code        = "";
		this.name        = "";
		this.description = "";
		this.format      = "";
	}

	public Course(String name, String code, String descr, String fmt)
	{
		this.code        = code;
		this.name        = name;
		this.description = descr;
		this.format      = fmt;
	}

	public String getCode()
	{
		return code;
	}

	public String getName()
	{
		return name;
	}

	public String getFormat()
	{
		return format;
	}

	public String getDescription()
	{
		return code +" - " + name + "\n" + description + "\n" + format;
	}
	//returns only the class description
	public String getClassDescription() {
		return description;
	}

	public String getInfo()
	{
		return code +" - " + name;
	}

	// static method to convert numeric score to letter grade string 
	// e.g. 91 --> "A+"
	public static String convertNumericGrade(double score)
	{

		if (score<50)return "F";
		else if (score<53) return "D-";
		else if (score < 57)return "D";
		else if (score<60)return "D+";
		else if (score<63)return "C-";
		else if (score <67)return "C";
		else if (score<70)return "C+";
		else if (score<73)return "B-";
		else if (score<77)return "B";
		else if (score<80)return "B+";
		else if (score <85)return "A-";
		else if (score <90)return "A";
		else return "A+";
	}

}
