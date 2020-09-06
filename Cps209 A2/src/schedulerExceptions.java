/**
 * Name: Yong Kang He
 * Student # 500570639
 */
public class schedulerExceptions extends Exception
{

	public schedulerExceptions () {
		super("There is an error when creating the schedule");
	}
	public schedulerExceptions (String message) {
		super(message);
	}
}
