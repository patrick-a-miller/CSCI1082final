package CalendarFiles;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class DefaultFileSetup {

	public static void maybeSetUpFiles() {
		maybeBuildTeacherDictionary();
		maybeBuildClassDictionary();
		maybeBuildRoomDictionary();

	}

	private static void maybeBuildRoomDictionary() {
		if (!fileExists("roomdictionary.txt")) {
			System.out.print("room build");
			buildRoomDictionary();
		}

	}

	private static void maybeBuildClassDictionary() {
		if (!fileExists("classdictionary.txt")) {
			buildClassDictionary();
		}

	}

	private static void maybeBuildTeacherDictionary() {
		if (!fileExists("teacherdictionary.txt")) {
			buildTeacherDictionary();
		}

	}

	private static void buildRoomDictionary() {
		PrintWriter outputFile = null;
		System.out.println("Missing Room Dictionary: initializing");
		try {
			outputFile = new PrintWriter(new FileOutputStream("roomdictionary.txt"));
		} catch (FileNotFoundException e) {
			System.out.println("Unable to open file.");
			System.exit(0);
		}

		outputFile.println("<ENTRYSTART>");
		outputFile.println("<ID>W101");
		outputFile.println("<NAME>West 101");
		outputFile.println("<CAPACITY>25");
		outputFile.println("<ENTRYEND>");
		outputFile.println("<ENTRYSTART>");
		outputFile.println("<ID>E2213");
		outputFile.println("<NAME>East 2213");
		outputFile.println("<CAPACITY>45");
		outputFile.println("<ENTRYEND>");
		outputFile.println("<ENTRYSTART>");
		outputFile.println("<ID>E1001");
		outputFile.println("<NAME>East 1001");
		outputFile.println("<CAPACITY>60");
		outputFile.println("<ENTRYEND>");

		outputFile.close();

	}

	private static void buildClassDictionary() {
		PrintWriter outputFile = null;
		System.out.println("Missing Class Dictionary: initializing");

		try {
			outputFile = new PrintWriter(new FileOutputStream("classdictionary.txt"));
		} catch (FileNotFoundException e) {
			System.out.println("Unable to open file.");
		}

		outputFile.println("<ENTRYSTART>");
		outputFile.println("<TITLE>MATH 2081_1");
		outputFile.println("<TEACHER>BRIAN_J");
		outputFile.println("<SCHEDULE>MATH 2081_1");
		outputFile.println("<STUDENTCOUNT>15");
		outputFile.println("<STUDENTLIST>CATHY4");
		outputFile.println("<STUDENTLIST>DAN5");
		outputFile.println("<STUDENTLIST>EVE6");
		outputFile.println("<ENTRYEND>");
		outputFile.println("<TITLE>PHYS 1082_1");
		outputFile.println("<TEACHER>MAHMOOD_E");
		outputFile.println("<SCHEDULE>PHYS 1082_1");
		outputFile.println("<STUDENTCOUNT>20");
		outputFile.println("<STUDENTLIST>FRANK7");
		outputFile.println("<STUDENTLIST>GRACE8");
		outputFile.println("<STUDENTLIST>HENRY9");
		outputFile.println("<ENTRYEND>");
		outputFile.println("<ENTRYSTART>");
		outputFile.println("<TITLE>CSCI 1082_2");
		outputFile.println("<TEACHER>ZACH_B");
		outputFile.println("<SCHEDULE>CSCI 1082_2");
		outputFile.println("<STUDENTCOUNT>15");
		outputFile.println("<STUDENTLIST>AARON1");
		outputFile.println("<STUDENTLIST>AMY2");
		outputFile.println("<STUDENTLIST>BOB3");
		outputFile.println("<ENTRYEND>");

		outputFile.close();

	}

	private static void buildTeacherDictionary() {

		PrintWriter outputFile = null;

		System.out.println("Missing Teacher Dictionary: initializing");

		try {
			outputFile = new PrintWriter(new FileOutputStream("teacherdictionary.txt"));
		} catch (FileNotFoundException e) {
			System.out.println("Unable to open file.");
		}

		outputFile.println("<ENTRYSTART>");
		outputFile.println("<ID>ZAK_B");
		outputFile.println("<NAME>Zakaria Baani");
		outputFile.println("<EMAIL>zakb@debug.fakecentury.edu");
		outputFile.println("<ENTRYEND>");
		outputFile.println("<ENTRYSTART>");
		outputFile.println("<ID>MAH_E");
		outputFile.println("<NAME>Mahmoud Eid");
		outputFile.println("<EMAIL>mahe@debug.fakecentury.edu");
		outputFile.println("<ENTRYEND>");
		outputFile.println("<ENTRYSTART>");
		outputFile.println("<ID>BRI_J");
		outputFile.println("<NAME>Brian Jenson");
		outputFile.println("<EMAIL>brij@debug.fakecentury.edu");
		outputFile.println("<ENTRYEND>");

		outputFile.close();

	}

	private static boolean fileExists(String fileName) {
		Scanner reader = null;
		try {
			reader = new Scanner(new FileInputStream(fileName));
		} catch (FileNotFoundException e) {
			return false;
		}
		reader.close();
		return true;

	}
}
