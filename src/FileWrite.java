import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileWrite implements Runnable{

    private static final Logger logger = Logger.getLogger(FileWrite.class.getName());
    private Employee.Builder employeeBuilder;
    private String filePath;

    public FileWrite(Employee.Builder employeeBuilder, String filePath) {
        this.employeeBuilder = employeeBuilder;
        this.filePath = filePath;
    }

    @Override
    public void run() {
        logger.info("Inside of :: run() method of FileWrite.");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Name:"+employeeBuilder.getName()+"\tDepartment: "+employeeBuilder.getDepartment()+
                    "\tAge: "+employeeBuilder.getAge()+"\t Hire Date:"+employeeBuilder.getDate()
                    +"\tSalary:"+employeeBuilder.getSalary());
            writer.append(stringBuilder);
            logger.info("Content Written");
        } catch (IOException e) {
            logger.log(Level.INFO, "Exception::", e);
            e.printStackTrace();
        }
    }
}
