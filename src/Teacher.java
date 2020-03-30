package dictionary;

/*
 * TODO: comments
 * review if Teacher and Student can be made derived classes of a Person or User class
 */
		
public class Teacher implements Comparable<Teacher>{

	private int index;
	private String id;
	private String name;
	private String email;
	
	public Teacher (int index, String id, String name, String email) {
		this.index=index;
		this.id=id;
		this.name=name;
		this.email=email;
	}
	
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	
	public String getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
		public int compareTo(Teacher otherTeacher) {
			return id.compareTo(otherTeacher.id);
		}
	
	@Override
	public String toString() {
		String text = "ID: "+id+" Name: "+name+" Email: "+email+"\n";
		return text;
	}
	

}
