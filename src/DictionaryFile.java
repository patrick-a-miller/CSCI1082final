package dictionary;

import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/*
 * TODO: not clear if ObjectFile or DictionaryFile can be made into abstract class
 * not crucial for function: hold off on review until more features are implemented
 */
public class DictionaryFile extends ObjectFile {

	private boolean isValid;
	private int entryCount;
	Scanner reader;
	private String[] dictionaryData;

	public DictionaryFile(String filePath, String type) {
		super(filePath, type);
		super.setOpenStatus("OpenRead");
		isValid = false;
		try {
			reader = new Scanner(new FileInputStream(filePath));
		} catch (FileNotFoundException e) {
			System.out.println("DictionaryFile error: " + " not found.");
			return;
		}
		dictionaryData = loadData();
		entryCount = dictionaryData.length;
		reader.close();
		super.setOpenStatus("Closed");

	}

	public boolean isValid() {
		return isValid;
	}

	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}

	public int getEntryCount() {
		return entryCount;
	}

	public void setEntryCount(int entryCount) {
		this.entryCount = entryCount;
	}
	
	public String[] getDictionaryData() {
		return dictionaryData;
	}

	private String[] loadData() {
		int lineCount = 0;
		String[] rawData = new String[100];
		while (reader.hasNextLine()) {
			rawData[lineCount] = reader.nextLine();
			lineCount++;
			if (lineCount == rawData.length - 1) {
				rawData = expandDataArray(rawData);
			}
		}
		return trimDataArray(rawData, lineCount);
	}

	private String[] expandDataArray(String[] rawData) {
		String[] newArray = new String[rawData.length + 100];
		for (int i = 0; i < rawData.length; i++) {
			newArray[i] = rawData[i];
		}

		return newArray;
	}

	private String[] trimDataArray(String[] rawData, int lineCount) {
		String[] trimArray = new String[lineCount];
		for (int i = 0; i < trimArray.length; i++) {
			trimArray[i] = rawData[i];
		}
		return trimArray;
	}
	
	@Override
	public String toString() {
		String text ="";
		for(String current : dictionaryData) {
			text+=current+"\n";
		}
		return text;
	}

}
