import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class FileProcessor implements Runnable{
    private String filename;

    public FileProcessor(String filename) {
        this.filename = filename;
    }

    @Override
    public void run() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(this.filename));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
