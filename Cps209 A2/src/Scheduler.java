/**
 * Name: Yong Kang He
 * Student # 500570639
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class Scheduler
{
	
	TreeMap<String,ActiveCourse> schedule;
	String[] days = {"mon","tue","wed","thur","fri"};
	TreeMap<Integer,String[]> time = new TreeMap<Integer, String[]>();
	
	// Constructor to initialize the values of lectureDay, LectureStart, and lectureDuration in activecourse class
	//sets up the time treemap which will become the schedule
	public Scheduler(TreeMap<String,ActiveCourse> courses)
	{
		//creates the time map
		String[] course = {"","","","",""};
		schedule = courses;
		for(int t=800;t<1700;t+=100) {
			time.put(t,course.clone() );
		}
		for(ActiveCourse ac: schedule.values()) {
			ac.setDay("");
			ac.setDuration(0);
			ac.setTime(0);
		}
	}
	// sets the lecture day, the lecture start time, and duration of lecture
	public void setDayAndTime(String courseCode, String day, int startTime, int duration)
	{
		// checks that need to be completed in order to set everything up
		boolean acFound = schedule.containsKey(courseCode);
		List<String> list = Arrays.asList(days);
		boolean dFound = list.contains(day.toLowerCase());
		boolean durCheck = duration <= 3 && duration >0;
		boolean sTime = time.containsKey(startTime);
		boolean collision = false;
		// exceptions that can form
		try {
			if(!acFound){
				throw new schedulerExceptions("Unknown Course: "+courseCode);
			}else if(!dFound){
				throw new schedulerExceptions("Invalid Lecture Day");
			}else if(!sTime){
				throw new schedulerExceptions("Invalid Lecture Start Time");
			}else if(!durCheck){
				throw new schedulerExceptions("Invalid Lecture Duration");
			}
			// makes sure the time being set up does not overlap other courses
			for(String s: schedule.keySet()) {
				ActiveCourse ac = schedule.get(s);
				if(!(ac.getCode().equalsIgnoreCase(courseCode))) {
					if(ac.getDay().equalsIgnoreCase(day)) {
						if(startTime > ac.getTime() && startTime < ac.getTime() +ac.getDuration()*100) {
							collision =true;
							throw new schedulerExceptions("Lecture Time Collision");
						}else if(startTime+duration*100 > ac.getTime() && startTime+duration*100< ac.getTime()+ac.getDuration()*100) {
							collision = true;
							throw new schedulerExceptions("Lecture Time Collision");
						}else if (startTime <= ac.getTime() && startTime+duration*100 >=  ac.getTime()+ac.getDuration()*100) {
							collision=true;
							throw new schedulerExceptions("Lecture Time Collision");
						}
					}
				}
			}
			// setting up the varibles in the active course object if all checks allow it
			if(acFound && dFound && sTime && durCheck&& !collision) {
				ActiveCourse ac =schedule.get(courseCode);
				ac.setDay(day);
				ac.setDuration(duration);
				ac.setTime(startTime);
			}
		}catch (schedulerExceptions e) {
			System.out.println(e.getMessage());
		}
	}
	
	// clears the scheduled time of a course
	public void clearSchedule(String courseCode)
	{
		//finds course
		for(String s: schedule.keySet()) {
			ActiveCourse ac = schedule.get(courseCode);
			// clears the schedule of that course
			if (courseCode.equalsIgnoreCase(s)) {
				int index = 0;
				if (ac.getDay().equalsIgnoreCase("mon") )index = 0;
				if (ac.getDay().equalsIgnoreCase("tue") )index = 1;
				if (ac.getDay().equalsIgnoreCase("wed") )index = 2;
				if (ac.getDay().equalsIgnoreCase("thur"))index = 3;
				if (ac.getDay().equalsIgnoreCase("fri") )index = 4;
				for(int i = 0; i<ac.getDuration();i++) {
					time.get(ac.getTime()+i*100)[index] = "";
				}
				
				// resets the day, duration and start time of lectures
				ac.setDay("");
				ac.setDuration(0);
				ac.setTime(0);
				
			}
		}
		
	}
	//prints the complete schedule of courses

	public void printSchedule()
	{
		//goes through every active course
		for(ActiveCourse ac : schedule.values()) {
			int index = 0;
			if (ac.getDay().equalsIgnoreCase("mon") )index = 0;
			if (ac.getDay().equalsIgnoreCase("tue") )index = 1;
			if (ac.getDay().equalsIgnoreCase("wed") )index = 2;
			if (ac.getDay().equalsIgnoreCase("thur"))index = 3;
			if (ac.getDay().equalsIgnoreCase("fri") )index = 4;
			for(int i = 0; i<ac.getDuration();i++) {
				time.get(ac.getTime()+i*100)[index] = ac.getCode();
			}
		}

		String day="";
		for(String d: days) {
			day+= d+"\t";
		}
		System.out.println("\t" + day);
		for( Map.Entry<Integer, String[]> entry: time.entrySet()) {
			String course="";
			for(String s: entry.getValue()) {
				course+= "\t"+s;
			}
			System.out.println(entry.getKey() + course);
		}
	}

}

