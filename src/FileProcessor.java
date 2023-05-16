import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class FileProcessor implements Runnable{
    private List<Map<Long, Employee.Builder>> employeeMapList;

    public FileProcessor(List<Map<Long,Employee.Builder>> employeeMapList) {
        this.employeeMapList = employeeMapList;
    }

    @Override
    public void run() {
        AtomicInteger counter = new AtomicInteger(1);
        employeeMapList.forEach(employeeMapList->{

           Employee.Builder employee = employeeMapList.get(counter.longValue());
           if(isDateGreaterThanOneMonth(employee.getDate())) {
               new Thread(new FileWrite(employee,"ExployeeInformation.csv")).start();
           }
        });
    }

    private static boolean isDateGreaterThanOneMonth(Date dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate givenDate = LocalDate.parse((CharSequence) dateString, formatter);
        LocalDate currentDate = LocalDate.now();
        LocalDate oneMonthLater = currentDate.plusMonths(1);
        return givenDate.isAfter(oneMonthLater);
    }
}
