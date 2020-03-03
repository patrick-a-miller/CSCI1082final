
public class Room {
	private int number;
	private int capacity;
	private int dictionaryIndex;
	
	public Room(int number, int capacity, int dictionaryIndex) {
		super();
		this.number = number;
		this.capacity = capacity;
		this.dictionaryIndex = dictionaryIndex;
	}

	public int getNumber() {
		return number;
	}

	public int getCapacity() {
		return capacity;
	}

	public int getDictionaryIndex() {
		return dictionaryIndex;
	}
	
	
}
