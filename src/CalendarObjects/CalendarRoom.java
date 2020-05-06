package CalendarObjects;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;


public class CalendarRoom {

	private Room room;
	private GregorianCalendar day;
	private TimeSlot[] timeSlots;
	private static final int SLOTS_PER_DAY=24;

	
	public CalendarRoom(GregorianCalendar day, Room room) {
		this.day=day;
		this.room = room;
		timeSlots = new TimeSlot[SLOTS_PER_DAY];

	}

	
	/**
	 * Accessor for room dictionary entry associated with this CalendarRoom
	 * @return room object
	 */
	public Room getRoom() {
		return room;
	}

	
	/**
	 * Method that returns the max capacity of a rooom.
	 * 
	 * @return max capacity of the room, as recorded in the Room dictionary 
	 */
	public int getMaxCapacity() {
		return room.getCapacity();
	}

	
	/**
	 * Accessor that provides the array of time slots for a given CalendarRoom
	 * @return timeSlots - array of time slots
	 */
	public TimeSlot[] getTimeSlots() {
		return timeSlots;
	}

	public TimeSlot getTimeSlot(int hour) {
		if(hour<0 || hour>23) {
			return null;
		}else {
			return timeSlots[hour];
		}
		
	}
	
	
	/**
	 * Indicates if a given time slot is free for a new event to be added
	 * @param hour - int value 0-23 for the hour of the day
	 * @return true if no timeslot has been registered for the given hour, false otherwise
	 */
	public boolean isAvailable(int hour) {
		if(hour<0 || hour>23) {
			return false;
		}
		if(timeSlots[hour]==null) {
			return true;
		}
		return false;
	}
	
	
	public boolean addTimeSlot(int time, ClassEntry classEntry, Teacher teacher) {
		if ((0 > time || time > 23)) {
			System.out.println("INVALID TIME!");
			return false;
		}
		if(!isAvailable(time)) {
			System.out.println("There is already an event here please select a different time.");
			return false;
		}
		timeSlots[time] = new TimeSlot(this.convertCalendarTime(day)+time,60,room,classEntry,teacher);
		return true;

	}
	
	public boolean removeTimeSlot(int time) {
		if ((0 > time || time > 23)) {
			System.out.println("INVALID TIME!");
			return false;
		}
		if(isAvailable(time)) {
			System.out.println("Nothing scheduled.");
			return false;
		}else {
			timeSlots[time]=null;
			return true;
		}
	}
	
	
	public int convertCalendarTime(GregorianCalendar date) {
		SimpleDateFormat timeFormat = new SimpleDateFormat("yyyyMMddHH");
		return Integer.parseInt(timeFormat.format(date.getTime()));
	}
	
	@Override
	public String toString() {
		String text = "Calendar for Room: " + room.getRoomId() +" Cap: "+ getMaxCapacity()+ "\n";
		for(int i = 0; i<timeSlots.length; i++) {
			TimeSlot indexTime = timeSlots[i];
			if(indexTime!=null) {
				text+=indexTime.toString()+"\n";
			}
		}
		return text;
	}
	
}
