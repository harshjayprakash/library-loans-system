package winchester.library.service;

import java.util.ArrayList;
import winchester.library.data.access.DataFetcher;
import winchester.library.data.access.DatabaseCredentials;
import winchester.library.data.model.items.Book;
import winchester.library.data.model.items.Film;
import winchester.library.data.model.loans.Loan;
import winchester.library.data.model.users.Customer;
import winchester.library.data.model.users.Employee;

/**
 * A class that provides an abstraction to the database operations for the graphical user interface.
 */
public final class DataPersistenceManager {

    private static DataPersistenceManager instance;
    private final DatabaseCredentials credentials;
    private final DataFetcher dataRetriever;

    private DataPersistenceManager() {
        this.credentials = DatabaseCredentials.getInstance();
        this.dataRetriever = new DataFetcher();
    }

    public static DataPersistenceManager getInstance() {
        if (DataPersistenceManager.instance == null) {
            DataPersistenceManager.instance = new DataPersistenceManager();
        }
        return DataPersistenceManager.instance;
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

    public void createNewEmployee(Employee employee) {

    }

}
