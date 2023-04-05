package winchester.library.data;

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

    public int testConnection(String url, String username, String password) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);
            connection.close();
        }
        catch (ClassNotFoundException exception) {
            return DatabaseConstants.DRIVER_NOT_FOUND.getIdentifier();
        }
        catch (SQLException exception) {
            return DatabaseConstants.NOT_ACCESSIBLE.getIdentifier();
        }
        catch (Exception exception) {
            System.out.println(exception.getMessage());
            return DatabaseConstants.UNKNOWN_ERROR.getIdentifier();
        }
        return DatabaseConstants.CONNECTION_SUCCESSFUL.getIdentifier();
    }

}
