package CalendarFiles;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

import CalendarObjects.CalendarRoom;
import CalendarObjects.CalendarTop;
import CalendarObjects.ClassEntry;
import CalendarObjects.Day;
import CalendarObjects.EventRecord;
import CalendarObjects.Month;
import CalendarObjects.Room;
import CalendarObjects.Teacher;
import CalendarObjects.TimeSlot;
import CalendarObjects.Year;

public class DataFile {

	private ArrayList<Year> yearList;
	private CalendarTop calendarYears;
	private String filePath;
	private Room[] roomArray;
	private ClassEntry[] classEntryArray;
	private Teacher[] teacherArray;
	private ArrayList<EventRecord> eventRecordList;
	private ArrayList<String> rawData;
	private SimpleDateFormat timeFormat = new SimpleDateFormat("yyyyMMddHH");

	// year,month,day,room,time

	// Room otherRoom = searchRoomId(roomIdString);
	// getClassEntryArray() {
	// getTeacherArray() {
	// addTimeSlot(int time, ClassEntry classEntry, Teacher teacher) {
	Scanner reader;
	PrintWriter writer;

	public DataFile(ArrayList<Year> yearList, CalendarTop calendarYears) {
		this.yearList = yearList;
		this.calendarYears = calendarYears;
		filePath = "calendarSave.txt";
		roomArray = calendarYears.getRoomArray();
		classEntryArray = calendarYears.getClassEntryArray();
		teacherArray = calendarYears.getTeacherArray();
		eventRecordList = new ArrayList<EventRecord>();
		buildList();
	}

	private void buildList() {
		try {
			reader = new Scanner(new FileInputStream(filePath));
			loadData();
			reader.close();
		} catch (FileNotFoundException e) {
			System.out.println("No stored calendar data.");
			return;
		}
		parseData();
		eventRecordList.sort(EventRecord.compareDateRoom);
	}

	private void loadData() {
		rawData = new ArrayList<String>();
		while (reader.hasNextLine()) {
			rawData.add(reader.nextLine());
		}
	}

	private void parseData() {
		boolean entryOpen = false;
		int year = 0;
		int month = 0;
		int day = 0;
		Room room = null;
		int hour = 0;
		ClassEntry classEntry = null;
		Teacher teacher = null;
		String temp = null;
		for (String currentText : rawData) {
			if (currentText.equals("<ENTRY>")) {
				if (entryOpen == true) {
					room = null;
					classEntry = null;
					teacher = null;
					year = month = day = hour = 0;
				}
				entryOpen = true;
			} else if (currentText.equals("<ENTRYEND>")) {
				entryOpen = false;
				eventRecordList.add(new EventRecord(year, month, day, room, hour, classEntry, teacher));
				room = null;
				classEntry = null;
				teacher = null;
				year = month = day = hour = 0;
			} else if ((currentText.length() >= 6) && currentText.substring(0, 6).equals("<YEAR>")) {
				year = Integer.parseInt(currentText.substring(6));
			} else if ((currentText.length() >= 7) && currentText.substring(0, 7).equals("<MONTH>")) {
				month = Integer.parseInt(currentText.substring(7));
			} else if ((currentText.length() >= 5) && currentText.substring(0, 5).equals("<DAY>")) {
				day = Integer.parseInt(currentText.substring(5));
			} else if ((currentText.length() >= 6) && currentText.substring(0, 6).equals("<ROOM>")) {
				temp = currentText.substring(6);
				room = searchRoomId(temp);
			} else if ((currentText.length() >= 6) && currentText.substring(0, 6).equals("<HOUR>")) {
				hour = Integer.parseInt(currentText.substring(6));
			} else if ((currentText.length() >= 12) && currentText.substring(0, 12).equals("<CLASSENTRY>")) {
				temp = currentText.substring(12);
				classEntry = searchClassEntry(temp);
			} else if ((currentText.length() >= 9) && currentText.substring(0, 9).equals("<TEACHER>")) {
				temp = currentText.substring(9);
				teacher = searchTeacher(temp);
			}

		}
		rawData = null;
	}

	private Teacher searchTeacher(String teacherString) {
		Teacher indexTeacher = null;
		for (int i = 0; i < teacherArray.length; i++) {
			indexTeacher = teacherArray[i];
			if (indexTeacher.getId().equals(teacherString)) {
				return teacherArray[i];
			}
		}
		return null;
	}

	private ClassEntry searchClassEntry(String classEntryString) {
		ClassEntry indexClassEntry = null;
		for (int i = 0; i < classEntryArray.length; i++) {
			indexClassEntry = classEntryArray[i];
			if (indexClassEntry.getClassTitle().equals(classEntryString)) {
				return classEntryArray[i];
			}
		}
		return null;
	}

	private Room searchRoomId(String roomIdString) {
		Room indexRoom = null;
		for (int i = 0; i < roomArray.length; i++) {
			indexRoom = roomArray[i];
			if (indexRoom.getRoomId().equals(roomIdString)) {
				return roomArray[i];
			}
		}
		return null;
	}

	public void updateSchedule() {
		if (!eventRecordList.isEmpty()) {
			for (EventRecord current : eventRecordList) {
				calendarYears.selectRoom(current.getCombinedDateValues(), current.getRoom());
				calendarYears.getSelectedCalendarRoom().addTimeSlot(current.getHour(), current.getClassEntry(),
						current.getTeacher());
			}
		}
	}

	public void writeData() {
		eventRecordList.clear();
		scanYears();
		writeFile();

	}

	private void scanYears() {
		for (Year currentYear : yearList) {
			scanMonths(currentYear);
		}
	}

	private void scanMonths(Year year) {
		String yearString = timeFormat.format(year.getYear().getTime());
		int yearNumber = extractYearValue(yearString);
		Month[] months = year.getMonths();
		for (Month currentMonth : months) {
			scanDays(yearNumber, currentMonth);
		}
	}

	private int extractYearValue(String yearString) {
		int yearNumber = Integer.parseInt(yearString.substring(0, 4));
		return yearNumber;
	}

	private void scanDays(int year, Month month) {
		int monthNumber = convertMonthToMonthNumber(month);
		Day[] days = month.getDays();
		for (Day currentDay : days) {
			scanCalendarRooms(year, monthNumber, currentDay);
		}
	}

	private int convertMonthToMonthNumber(Month month) {
		String dateString = timeFormat.format(month.getMonth().getTime());
		return extractMonthValue(dateString);
	}

	private int extractMonthValue(String yearString) {
		int monthNumber = Integer.parseInt(yearString.substring(4, 6)) - 1;
		return monthNumber;
	}

	private void scanCalendarRooms(int year, int month, Day day) {
		int dayNumber = convertDayToDayNumber(day);
		ArrayList<CalendarRoom> rooms = day.getRooms();
		for (CalendarRoom currentRoom : rooms) {
			scanHours(year, month, dayNumber, currentRoom);
		}
	}

	private int convertDayToDayNumber(Day day) {
		String dateString = timeFormat.format(day.getDay().getTime());
		return extractDayValue(dateString);
	}

	private int extractDayValue(String yearString) {
		int dayNumber = Integer.parseInt(yearString.substring(6, 8));
		return dayNumber;

	}

	private void scanHours(int year, int month, int day, CalendarRoom room) {
		TimeSlot[] timeSlots = room.getTimeSlots();
		Room roomEntry = room.getRoom();
		for (int i = 0; i < 24; i++) {
			TimeSlot indexSlot = timeSlots[i];
			if (indexSlot != null) {
				ClassEntry classEntry = indexSlot.getClassEntry();
				Teacher teacher = indexSlot.getTeacher();
				eventRecordList.add(new EventRecord(year, month, day, roomEntry, i, classEntry, teacher));
			}
		}

	}

	private void writeFile() {
		try {
			writer = new PrintWriter(new FileOutputStream(filePath));
		} catch (FileNotFoundException e) {
			System.out.println("Unable to open file.");
		}

		for (EventRecord currentEvent : eventRecordList) {
			writer.println(currentEvent);
		}

		writer.close();

	}

}
