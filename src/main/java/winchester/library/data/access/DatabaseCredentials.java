package winchester.library.data.access;

/**
 * A singleton class that stores the credentials to connect to the database, providing mutators for the ability of
 * changing the credentials
 */
public class DatabaseCredentials {

    private static DatabaseCredentials instance;
    private String url = "jdbc:mysql://localhost:3306/library";
    private String username = "root";
    private String password = "dbpassword";

    private DatabaseCredentials() {

    }

    public static DatabaseCredentials getInstance() {
        if (DatabaseCredentials.instance == null) {
            DatabaseCredentials.instance = new DatabaseCredentials();
        }
        return DatabaseCredentials.instance;
    }

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
}
