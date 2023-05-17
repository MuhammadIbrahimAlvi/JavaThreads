import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

public class FileProcessor implements Runnable{

    private static final Logger logger = Logger.getLogger(FileWrite.class.getName());
    private List<Map<Long, Employee.Builder>> employeeMapList;

    public FileProcessor(List<Map<Long,Employee.Builder>> employeeMapList) {
        this.employeeMapList = employeeMapList;
    }

    @Override
    public void run() {
        logger.info("Started run():: of FileProcessor.");
        AtomicInteger counter = new AtomicInteger(1);
        employeeMapList.forEach(employeeMapList->{

           Employee.Builder employee = employeeMapList.get(counter.longValue());
           if(isDateGreaterThanOneMonth(employee.getDate().toString())) {
              Thread fileWriteThreadHandler = new Thread(new FileWrite(employee, "ExployeeInformation.txt"));
               try {
                   fileWriteThreadHandler.start();
                   fileWriteThreadHandler.join();
               } catch (InterruptedException e) {
                   throw new RuntimeException(e);
               }
           }
           counter.set(counter.get()+1);
        });
    }

    private static boolean isDateGreaterThanOneMonth(String dateString) {
        logger.info("Inside of :: isDateGreaterThanOneMonth() to check date"+ dateString);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate givenDate = LocalDate.parse(dateString, formatter);
        LocalDate currentDate = LocalDate.now();
        LocalDate oneMonthLater = currentDate.plusMonths(1);
        return givenDate.isBefore(oneMonthLater);
    }
}
