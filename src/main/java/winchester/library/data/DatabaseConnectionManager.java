package winchester.library.data;

public class DatabaseConnectionManager {

    private final static DatabaseConnectionManager instance = new DatabaseConnectionManager();
    private String url;
    private String username;
    private String password;

    private DatabaseConnectionManager() { }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static DatabaseConnectionManager getInstance() {
        return DatabaseConnectionManager.instance;
    }

}
