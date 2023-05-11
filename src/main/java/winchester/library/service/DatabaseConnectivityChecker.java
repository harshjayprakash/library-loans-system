package winchester.library.service;

import winchester.library.data.access.DatabaseConnectionTester;
import winchester.library.data.access.DatabaseConstant;
import winchester.library.data.access.DatabaseCredentials;

/**
 * A singleton class that provides an abstraction to checking the database status for the graphical user interface.
 */
public class DatabaseConnectivityChecker {

    private static DatabaseConnectivityChecker instance = null;
    private final DatabaseConnectionTester connectionTester;
    private final DatabaseCredentials credentials;

    /**
     * A private constructor to prevent multiple instances.
     */
    private DatabaseConnectivityChecker() {
        this.connectionTester = new DatabaseConnectionTester();
        this.credentials = DatabaseCredentials.getInstance();
    }

    /**
     * A static accessor to retrieve the single instance of the DatabaseConnectivityChecker class.
     * @return the instance of the class.
     */
    public static DatabaseConnectivityChecker getInstance() {
        if (DatabaseConnectivityChecker.instance == null) {
            DatabaseConnectivityChecker.instance = new DatabaseConnectivityChecker();
        }
        return DatabaseConnectivityChecker.instance;
    }

    /**
     * A method to check where the database connector driver is available.
     * @return a boolean if the driver is available.
     */
    public boolean isDriverAvailable() {
        return connectionTester.testDriver() == DatabaseConstant.DRIVER_FOUND;
    }

    /**
     * A method check whether the availability of the database based on the credentials.
     * @return a boolean if the database is connected successfully.
     */
    public boolean isDatabaseAvailable() {
        return connectionTester.testCredentials(credentials) == DatabaseConstant.CONNECTION_SUCCESSFUL;
    }

    /**
     * An accessor to retrieve the status of the database connection based on the stored credentials.
     * @return a DatabaseConstant enumeration to describe the connection.
     */
    public DatabaseConstant getDatabaseStatus() {
        return connectionTester.testCredentials(credentials);
    }

    /**
     * An accessor to retrieve the status of the database connection.
     * @param url the database url.
     * @param username the database username.
     * @param password the database password.
     * @return a DatabaseConstant enumeration to describe the connection.
     */
    public DatabaseConstant getDatabaseStatus(String url, String username, String password) {
        return connectionTester.testCredentials(url, username, password);
    }

}
