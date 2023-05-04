package winchester.library.data.access;

/**
 * A singleton class that stores the credentials to connect to the database, providing mutators for the ability of
 * changing the credentials. This class as been marked as final to prevent extension.
 */
public final class DatabaseCredentials {

    private static DatabaseCredentials instance;
    private String url = "jdbc:mysql://localhost:3306/library";
    private String username = "root";
    private String password = "dbpassword";

    /**
     * The default constructor.
     */
    private DatabaseCredentials() { }

    /**
     * This is a method to get the singleton instance of the DatabaseCredentials class.
     * @return the single instance of the class.
     */
    public static DatabaseCredentials getInstance() {
        if (DatabaseCredentials.instance == null) {
            DatabaseCredentials.instance = new DatabaseCredentials();
        }
        return DatabaseCredentials.instance;
    }

    /**
     * An accessor that retrieves the database url.
     * @return the database URL as a String.
     */
    public String getUrl() {
        return this.url;
    }

    /**
     * A mutator that sets the database url.
     * @param url the database url.
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * An accessor that retrieves the database username.
     * @return the database username.
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * A mutator that sets the database username.
     * @param username the database username.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * An accessor that retrieves the database password.
     * @return the database password.
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * A mutator that sets the database password.
     * @param password the database password.
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
