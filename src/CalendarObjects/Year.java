package CalendarObjects;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;


public class Year {
	private GregorianCalendar year;
	private Month[] months = new Month[12];


	public Year(GregorianCalendar year, Room[] roomArray) {
		super();
		this.year = year;
		for (int i = 0; i < months.length; i++) {
			months[i] = new Month(new GregorianCalendar(year.get(Calendar.YEAR), i+1, 0),roomArray);
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

	
	public Month getMonth(int monthNumber) {
		if(monthNumber<0 || monthNumber>11) {
			return null;
		}
		return months[monthNumber];
	}

	@Override
	public String toString() {
		SimpleDateFormat timeFormat = new SimpleDateFormat("yyyyMMddHH");
		String text= "Year: " + timeFormat.format(year.getTime())+ "\n";
		for(int i = 0; i<months.length;i++) {
			text+=months[i].toString()+"\n";
		}
		return text;
		}
	

}