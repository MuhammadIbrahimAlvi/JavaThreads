import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.Map;

public class FileProcessor implements Runnable{
    private List<Map<Long, Employee.Builder>> employeeMapList;

    public FileProcessor(List<Map<Long,Employee.Builder>> employeeMapList) {
        this.employeeMapList = employeeMapList;
    }

    @Override
    public void run() {

    }
}
