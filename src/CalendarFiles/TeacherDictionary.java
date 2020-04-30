package CalendarFiles;
import CalendarObjects.Teacher;

/*
 * TODO:
 * length checks for parsing
 * sorting
 * comments
 */
public class TeacherDictionary extends DictionaryFile {
	private Teacher currentTeacher;
	private int currentIndex;
	private Teacher[] teacherArray;

	public TeacherDictionary() {
		super("teacherdictionary.txt", "TeacherDictionary");
		teacherArray = convertData();
		this.setEntryCount(teacherArray.length);
		if (teacherArray.length > 0) {
			this.setValid(true);
			currentIndex = 0;
			currentTeacher = teacherArray[currentIndex];
		}
	}

	private Teacher[] convertData() {
		String[] rawData = super.getDictionaryData();
		teacherArray = new Teacher[10];
		int currentCount = 0;
		boolean entryOpen = false;
		String id = "";
		String name = "";
		String email = "";
		for (int i = 0; i < rawData.length; i++) {
			String currentText = rawData[i];
			if (currentText.equals("<ENTRY>")) {
				if (entryOpen == true) {
					id = name = email = "";
				}
				entryOpen = true;
			} else if (currentText.equals("<ENTRYEND>")) {
				entryOpen = false;
				teacherArray[currentCount] = new Teacher(id, name, email);
				id = name = email = "";
				currentCount++;
				if (currentCount == rawData.length - 1) {
					teacherArray = expandTeacherArray(teacherArray);
				}
			} else if (currentText.substring(0, 4).contentEquals("<ID>")) {
				id = currentText.substring(4);
			} else if (currentText.substring(0, 6).contentEquals("<NAME>")) {
				name = currentText.substring(6);
			} else if (currentText.substring(0, 7).contentEquals("<EMAIL>")) {
				email = currentText.substring(7);
			}
		}

		teacherArray = trimTeacherArray(teacherArray, currentCount);
		sortArray(teacherArray);
		for(int i = 0; i<teacherArray.length;i++) {
			teacherArray[i].setIndex(i);
		}
		return teacherArray;
	}

	private Teacher[] expandTeacherArray(Teacher[] teacherArray) {
		Teacher[] newArray = new Teacher[teacherArray.length + 10];
		for (int i = 0; i < teacherArray.length; i++) {
			newArray[i] = teacherArray[i];
		}
		return newArray;
	}

	private Teacher[] trimTeacherArray(Teacher[] teacherArray, int currentCount) {
		Teacher[] trimArray = new Teacher[currentCount];
		for (int i = 0; i < trimArray.length; i++) {
			trimArray[i] = teacherArray[i];
		}
		return trimArray;
	}

	public Teacher getCurrentTeacher() {
		return currentTeacher;
	}

	public void setCurrentIndex(int index) {
		if (index > -1 && index < teacherArray.length) {
			currentIndex = index;
			currentTeacher = teacherArray[index];
		}
	}
	
	public Teacher[] getTeacherArray() {
		return teacherArray;
	}

	@Override
	public String toString() {
		String text = "";
		for (Teacher current : teacherArray) {
			text += current.toString();
		}
		return text;
	}

}
