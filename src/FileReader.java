import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

public class FileReader implements Runnable{
    Logger logger = Logger.getAnonymousLogger();

    @Override
    public void run() {
        logger.info("Started :: run() method of Thread with reference: "+ Thread.currentThread() + "and of name: "+ Thread.currentThread().getName());

        MySqlAccess mySqlAccess = new MySqlAccess();
        try {
           ResultSet resultSet = mySqlAccess.readDatabase("SELECT * FROM EMPLOYEES");

           while (resultSet.next()) {
               int id = resultSet.getInt("id");
           }

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
