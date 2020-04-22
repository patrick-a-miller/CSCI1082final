package CalendarObjects;
import java.util.ArrayList;
//import java.util.GregorianCalendar;

public class CalendarRoom {

	private Room room;
	private int maxCapacity;
	private ArrayList<DayMonthYear> calendarDays;
	//this is suppost to be a 24 size array of timeslots right?

	public CalendarRoom(Room room) {
		this.room = room;
		maxCapacity = room.getCapacity();
		calendarDays = new ArrayList<DayMonthYear>();

	}

	public Room getRoom() {
		return room;
	}

	public int getMaxCapacity() {
		return maxCapacity;
	}

	public ArrayList<DayMonthYear> getCalendarDays() {
		return calendarDays;
	}

	public DayMonthYear addCalendarDay(int year, int month, int day) {
		DayMonthYear newDate = new DayMonthYear(year, month, day);
		if (calendarDays.size() == 0) {
			calendarDays.add(newDate);
			return calendarDays.get(0);
		} else {
			for (int i = 0; i < calendarDays.size(); i++) {
				DayMonthYear indexDay = calendarDays.get(i);
				int comparison = indexDay.compareTo(newDate);
				if (comparison == 0) {
					return calendarDays.get(i);
				} else if (comparison < 0) {
					calendarDays.add(i, newDate);
					return calendarDays.get(i);
				} else if (i == calendarDays.size() - 1) {

					calendarDays.add(newDate);
					return calendarDays.get(i+1);
				}
			}
		}
		return null;
	}

	public int searchCalendarDay(int year, int month, int day) {
		DayMonthYear searchDate = new DayMonthYear(year, month, day);
		if (calendarDays.size() == 0) {
			return -1;
		} else {
			for (int i = 0; i < calendarDays.size(); i++) {
				DayMonthYear indexDay = calendarDays.get(i);
				int comparison = indexDay.compareTo(searchDate);
				if (comparison == 0) {
					return i;
				}

			}
			return -1;
		}
	}
	
	public DayMonthYear getDayMonthYear(int index) {
		return calendarDays.get(index);
		
	}

	@Override
	public String toString() {
		String text = "Calendar for Room: " + room.getRoomId() +" Cap: "+ maxCapacity+ "\n";
		for(int i = 0; i<calendarDays.size(); i++) {
			DayMonthYear indexDay = calendarDays.get(i);
			text+= indexDay+"\n";
		}
		return text;
	}
	
}
