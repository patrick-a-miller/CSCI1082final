package dictionary;

/*TODO:
 * generate enough dictionaries to revie converting teacher, classSchedule, studentList variables to
 * Teacher, ClassSchedule, and Student -- also review implementing checks that the values exist in dictionary
 * 
 */
public class ClassEntry implements Comparable<ClassEntry> {
	private String classTitle;
	private String teacher;
	private String classSchedule;
	private int studentCount;
	private String[] studentList;
	private int dictionaryIndex;
	
	public ClassEntry(String classTitle, String teacher, String classSchedule, int studentCount, String[] studentList,
			int dictionaryIndex) {
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

	public int getDictionaryIndex() {
		return dictionaryIndex;
	}
	
	public int compareTo(ClassEntry otherClass) {
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
		text+= "Index: "+dictionaryIndex+"\n";
		return text;
	}
	
}
