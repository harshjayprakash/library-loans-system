package winchester.library.data.access;

import winchester.library.data.model.items.Book;
import winchester.library.data.model.items.Film;
import winchester.library.data.model.users.Administrator;
import winchester.library.data.model.users.Customer;
import winchester.library.data.model.users.Employee;

import java.util.function.Function;

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

    public void insert(Employee employee) {

    }

    public void insert(Administrator administrator) {

    }

}
