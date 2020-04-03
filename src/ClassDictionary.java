package dictionary;

/*
 * dictionary may have dependencies on several other dictionaries in the future
 * TODO: review sorting of entries
 * review comments, refactoring if teacher, schedule, and student values are changed 
 * to Teacher, ClassSchedule, and Student type
 * review if parsing methods need exception handling
 * 
 */
public class ClassDictionary extends DictionaryFile {

	private ClassEntry currentClass;
	private int currentIndex;
	private ClassEntry[] classArray;

	public ClassDictionary() {
		super("classdictionary.txt", "ClassDictionary");
		classArray = convertData();
		this.setEntryCount(classArray.length);
		if (classArray.length > 0) {
			this.setValid(true);
			currentIndex = 0;
			currentClass = classArray[currentIndex];
		}
	}

	private ClassEntry[] convertData() {
		String[] rawData = super.getDictionaryData();
		classArray = new ClassEntry[10];
		int currentCount = 0;
		boolean entryOpen = false;
		String title = "";
		String teacher = "";
		String schedule = "";
		int studentCount = 0;
		String[] studentList = new String[30];
		int listLength = 0;
		for (int i = 0; i < rawData.length; i++) {
			String currentText = rawData[i];
			if ((currentText.length() >= 7) && currentText.equals("<ENTRY>")) {
				if (entryOpen == true) {
					title = "";
					teacher = "";
					studentCount = 0;
					studentList = new String[30];
					listLength = 0;
				}
				entryOpen = true;
			} else if ((currentText.length() >= 10) && currentText.equals("<ENTRYEND>")) {
				entryOpen = false;
				studentList = trimStudentArray(studentList, listLength);
				classArray[currentCount] = new ClassEntry(title, teacher, schedule, studentCount, studentList);
				currentCount++;
				title = "";
				teacher = "";
				studentCount = 0;
				studentList = new String[30];
				listLength = 0;
				if (currentCount == rawData.length - 1) {
					classArray = expandClassArray(classArray);
				}
			} else if ((currentText.length() >= 7) && currentText.substring(0, 7).equals("<TITLE>")) {
				title = currentText.substring(7);
			} else if ((currentText.length() >= 9) && currentText.substring(0, 9).equals("<TEACHER>")) {
				teacher = currentText.substring(9);
			} else if ((currentText.length() >= 10) && currentText.substring(0, 10).equals("<SCHEDULE>")) {
				schedule = currentText.substring(10);
			} else if ((currentText.length() >= 14) && (currentText.substring(0, 14).equals("<STUDENTCOUNT>"))) {
				studentCount = Integer.parseInt(currentText.substring(14));

			} else if ((currentText.length() >= 13) && (currentText.substring(0, 13).equals("<STUDENTLIST>"))) {
				String studentId = currentText.substring(13);
				studentList[listLength] = studentId;
				listLength++;
				if (listLength == studentList.length) {
					studentList = expandStudentArray(studentList);
				}
			}
		}

		classArray= trimClassArray(classArray, currentCount);
		sortArray(classArray);
		for(int i = 0; i<classArray.length;i++) {
			classArray[i].setIndex(i);
		}
		return classArray;
	}

	private ClassEntry[] expandClassArray(ClassEntry[] classArray) {
		ClassEntry[] newArray = new ClassEntry[classArray.length + 10];
		for (int i = 0; i < classArray.length; i++) {
			newArray[i] = classArray[i];
		}
		return newArray;
	}

	private ClassEntry[] trimClassArray(ClassEntry[] classArray, int currentCount) {
		ClassEntry[] trimArray = new ClassEntry[currentCount];
		for (int i = 0; i < trimArray.length; i++) {
			trimArray[i] = classArray[i];
		}
		return trimArray;
	}

	private String[] expandStudentArray(String[] studentList) {
		String[] newArray = new String[studentList.length + 30];
		for (int i = 0; i < studentList.length; i++) {
			newArray[i] = studentList[i];
		}
		return newArray;
	}

	private String[] trimStudentArray(String[] studentList, int currentCount) {
		String[] trimArray = new String[currentCount];
		for (int i = 0; i < trimArray.length; i++) {
			trimArray[i] = studentList[i];
		}
		return trimArray;
	}

	public ClassEntry getCurrentClass() {
		return currentClass;
	}

	public void setCurrentIndex(int index) {
		if (index > -1 && index < classArray.length) {
			currentIndex = index;
			currentClass = classArray[index];
		}
	}

	@Override
	public String toString() {
		String text = "";
		for (ClassEntry current : classArray) {
			text += current.toString();
		}
		return text;
	}

}
