package winchester.library.data.access;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLTimeoutException;

/**
 * A class that provides an interface for testing the database connection credentials, providing a status. This class
 * has been marked as final to prevent extension.
 */
public final class DatabaseConnectionTester {

    /**
     * The default constructor.
     */
    public DatabaseConnectionTester() { }

    /**
     * A method to test whether the MySQL JDBC Driver is available.
     * @return a DatabaseConstant enumeration that specifies if the driver was found.
     */
    public DatabaseConstant testDriver() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DatabaseConstant.DRIVER_FOUND;
        }
        catch (ClassNotFoundException ignored) {
            return DatabaseConstant.DRIVER_NOT_FOUND;
        }
    }

    /**
     * A method to test whether a connection can be established with the provided credentials.
     * @param credentials an instance of the DatabaseCredentials class that stores the url, username and password.
     * @return a DatabaseConstant enumeration that specifies the connection was successful.
     */
    public DatabaseConstant testCredentials(DatabaseCredentials credentials) {
        return this.testCredentials(credentials.getUrl(), credentials.getUsername(), credentials.getPassword());
    }

    /**
     * A method to test whether a connection can be established with a provided url, username and password.
     * @param url the database url.
     * @param username the database username.
     * @param password the database password.
     * @return a DatabaseConstant enumeration that specifies if the connection was successful.
     */
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
