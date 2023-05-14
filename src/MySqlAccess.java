import java.sql.*;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;


public class MySqlAccess {
    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    Logger logger = Logger.getAnonymousLogger();


    public void setConnect(String user, String password) {
        try {
            logger.info("Inside :: setConnect()");
            DriverManager.registerDriver(new com.mysql.jdbc.Driver ());
            connect = DriverManager
                    .getConnection("jdbc:mysql://localhost/employee_system"
                            ,user,password);
            logger.info("Connected..!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ResultSet readDatabase(String query) throws ClassNotFoundException, SQLException {
        DriverManager.registerDriver(new com.mysql.jdbc.Driver ());


        connect = DriverManager
                .getConnection("jdbc:mysql://localhost/employee_system?"
                        ,"root","");
        logger.info("Inside :: readDatabase()");
        statement = connect.createStatement();

        resultSet = statement.executeQuery(query);
        logger.info("Executed query :: " + query);
        return resultSet;
    }

    public int insertIntoEmployeeTable(Long id, String name, Long age, String department, Long salary, Date date) throws SQLException {

        logger.info("Inside :: insertIntoEmployeeTable()");
        preparedStatement = connect
                .prepareStatement("INSERT INTO EMPLOYEES VALUES(?, ?, ?, ?, ?, ?)");

        preparedStatement.setLong(1, id);
        preparedStatement.setString(2, name);
        preparedStatement.setLong(3, age);
        preparedStatement.setString(4, department);
        preparedStatement.setLong(5, salary);
        preparedStatement.setDate(6, new java.sql.Date(date.getTime()));

        logger.info("Exiting :: insertIntoEmployeeTable()");
       return preparedStatement.executeUpdate();
    }

    public int updateEmployee(Long id, String name, Long age, String department, Long salary) throws SQLException {
        preparedStatement = connect.prepareStatement("UPDATE EMPLOYEES SET name = ?, age = ? , department = ?, salary = ? WHERE id = ?");
        preparedStatement.setString(1, name);
        preparedStatement.setLong(2, age);
        preparedStatement.setString(3, department);
        preparedStatement.setLong(4, salary);
        preparedStatement.setLong(5, id);

        return preparedStatement.executeUpdate();
    }
}
