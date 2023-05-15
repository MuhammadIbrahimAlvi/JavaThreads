import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Logger;

public class FileReader implements Runnable{
    Logger logger = Logger.getAnonymousLogger();

    @Override
    public void run() {
        logger.info("Started :: run() method of Thread with reference: "+ Thread.currentThread() + "and of name: "+ Thread.currentThread().getName());

        MySqlAccess mySqlAccess = new MySqlAccess();
        try {
           ResultSet resultSet = mySqlAccess.readDatabase("SELECT * FROM EMPLOYEES");
           List<Map<Long, Employee.Builder>> employeeList = new ArrayList<>();
           while (resultSet.next()) {
               Map<Long, Employee.Builder> builderMap = new HashMap<>();
               long id = resultSet.getLong("id");
               String name = resultSet.getString("name");
               String department = resultSet.getString("department");
               long salary = resultSet.getLong("salary");
               Date hireDate = resultSet.getDate("hire_date");
               long age = resultSet.getLong("age");
               Employee.Builder employee = new Employee.Builder(name, age, department, salary, hireDate);
               builderMap.put(id, employee);
               employeeList.add(builderMap);
           }

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
