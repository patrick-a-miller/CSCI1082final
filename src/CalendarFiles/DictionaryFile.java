

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
	
	public void sortArray(Comparable[] entryArray) {
		if(entryArray==null||!(entryArray instanceof Comparable[])) {
			return;
		}else {
			int[] brackets = {0,entryArray.length-1};
			mergeArray(entryArray,brackets);
		}
		
	}

	private void mergeArray(Comparable[] sortArray, int[] brackets) {
		int start = brackets[0];
		int end = brackets[1];
		int span = end-start;
		Comparable temp = null;
		if(span>1) {
			int[] leftBracket = {start,start+span/2};
			mergeArray(sortArray,leftBracket);
			int[] rightBracket = {start+span/2+1,end};
			mergeArray(sortArray,rightBracket);
	    int leftPlace = start;
	    int rightPlace = start+span/2+1;
	    Comparable[] tempArray = new Comparable[span+1];
	for(int i = 0; i<span+1;i++) {
		Comparable leftCandidate =null;
		Comparable rightCandidate =null;
		//left
		if((leftPlace<start+span/2+1)&&(leftCandidate==null)){
			leftCandidate=sortArray[leftPlace];
		}
		//right
		if((rightPlace<end+1)&&(rightCandidate==null)){
			rightCandidate=sortArray[rightPlace];
		}
		if(leftCandidate!=null&&rightCandidate==null) {
			tempArray[i]=leftCandidate;
			leftCandidate=null;
			leftPlace++;
		}else if(rightCandidate!=null&&leftCandidate==null) {
			tempArray[i]=rightCandidate;
			rightCandidate=null;
			rightPlace++;
		}else if(leftCandidate.compareTo(rightCandidate)<0) {
			tempArray[i]=leftCandidate;
			leftCandidate=null;
			leftPlace++;
		}else {
			tempArray[i]=rightCandidate;
			rightCandidate=null;
			rightPlace++;
		}
	}
	for(int i=0;i<span+1;i++) {
		sortArray[start+i]=tempArray[i];
	}
	    
		}else if(span==1) {
			if(sortArray[start].compareTo(sortArray[end])>0) {
						temp=sortArray[start];
				sortArray[start]=sortArray[end];
				sortArray[end]=temp;
			}
		}
		
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
