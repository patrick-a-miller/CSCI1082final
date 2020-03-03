
public class ClassEntry {
	private String classTitle;
	private Teacher teacher;
	private int studentCount;
	private Student[] studentList;
	private Schedule classSchedule;
	private int dictionaryIndex;
	
	public ClassEntry(String classTitle, Teacher teacher, int studentCount, Student[] studentList,
			Schedule classSchedule, int dictionaryIndex) {
		super();
		this.classTitle = classTitle;
		this.teacher = teacher;
		this.studentCount = studentCount;
		this.studentList = studentList;
		this.classSchedule = classSchedule;
		this.dictionaryIndex = dictionaryIndex;
	}

	public String getClassTitle() {
		return classTitle;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public int getStudentCount() {
		return studentCount;
	}

	public Student[] getStudentList() {
		return studentList;
	}

	public Schedule getClassSchedule() {
		return classSchedule;
	}

	public int getDictionaryIndex() {
		return dictionaryIndex;
	}
	
	
}
