import java.util.GregorianCalendar;


public class Year {
	private GregorianCalendar year;
	private Month[] months = new Month[12];
	
	
	
	
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
	
	
	public Year(GregorianCalendar year) {
		super();
		this.year = year;
		for (int i = 0; i < months.length; i++) {
			months[i] = new Month(year, new GregorianCalendar(year.YEAR, i, 0));
		}
	}
	
	
}
