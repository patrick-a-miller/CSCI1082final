

public class Room implements Comparable{
	private String roomId;
	private String roomName;
	private int capacity;
	private int index;
	
	public Room(String roomId, String roomName, int capacity) {
		this.roomId = roomId;
		this.roomName=roomName;
		this.capacity = capacity;
	}

	public String getRoomId() {
		return roomId;
	}
	
	public String getRoomName() {
		return roomName;
	}

	public int getCapacity() {
		return capacity;
	}

	public int getIndex() {
		return index;
	}
	
	
	public void setIndex(int index) {
		this.index = index;
	}

	public int compareTo(Object otherObject) {
		if((otherObject == null)||!(otherObject instanceof Room)) {
			throw new IllegalArgumentException("Not Room Entry.");
		}
		Room otherRoom = (Room) otherObject;
		return this.roomId.compareTo(otherRoom.roomId);
	}
			
	@Override
	public String toString() {
		String text = "Room: "+roomId+" Capacity: "+capacity + " Index: "+index;
		return text;
	}
	
	@Override
	public boolean equals(Object otherObject) {
		if(!(otherObject instanceof Room)){
			return false;
		}
		Room otherRoom = (Room) otherObject;
		if(roomId.equals(otherRoom.getRoomId())){
			return true;
		}
//		}else if(!roomName.contentEquals(otherRoom.getRoomName())){
//			return false;
//		}else if(capacity!=otherRoom.capacity) {
//			return false;
//		}else if(index!= otherRoom.index) {
//			return false;
//		}
		return false;
		
	}
	
}
