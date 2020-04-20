package CalendarObjects;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Month{

	private GregorianCalendar month;
	private Day[] days;



	public GregorianCalendar getMonth() {
		return month;
	}
	public void setMonth(GregorianCalendar month) {
		this.month = month;
	}
	public Day[] getDays() {
		return days;
	}
	public void setDays(Day[] days) {
		this.days = days;
	}
	

	public Month(GregorianCalendar month, Room[] roomArray) {
		this.month = month;
		days = new Day[month.getActualMaximum(Calendar.DAY_OF_MONTH)];
		for (int i = 0; i < days.length; i++) {
			days[i] = new Day(new GregorianCalendar(month.get(Calendar.YEAR), month.get(Calendar.MONTH), i+1,0,0),roomArray);
		}
	}


}