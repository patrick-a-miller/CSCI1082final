package dictionary;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

/*
 * TODO - review basic write (read?) functionality
 * determine format 
 * 
 * year?
 * file granularity?
 * month with day/week slots?
 * week with day slots
 * day with room slots with hour slots)
 * 
 */

public class DataFile {
	private boolean isValid;
	private String[] format;
	private RandomAccessFile stream;
	private FileChannel channel;
	private FileLock lock;
}
