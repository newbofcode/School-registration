import java.io.File;
import java.util.Scanner;
import java.util.TreeMap;

public class testingStuff {
	public static void main(String[] args) {
		 TreeMap<String,String> list = new TreeMap<String,String>();
		try {
			  
			  String student = readLine("C:\\Users\\kenhy\\eclipse-workspace\\Cps209 A2\\src\\students");
			  Scanner input =  new Scanner(student);
			  while (input.hasNextLine()) {
				  String students = input.nextLine();
				  Scanner s = new Scanner(students);
				  System.out.println(students);
				  String name = s.next();
				  String id = s.next();
				  list.put(name, id);
				  
			  }
			  }catch(Exception e) {
				  System.out.println("There is a problem with your file of students");
			  }
		System.out.println(list);
	}
	public static String readLine(String studentFile) {
		String file="";
		try {
			Scanner scanner = new Scanner(new File(studentFile));
			while (scanner.hasNextLine()) {
				file += scanner.nextLine() +"\n";
			}
		}catch (Exception e) {
			System.out.println("Something is wrong");
		}
		return file;
	}
}
