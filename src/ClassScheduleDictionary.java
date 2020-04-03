package dictionary;

/*
 * TODO: review sorting of entries,
 * review length checks prior to parsing
 */
		
public class ClassScheduleDictionary extends DictionaryFile {
	private ClassSchedule currentSchedule;
	private int currentIndex;
	private ClassSchedule[] scheduleArray;

	public ClassScheduleDictionary() {
		super("scheduledictionary.txt", "ScheduleDictionary");
		scheduleArray = convertData();
		this.setEntryCount(scheduleArray.length);
		if (scheduleArray.length > 0) {
			this.setValid(true);
			currentIndex = 0;
			currentSchedule = scheduleArray[currentIndex];
		}
	}

	private ClassSchedule[] convertData() {
		String[] rawData = super.getDictionaryData();
		scheduleArray = new ClassSchedule[10];
		int currentCount = 0;
		boolean entryOpen = false;
		String mnemonic = "";
		int start = 0;
		int end = 0;
		boolean[] useDay = new boolean[7];
		for (int i = 0; i < rawData.length; i++) {
			String currentText = rawData[i];
			if (currentText.equals("<ENTRY>")) {
				if (entryOpen == true) {
					mnemonic = "";
					start = end = 0;
					useDay = new boolean[7];
				}
				entryOpen = true;
			} else if (currentText.equals("<ENTRYEND>")) {
				entryOpen = false;
				scheduleArray[currentCount] = new ClassSchedule(currentCount, mnemonic, start, end, useDay);
				currentCount++;
				mnemonic = "";
				start = end = 0;
				useDay = new boolean[7];
				if (currentCount == rawData.length - 1) {
					scheduleArray = expandScheduleArray(scheduleArray);
				}
			} else if ((currentText.length()>=6)&&currentText.substring(0, 6).contentEquals("<MNEM>")) {
				mnemonic = currentText.substring(6);
			} else if ((currentText.length()>=7)&&currentText.substring(0, 7).contentEquals("<START>")) {
				start = Integer.parseInt(currentText.substring(6));
			} else if ((currentText.length()>=5)&&currentText.substring(0, 5).contentEquals("<END>")) {
				end = Integer.parseInt(currentText.substring(5));
			} else if ((currentText.length()>=6)&&currentText.substring(0, 6).contentEquals("<DAYS>")) {
				String booltext = currentText.substring(6);
				for (int j = 0; j < 7; j++) {
					if (booltext.charAt(j) == 'Y') {
						useDay[j] = true;
					} else {
						useDay[j] = false;
					}
				}
			}
		}

		scheduleArray= trimScheduleArray(scheduleArray, currentCount);
		sortArray(scheduleArray);
		for(int i = 0; i<scheduleArray.length;i++) {
			scheduleArray[i].setIndex(i);
		}
		return scheduleArray;
	}

	private ClassSchedule[] expandScheduleArray(ClassSchedule[] scheduleArray) {
		ClassSchedule[] newArray = new ClassSchedule[scheduleArray.length + 10];
		for (int i = 0; i < scheduleArray.length; i++) {
			newArray[i] = scheduleArray[i];
		}
		return newArray;
	}

	private ClassSchedule[] trimScheduleArray(ClassSchedule[] scheduleArray, int currentCount) {
		ClassSchedule[] trimArray = new ClassSchedule[currentCount];
		for (int i = 0; i < trimArray.length; i++) {
			trimArray[i] = scheduleArray[i];
		}
		return trimArray;
	}

	public ClassSchedule getCurrentSchedule() {
		return currentSchedule;
	}

	public void setCurrentIndex(int index) {
		if (index > -1 && index < scheduleArray.length) {
			currentIndex = index;
			currentSchedule = scheduleArray[index];
		}
	}

	@Override
	public String toString() {
		String text = "";
		for (ClassSchedule current : scheduleArray) {
			text += current.toString();
		}
		return text;
	}

}
