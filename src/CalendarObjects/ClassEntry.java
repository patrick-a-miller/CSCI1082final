package CalendarObjects;



/*TODO:
 * generate enough dictionaries to revie converting teacher, classSchedule, studentList variables to
 * Teacher, ClassSchedule, and Student -- also review implementing checks that the values exist in dictionary
 * 
 */
public class ClassEntry implements Comparable {
	private String classTitle;
	private String teacher;
	private String classSchedule;
	private int studentCount;
	private String[] studentList;
	private int index;
	
	public ClassEntry(String classTitle, String teacher, String classSchedule, int studentCount, String[] studentList) {
		super();
		this.classTitle = classTitle;
		this.teacher = teacher;
		this.studentCount = studentCount;
		this.studentList = studentList;
		this.classSchedule = classSchedule;
		}

	public String getClassTitle() {
		return classTitle;
	}

	public String getTeacher() {
		return teacher;
	}

	public int getStudentCount() {
		return studentCount;
	}

	public String[] getStudentList() {
		return studentList;
	}

	public String getClassSchedule() {
		return classSchedule;
	}

	public int getIndex() {
		return index;
	}
	
	public void setIndex(int index) {
		this.index=index;
	}
	
	public int compareTo(Object otherObject) {
		if((otherObject == null)||!(otherObject instanceof ClassEntry)) {
			throw new IllegalArgumentException("Not Class Entry.");
		}
		ClassEntry otherClass = (ClassEntry) otherObject;
		return this.classTitle.compareTo(otherClass.classTitle);
	}
	
	@Override
	public String toString() {
		String text = "Class: "+ classTitle +"\n";
		text+= "Teacher: "+ teacher+"\n";
		text+= "Schedule: " + classSchedule +"\n";
		text+= "Count: " + studentCount+"\n";
		text+= "Students: \n";
		for(String current:studentList) {
			text+=current+"\n";
		}
		text+= "Index: "+index+"\n";
		return text;
	}
	
}
