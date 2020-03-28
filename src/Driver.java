import java.util.Scanner;
import java.util.Date;
public class Driver {
	public static void main(String[] args) {
		String day;
		String time;
		String dateString;
		String room;
		Scanner scan = new Scanner(System.in);
		Date date = new Date();
		System.out.println("Please enter date you would like to alter in"
				+ "format dd/mm/yy:");
		day = scan.nextLine();
		//add a pattern check here first get date then get time and them together
		System.out.println("Please enter the time on that date you would like to alter"
				+ "in 24 hour format hh:mm");
		time = scan.nextLine();
		//once again add a pattern checker
		dateString = day + time;
		//Obviously it doesn't work like this fix later with greg calendar
		date = dateString;
		System.out.println("What room would you like to edit enter room number:");
		room = scan.nextLine();
		System.out.println("What would you like to do to this room"
				+ "/n(1) Add event"
				+ "/n(2) Remove event"
				+ "/n(3) Replace an event"
				+ "/n(0) change room"
				+ "/ntype 1-3 or 0");
		int choice = scan.nextInt();
		case: 
			//done for now will do more
	}
}
