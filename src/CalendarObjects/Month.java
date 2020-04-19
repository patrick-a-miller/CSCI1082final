import java.util.GregorianCalendar;

public class Month{

	private GregorianCalendar month;
	private Day[] days = new Day[31];
	
	
	
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
	
	
	public Month(GregorianCalendar month) {
		this.month = month;
		for (int i = 0; i < days.length; i++) {
			days[i] = new Day(new GregorianCalendar(month.YEAR, month.MONTH, i+1));
		}
	}
	
	
}
