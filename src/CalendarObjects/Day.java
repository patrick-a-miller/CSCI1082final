
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class Day{
	private GregorianCalendar day;
	private ArrayList<Room> rooms;
	//would you rather Day have an arraylist of Room or of CalenderRoom?
	
	public GregorianCalendar getDay() {
		return day;
	}
	public void setDay(GregorianCalendar day) {
		this.day = day;
	}
	public ArrayList<Room> getRooms() {
		return rooms;
	}
	public void setRooms(ArrayList<Room> rooms) {
		this.rooms = rooms;
	}
	public Day(GregorianCalendar day) {
		this.day = day;
	}
	
	
	
	
}