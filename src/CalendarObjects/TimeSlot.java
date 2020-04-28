package CalendarObjects;
public class TimeSlot {

	private ClassEntry classEntry;
	private Teacher teacher;
	
	public TimeSlot(int time, int durationMinutes, Room room, ClassEntry classEntry, Teacher teacher) {
		this.time=time;
		this.durationMinutes=durationMinutes;
		this.room=room;

		this.classEntry=classEntry;
		this.teacher=teacher;
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
		String text = "Class: " + classEntry.getClassTitle() + " Teacher: " + teacher.getId();
		return text;
	}
}
