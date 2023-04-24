package winchester.library.data.access;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import winchester.library.data.model.items.Book;
import winchester.library.data.model.items.Film;
import winchester.library.data.model.items.ItemFormat;
import winchester.library.data.model.items.ItemStock;
import winchester.library.data.model.items.ItemType;
import winchester.library.data.model.users.Customer;
import winchester.library.data.model.users.Employee;
import winchester.library.service.ConsolePrinter;

/**
 * A class that has the ability to map the returned ResultSet from the database connection to the correct list of
 * objects.
 */
public class DataMapper {

    private final ConsolePrinter consolePrinter;

    public DataMapper() {
        this.consolePrinter = ConsolePrinter.getInstance();
    }

    public Optional<ArrayList<Book>> mapAsBooks(ResultSet data) {
        ArrayList<Book> books = new ArrayList<>();
        try {
            while (data.next()) {
                Book individualBook = new Book(
                        data.getString("isbn"), data.getString("title"), data.getString("author"),
                        data.getInt("publication_year"), data.getString("publisher"), data.getString("image_url"));
                books.add(individualBook);
            }
            return Optional.of(books);
        }
        catch (NullPointerException exception) {
            consolePrinter.WriteLineError(DatabaseConstant.DATA_NOT_ACCESSIBLE.toString(), exception.getMessage());
        }
        catch (SQLException exception) {
            consolePrinter.WriteLineError(DatabaseConstant.DATABASE_NOT_ACCESSIBLE.toString(), exception.getMessage());
        }
        catch (Exception exception) {
            consolePrinter.WriteLineError(DatabaseConstant.UNKNOWN_ERROR.toString(), exception.getMessage());
        }
        return Optional.empty();
    }

    public Optional<ArrayList<ItemStock>> mapAsItemStock(ResultSet data, ItemType type) {
        ArrayList<ItemStock> itemStock = new ArrayList<>();
        try {
            while (data.next()) {
                ItemStock individualStock = new ItemStock(
                        switch (type) {
                            case BOOK -> data.getString("isbn");
                            case FILM -> data.getString("film_id");
                        },
                        ItemFormat.getFromIdentifier(data.getInt("item_subtype_id")).orElse(null),
                        data.getInt("copies_available"),
                        0);
                itemStock.add(individualStock);
            }
            return Optional.of(itemStock);
        }
        catch (NullPointerException exception) {
            consolePrinter.WriteLineError(DatabaseConstant.DATA_NOT_ACCESSIBLE.toString(), exception.getMessage());
        }
        catch (SQLException exception) {
            consolePrinter.WriteLineError(DatabaseConstant.DATABASE_NOT_ACCESSIBLE.toString(), exception.getMessage());
        }
        catch (Exception exception) {
            consolePrinter.WriteLineError(DatabaseConstant.UNKNOWN_ERROR.toString(), exception.getMessage());
        }
        return Optional.empty();
    }

    public Optional<ArrayList<Film>> mapAsFilms(ResultSet data) {
        ArrayList<Film> films = new ArrayList<>();
        try {
            while (data.next()) {
                Film individualFilm = new Film(
                        data.getString("film_id"), data.getString("title"), data.getString("director"),
                        data.getInt("release_year"), data.getString("distributor"), data.getInt("duration_minutes"),
                        data.getString("image_url"));
                films.add(individualFilm);
            }
            return Optional.of(films);
        }
        catch (NullPointerException exception) {
            consolePrinter.WriteLineError(DatabaseConstant.DATA_NOT_ACCESSIBLE.toString(), exception.getMessage());
        }
        catch (SQLException exception) {
            consolePrinter.WriteLineError(DatabaseConstant.DATABASE_NOT_ACCESSIBLE.toString(), exception.getMessage());
        }
        catch (Exception exception) {
            consolePrinter.WriteLineError(DatabaseConstant.UNKNOWN_ERROR.toString(), exception.getMessage());
        }
        return Optional.empty();
    }

    public Optional<ArrayList<Customer>> mapAsCustomers(ResultSet data) {
        ArrayList<Customer> customers = new ArrayList<>();
        try {
            while (data.next()) {
                Customer individualCustomer = new Customer(
                        data.getInt("user_id"), data.getString("first_name"), 
                        data.getString("last_name"), data.getString("postal_code"));
                customers.add(individualCustomer);
            }
            return Optional.of(customers);
        }
        catch (NullPointerException exception) {
            consolePrinter.WriteLineError(DatabaseConstant.DATA_NOT_ACCESSIBLE.toString(), exception.getMessage());
        }
        catch (SQLException exception) {
            consolePrinter.WriteLineError(DatabaseConstant.DATABASE_NOT_ACCESSIBLE.toString(), exception.getMessage());
        }
        catch (Exception exception) {
            consolePrinter.WriteLineError(DatabaseConstant.UNKNOWN_ERROR.toString(), exception.getMessage());
        }
        return Optional.empty();
    }

    public Optional<ArrayList<Employee>> mapAsEmployees(ResultSet data) {
        ArrayList<Employee> employees = new ArrayList<>();
        return Optional.of(employees);
    }

}
