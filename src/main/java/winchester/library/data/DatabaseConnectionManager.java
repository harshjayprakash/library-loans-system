package winchester.library.data;

public class DatabaseConnectionManager {
    
    private final static DatabaseConnectionManager instance = new DatabaseConnectionManager();

    private DatabaseConnectionManager() {

    }

    public static DatabaseConnectionManager getInstance() {
        return DatabaseConnectionManager.instance;
    }

}
