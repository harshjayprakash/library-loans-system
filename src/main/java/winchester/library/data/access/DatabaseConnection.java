package winchester.library.data.access;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLTimeoutException;
import java.util.Optional;
import winchester.library.service.Logger;

/**
 * A class that encapsulates the database connection separating each operation into callable functions that have
 * exception handling, written to the standard error stream.
 */
public class DatabaseConnection {

    private Connection connection;

    public DatabaseConnection() { }

    public void establish(DatabaseCredentials credentials) {
        try {
            this.loadDriver();
            this.connection = DriverManager.getConnection(
                    credentials.getUrl(), credentials.getUsername(), credentials.getPassword());
        }
        catch (SQLTimeoutException exception) {
            Logger.getInstance().PrintError(
                    this.getClass().getName(),
                    "Establishing Database Connection",
                    DatabaseConstant.CONNECTION_TIMEOUT.toString(),
                    "Ensure that the Connection is Active");
        }
        catch (SQLException exception) {
            Logger.getInstance().PrintError(
                    this.getClass().getName(),
                    "Establishing Database Connection",
                    DatabaseConstant.DATABASE_NOT_ACCESSIBLE.toString(),
                    "Ensure that the credentials provided are correct");
        }
    }

    public Optional<ResultSet> executeQuery(QueryBuilder sql) {
        try {
            PreparedStatement statement = this.connection.prepareStatement(sql.toString());
            return Optional.ofNullable(statement.executeQuery());
        }
        catch (NullPointerException exception) {
            Logger.getInstance().PrintError(
                    this.getClass().getName(),
                    "Executing an SQL Query",
                    DatabaseConstant.CONNECTION_NOT_AVAILABLE.toString(),
                    "Ensure that there is a Connection Available");
        }
        catch (SQLTimeoutException exception) {
            Logger.getInstance().PrintError(
                    this.getClass().getName(),
                    "Executing an SQL Query",
                    DatabaseConstant.CONNECTION_TIMEOUT.toString(),
                    "Ensure that the Connection is Active");
        }
        catch (SQLException exception) {
            Logger.getInstance().PrintError(
                    this.getClass().getName(),
                    "Executing an SQL Query",
                    DatabaseConstant.DATABASE_NOT_ACCESSIBLE.toString(),
                    "Ensure that the Connection is Active");
        }
        return Optional.empty();
    }

    public Optional<Integer> executeUpdate(QueryBuilder sql) {
        try {
            PreparedStatement statement = this.connection.prepareStatement(sql.toString());
            return Optional.of(statement.executeUpdate());
        }
        catch (NullPointerException exception) {
            Logger.getInstance().PrintError(
                    this.getClass().getName(),
                    "Executing an SQL Query",
                    DatabaseConstant.CONNECTION_NOT_AVAILABLE.toString(),
                    "Ensure that there is a Connection Available");
        }
        catch (SQLTimeoutException exception) {
            Logger.getInstance().PrintError(
                    this.getClass().getName(),
                    "Executing an SQL Query",
                    DatabaseConstant.CONNECTION_TIMEOUT.toString(),
                    "Ensure that the Connection is Active");
        }
        catch (SQLException exception) {
            Logger.getInstance().PrintError(
                    this.getClass().getName(),
                    "Executing an SQL Query",
                    DatabaseConstant.DATABASE_NOT_ACCESSIBLE.toString(),
                    "Ensure that the Connection is Active");
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
            Logger.getInstance().PrintError(
                    this.getClass().getName(),
                    "Closing Database Connection",
                    DatabaseConstant.CONNECTION_CLOSE_ERROR.toString(),
                    "Ensure that the Connection is Active.");
        }
    }

    private void loadDriver() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch (ClassNotFoundException exception) {
            Logger.getInstance().PrintError(
                    this.getClass().getName(),
                    "Loading MySQL Driver",
                    DatabaseConstant.DRIVER_NOT_FOUND.toString(),
                    "Ensure the availability of the MySQL Connector Dependency");
        }
    }
}
