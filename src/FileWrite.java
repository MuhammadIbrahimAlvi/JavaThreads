import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FileWrite implements Runnable{
    private Employee.Builder employeeBuilder;
    private String filePath;

    public FileWrite(Employee.Builder employeeBuilder, String filePath) {
        this.employeeBuilder = employeeBuilder;
        this.filePath = filePath;
    }

    @Override
    public void run() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(employeeBuilder.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
