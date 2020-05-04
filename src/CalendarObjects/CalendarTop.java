package CalendarObjects;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import CalendarFiles.ClassDictionary;
import CalendarFiles.RoomDictionary;
import CalendarFiles.TeacherDictionary;

public class CalendarTop {

	private ArrayList<Year> yearList;
	private Year selectedYear;
	private Month selectedMonth;
	private Day selectedDay;
	private CalendarRoom selectedCalendarRoom;
	private TimeSlot selectedTimeSlot;
		
	private TeacherDictionary teacherDictionary;
	private RoomDictionary roomDictionary;
	private ClassDictionary classDictionary;

	private Room[] roomArray;
	private SimpleDateFormat timeFormat = new SimpleDateFormat("yyyyMMddHH");

	public CalendarTop() {
		setupDictionaries();
		roomArray = roomDictionary.getRoomArray();
		setupYearList(roomArray);
		setupDefaultSelections();
	}

	private void setupDictionaries() {
		CalendarFiles.DefaultFileSetup.maybeSetUpFiles();
		teacherDictionary = new TeacherDictionary();
		roomDictionary = new RoomDictionary();
		classDictionary = new ClassDictionary();

	}

	private void setupYearList(Room[] roomArray) {
		yearList = new ArrayList<Year>();
		selectedYear = new Year(new GregorianCalendar(), roomArray);
		yearList.add(selectedYear);

	}

	private void setupDefaultSelections() {
		String yearString = timeFormat.format(selectedYear.getYear().getTime());
		selectTimeSlot(yearString, roomArray[0]);
	}

	public void selectYear(String yearString) {
		int yearNumber = extractYearValue(yearString);
		selectYear(yearNumber);
	}

	public void selectYear(int yearNumber) {
		if (selectedYear != null) {
			int currentNumber = convertYearToYearNumber(selectedYear);
			if (yearNumber == currentNumber) {
				return;
			}
		}
		Year newYear = searchYearList(yearNumber);
		if(newYear!=null) {
			selectedYear=newYear;
			selectedMonth = null;
			selectedDay = null;
			selectedCalendarRoom = null;
			selectedTimeSlot = null;
		}

	}

	private Year searchYearList(int yearNumber) {
		int insertIndex = -1;
		for (int i = 0; i < yearList.size(); i++) {
			Year indexYear = yearList.get(i);
			if (indexYear != null) {
				int indexYearNumber = convertYearToYearNumber(indexYear);
				if (yearNumber == indexYearNumber) {
					selectedYear = indexYear;
					return selectedYear;
				} else if (yearNumber < indexYearNumber) {
					insertIndex = i;
					break;
				}
			}
		}
		if(insertIndex<0) {
			insertIndex=yearList.size();
			insertNewYear(yearNumber,yearList.size());
		}else {
		insertNewYear(yearNumber, insertIndex);
		}
		return yearList.get(insertIndex);

	}

	private void insertNewYear(int yearNumber, int indexNumber) {
		if (indexNumber < 0 || yearNumber < 0) {
			return;
		}
		Year newYear = new Year(new GregorianCalendar(yearNumber, 0, 1), roomArray);
		yearList.add(indexNumber, newYear);

	}

	private int convertYearToYearNumber(Year year) {
		String dateString = timeFormat.format(year.getYear().getTime());
		return extractYearValue(dateString);
	}

	public void selectMonth(String yearString) {
		selectYear(yearString);
		int monthNumber = extractMonthValue(yearString);
		if (selectedMonth != null) {
			int currentNumber = convertMonthToMonthNumber(selectedMonth);
			if (monthNumber == currentNumber) {
				return;
			}
		}
		Month newMonth = selectedYear.getMonth(monthNumber);
		if (newMonth != null) {
			selectedMonth = newMonth;
			selectedDay = null;
			selectedCalendarRoom = null;
			selectedTimeSlot = null;
		}
	}

	private int convertMonthToMonthNumber(Month month) {
		String dateString = timeFormat.format(month.getMonth().getTime());
		return extractMonthValue(dateString);
	}

	public void selectDay(String yearString) {
		selectYear(yearString);
		selectMonth(yearString);
		int dayNumber = extractDayValue(yearString);
		if (selectedDay != null) {
			int currentNumber = convertDayToDayNumber(selectedDay);
			if (dayNumber == currentNumber) {
				return;
			}
		}
		Day newDay = selectedMonth.getDay(dayNumber);
		if (newDay != null) {
			selectedDay = newDay;
			selectedCalendarRoom = null;
			selectedTimeSlot = null;
		}
	}

	private int convertDayToDayNumber(Day day) {
		String dateString = timeFormat.format(day.getDay().getTime());
		return extractDayValue(dateString);
	}

	public void selectRoom(String yearString, Room otherRoom) {
		selectYear(yearString);
		selectMonth(yearString);
		selectDay(yearString);
		if (selectedCalendarRoom != null) {
			if (selectedCalendarRoom.getRoom().equals(otherRoom)) {
				return;
			}
		}
		CalendarRoom newCalendarRoom = selectedDay.getCalendarRoom(otherRoom);
		if (newCalendarRoom == null) {
			return;
		} else {
			selectedCalendarRoom = newCalendarRoom;
			selectedTimeSlot = null;
		}
	}

	public void selectRoom(String yearString, String roomIdString) {
		Room otherRoom = searchRoomId(roomIdString);
		if (roomIdString == null) {
			return;
		}
		selectRoom(yearString, otherRoom);
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

	public void selectTimeSlot(String yearString, Room otherRoom) {
		selectYear(yearString);
		selectMonth(yearString);
		selectDay(yearString);
		selectRoom(yearString, otherRoom);
		int time = extractHourValue(yearString);
		if (selectedTimeSlot != null) {
			if (selectedTimeSlot.getTime() == time) {
				return;
			}
		}
		TimeSlot otherTimeSlot = selectedCalendarRoom.getTimeSlot(time);
		if (otherTimeSlot == null) {
			return;
		} else {
			selectedTimeSlot = otherTimeSlot;
		}

	}

	public void selectTimeSlot(String yearString, String roomIdString) {
		if (roomIdString == null) {
			return;
		}
		Room otherRoom = searchRoomId(roomIdString);
		selectTimeSlot(yearString, otherRoom);

	}

	private int extractYearValue(String yearString) {
		int yearNumber = Integer.parseInt(yearString.substring(0, 4));
		return yearNumber;
	}

	private int extractMonthValue(String yearString) {
		int monthNumber = Integer.parseInt(yearString.substring(4, 6)) - 1;
		return monthNumber;
	}

	private int extractDayValue(String yearString) {
		int dayNumber = Integer.parseInt(yearString.substring(6, 8));
		return dayNumber;

	}

	private int extractHourValue(String yearString) {
		int hourNumber = Integer.parseInt(yearString.substring(8, 10));
		return hourNumber;
	}

	public String adjustDateInput(String year, String month, String day, String hour) {
		int yearNumber = Integer.parseInt(year);
		int monthNumber = Integer.parseInt(month);
		int dayNumber = Integer.parseInt(day);
		int hourNumber = Integer.parseInt(hour);
		return adjustDateInput(yearNumber, monthNumber, dayNumber, hourNumber);
	}

	public String adjustDateInput(int year, int month, int day, int hour) {
		GregorianCalendar newDay = new GregorianCalendar(year, month - 1, day, hour, 0);
		return timeFormat.format(newDay.getTime());
	}
	
	public String[] getSelectedWeekLabelText() {
		Day[] weekOfDays = getWeekForSelectedDay();
		String [] weekLabels = {"Sun","Mon","Tue","Wed","Thu","Fri","Sat"};
		for(int i = 0; i<weekLabels.length; i++) {
			weekLabels[i]= "<html>"+weekLabels[i]+"<br>"+weekOfDays[i].textLabelFormat()+"</html>";
		}
		return weekLabels;
		
	}
	
	public Day[] getWeekForSelectedDay() {
		if(selectedDay==null) {
			return null;
		}
		Day[] weekOfDays = new Day[7];
		//day of week starts at 1, not 0
		int selectedDayOfWeek = selectedDay.getDay().get(Calendar.DAY_OF_WEEK);
		int dayWeekOffset = 1-selectedDayOfWeek;
		int currentYear =selectedDay.getDay().get(Calendar.YEAR);
		int currentMonth = selectedDay.getDay().get(Calendar.MONTH);
		int currentDayOfMonth = selectedDay.getDay().get(Calendar.DAY_OF_MONTH);
		GregorianCalendar weekDay = new GregorianCalendar(currentYear,currentMonth,currentDayOfMonth+dayWeekOffset);
		for(int index=0;index<7;index++) {
			Year indexYear=selectedYear;
			Month indexMonth=selectedMonth;
			//check if week crosses year boundary
			if(weekDay.get(Calendar.YEAR)!=currentYear) {
				indexYear=searchYearList(weekDay.get(Calendar.YEAR));
			}
			//check if week crosses month boundary
			if(weekDay.get(Calendar.MONTH)!=currentMonth) {
				indexMonth=indexYear.getMonth(weekDay.get(Calendar.MONTH));
			}
			//getday
			weekOfDays[index]=indexMonth.getDay(weekDay.get(Calendar.DAY_OF_MONTH));
			weekDay.add(Calendar.DAY_OF_YEAR, 1);
			
		}
		
		return weekOfDays;
	}
	
	/**
	 * @return the selectedYear
	 */
	public Year getSelectedYear() {
		return selectedYear;
	}

	/**
	 * @return the selectedMonth
	 */
	public Month getSelectedMonth() {
		return selectedMonth;
	}

	/**
	 * @return the selectedDay
	 */
	public Day getSelectedDay() {
		return selectedDay;
	}

	/**
	 * @return the selectedTimeSlot
	 */
	public TimeSlot getSelectedTimeSlot() {
		return selectedTimeSlot;
	}

	/**
	 * @return the teacherDictionary
	 */
	public TeacherDictionary getTeacherDictionary() {
		return teacherDictionary;
	}

	/**
	 * @return the roomDictionary
	 */
	public RoomDictionary getRoomDictionary() {
		return roomDictionary;
	}

	/**
	 * @return the classDictionary
	 */
	public ClassDictionary getClassDictionary() {
		return classDictionary;
	}

	public Room[] getRoomArray() {
		return roomDictionary.getRoomArray();
	}

	public Teacher[] getTeacherArray() {
		return teacherDictionary.getTeacherArray();
	}

	public ClassEntry[] getClassEntryArray() {
		return classDictionary.getClassEntryArray();
	}

	@Override
	public String toString() {
		String text = "Calendar: \n";
		text += "Current Year: " + selectedYear + " Current Month: " + selectedMonth + " Current Day: " + selectedDay
				+ " Current Room: " + selectedCalendarRoom + " Selected Time: " + selectedTimeSlot + "\n";
		for (int i = 0; i < yearList.size(); i++) {
			text += yearList.get(i).toString();
		}
		return text;
	}

}
