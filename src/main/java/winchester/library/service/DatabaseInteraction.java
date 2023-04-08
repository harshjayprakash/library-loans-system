package winchester.library.service;

import winchester.library.data.access.DatabaseConnectionManager;
import winchester.library.data.access.DatabaseConstants;
import winchester.library.data.access.DatabaseCredentialsManager;

public class DatabaseInteraction {

    private static final DatabaseInteraction instance = new DatabaseInteraction();

    private DatabaseInteraction() { }

    public static DatabaseInteraction getInstance() {
        return DatabaseInteraction.instance;
    }

    public boolean getDatabaseStatus() {
        DatabaseConnectionManager databaseConnectionManager = DatabaseConnectionManager.getInstance();
        DatabaseCredentialsManager databaseCredentialsManager = DatabaseCredentialsManager.getInstance();
        return databaseConnectionManager.testConnection(
                databaseCredentialsManager.getUrl(),
                databaseCredentialsManager.getUsername(),
                databaseCredentialsManager.getPassword()) == DatabaseConstants.CONNECTION_SUCCESSFUL.getIdentifier();
    }

}
