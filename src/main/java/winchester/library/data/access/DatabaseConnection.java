package winchester.library.data.access;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLTimeoutException;
import java.util.Optional;
import winchester.library.service.ConsolePrinter;

/**
 * A class encapsulates the sql connection as well as handles any exceptions that may occur, which is written to the
 * standard error stream.
 */
public class DatabaseConnection {

    private Connection connection;

    public DatabaseConnection() { }

    private void loadDriver() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch (ClassNotFoundException exception) {
            ConsolePrinter.getInstance().WriteLineError(
                    DatabaseConstant.DRIVER_NOT_FOUND.toString(), exception.getMessage());
        }
    }

    public void establish(DatabaseCredentials credentials) {
        try {
            this.loadDriver();
            this.connection = DriverManager.getConnection(
                    credentials.getUrl(), credentials.getUsername(), credentials.getPassword());
        }
        catch (SQLTimeoutException exception) {
            ConsolePrinter.getInstance().WriteLineError(
                    DatabaseConstant.CONNECTION_TIMEOUT.toString(), exception.getMessage());
        }
        catch (SQLException exception) {
            ConsolePrinter.getInstance().WriteLineError(
                    DatabaseConstant.DATABASE_NOT_ACCESSIBLE.toString(), exception.getMessage());
        }
    }

    public Optional<ResultSet> executeQuery(QueryBuilder sql) {
        try {
            PreparedStatement statement = this.connection.prepareStatement(sql.toString());
            return Optional.ofNullable(statement.executeQuery());
        }
        catch (NullPointerException exception) {
            ConsolePrinter.getInstance().WriteLineError(
                    DatabaseConstant.CONNECTION_NOT_AVAILABLE.toString(), exception.getMessage());
        }
        catch (SQLTimeoutException exception) {
            ConsolePrinter.getInstance().WriteLineError(
                    DatabaseConstant.CONNECTION_TIMEOUT.toString(), exception.getMessage());
        }
        catch (SQLException exception) {
            ConsolePrinter.getInstance().WriteLineError(
                    DatabaseConstant.DATABASE_NOT_ACCESSIBLE.toString(), exception.getMessage());
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
            ConsolePrinter.getInstance().WriteLineError(
                    DatabaseConstant.CONNECTION_CLOSE_ERROR.toString(), exception.getMessage());
        }
    }
}
