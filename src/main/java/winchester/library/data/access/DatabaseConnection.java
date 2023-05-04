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
 * exception handling, written to the standard error stream. This class cannot be extended due to being marked as final.
 */
public final class DatabaseConnection {

    private Connection connection;

    /**
     * The default constructor.
     */
    public DatabaseConnection() { }

    /**
     * This method creates a connection to the database based on the credentials provided. It handles the
     * SQLTimeoutException and SQLException, printing a message to the standard error stream.
     * @param credentials takes in the database credentials in the form of the DatabaseCredentials class.
     */
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

    /**
     * This method runs a sql query to get information from a data source. It handles the NullPointerException,
     * SQLTimeoutException and SQLException printing an error to the standard error stream.
     * @param sql a sql statement in the form of the QueryBuilder class.
     * @return an optional version of the ResultSet interface that contains the data fetched from the database or empty
     * if an exception has been thrown.
     */
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

    /**
     * This method runs a sql query to update or add records to the data source.It handles the NullPointerException,
     * SQLTimeoutException and SQLException printing an error to the standard error stream
     * @param sql a sql statement in the form of the QueryBuilder class.
     * @return an optional integer that is either the row count or 0 if the statement returns nothing. An optional of
     * empty is returned if an exception is thrown.
     */
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

    /**
     * This method closes the connection to the data source, handing an SQLException by printing to the standard error
     * stream.
     */
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

    /**
     * This method loads the JDBC MySQL driver.
     */
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
