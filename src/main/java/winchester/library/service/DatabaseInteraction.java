package winchester.library.service;

import java.util.ArrayList;
import winchester.library.data.access.DataRetriever;
import winchester.library.data.access.DatabaseConnectionTester;
import winchester.library.data.access.DatabaseConstant;
import winchester.library.data.access.DatabaseCredentials;
import winchester.library.data.model.items.Book;
import winchester.library.data.model.items.Film;
import winchester.library.data.model.loans.Loan;
import winchester.library.data.model.users.Customer;
import winchester.library.data.model.users.Employee;

/**
 * A class that provides an abstraction to the database for the graphical user interface.
 */
public final class DatabaseInteraction {

    private static DatabaseInteraction instance;
    private final DatabaseCredentials credentials;
    private final DatabaseConnectionTester connectionTester;
    private final DataRetriever dataRetriever;

    private DatabaseInteraction() {
        this.credentials = DatabaseCredentials.getInstance();
        this.connectionTester = DatabaseConnectionTester.getInstance();
        this.dataRetriever = new DataRetriever();
    }

    public static DatabaseInteraction getInstance() {
        if (DatabaseInteraction.instance == null) {
            DatabaseInteraction.instance = new DatabaseInteraction();
        }
        return DatabaseInteraction.instance;
    }

    public boolean getDatabaseAvailable() {
        return connectionTester.testCredentials(credentials) == DatabaseConstant.CONNECTION_SUCCESSFUL;
    }

    public DatabaseConstant getDatabaseStatus() {
        return connectionTester.testCredentials(credentials);
    }

    public ArrayList<Book> getBooks() {
        return dataRetriever.getBooks();
    }

    public ArrayList<Film> getFilms() {
        return dataRetriever.getFilms();
    }

    public ArrayList<Customer> getCustomers() {
        return dataRetriever.getCustomers();
    }

    public ArrayList<Employee> getEmployees() {
        return dataRetriever.getEmployees();
    }

    public ArrayList<Loan> getLoans() {
        return dataRetriever.getLoans();
    }

}
