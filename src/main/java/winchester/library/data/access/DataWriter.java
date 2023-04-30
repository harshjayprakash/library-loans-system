package winchester.library.data.access;

import java.util.Optional;
import winchester.library.data.model.items.Book;
import winchester.library.data.model.items.Film;
import winchester.library.data.model.users.Administrator;
import winchester.library.data.model.users.Customer;
import winchester.library.data.model.users.Employee;

/**
 * A class that abstracts the method of creating a new record within the database.
 */
public class DataWriter {

    private final DatabaseCredentials credentials;

    public DataWriter() {
        this.credentials = DatabaseCredentials.getInstance();
    }

    public void insert(Book book) {

    }

    public void insert(Film film) {

    }

    public void insert(Customer customer) {

    }

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
                        .insertInto("library.employees (user_id, username, password, status_id)")
                        .values(String.format("(%d, '%s', '%s', %d)",
                                employee.getIdentifier(), employee.getUsername(), employee.getPassword(),
                                employee.getStatus().getIdentifier()))
        );
        if (employeeInsertResult.isEmpty()) {
            return DatabaseConstant.INSERTION_ERROR;
        }
        connection.close();
        return DatabaseConstant.INSERTION_SUCCESSFUL;
    }

    public DatabaseConstant insert(Administrator administrator) {
        DatabaseConnection connection = new DatabaseConnection();
        connection.establish(this.credentials);
        Optional<Integer> userInsertResult = connection.executeUpdate(
                QueryBuilder.createQuery(QueryType.INSERT_ONE)
                        .insertInto("library.users")
                        .values(String.format("(%d, %d, %s, %s, %s)",
                                administrator.getIdentifier(), administrator.getType().getIdentifier(), administrator.getFirstName(),
                                administrator.getLastName(), administrator.getPostalCode())));
        if (userInsertResult.isEmpty()) {
            return DatabaseConstant.INSERTION_ERROR;
        }
        Optional<Integer> employeeInsertResult = connection.executeUpdate(
                QueryBuilder.createQuery(QueryType.INSERT_ONE)
                        .insertInto("library.employees (user_id, username, password, status_id)")
                        .values(String.format("(%d, %s, %s, %d)",
                                administrator.getIdentifier(), administrator.getUsername(), administrator.getPassword(),
                                administrator.getStatus().getIdentifier()))
        );
        if (employeeInsertResult.isEmpty()) {
            return DatabaseConstant.INSERTION_ERROR;
        }
        connection.close();
        return DatabaseConstant.INSERTION_SUCCESSFUL;
    }

}
