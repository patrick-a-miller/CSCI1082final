package CalendarObjects;


public class ClassSchedule implements Comparable {

	private int index;
	private String mnemonic;
	private int startTime;
	private int endTime;
	private boolean[] useDay;
	
	public ClassSchedule(int index, String mnemonic, int startTime, int endTime, boolean[] useDay) {
		this.index=index;
		this.startTime = startTime;
		this.endTime = endTime;
		this.useDay = useDay;
	}
	
	public int getIndex() {
		return index;
	}
	
	public void setIndex(int index) {
		this.index=index;
	}
	
	public int getStartTime() {
		return startTime;
	}
	public void setStartTime(int startTime) {
		this.startTime = startTime;
	}
	public int getEndTime() {
		return endTime;
	}
	public void setEndTime(int endTime) {
		this.endTime = endTime;
	}
	public boolean[] getUseDay() {
		return useDay;
	}
	public void setUseDay(boolean[] useDay) {
		this.useDay = useDay;
	}
	public String getMnemonic() {
		return mnemonic;
	}
	
	public int compareTo(Object otherObject) {
		if((otherObject == null)||!(otherObject instanceof ClassSchedule)) {
			throw new IllegalArgumentException("Not ClassSchedule Entry.");
		}
		ClassSchedule otherSchedule = (ClassSchedule) otherObject;
		return this.mnemonic.compareTo(otherSchedule.mnemonic);
	}
}


