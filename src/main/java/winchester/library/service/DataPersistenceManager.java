package winchester.library.service;

import java.util.ArrayList;
import winchester.library.data.access.DataFetcher;
import winchester.library.data.access.DataWriter;
import winchester.library.data.access.DatabaseConstant;
import winchester.library.data.model.items.Book;
import winchester.library.data.model.items.Film;
import winchester.library.data.model.loans.Loan;
import winchester.library.data.model.users.Administrator;
import winchester.library.data.model.users.Customer;
import winchester.library.data.model.users.Employee;
import winchester.library.data.model.users.EmployeeStatus;

/**
 * A class that provides an abstraction to the database operations for the graphical user interface.
 */
public final class DataPersistenceManager {

    private static DataPersistenceManager instance;
    private final DataFetcher dataRetriever;
    private final DataWriter dataWriter;

    private DataPersistenceManager() {
        this.dataRetriever = new DataFetcher();
        this.dataWriter = new DataWriter();
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

    public boolean createUser(UserType userType, String firstName, String lastName, String postalCode, String username, String password) {
        return switch (userType) {
            case STANDARD -> dataWriter.insert(new Employee(new IdentifierGenerator().generateForUser(), userType, firstName, lastName, postalCode, username, password, EmployeeStatus.NOT_APPROVED));
            case ADMINISTRATOR -> dataWriter.insert(new Administrator(new IdentifierGenerator().generateForUser(), userType, firstName, lastName, postalCode, username, password, EmployeeStatus.NOT_APPROVED));
            default -> DatabaseConstant.INSERTION_ERROR;
        } == DatabaseConstant.INSERTION_SUCCESSFUL;

    }

}
