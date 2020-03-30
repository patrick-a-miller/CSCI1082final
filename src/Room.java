package dictionary;

public class Room implements Comparable<Room>{
	private String roomId;
	private int capacity;
	private int dictionaryIndex;
	
	public Room(String roomId, int capacity, int dictionaryIndex) {
		this.roomId = roomId;
		this.capacity = capacity;
		this.dictionaryIndex = dictionaryIndex;
	}

	public String getRoomId() {
		return roomId;
	}

	public int getCapacity() {
		return capacity;
	}

	public int getDictionaryIndex() {
		return dictionaryIndex;
	}
	
	
	public int compareTo(Room otherRoom) {
		return this.roomId.compareTo(otherRoom.roomId);
	}
	
	@Override
	public String toString() {
		String text = "Room: "+roomId+" Capacity: "+capacity + "Index: ";
		return text;
	}
	
}
