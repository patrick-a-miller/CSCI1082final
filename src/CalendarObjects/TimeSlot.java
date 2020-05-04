package CalendarObjects;
public class TimeSlot implements Comparable {
	private int time;
	private int durationMinutes;
	private Room room;
	private ClassEntry classEntry;
	private Teacher teacher;
	
	public TimeSlot(int time, int durationMinutes, Room room, ClassEntry classEntry, Teacher teacher) {
		this.time=time;
		this.durationMinutes=durationMinutes;
		this.room=room;
		this.classEntry=classEntry;
		this.teacher=teacher;
	}
	
	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time=time;
	}

	public int getDurationMinutes() {
		return durationMinutes;
	}

	public void setDurationMinutes(int durationMinutes) {
		this.durationMinutes = durationMinutes;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public ClassEntry getClassEntry() {
		return classEntry;
	}

	public void setClassEntry(ClassEntry classEntry) {
		this.classEntry = classEntry;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
	
	public static String[] getTimeArray() {
	String[] hourTimes = new String[24];
	for(int index = 0; index<hourTimes.length;index++) {
		hourTimes[index] = convertSlotTime(index);
	}
	return hourTimes;
	}
	
	public static String convertSlotTime(int hour) {
		if(hour==0) {
			return "12:00am";
		}else if(hour<10) {
			return "0"+Integer.toString(hour)+":00am";
		}else if(hour<12) {
			return Integer.toString(hour)+":00am";
		}else if(hour==12) {
			return Integer.toString(hour)+":00pm";
		}else if(hour>12&&hour<22) {
			return "0"+Integer.toString(hour-12)+":00pm";
		}else {
			return Integer.toString(hour-12)+":00pm";
		}
	}
	
	public String buttonTextFormat(){
		String buttonText = "<html>"+teacher.getName()+"<br>"+classEntry.getClassTitle()+"</html>";
		return buttonText;
	}
	
	@Override
	public int compareTo(Object otherObject) {
		if(!(otherObject instanceof TimeSlot)) {
			throw new IllegalArgumentException("TimeSlot.compareTo: Not TimeSlot.");
		}
		
			TimeSlot otherTime = (TimeSlot) otherObject;
			int compareTime = otherTime.getTime();
		if(time==compareTime) {
			return 0;
		}else if(time<compareTime) {
			return -1;
		}else{
			return 1;
		}
		
	}

	@Override
	public boolean equals(Object otherObject) {
		if(!(otherObject instanceof TimeSlot)) {
			return false;
		}
		TimeSlot otherTime= (TimeSlot) otherObject;
		if(time!=otherTime.getTime()) {
			return false;
		}else if (durationMinutes!=otherTime.getDurationMinutes()) {
			return false;
		}else if(room.equals(otherTime.getRoom())) {
			return false;
		}else if(classEntry.equals(otherTime.getClassEntry())) {
			return false;
		}else if(teacher.equals(otherTime.getTeacher())) {
			return false;
		}
		return true;
	}
	
	@Override
	public String toString() {
		String text = "Time "+time +" Dur: " + durationMinutes + " Room: " + room.getRoomId()+ " Class: " 
	+ classEntry.getClassTitle() + " Teacher: " + teacher.getId();
		return text;
	}
}