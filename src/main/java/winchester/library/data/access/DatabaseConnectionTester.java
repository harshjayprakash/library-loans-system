package winchester.library.data.access;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLTimeoutException;

/**
 * A singleton class that provides a way of testing the connection to get the current status.
 */
public class DatabaseConnectionTester {

    private static DatabaseConnectionTester instance;

    private DatabaseConnectionTester() { }

    public static DatabaseConnectionTester getInstance() {
        if (DatabaseConnectionTester.instance == null) {
            DatabaseConnectionTester.instance = new DatabaseConnectionTester();
        }
        return DatabaseConnectionTester.instance;
    }

    public DatabaseConstant testDriver() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DatabaseConstant.DRIVER_FOUND;
        }
        catch (ClassNotFoundException ignored) {
            return DatabaseConstant.DRIVER_NOT_FOUND;
        }
    }

    public DatabaseConstant testCredentials(DatabaseCredentials credentials) {
        return this.testCredentials(credentials.getUrl(), credentials.getUsername(), credentials.getPassword());
    }

    public DatabaseConstant testCredentials(String url, String username, String password) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);
            connection.close();
            return DatabaseConstant.CONNECTION_SUCCESSFUL;
        }
        catch (ClassNotFoundException ignored) {
            return DatabaseConstant.DRIVER_NOT_FOUND;
        }
        catch (SQLTimeoutException ignored) {
            return DatabaseConstant.CONNECTION_TIMEOUT;
        }
        catch (SQLException ignored) {
            return DatabaseConstant.DATABASE_NOT_ACCESSIBLE;
        }
        catch (Exception ignored) {
            return DatabaseConstant.UNKNOWN_ERROR;
        }
    }

}
