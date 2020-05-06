package CalendarObjects;

import java.util.Comparator;

public class EventRecord {
	private boolean status;
	private int year;
	private int month;
	private int day;
	private Room room;
	private int hour;
	private ClassEntry classEntry;
	private Teacher teacher;
	
	
	public static Comparator<EventRecord> compareDateRoom = new Comparator<EventRecord>() {
		@Override
		public int compare(EventRecord event1, EventRecord event2) {
			String thisRecord=combineValues(event1.year,event1.month,event1.day,event1.room,event1.hour);
			String otherRecord=combineValues(event1.year,event1.month,event1.day,event1.room,event1.hour);
			return thisRecord.compareTo(otherRecord);
		}
		
		private String combineValues(int year,int month,int day,Room room,int hour) {
			String monthPad="";
			String dayPad="";
			String hourPad="";
			if(month<10) {
				monthPad="0";
			}
			if(day<10) {
				dayPad="0";
			}
			if(hour<10) {
				hourPad="0";
			}
			String text = Integer.toString(year)+monthPad+Integer.toString(month)+dayPad
			+Integer.toString(day)+room.getRoomId()+hourPad+Integer.toString(hour);
			return text;
		}
		
	};
	
		
	
	public EventRecord(int year, int month, int day, Room room, int hour, ClassEntry classEntry,Teacher teacher) {
		status=true;
		status=setYear(year)&&status;
		status=setMonth(month)&&status;
		status=setDay(day)&&status;
		status=setRoom(room)&&status;
		status=setHour(hour)&&status;
		status=setClassEntry(classEntry)&&status;
		status=setTeacher(teacher)&&status;
		
	}
	
	private boolean setYear(int year) {
			this.year=year;
			if(year<1000||year>9999) {
				return false;
		}else {
			return true;
		}
	}
	
	private boolean setMonth(int month) {
		this.month=month;
		if(month<0||month>11) {
			return false;
		}else {
			return true;
		}
	}
	
	private boolean setDay(int day) {
		this.day=day;
		if(day<0||day>31) {
			return false;
		}else {
			return true;
			}
	}

	private boolean setRoom(Room room) {
		this.room=room;
		if(room==null) {
			return false;
		}else {
			return true;
		}
	}
	
	private boolean setHour(int hour) {
		this.hour=hour;
		if(hour<0||hour>23) {
			return false;
		}else {
			return true;
			}
	}
	
	private boolean setClassEntry(ClassEntry classEntry) {
		this.classEntry=classEntry;
		if(classEntry==null) {
			return false;
		}else {
			return true;
		}
	}
	
	private boolean setTeacher(Teacher teacher) {
		this.teacher=teacher;
		if(teacher==null) {
			return false;
		}else {
			return true;
		}
	}
	
	
	/**
	 * @return the status
	 */
	public boolean isStatus() {
		return status;
	}

	/**
	 * @return the year
	 */
	public int getYear() {
		return year;
	}
	/**
	 * @return the month
	 */
	public int getMonth() {
		return month;
	}
	/**
	 * @return the day
	 */
	public int getDay() {
		return day;
	}
	/**
	 * @return the room
	 */
	public Room getRoom() {
		return room;
	}
	/**
	 * @return the hour
	 */
	public int getHour() {
		return hour;
	}
	/**
	 * @return the classEntry
	 */
	public ClassEntry getClassEntry() {
		return classEntry;
	}
	/**
	 * @return the teacher
	 */
	public Teacher getTeacher() {
		return teacher;
	}
	
	public String getCombinedDateValues() {
		String monthPad="";
		String dayPad="";
		if(month<10) {
			monthPad="0";
		}
		if(day<10) {
			dayPad="0";
		}
		String text = Integer.toString(year)+monthPad+Integer.toString(month)+dayPad
		+Integer.toString(day);
		
		return text;
	}

	
	@Override
	public String toString() {
		String text="<ENTRY>\n<YEAR>"+year+"\n<MONTH>" +(month+1)+ "\n<DAY>" +day+"\n<ROOM>" +room.getRoomId()
		+"\n<HOUR>"+hour+"\n<CLASSENTRY>"+classEntry.getClassTitle()+"\n<TEACHER>"+teacher.getId()
		+"\n<ENTRYEND>";
		
		return text;
	}

	
	
}
