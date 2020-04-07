import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class DayMonthYear implements Comparable{
	private GregorianCalendar day;
	private ArrayList<TimeSlot> timeSlots;
	private SimpleDateFormat timeFormat = new SimpleDateFormat("yyyyMMddHH");
	// This forces users to divvy time up hour by hour but personal I think thats
	// perfectly fine.

	public DayMonthYear(int year, int month, int day) {
		// day is given value of midnight
		this.day = new GregorianCalendar(year, month, day, 0, 0);
		timeSlots = new ArrayList<TimeSlot>();
//		if(day.DAY_OF_WEEK==7 || day.DAY_OF_WEEK==1) {
//			for (int i = 0; i < timeSlot.length; i++) {
//				timeSlot[i] = weekend;//weekend days give warnings when this is seen
//			}
//		}else{
//			for (int i = 0; i < timeSlot.length; i++) {
//				timeSlot[i] = null;//blank default constructor for day where we don't have a recurring class
//			}
//		}

	}

	public boolean isAvailable(int hour) {
		if (hour > 23)  {
			return false;
		}
		if (timeSlots.size()==24) {
			return false;
		}
		int checkTime = convertCalendarTime(day) + hour;
		for (int i = 0; i < timeSlots.size(); i++) {
			if (timeSlots.get(i).getTime() == checkTime) {
				return false;
			}
		}
		return true;
//		if(timeSlot[hour]==weekend) {
//			System.out.println("Warning you are about to schedule an event on a weekend");
//			return true;
//		}
//		return false;
	}

	public boolean addTimeSlot(int time, Room room, ClassEntry classEntry, Teacher teacher) {
		if ((0 > time || time > 23)) {
			System.out.println("INVALID TIME!");
			return false;
		}
		int dateNumber = convertCalendarTime(day);
		TimeSlot event = new TimeSlot(dateNumber+time,60,room,classEntry,teacher);
		if (isAvailable(time)) {
			if(timeSlots.size()==0) {
				timeSlots.add(event);
				return true;
			}else {
			for (int i = 0; i < timeSlots.size(); i++) {
				TimeSlot indexTime = timeSlots.get(i);
				if(indexTime.compareTo(event) < 0) {
					timeSlots.add(i, event);
					return true;
				}
			}
			timeSlots.add(event);
			return true;
			}
		} else {
			System.out.println("There is already an event here please select a different time.");
			return false;
		}
	}

	public boolean removeTimeSlot(int time) {
		if ((0 > time || time > 23)) {
			System.out.println("INVALID TIME!");
			return false;
		}
		int dateNumber = convertCalendarTime(day)+time;
		for (int i = 0; i < timeSlots.size(); i++) {
			TimeSlot indexTime = timeSlots.get(i);
			if(indexTime.getTime()==dateNumber) {
				timeSlots.remove(i);
				return true;
			}
			}
		return false;
	}
	
	public int convertCalendarTime(GregorianCalendar date) {
		return Integer.parseInt(timeFormat.format(date.getTime()));
	}

	@Override
	public boolean equals(Object otherObject){
		if(!(otherObject instanceof DayMonthYear)) {
			return false;
		}
		DayMonthYear otherDay = (DayMonthYear) otherObject;
		int todayTime = convertCalendarTime(day);
		int otherTime = convertCalendarTime(otherDay.day);
		return (todayTime==otherTime);
		
	}
	
	@Override
	public int compareTo(Object otherObject) {
		if(!(otherObject instanceof DayMonthYear)) {
			throw new IllegalArgumentException("DayMonthYear.compareTo: Not DayMonthYear.");
		}
		
			DayMonthYear otherDay = (DayMonthYear) otherObject;
			int today = convertCalendarTime(day);
			int compareDay = convertCalendarTime(otherDay.day);
			
		if(today==compareDay) {
			return 0;
		}else if(today<compareDay) {
			return -1;
		}else{
			return 1;
		}
		
	
	}
	
	@Override
	public String toString() {
		SimpleDateFormat df = new SimpleDateFormat("EEE d MMM yy");
		String text = "DayMonthYear:" + df.format(day.getTime()) +" \n";
				if(timeSlots.isEmpty()) {
					text+="Empty\n";
				}else {
					for(int i=0;i<timeSlots.size();i++) {
				text+= timeSlots.get(i).toString()+" \n";
	}
					
				}
				return text;
	}
	
}