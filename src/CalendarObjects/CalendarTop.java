package CalendarObjects;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

public class CalendarTop {

	private ArrayList<Year> yearList;
	private Year selectedYear;
	private Month selectedMonth;
	private Day selectedDay;
	private CalendarRoom selectedCalendarRoom;
	private TimeSlot selectedTimeSlot;
	private Room[] roomArray;
	private SimpleDateFormat timeFormat = new SimpleDateFormat("yyyyMMddHH");

	public CalendarTop(Room[] roomArray) {
		setupYearList(roomArray);
		setupDefaultSelections();

	}

	private void setupYearList(Room[] roomArray) {
		yearList = new ArrayList<Year>();
		selectedYear = new Year(new GregorianCalendar(), roomArray);
		yearList.add(selectedYear);

	}

	private void setupDefaultSelections() {
		String yearString = timeFormat.format(selectedYear.getYear().getTime());
		System.out.println("CalendarTop yearString: " + yearString);

	}

	public void selectYear(String yearString) {
		int yearNumber = extractYearValue(yearString);
		selectYear(yearNumber);
	}

	public void selectYear(int yearNumber) {
		int currentNumber = convertYearToYearNumber(selectedYear);
		if (yearNumber == currentNumber) {
			return;
		}
		searchYearList(yearNumber);

	}

	private void searchYearList(int yearNumber) {
		int insertIndex = -1;
		for (int i = 0; i < yearList.size(); i++) {
			Year indexYear = yearList.get(i);
			if (indexYear != null) {
				int indexYearNumber = convertYearToYearNumber(indexYear);
				if (yearNumber == indexYearNumber) {
					selectedYear = indexYear;
					return;
				} else if (yearNumber < indexYearNumber) {
					insertIndex = i;
					break;
				}
			}
		}
		insertNewYear(yearNumber, insertIndex);

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
		int currentNumber = convertMonthToMonthNumber(selectedMonth);
		if (monthNumber == currentNumber) {
			return;
		}
		Month newMonth = selectedYear.getMonth(monthNumber);
		if (newMonth != null) {
			selectedMonth = newMonth;
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
		int currentNumber = convertDayToDayNumber(selectedDay);
		if (dayNumber == currentNumber) {
			return;
		}
		Day newDay = selectedMonth.getDay(dayNumber);
		if (newDay != null) {
			selectedDay = newDay;
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
		if (selectedCalendarRoom.getRoom().equals(otherRoom)) {
			return;
		}
		CalendarRoom newCalendarRoom = selectedDay.getCalendarRoom(otherRoom);
		if (newCalendarRoom == null) {
			return;
		} else {
			selectedCalendarRoom = newCalendarRoom;
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
		if (selectedTimeSlot.getTime() == time) {
			return;
		}
		TimeSlot otherTimeSlot = selectedCalendarRoom.getTimeSlot(time);
		if (otherTimeSlot == null) {
			return;
		} else {
			selectedTimeSlot = otherTimeSlot;
		}

	}

	public void selectTimeSlot(String yearString, String roomIdString) {
		Room otherRoom = searchRoomId(roomIdString);
		if (roomIdString == null) {
			return;
		}
		selectTimeSlot(yearString, otherRoom);

	}

	private int extractYearValue(String yearString) {
		int yearNumber = Integer.parseInt(yearString.substring(0, 4));
		return yearNumber;
	}

	private int extractMonthValue(String yearString) {
		int monthNumber = Integer.parseInt(yearString.substring(4, 6));
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
	
	@Override
	public String toString() {
		String text = "Calendar: \n";
		text+="Current Year: "+selectedYear+" Current Month: "+selectedMonth+" Current Day: "+selectedDay
			+ " Current Room: "+selectedCalendarRoom+" Selected Time: "+ selectedTimeSlot+"\n";
		for(int i=0; i<yearList.size();i++) {
			text+=yearList.get(i).toString();
		}
		return text;
	}

}
