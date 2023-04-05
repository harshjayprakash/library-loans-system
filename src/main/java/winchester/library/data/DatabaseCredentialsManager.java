package winchester.library.data;

public class DatabaseCredentialsManager {

    private final static DatabaseCredentialsManager instance = new DatabaseCredentialsManager();
    private String url;
    private String username;
    private String password;

    private DatabaseCredentialsManager() { }

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

    public static DatabaseCredentialsManager getInstance() {
        return DatabaseCredentialsManager.instance;
    }

}
