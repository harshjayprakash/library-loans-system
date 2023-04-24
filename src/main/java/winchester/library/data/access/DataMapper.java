package winchester.library.data.access;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.function.Function;
import winchester.library.data.model.items.Book;
import winchester.library.data.model.items.Film;
import winchester.library.data.model.items.ItemFormat;
import winchester.library.data.model.items.ItemStock;
import winchester.library.data.model.items.ItemType;
import winchester.library.data.model.users.Customer;
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

    private <T> Optional<ArrayList<T>> mapToList(ResultSet data, Function<ResultSet, T> mapFunction) {
        ArrayList<T> entityList = new ArrayList<>();
        try {
            while (data.next()) {
                T entity = mapFunction.apply(data);
                if (entity != null) {
                    entityList.add(entity);
                }
            }
            return Optional.of(entityList);
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

    public Optional<ArrayList<Book>> mapAsBooks(ResultSet data) {
        return this.mapToList(data, result -> {
            try {
                return new Book(
                        result.getString("isbn"), result.getString("title"), result.getString("author"),
                        result.getInt("publication_year"), result.getString("publisher"),
                        result.getString("image_url"));
            }
            catch (SQLException exception) {
                consolePrinter.WriteLineError(DatabaseConstant.DATA_NOT_ACCESSIBLE.toString(), exception.getMessage());
                return null;
            }
        });
    }

    public Optional<ArrayList<Film>> mapAsFilms(ResultSet data) {
        return this.mapToList(data, result -> {
            try {
                return new Film(
                        result.getString("film_id"), result.getString("title"), result.getString("director"),
                        result.getInt("release_year"), result.getString("distributor"),
                        result.getInt("duration_minutes"), result.getString("image_url"));
            }
            catch (SQLException exception) {
                consolePrinter.WriteLineError(DatabaseConstant.DATA_NOT_ACCESSIBLE.toString(), exception.getMessage());
                return null;
            }
        });
    }

    public Optional<ArrayList<Customer>> mapAsCustomers(ResultSet data) {
        return this.mapToList(data, result -> {
            try {
                return new Customer(
                        result.getInt("user_id"), result.getString("first_name"),
                        result.getString("last_name"), result.getString("postal_code"));
            }
            catch (SQLException exception) {
                consolePrinter.WriteLineError(DatabaseConstant.DATA_NOT_ACCESSIBLE.toString(), exception.getMessage());
                return null;
            }
        });
    }

    public Optional<ArrayList<ItemStock>> mapAsItemStock(ResultSet data, ItemType type) {
        return this.mapToList(data, result -> {
            try {
                return new ItemStock (
                        switch (type) {
                            case BOOK -> result.getString("isbn");
                            case FILM -> result.getString("film_id");
                        },
                        ItemFormat.getFromIdentifier(result.getInt("item_subtype_id")).orElse(null),
                        result.getInt("copies_available"),
                        0);
            }
            catch (SQLException exception) {
                consolePrinter.WriteLineError(DatabaseConstant.DATA_NOT_ACCESSIBLE.toString(), exception.getMessage());
                return null;
            }
        });
    }

}
