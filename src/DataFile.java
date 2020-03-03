import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

public class DataFile {
	private boolean isValid;
	private String[] format;
	private RandomAccessFile stream;
	private FileChannel channel;
	private FileLock lock;
}
