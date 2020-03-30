package dictionary;

public class ObjectFile {

	private String filePath;
	private String type;
	private String status;
	
	public ObjectFile(String filePath, String type) {
		this.filePath=filePath;
		this.type=type;
		status="Invalid.init1";
	}
	
	public String getOpenStatus() {
		return status;
	}
	public void setOpenStatus(String status) {
		this.status = status;
	}
	
}
