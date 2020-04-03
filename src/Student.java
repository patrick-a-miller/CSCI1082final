package dictionary;

/*
 * TODO: review comments
 */

public class Student implements Comparable {

	private int index;
	private String id;
	private String name;
	private String email;

	public Student(int index, String id, String name, String email) {
		this.index = index;
		this.id = id;
		this.name = name;
		this.email = email;
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

	public int compareTo(Object otherObject) {
		if ((otherObject == null) || !(otherObject instanceof Student)) {
			throw new IllegalArgumentException("Not Student Entry.");
		}
		Student otherStudent = (Student) otherObject;
		return this.id.compareTo(otherStudent.id);
	}

	@Override
	public String toString() {
		String text = "ID: " + id + " Name: " + name + " Email: " + email + "\n";
		return text;
	}

}
