package winchester.library.data.access;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnectionManager {
    
    private final static DatabaseConnectionManager instance = new DatabaseConnectionManager();

    private DatabaseConnectionManager() {

    }

    public static DatabaseConnectionManager getInstance() {
        return DatabaseConnectionManager.instance;
    }

    public int test(DatabaseCredentialsManager credentials) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    credentials.getUrl(), credentials.getUsername(), credentials.getPassword());
            connection.close();
        }
        catch (ClassNotFoundException exception) {
            return DatabaseConstant.DRIVER_NOT_FOUND.getIdentifier();
        }
        catch (SQLException exception) {
            return DatabaseConstant.NOT_ACCESSIBLE.getIdentifier();
        }
        catch (Exception exception) {
            System.out.println(exception.getMessage());
            return DatabaseConstant.UNKNOWN_ERROR.getIdentifier();
        }
        return DatabaseConstant.CONNECTION_SUCCESSFUL.getIdentifier();
    }

}
