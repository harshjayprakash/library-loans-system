package winchester.library.service;

import java.time.LocalDate;
import java.util.ArrayList;
import winchester.library.data.access.DataFetcher;
import winchester.library.data.access.DataUpdater;
import winchester.library.data.access.DataWriter;
import winchester.library.data.access.DatabaseConstant;
import winchester.library.data.model.items.Book;
import winchester.library.data.model.items.Film;
import winchester.library.data.model.items.Item;
import winchester.library.data.model.items.ItemFormat;
import winchester.library.data.model.loans.Loan;
import winchester.library.data.model.users.Customer;
import winchester.library.data.model.users.Employee;
import winchester.library.data.model.users.EmployeeStatus;
import winchester.library.data.model.users.UserType;

/**
 * A singleton class that provides an abstraction to the database operations for the graphical user interface.
 */
public final class DataPersistenceManager {

    private static DataPersistenceManager instance;
    private final DataFetcher dataRetriever;
    private final DataWriter dataWriter;
    private final DataUpdater dataUpdater;

    /**
     * A private constructor to prevent multiple instances.
     */
    private DataPersistenceManager() {
        this.dataRetriever = new DataFetcher();
        this.dataWriter = new DataWriter();
        this.dataUpdater = new DataUpdater();
    }

    /**
     * A static accessor to retrieve the single instance of the DataPersistenceManager class.
     * @return the instance of the class.
     */
    public static DataPersistenceManager getInstance() {
        if (DataPersistenceManager.instance == null) {
            DataPersistenceManager.instance = new DataPersistenceManager();
        }
        return DataPersistenceManager.instance;
    }

    /**
     * An accessor to retrieve the list of books from the data source.
     * @return an array list of books.
     */
    public ArrayList<Book> getBooks() {
        return dataRetriever.getBooks();
    }

    /**
     * An accessor to retrieve the list of films from the data source.
     * @return an array list of film.
     */
    public ArrayList<Film> getFilms() {
        return dataRetriever.getFilms();
    }

    /**
     * An accessor to retrieve the list of customers from the data source.
     * @return an array list of customers.
     */
    public ArrayList<Customer> getCustomers() {
        ArrayList<Customer> customers = dataRetriever.getCustomers();
        ArrayList<Loan> loans = dataRetriever.getLoans();
        Searcher searcher = new Searcher();
        customers.forEach(customer ->
            loans.stream()
                    .filter(loan -> loan.getCustomer().getIdentifier() == customer.getIdentifier()
                            && !loan.getReturned() && loan.calculateDaysRemaining() < 0)
                    .forEach(loan -> customer.setOverdueFees((int)
                            Math.abs(loan.calculateDaysRemaining())
                            * searcher.searchItemFromIdentifier(loan.getLoanedItemIdentifier()).getOverdueFeePence())));
        return customers;
    }

    /**
     * An accessor to retrieve the list of employees from the data source.
     * @return an array list of employees
     */
    public ArrayList<Employee> getEmployees() {
        return dataRetriever.getEmployees();
    }

    /**
     * An accessor to retrieve the list of loans from the data source.
     * @return an array list of the loans.
     */
    public ArrayList<Loan> getLoans() {
        return dataRetriever.getLoans();
    }

    /**
     * A method to create a new user, syncing it to the data source.
     * @param userType the user type.
     * @param firstName the first name of the user.
     * @param lastName the last name of the user.
     * @param postalCode the postal code of the user.
     * @param username the username of the user.
     * @param password the password of the user.
     * @return true if the operation was successful.
     */
    public boolean createUser(UserType userType, String firstName, String lastName, String postalCode, String username,
                              String password) {
        return switch (userType) {
            case STANDARD, ADMINISTRATOR -> dataWriter.insert(new Employee(new IdentifierGenerator().generateForUser(),
                    userType, firstName, lastName, postalCode, username, password,
                    EmployeeStatus.NOT_APPROVED));
            default -> DatabaseConstant.INSERTION_ERROR;
        } == DatabaseConstant.INSERTION_SUCCESSFUL;
    }

    /**
     * A method to create a new customer, syncing it to the data source.
     * @param firstName the first name of the customer.
     * @param lastName the last name of the customer.
     * @param postalCode the postal code of the customer.
     * @return true if the operation was successful.
     */
    public boolean createCustomer(String firstName, String lastName, String postalCode) {
        return dataWriter.insert(new Customer(new IdentifierGenerator().generateForUser(),
                firstName, lastName, postalCode)) == DatabaseConstant.INSERTION_SUCCESSFUL;
    }

    /**
     * A method to create a new loan, syncing it to the data source.
     * @param item the item to be loaned out.
     * @param format the format of the item to be loaned out.
     * @param customer the customer loaning out the item.
     * @return true if the operation was successful
     */
    public boolean createLoan(Item item, ItemFormat format, Customer customer) {
        return switch (item.getType()) {
            case BOOK -> dataWriter.insert(new Loan(0, customer, Book.castFrom(item).getIsbn(), format,
                    LocalDate.now(), LocalDate.now().plusDays(30), false));
            case FILM -> dataWriter.insert(new Loan(0, customer, Film.castFrom(item).getIdentifier(), format,
                    LocalDate.now(), LocalDate.now().plusDays(7), false));
        } == DatabaseConstant.INSERTION_SUCCESSFUL;
    }

    /**
     * A method to approve an employee, syncing this to the data source.
     * @param employee the employee to be approved.
     * @return true if the operation was successful.
     */
    public boolean approveEmployee(Employee employee) {
        return dataUpdater.updateEmployeeStatus(employee, EmployeeStatus.ACTIVE) == DatabaseConstant.UPDATE_SUCCESSFUL;
    }

    /**
     * A method to disable an employee, syncing this to the data source.
     * @param employee the employee to be disabled.
     * @return true if the operation was successful.
     */
    public boolean disableEmployee(Employee employee) {
        return dataUpdater.updateEmployeeStatus(
                employee, EmployeeStatus.DISABLED) == DatabaseConstant.UPDATE_SUCCESSFUL;
    }

    /**
     * A method to enable an employee, syncing this to the data source.
     * @param employee the employee to be enabled.
     * @return true if the operation was successful.
     */
    public boolean enableEmployee(Employee employee) {
        return dataUpdater.updateEmployeeStatus(employee, EmployeeStatus.ACTIVE) == DatabaseConstant.UPDATE_SUCCESSFUL;
    }

    /**
     * A method to mark a loan as returned, syncing it to the data source.
     * @param loan the loan to be returned.
     * @return true if the operation was successful.
     */
    public boolean returnItem(Loan loan) {
        return dataUpdater.returnLoan(loan) == DatabaseConstant.UPDATE_SUCCESSFUL;
    }

    /**
     * A method to update the amount of days the item can be taken out for.
     * @param loan the loan to be extended.
     * @return true if the operation was successful.
     */
    public boolean extendLoan(Loan loan) {
        return dataUpdater.updateDueDateOnLoan(loan) == DatabaseConstant.UPDATE_SUCCESSFUL;
    }

    /**
     * A method to create a new film entity and add it to the data source.
     * @param film the film to be added.
     * @return true if the operation was successful.
     */
    public boolean createFilm(Film film) {
        return dataWriter.insert(film) == DatabaseConstant.INSERTION_SUCCESSFUL;
    }

    /**
     * A method to create a new book and add it to the data source.
     * @param book the book to be added.
     * @return true if the operation was successful.
     */
    public boolean createBook(Book book) {
        return dataWriter.insert(book) == DatabaseConstant.INSERTION_SUCCESSFUL;
    }

    /**
     * A method to update the employee's password, syncing it to the data source.
     * @param employee the employee to be updated.
     * @return true if the operation was successful.
     */
    public boolean updatePassword(Employee employee) {
        return dataUpdater.updateEmployeePassword(employee) == DatabaseConstant.UPDATE_SUCCESSFUL;
    }

}
