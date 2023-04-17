package winchester.library.service;

import java.util.ArrayList;
import java.util.Optional;
import winchester.library.data.access.DatabaseConnectionManager;
import winchester.library.data.access.DatabaseConstant;
import winchester.library.data.access.DatabaseCredentialsManager;
import winchester.library.data.model.items.Book;



public class DatabaseInteraction {

    private static final DatabaseInteraction instance = new DatabaseInteraction();
    private final DatabaseConnectionManager connectionManager = DatabaseConnectionManager.getInstance();
    private final DatabaseCredentialsManager credentialsManager = DatabaseCredentialsManager.getInstance();

    private DatabaseInteraction() { }

    public static DatabaseInteraction getInstance() {
        return DatabaseInteraction.instance;
    }

    public boolean getDatabaseAvailable() {
        return connectionManager.test(credentialsManager) == DatabaseConstant.CONNECTION_SUCCESSFUL.getIdentifier();
    }

    public DatabaseConstant getDatabaseStatus() {
        return DatabaseConstant.getFromIdentifier(connectionManager.test(credentialsManager));
    }

    public Optional<ArrayList<Book>> getBooks() {
        return connectionManager.getBooks(credentialsManager);
    }

}
