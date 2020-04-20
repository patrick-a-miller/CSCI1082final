package CalendarObjects;

import java.util.Calendar;
import java.util.GregorianCalendar;


public class Year {
	private GregorianCalendar year;
	private Month[] months = new Month[12];


	public Year(GregorianCalendar year, Room[] roomArray) {
		super();
		this.year = year;
		for (int i = 0; i < months.length; i++) {
			months[i] = new Month(new GregorianCalendar(year.get(Calendar.YEAR), i, 0),roomArray);
		}
	}



	public GregorianCalendar getYear() {
		return year;
	}
	public void setYear(GregorianCalendar year) {
		this.year = year;
	}
	public Month[] getMonths() {
		return months;
	}
	public void setMonths(Month[] months) {
		this.months = months;
	}




}