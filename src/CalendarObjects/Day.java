package CalendarObjects;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class Day{
	private GregorianCalendar day;
	private ArrayList<CalendarRoom> rooms;


	public Day(GregorianCalendar day, Room[] roomArray) {
		this.day = day;
		buildRoomList(day,roomArray);
	}

	
	public GregorianCalendar getDay() {
		return day;
	}
	public void setDay(GregorianCalendar day) {
		this.day = day;
	}
	public ArrayList<CalendarRoom> getRooms() {
		return rooms;
	}
	public void setRooms(ArrayList<CalendarRoom> rooms) {
		this.rooms = rooms;
	}
	
	private void buildRoomList(GregorianCalendar day,Room[] roomArray) {
		rooms = new ArrayList<CalendarRoom>();
		for(int i = 0; i < roomArray.length; i++) {
			if(roomArray[i]!=null) {
			rooms.add(new CalendarRoom(day,roomArray[i]));
			}
		}
	}
	
	public CalendarRoom getCalendarRoom(Room otherRoom) {
		CalendarRoom currentCalendarRoom = null;
		for(int i = 0; i<rooms.size();i++) {
			currentCalendarRoom = rooms.get(i);
			if(currentCalendarRoom!=null&&currentCalendarRoom.getRoom().equals(otherRoom)) {
				return currentCalendarRoom;
			}
		}
		return null;
	}

public String toString() {
	SimpleDateFormat timeFormat = new SimpleDateFormat("yyyyMMddHH");
	String text = "Day: "+timeFormat.format(day.getTime())+"\n";
	for(int i=0; i<rooms.size();i++) {
		text+=rooms.get(i).toString()+"\n";
	}
	return text;
}


}