package winchester.library.service;

import winchester.library.data.access.DatabaseConnectionTester;
import winchester.library.data.access.DatabaseConstant;
import winchester.library.data.access.DatabaseCredentials;

/**
 * A class that provides an abstraction to checking the database status for the graphical user interface.
 */
public class DatabaseConnectivityChecker {

    private static DatabaseConnectivityChecker instance = null;
    private final DatabaseConnectionTester connectionTester;
    private final DatabaseCredentials credentials;

    private DatabaseConnectivityChecker() {
        this.connectionTester = new DatabaseConnectionTester();
        this.credentials = DatabaseCredentials.getInstance();
    }

    public boolean isDriverAvailable() {
        return connectionTester.testDriver() == DatabaseConstant.DRIVER_FOUND;
    }

    public static DatabaseConnectivityChecker getInstance() {
        if (DatabaseConnectivityChecker.instance == null) {
            DatabaseConnectivityChecker.instance = new DatabaseConnectivityChecker();
        }
        return DatabaseConnectivityChecker.instance;
    }

    public boolean getDatabaseAvailable() {
        return connectionTester.testCredentials(credentials) == DatabaseConstant.CONNECTION_SUCCESSFUL;
    }

    public DatabaseConstant getDatabaseStatus() {
        return connectionTester.testCredentials(credentials);
    }

    public DatabaseConstant getDatabaseStatus(String url, String username, String password) {
        return connectionTester.testCredentials(url, username, password);
    }

}
