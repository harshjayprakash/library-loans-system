package winchester.library.data.access;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLTimeoutException;
import java.util.Optional;

public class DatabaseConnection {

    private Connection connection;

    public DatabaseConnection() { }

    private void loadDriver() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch (ClassNotFoundException exception) {
            System.err.printf("%s : %s%n", DatabaseConstant.DRIVER_NOT_FOUND, exception.getMessage());
        }
    }

    public void establish(DatabaseCredentials credentials) {
        try {
            this.loadDriver();
            this.connection = DriverManager.getConnection(
                    credentials.getUrl(), credentials.getUsername(), credentials.getPassword());
        }
        catch (SQLTimeoutException exception) {
            System.err.printf("%s : %s%n", DatabaseConstant.CONNECTION_TIMEOUT, exception.getMessage());
        }
        catch (SQLException exception) {
            System.err.printf("%s : %s%n", DatabaseConstant.NOT_ACCESSIBLE, exception.getMessage());
        }
    }

    public Optional<ResultSet> executeQuery(QueryBuilder sql) {
        try {
            PreparedStatement statement = this.connection.prepareStatement(sql.toString());
            return Optional.ofNullable(statement.executeQuery());
        }
        catch (NullPointerException exception) {
            System.err.printf("%s : %s%n", DatabaseConstant.CONNECTION_NOT_AVAILABLE, exception.getMessage());
        }
        catch (SQLTimeoutException exception) {
            System.err.printf("%s : %s%n", DatabaseConstant.CONNECTION_TIMEOUT, exception.getMessage());
        }
        catch (SQLException exception) {
            System.err.printf("%s : %s", DatabaseConstant.NOT_ACCESSIBLE, exception.getMessage());
        }
        return Optional.empty();
    }

    public void close() {
        try {
            if (this.connection != null) {
                this.connection.close();
            }
        }
        catch (SQLException exception) {
            System.err.printf("%s : %s%n", DatabaseConstant.CONNECTION_CLOSE_ERROR, exception.getMessage());
        }
    }
}
