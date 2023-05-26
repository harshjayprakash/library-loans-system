package winchester.library.data.access;

import java.util.Optional;
import winchester.library.data.model.items.Book;
import winchester.library.data.model.items.Film;
import winchester.library.data.model.items.ItemStock;
import winchester.library.data.model.loans.Loan;
import winchester.library.data.model.users.Customer;
import winchester.library.data.model.users.Employee;

/**
 * A class that abstracts the method of creating a new record within the database.
 */
public class DataWriter {

    private final DatabaseCredentials credentials;

    /**
     * The default constructor that gets the DatabaseCredentials instance.
     */
    public DataWriter() {
        this.credentials = DatabaseCredentials.getInstance();
    }

    /**
     * A method to insert an employee into the data source.
     * @param employee the employee to be inserted.
     * @return a DatabaseConstant enumeration of either INSERTION_ERROR if the operation failed or if the operation
     * succeeded returns it returns INSERTION_SUCCESSFUL.
     */
    public DatabaseConstant insert(Employee employee) {
        DatabaseConnection connection = new DatabaseConnection();
        connection.establish(this.credentials);
        Optional<Integer> userInsertResult = connection.executeUpdate(
                QueryBuilder.createQuery(QueryType.INSERT_ONE)
                        .insertInto("library.users")
                        .values(String.format("(%d, %d, '%s', '%s', '%s')",
                                employee.getIdentifier(), employee.getType().getIdentifier(), employee.getFirstName(),
                                employee.getLastName(), employee.getPostalCode())));
        if (userInsertResult.isEmpty()) {
            return DatabaseConstant.INSERTION_ERROR;
        }
        Optional<Integer> employeeInsertResult = connection.executeUpdate(
                QueryBuilder.createQuery(QueryType.INSERT_ONE)
                        .insertInto("library.employees (user_id, username, hashed_password, status_id)")
                        .values(String.format("(%d, '%s', '%s', %d)",
                                employee.getIdentifier(), employee.getUsername(), employee.getHashedPassword(),
                                employee.getStatus().getIdentifier()))
        );
        if (employeeInsertResult.isEmpty()) {
            return DatabaseConstant.INSERTION_ERROR;
        }
        connection.close();
        return DatabaseConstant.INSERTION_SUCCESSFUL;
    }

    /**
     * A method to insert a customer into the data source.
     * @param customer the customer to be inserted.
     * @return a DatabaseConstant enumeration of either INSERTION_ERROR if the operation failed or if the operation
     * succeeded returns it returns INSERTION_SUCCESSFUL.
     */
    public DatabaseConstant insert(Customer customer) {
        DatabaseConnection connection = new DatabaseConnection();
        connection.establish(this.credentials);
        Optional<Integer> userInsertResult = connection.executeUpdate(
                QueryBuilder.createQuery(QueryType.INSERT_ONE)
                        .insertInto("library.users")
                        .values(String.format("(%d, %d, '%s', '%s', '%s')",
                                customer.getIdentifier(), customer.getType().getIdentifier(), customer.getFirstName(),
                                customer.getLastName(), customer.getPostalCode())));
        if (userInsertResult.isEmpty()) {
            return DatabaseConstant.INSERTION_ERROR;
        }
        connection.close();
        return DatabaseConstant.INSERTION_SUCCESSFUL;
    }

    /**
     * A method to insert a loan into the data source.
     * @param loan the loan to be inserted.
     * @return a DatabaseConstant enumeration of either INSERTION_ERROR if the operation failed or if the operation
     * succeeded returns it returns INSERTION_SUCCESSFUL.
     */
    public DatabaseConstant insert(Loan loan) {
        DatabaseConnection connection = new DatabaseConnection();
        connection.establish(this.credentials);
        Optional<Integer> loanInsertResult = connection.executeUpdate(
                QueryBuilder.createQuery(QueryType.INSERT_ONE)
                        .insertInto("library.loans (customer_id, item_id, item_subtype_id, loan_date, return_date, returned)")
                        .values(String.format("(%d,'%s', %d, '%s', '%s', %d)",
                                loan.getCustomer().getIdentifier(), loan.getLoanedItemIdentifier(),
                                loan.getLoanedItemFormat().getIdentifier(), loan.getLoanDate().toString(),
                                loan.getDueDate().toString(), (loan.getReturned()) ? 1 : 0)));
        if (loanInsertResult.isEmpty()) { return DatabaseConstant.INSERTION_ERROR; }
        connection.close();
        return DatabaseConstant.INSERTION_SUCCESSFUL;
    }

    /**
     * A method to insert a film and its item stock into the data source.
     * @param film the film to inserted.
     * @return a DatabaseConstant enumeration of either INSERTION_ERROR if the operation failed or if the operation
     * succeeded returns it returns INSERTION_SUCCESSFUL.
     */
    public DatabaseConstant insert(Film film) {
        DatabaseConnection connection = new DatabaseConnection();
        connection.establish(this.credentials);
        Optional<Integer> filmInsertResult = connection.executeUpdate(
                QueryBuilder.createQuery(QueryType.INSERT_ONE)
                        .insertInto("library.films")
                        .values(String.format("('%s', '%s', '%s', %d, '%s', %d, '%s')",
                                film.getIdentifier(), film.getTitle(), film.getDirector(), film.getReleaseYear(),
                                film.getDistributor(), film.getDurationMinutes(), film.getImageUrl())));
        if (filmInsertResult.isEmpty()) { return DatabaseConstant.INSERTION_ERROR; }
        for (ItemStock stock : film.getStockAvailable().getItemStock()) {
            Optional<Integer> filmStockResult = connection.executeUpdate(
                    QueryBuilder.createQuery(QueryType.INSERT_ONE)
                            .insertInto("library.item_copies (item_id, item_subtype_id, copies_available)")
                            .values(String.format("('%s', %d, %d)",
                                    film.getIdentifier(), stock.getItemFormat().getIdentifier(),
                                    stock.getCopiesAvailable())));
            if (filmStockResult.isEmpty()) { return DatabaseConstant.INSERTION_ERROR; }
        }
        return DatabaseConstant.INSERTION_SUCCESSFUL;
    }

    /**
     * A method to insert a book and its item stock into the data source.
     * @param book the book to inserted.
     * @return a DatabaseConstant enumeration of either INSERTION_ERROR if the operation failed or if the operation
     * succeeded returns it returns INSERTION_SUCCESSFUL.
     */
    public DatabaseConstant insert(Book book) {
        DatabaseConnection connection = new DatabaseConnection();
        connection.establish(this.credentials);
        Optional<Integer> filmInsertResult = connection.executeUpdate(
                QueryBuilder.createQuery(QueryType.INSERT_ONE)
                        .insertInto("library.books")
                        .values(String.format("('%s', '%s', '%s', %d, '%s', '%s')",
                                book.getIsbn(), book.getTitle(), book.getAuthor(), book.getPublicationYear(),
                                book.getPublisher(), book.getImageUrl())));
        if (filmInsertResult.isEmpty()) { return DatabaseConstant.INSERTION_ERROR; }
        for (ItemStock stock : book.getStockAvailable().getItemStock()) {
            Optional<Integer> filmStockResult = connection.executeUpdate(
                    QueryBuilder.createQuery(QueryType.INSERT_ONE)
                            .insertInto("library.item_copies (item_id, item_subtype_id, copies_available)")
                            .values(String.format("('%s', %d, %d)",
                                    book.getIsbn(), stock.getItemFormat().getIdentifier(),
                                    stock.getCopiesAvailable())));
            if (filmStockResult.isEmpty()) { return DatabaseConstant.INSERTION_ERROR; }
        }
        return DatabaseConstant.INSERTION_SUCCESSFUL;
    }

}
