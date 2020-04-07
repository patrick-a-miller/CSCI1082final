
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.InputMismatchException;

public class Driver {

	static int year = 0;
	static int month = 0;
	static int day = 0;
	static int time = 0;
	static int roomIndex=0;
	static int teacherIndex=0;
	static int classIndex=0;
	static Scanner scan = new Scanner(System.in);

	static TeacherDictionary teachers;
	static RoomDictionary rooms;      
	static ClassDictionary classes; 
	static CalendarRoom[] calendarRooms;

	public static void main(String[] args) {
		setUpFiles();
		setupDictionaries();
			String mainLoop = "y";
			//Date date = new Date();
			while(mainLoop.equals("y")) {
				//initial date settings
				promptDaySettings();
				//day = scan.nextLine();
				//add a pattern check here first get date then get time and them together
				//System.out.println("Please enter the time on that date you would like to alter"
						//+ "in 24 hour format hh:mm");
				//time = scan.nextLine();
				//once again add a pattern checker
//dateString = day + time;
		//Obviously it doesn't work like this fix later with greg calendar
	//date = dateString;
		//System.out.println("What room would you like to edit enter room number:");
			//room = scan.nextLine();
				promptRoomSetting();
				System.out.println("What would you like to do to this room"
						+ "\n(1) Add event"
						+ "\n(2) Remove event"
						+ "\n(3) Replace an event"
						+ "\n(4) Change date"
						+ "\n(0) change room"
						+ "\ntype 1-4 or 0");
				int choice = scan.nextInt();
				switch(choice) {
				case 0:
					promptRoomSetting();
					break;
					case 1: if(roomCheckAvailability(year,month,day,time)) {
						System.out.println("What class would you like to set here?");
addTimeSlot(year,month,day,time);
						break;
					}else {
						System.out.println("room is busy at this time.");
						//find way to return to the 1-3 menu if else happens
						break;
					}
					case 2: if(!roomCheckAvailability(year,month,day,time)) {
						removeTimeSlot(year,month,day,time);
						break;
					}else {
						System.out.println("nothing is scheduled for this time");
						//find way to return to the 1-3 menu if else happens
						break;
					}
					case 3: if(false) {
						System.out.println("What event would you like to set here");
						//room.event = scan.nextLine();
					}else {
						System.out.println("Development in progress...");
						//find way to return to the 1-3 menu if else happens
						}
						break;
					case 4:
						promptDaySettings();
						break;
					}
				
				System.out.println("Would you like to make another alteration y/n");
				mainLoop = scan.next();
			}
			System.out.println("Would you like to see the current schedule y/n");
			String print = scan.next();
			if(print.equals("y")) {
				System.out.println(calendarRooms[roomIndex].toString());
			}else {
				System.out.println("Shutting down...");
			}
		}

	public static void setupDictionaries() {
		teachers = new TeacherDictionary();
		rooms = new RoomDictionary();
		classes = new ClassDictionary();
		calendarRooms = new CalendarRoom[3];
		System.out.println(teachers);
		System.out.println(rooms);
		System.out.println(classes);
		
		rooms.setCurrentIndex(0);
		calendarRooms[0] = new CalendarRoom(rooms.getCurrentRoom());
		rooms.setCurrentIndex(1);
		calendarRooms[1] = new CalendarRoom(rooms.getCurrentRoom());
		rooms.setCurrentIndex(2);
		calendarRooms[2] = new CalendarRoom(rooms.getCurrentRoom());

	}

	public static void promptDaySettings() {
		System.out.println("Please enter date you would like to alter:");
//		+ "format dd/mm/yy:");
		System.out.print("Year (nnnn): ");
		enterInteger_Required(scan, "Year");
		System.out.print("Month (0-11");
		enterInteger_Required(scan, "Month");
		System.out.print("Day (1-31)");
		enterInteger_Required(scan, "Day");
		System.out.print("Hour (0-23)");
		enterInteger_Required(scan, "Hour");
	}

	public static void promptRoomSetting() {
		System.out.println("What room would you like to edit?");
		System.out.println(rooms);
		System.out.println("Select from 0-2.");
		enterInteger_Required(scan, "Room");
	}

	public static boolean roomCheckAvailability(int year, int month, int day, int hour) {
		CalendarRoom selectedRoom = calendarRooms[roomIndex];
		DayMonthYear selectedDay = null;
		int roomSearch = selectedRoom.searchCalendarDay(year, month, day);
		if (roomSearch < 0) {
			return true;
		} else {
			selectedDay = selectedRoom.getDayMonthYear(roomSearch);
			return selectedDay.isAvailable(time);
		}
	}
	
	public static void addTimeSlot(int year, int month, int day, int hour) {
		CalendarRoom selectedRoom = calendarRooms[roomIndex];
		DayMonthYear selectedDay = null;
		int roomSearch = selectedRoom.searchCalendarDay(year, month, day);
		if(roomSearch<0) {
			selectedDay = selectedRoom.addCalendarDay(year,month,day);
		}else {
			selectedDay=selectedRoom.getDayMonthYear(roomSearch);
		}
		rooms.setCurrentIndex(roomIndex);
		Room room = rooms.getCurrentRoom();;
		//teacher
		System.out.println("Select Teacher:");
		System.out.println(teachers);
		System.out.println("Select 0-2 ");
		enterInteger_Required(scan, "Teacher");
		 teachers.setCurrentIndex(teacherIndex);
		 Teacher selectedTeacher = teachers.getCurrentTeacher();
		//class
		 System.out.println("Select Class: ");
		  System.out.println(classes);
			 System.out.println("Select 0-2 ");
		 enterInteger_Required(scan, "Class");
		 classes.setCurrentIndex(classIndex);
		 ClassEntry selectedClass = classes.getCurrentClass();
		boolean result=selectedDay.addTimeSlot(hour, room, selectedClass, selectedTeacher);
		System.out.println("Slot created: "+result);
	}
	
	public static void removeTimeSlot(int year, int month, int day, int hour) {
		CalendarRoom selectedRoom = calendarRooms[roomIndex];
		DayMonthYear selectedDay = null;
		int roomSearch = selectedRoom.searchCalendarDay(year, month, day);
		if (roomSearch < 0) {
			System.out.println("Time not found.");
				} else {
			selectedDay = selectedRoom.getDayMonthYear(roomSearch);
			if(!selectedDay.isAvailable(time)) {
				selectedDay.removeTimeSlot(time);
				System.out.println("Time cleared.");
			}
		}
	}

	/*
	 * Method: enterInteger_required Purpose: Input loop for unskippable data fields
	 * for integer values Parameters: Scanner keyboard, String fieldLabel Returns:
	 * None Preconditions: Assumes Scanner keyboard initialized for console input
	 * Postconditions: will make forward progress only after a valid value has been
	 * entered and accepted. The conditions for acceptance are dictated by the
	 * fieldLabel, and its associated setter method *fall-through case if code
	 * cannot determine matching setter for fieldLabel will exit program
	 */
	public static void enterInteger_Required(Scanner keyboard, String fieldLabel) {
		// inputAccepted is controlling variable for do-while loop
		boolean inputAccepted = false;
		int inputNumber;
		// do-while checks for non-integer inputs
		// try-catch will clear buffer of non-integer input before repeating.
		// nextInt already does not permit null input
		// switch statement routes to setter methods, whose true/false
		// return value will determine if loop terminates
		do {
			try {
				inputNumber = keyboard.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("Integer required");
				// clear keyboard buffer if filled with non-integer value
				keyboard.nextLine();
				continue;
			}
			// route to setter method based on fieldLabel
			// switch statement used for extensibility purposes
			switch (fieldLabel) {
			case "Year":
				if (inputNumber > 1989 && inputNumber < 2051) {
					year = inputNumber;
					inputAccepted = true;
				} else {
					System.out.println("1990-2050");
				}
				break;
			case "Month":
				if (inputNumber >= 0 && inputNumber < 12) {
					month = inputNumber;
					inputAccepted = true;
				} else {
					System.out.println("number from 0-11");
				}
				break;
			case "Day":
				// TODO: variable month checks?
				if (inputNumber >= 0 && inputNumber < 31) {
					day = inputNumber;
					inputAccepted = true;
				} else {
					System.out.println("0-30");
				}
				break;
			case "Hour":
				if (inputNumber >= 0 && inputNumber < 24) {
					time = inputNumber;
					inputAccepted = true;
				} else {
					System.out.println("0-23");
				}
				break;
			case "Room":
				if (inputNumber >= 0 && inputNumber < 3) {
					int roomSelect = inputNumber;
					rooms.setCurrentIndex(roomSelect);
					roomIndex = rooms.getCurrentRoom().getIndex();
					inputAccepted = true;
				} else {
					System.out.println("0-2");
				}
				break;
			case "Teacher":
				if(inputNumber>=0 && inputNumber<3) {
					teacherIndex=inputNumber;
					inputAccepted=true;
				}else {
					System.out.println("0-2");
				}
				break;
			case "Class":
				if(inputNumber>=0 && inputNumber<3) {
					classIndex=inputNumber;
					inputAccepted=true;
				}else {
					System.out.println("0-2");
				}
				break;
			default:
				// failure case: code failed to provide valid field label
				System.out.println("Error: Unrecognized field. Exiting.");
				System.exit(0);
				break;
			}

		} while (!inputAccepted);
		// clear keyboard buffer of \n character to prevent
		// interference with later fields
		keyboard.nextLine();
	}

	public static void setUpFiles() {

		/*
		 * Working on filling out initial tests of all dictionaries May need to default
		 * to Scanner-based file writing May keep writing in DataFile and keep
		 * DictionaryFile code for read-only functionality
		 */
		// Class
		// Teacher
		// Student
		// ClassSchedule
		// Day
		// Holiday
		// Room
		PrintWriter outputFile = null;

		System.out.println("Test Teacher Dictionary write");

		try {
			outputFile = new PrintWriter(new FileOutputStream("teacherdictionary.txt"));
		} catch (FileNotFoundException e) {
			System.out.println("Unable to open file.");
		}

		outputFile.println("<ENTRYSTART>");
		outputFile.println("<ID>ZAK_B");
		outputFile.println("<NAME>Zakaria Baani");
		outputFile.println("<EMAIL>zakb@debug.fakecentury.edu");
		outputFile.println("<ENTRYEND>");
		outputFile.println("<ENTRYSTART>");
		outputFile.println("<ID>MAH_E");
		outputFile.println("<NAME>Mahmoud Eid");
		outputFile.println("<EMAIL>mahe@debug.fakecentury.edu");
		outputFile.println("<ENTRYEND>");
		outputFile.println("<ENTRYSTART>");
		outputFile.println("<ID>BRI_J");
		outputFile.println("<NAME>Brian Jenson");
		outputFile.println("<EMAIL>brij@debug.fakecentury.edu");
		outputFile.println("<ENTRYEND>");

		outputFile.close();

		// TeacherDictionary teachers = new TeacherDictionary();
		//System.out.println(teachers);

		// try {
		// outputFile = new PrintWriter(new FileOutputStream("classdictionary.txt"));
		// }catch (FileNotFoundException e){
//			System.out.println("Unable to open file.");			
		// }

		System.out.println("Test Room Dictionary write");

		try {
			outputFile = new PrintWriter(new FileOutputStream("roomdictionary.txt"));
		} catch (FileNotFoundException e) {
			System.out.println("Unable to open file.");
		}

		outputFile.println("<ENTRYSTART>");
		outputFile.println("<ID>W101");
		outputFile.println("<NAME>West 101");
		outputFile.println("<CAPACITY>25");
		outputFile.println("<ENTRYEND>");
		outputFile.println("<ENTRYSTART>");
		outputFile.println("<ID>E2213");
		outputFile.println("<NAME>East 2213");
		outputFile.println("<CAPACITY>45");
		outputFile.println("<ENTRYEND>");
		outputFile.println("<ENTRYSTART>");
		outputFile.println("<ID>E1001");
		outputFile.println("<NAME>East 1001");
		outputFile.println("<CAPACITY>60");
		outputFile.println("<ENTRYEND>");

		outputFile.close();

		// RoomDictionary rooms = new RoomDictionary();
		// System.out.println(rooms);

		/*
		 * class
		 * 
		 * <ENTRYSTART> <TITLE> <TEACHER> <STUDENTCOUNT> <STUDENTLIST> <SCHEDULE>
		 * <ENTRYEND>
		 * 
		 */

		System.out.println("Test Class Dictionary write");

		try {
			outputFile = new PrintWriter(new FileOutputStream("classdictionary.txt"));
		} catch (FileNotFoundException e) {
			System.out.println("Unable to open file.");
		}

		outputFile.println("<ENTRYSTART>");
		outputFile.println("<TITLE>MATH 2081_1");
		outputFile.println("<TEACHER>BRIAN_J");
		outputFile.println("<SCHEDULE>MATH 2081_1");
		outputFile.println("<STUDENTCOUNT>15");
		outputFile.println("<STUDENTLIST>CATHY4");
		outputFile.println("<STUDENTLIST>DAN5");
		outputFile.println("<STUDENTLIST>EVE6");
		outputFile.println("<ENTRYEND>");
		outputFile.println("<TITLE>PHYS 1082_1");
		outputFile.println("<TEACHER>MAHMOOD_E");
		outputFile.println("<SCHEDULE>PHYS 1082_1");
		outputFile.println("<STUDENTCOUNT>20");
		outputFile.println("<STUDENTLIST>FRANK7");
		outputFile.println("<STUDENTLIST>GRACE8");
		outputFile.println("<STUDENTLIST>HENRY9");
		outputFile.println("<ENTRYEND>");
		outputFile.println("<ENTRYSTART>");
		outputFile.println("<TITLE>CSCI 1082_2");
		outputFile.println("<TEACHER>ZACH_B");
		outputFile.println("<SCHEDULE>CSCI 1082_2");
		outputFile.println("<STUDENTCOUNT>15");
		outputFile.println("<STUDENTLIST>AARON1");
		outputFile.println("<STUDENTLIST>AMY2");
		outputFile.println("<STUDENTLIST>BOB3");
		outputFile.println("<ENTRYEND>");

		outputFile.close();

		///
		// DictionaryFile testDictionary = new
		/// DictionaryFile("classdictionary.txt","ClassDictionary");
		// ClassDictionary testDictionary = new ClassDictionary();
//		System.out.println(testDictionary.getEntryCount());
//		System.out.println(testDictionary.getOpenStatus());
//		System.out.println(testDictionary.isValid());
		// System.out.println(testDictionary);

		// Calendar calendar = new GregorianCalendar();
		// SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHH");
		// System.out.println(df.format(calendar.getTime()));

	}

}
