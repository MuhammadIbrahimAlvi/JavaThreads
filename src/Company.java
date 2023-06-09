import java.sql.SQLException;
import java.util.Date;
import java.util.Objects;
import java.util.logging.Logger;


public class Company {
    public static void main(String[] args) throws InterruptedException {

        final Logger logger = Logger.getLogger(FileWrite.class.getName());


        Employee.Builder employee = new Employee.Builder("Ibrahim", 24L,
                "Software", 90000L,new Date());

        MySqlAccess sqlAccess = new MySqlAccess();
        sqlAccess.setConnect("root", "");
        Thread fileReaderThreadHandler = new Thread(new FileReader());
        try {
            fileReaderThreadHandler.start();
            int insertionResponse = sqlAccess.insertIntoEmployeeTable((long) sqlAccess.readDatabase("select * from employees").getRow(),employee.getName(), employee.getAge(),
                   employee.getDepartment(), employee.getSalary(), new java.sql.Date(employee.getDate().getTime()));
           if(Objects.equals(insertionResponse, 1)){
               logger.info("Successfully inserted.");
           } else {
               logger.info("Not inserted.");
           }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            logger.info("Starting finally Statement.");
            fileReaderThreadHandler.join();
        }
    }
}
