package dictionary;

/*
 * TODO:
 * review sorting,
 * review comments,
 * evaluate length checks for parsing
 */

public class RoomDictionary extends DictionaryFile {
	private Room currentRoom;
	private int currentIndex;
	private Room[] roomArray;

	public RoomDictionary() {
		super("roomdictionary.txt", "RoomDictionary");
		roomArray = convertData();
		this.setEntryCount(roomArray.length);
		if (roomArray.length > 0) {
			this.setValid(true);
			currentIndex = 0;
			currentRoom = roomArray[currentIndex];
		}
	}

	private Room[] convertData() {
		String[] rawData = super.getDictionaryData();
		roomArray = new Room[10];
		int currentCount = 0;
		boolean entryOpen = false;
		String id = "";
		String name = "";
		int capacity = 0;
		for (int i = 0; i < rawData.length; i++) {
			String currentText = rawData[i];
			if (currentText.equals("<ENTRY>")) {
				if (entryOpen == true) {
					id = "";
					name = "";
					capacity = 0;
				}
				entryOpen = true;
			} else if (currentText.equals("<ENTRYEND>")) {
				entryOpen = false;
				roomArray[currentCount] = new Room(id, name, capacity);
				id = "";
				name = "";
				capacity = 0;
				currentCount++;
				if (currentCount == rawData.length - 1) {
					roomArray = expandRoomArray(roomArray);
				}
			} else if (currentText.substring(0, 4).contentEquals("<ID>")) {
				id = currentText.substring(4);
			} else if (currentText.substring(0, 6).contentEquals("<NAME>")) {
				name = currentText.substring(6);
			} else if (currentText.substring(0, 10).contentEquals("<CAPACITY>")) {
				capacity = Integer.parseInt(currentText.substring(10));
			}
		}

		roomArray = trimRoomArray(roomArray, currentCount);
		sortArray(roomArray);
		for(int i = 0; i<roomArray.length;i++) {
			roomArray[i].setIndex(i);
		}
		return roomArray;
	}

	private Room[] expandRoomArray(Room[] roomArray) {
		Room[] newArray = new Room[roomArray.length + 10];
		for (int i = 0; i < roomArray.length; i++) {
			newArray[i] = roomArray[i];
		}
		return newArray;
	}

	private Room[] trimRoomArray(Room[] roomArray, int currentCount) {
		Room[] trimArray = new Room[currentCount];
		for (int i = 0; i < trimArray.length; i++) {
			trimArray[i] = roomArray[i];
		}
		return trimArray;
	}

	public Room getCurrentRoom() {
		return currentRoom;
	}

	public void setCurrentIndex(int index) {
		if (index > -1 && index < roomArray.length) {
			currentIndex = index;
			currentRoom = roomArray[index];
		}
	}

	@Override
	public String toString() {
		String text = "";
		for (Room current : roomArray) {
			text += current.toString()+"\n";
			
		}
		return text;
	}

}