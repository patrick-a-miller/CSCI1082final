import java.util.Scanner;
import java.util.Date;
public class Driver {
	public static void main(String[] args) {
		String mainLoop = "y";
		String day;
		String time;
		String dateString;
		String room;
		Scanner scan = new Scanner(System.in);
		Date date = new Date();
		while(mainLoop.equals("y")) {
			System.out.println("Please enter date you would like to alter in"
					+ "format dd/mm/yy:");
			day = scan.nextLine();
			//add a pattern check here first get date then get time and them together
			System.out.println("Please enter the hour on that date you would like to alter"
					+ "in 24 hour format hh");
			time = scan.nextLine();
			//we keep day and time separate
			System.out.println("What room would you like to edit enter room number:");
			room = scan.nextLine();
			System.out.println("What would you like to do to this room"
					+ "/n(1) Add event"
					+ "/n(2) Remove event"
					+ "/n(3) Replace an event"
					+ "/n(0) change room"
					+ "/ntype 1-3 or 0");
			int choice = scan.nextInt();
			switch(choice) {
				case 1: 
					System.out.println("What event would you like to set here");
					room.event = scan.nextLine();
					break;
				}else {
					System.out.println("room is busy at this time.");
					//find way to return to the 1-3 menu if else happens
					break;
				}
				case 2: if(!room.checkAvalibilty) {
					room.clear;
					break;
				}else {
					System.out.println("nothing is schedualed for this time");
					//find way to return to the 1-3 menu if else happens
					break;
				}
				case 3: if(!room.checkAvalibilty) {
					System.out.println("What event would you like to set here");
					room.event = scan.nextLine();
					break;
				}else {
					System.out.println("room room doesn't have an event at this time at this time.");
					//find way to return to the 1-3 menu if else happens
					break;
				}
			}
			System.out.println("Would you like to make another alteration y/n");
			mainLoop = scan.next();
		}
		System.out.println("Would you like to see the current schedual y/n");
		String print = scan.next();
		if(print.equals("y")) {
			System.out.println(building.toString);
		}else {
			System.out.println("Shutting down...");
		}
	}
}
