package CalendarObjects;
public class TimeSlot {

	private ClassEntry classEntry;
	private Teacher teacher;
	
	// private String description;
	// private String event;

	public TimeSlot(ClassEntry classEntry, Teacher teacher) {

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
	


	}
	
	@Override
	public String toString() {
		String text = "Class: " + classEntry.getClassTitle() + " Teacher: " + teacher.getId();
		return text;
	}
}
