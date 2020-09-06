/**
 * Name: Yong Kang He
 * Student # 500570639
 */
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class StudentRegistrySimulator 
{
	public static void main(String[] args)
	{
		try {
			Registry registry = new Registry();
			Scheduler schedule = new Scheduler(registry.getCourses());
			Scanner scanner = new Scanner(System.in);
			System.out.print(">");

			while (scanner.hasNextLine())
			{
				String inputLine = scanner.nextLine();
				if (inputLine == null || inputLine.equals("")) continue;

				Scanner commandLine = new Scanner(inputLine);
				String command = commandLine.next();

				if (command == null || command.equals("")) continue;

				else if (command.equalsIgnoreCase("L") || command.equalsIgnoreCase("LIST"))
				{
					registry.printAllStudents();
				}
				else if (command.equalsIgnoreCase("Q") || command.equalsIgnoreCase("QUIT"))
					return;

				else if (command.equalsIgnoreCase("REG"))
				{
					try {
						// registers a new student in registry
						// gets name and student id string 
						String name = commandLine.next();
						String id = commandLine.next();
						// Checks if name is all alphabetic characters
						//  and id string is all numeric characters
						boolean checkName = isStringOnlyAlphabet(name);
						boolean checkId = isNumeric(id);
						if(checkName && checkId) registry.addNewStudent(name, id);
					}catch (NoSuchElementException e) {
						System.out.println("Input all required fields for example: reg school 12345");
					}
				}
				else if (command.equalsIgnoreCase("DEL"))
				{
					try {
						// deletes a student from registry
						// gets student id
						String id = commandLine.next();
						// ensures numeric
						boolean checkId = isNumeric(id);
						// removes student from registry
						if (checkId)registry.removeStudent(id);
					}catch (NoSuchElementException e) {
						System.out.println("Input all required fields for example: del 12345");
					}
				}

				else if (command.equalsIgnoreCase("ADDC"))
				{
					try {
						// adds a student to an active course
						// gets student id and course code strings
						// adds student to course
						String id = commandLine.next();
						String code = commandLine.next();
						registry.addCourse(id, code);
					}catch (NoSuchElementException e) {
						System.out.println("Input all required fields for example: addc 12345 cps209");
					}

				}
				else if (command.equalsIgnoreCase("DROPC"))
				{
					try {
						// gets student id and course code strings
						String id = commandLine.next();
						String code = commandLine.next();
						// drops student from course
						registry.dropCourse(id, code);
					}catch(NoSuchElementException e) {
						System.out.println("Input all required fields for example: del 12345 cps209");
					}
				}
				else if (command.equalsIgnoreCase("PAC"))
				{
					// prints all active courses
					registry.printActiveCourses();
				}		  
				else if (command.equalsIgnoreCase("PCL"))
				{
					try {
						// gets course code string
						String code =commandLine.next();
						// prints class list (i.e. students) for this course
						registry.printClassList(code);
					}catch (NoSuchElementException e) {
						System.out.println("Input all required fields for example: pcl cps209");
					}
				}
				else if (command.equalsIgnoreCase("PGR"))
				{
					try {
						// gets course code string
						String code = commandLine.next();
						// print name, id and grade of all students in active course
						registry.printGrades(code);
					}catch (NoSuchElementException e) {
						System.out.println("Input all required fields for example: pgr cps209");
					}
				}
				else if (command.equalsIgnoreCase("PSC"))
				{
					try {
						// get student id string
						String id = commandLine.next();
						// print all credit courses of student
						registry.printStudentCourses(id);
					}catch (NoSuchElementException e) {
						System.out.println("Input all required fields for example: psc 12345");
					}

				}
				else if (command.equalsIgnoreCase("PST"))
				{
					try {
						// get student id string
						String id = commandLine.next();
						// print student transcript
						registry.printStudentTranscript(id);
					}catch (NoSuchElementException e) {
						System.out.println("Input all required fields for example: pst 12345");
					}
				}
				else if (command.equalsIgnoreCase("SFG"))
				{
					try {
						// set final grade of student
						// get course code, student id, numeric grade
						String code = commandLine.next();
						String id =commandLine.next();
						double grade = commandLine.nextDouble();
						//set final grade of this student
						registry.setFinalGrade(code, id, grade);
					}catch (NoSuchElementException e) {
						System.out.println("Input all required fields for example: sfg cps209 12345 90");
					}
				}
				else if (command.equalsIgnoreCase("SCN"))
				{
					try {
						// get course code
						String code =commandLine.next();
						// sort list of students in course by name (i.e. alphabetically)
						registry.sortCourseByName(code);
					}catch (NoSuchElementException e) {
						System.out.println("Input all required fields for example: scn cps209");
					}

				}
				else if (command.equalsIgnoreCase("SCI"))
				{
					try {
						// get course code
						String code = commandLine.next();
						// sort list of students in course by student id
						registry.sortCourseById(code);
					}catch (NoSuchElementException e) {
						System.out.println("Input all required fields for example: sci cps209");
					}
				}
				//sets a lcture time for a course
				else if (command.equalsIgnoreCase("SCH"))
				{
					try {
						String code = commandLine.next();
						String day = commandLine.next();
						int time = commandLine.nextInt();
						int duration = commandLine.nextInt();
						schedule.setDayAndTime(code, day, time, duration);
					}catch (NoSuchElementException e) {
						System.out.println("Input all required fields for example: sch cps209 Mon 900 3");
					}
				}
				// clears lecture time of course
				else if (command.equalsIgnoreCase("CSCH"))
				{
					try {
						String courseCode = commandLine.next();
						schedule.clearSchedule(courseCode);
					}catch (NoSuchElementException e) {
						System.out.println("Input all required fields for example: CSCH cps209");
					}
				}
				// prints a 2d schedule
				else if (command.equalsIgnoreCase("PSCH"))
				{
					try {
						schedule.printSchedule(); 
					}catch (NoSuchElementException e) {
						System.out.println("Input all required fields for example: psch");
					}
				}
				System.out.print("\n>");
			}
		}catch(FileNotFoundException e) {
			System.out.println("students.txt or students file is not found");
		}
	}

	private static boolean isStringOnlyAlphabet(String str) 
	{ 
		// method to check if string str contains only alphabetic characters 
		for (int i=0;i<str.length();i++) {
			char letter = str.charAt(i);
			if (!(Character.isLetter(letter))) {
				System.out.println("Invaalid Character in Name "+ str);
				return false;
			}
		}
		return true;
	} 

	public static boolean isNumeric(String str)
	{
		// method to check if string str contains only numeric characters
		for(int i = 0; i<str.length();i++) {
			if(!(Character.isDigit(str.charAt(i)) && str.length()==5)){
				System.out.println("Invalid Characters in Id "+str);
				return false;
			}
		}
		return true;
	}


}