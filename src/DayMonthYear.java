import java.util.ArrayList;
import java.util.Arrays;
import java.util.GregorianCalendar;

public class DayMonthYear {
	private GregorianCalendar day;
	private ClassEntry[] timeSlot = new ClassEntry[24];
	//This forces users to divvy time up hour by hour but personal I think thats perfectly fine.
	public DayMonthYear (GregorianCalendar day) {
		this.day = day;
		if(day.DAY_OF_WEEK==7 || day.DAY_OF_WEEK==1) {
			for (int i = 0; i < timeSlot.length; i++) {
				timeSlot[i] = weekend;//weekend days give warnings when this is seen
			}
		}else{
			for (int i = 0; i < timeSlot.length; i++) {
				timeSlot[i] = null;//blank default constructor for day where we don't have a recurring class
			}
		}
		
	}
	
	
	
	public boolean isAvailable (int hour) {
		if(timeSlot[hour]==null)
			return true;
		if(timeSlot[hour]==weekend) {
			System.out.println("Warning you are about to schedule an event on a weekend");
			return true;
		}
		return false;
	}



	public boolean setClass(int time, ClassEntry event) {
		if(!(0<=time && time<=24)) {
			System.out.println("INVALID TIME!");
			return false;
		}
		if(isAvailable(time)) {
			timeSlot[time] = event;
			return true;
		}else {
			System.out.println("There is already an event here please select a different time.");
			return false;
		}
	}



	@Override
	public String toString() {
		return "DayMonthYear [day=" + day + ", timeSlot=" + Arrays.toString(timeSlot) + "]";
	}
	
	
	
}
