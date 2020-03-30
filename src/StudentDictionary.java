package dictionary;
/*
 * TODO:
 * review sorting,
 * review comments,
 * evaluate length checks for parsing
 */
		
public class StudentDictionary extends DictionaryFile {
	private Student currentStudent;
	private int currentIndex;
	private Student[] studentArray;

	public StudentDictionary() {
		super("studentdictionary.txt", "StudentDictionary");
		studentArray = convertData();
		this.setEntryCount(studentArray.length);
		if (studentArray.length > 0) {
			this.setValid(true);
			currentIndex=0;
			currentStudent = studentArray[currentIndex];
		}
	}

	private Student[] convertData() {
		String[] rawData = super.getDictionaryData();
		studentArray = new Student[10];
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
				studentArray[currentCount] = new Student(currentCount, id, name, email);
				currentCount++;
				if (currentCount == rawData.length - 1) {
					studentArray = expandStudentArray(studentArray);
				}
			} else if (currentText.substring(0, 4).contentEquals("<ID>")) {
				id = currentText.substring(4);
			} else if (currentText.substring(0, 6).contentEquals("<NAME>")) {
				name = currentText.substring(6);
			} else if (currentText.substring(0, 7).contentEquals("<EMAIL>")) {
				email = currentText.substring(7);
			}
		}

		return trimStudentArray(studentArray, currentCount);
	}

	private Student[] expandStudentArray(Student[] studentArray) {
		Student[] newArray = new Student[studentArray.length + 10];
		for (int i = 0; i < studentArray.length; i++) {
			newArray[i] = studentArray[i];
		}
		return newArray;
	}

	private Student[] trimStudentArray(Student[] studentArray, int currentCount) {
		Student[] trimArray = new Student[currentCount];
		for (int i = 0; i < trimArray.length; i++) {
			trimArray[i] = studentArray[i];
		}
		return trimArray;
	}
	
	public Student getCurrentStudent() {
		return currentStudent;
	}
	
	public void setCurrentIndex(int index) {
		if(index>-1 && index<studentArray.length) {
			currentIndex=index;
			currentStudent = studentArray[index];
		}
	}
	
	@Override
	public String toString() {
		String text = "";
		for(Student current:studentArray) {
			text+=current.toString();
		}
		return text;
	}

}
