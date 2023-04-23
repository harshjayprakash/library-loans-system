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

/**
 * A class that has the ability to map the returned ResultSet from the database connection to the correct list of
 * objects.
 */
public class DataMapper {

    public DataMapper() { }

    public Optional<ArrayList<Book>> mapAsBooks(ResultSet data) {
        ArrayList<Book> books = new ArrayList<>();
        try {
            while (data.next()) {
                Book individualBook = new Book(
                        data.getString("isbn"), data.getString("title"), data.getString("author"),
                        data.getInt("publication_year"), data.getString("publisher"), data.getString("image_url"));
                books.add(individualBook);
            }
        }
        catch (SQLException exception) {
            System.err.printf("%s : %s%n", DatabaseConstant.NOT_ACCESSIBLE, exception.getMessage());
            return Optional.empty();
        }
        catch (Exception exception) {
            System.err.printf("%s : %s%n", DatabaseConstant.UNKNOWN_ERROR, exception.getMessage());
            return Optional.empty();
        }
        return Optional.of(books);
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
        }
        catch (SQLException exception) {
            System.err.printf("%s : %s%n", DatabaseConstant.NOT_ACCESSIBLE, exception.getMessage());
            return Optional.empty();
        }
        catch (Exception exception) {
            System.err.printf("%s : %s%n", DatabaseConstant.UNKNOWN_ERROR, exception.getMessage());
            return Optional.empty();
        }
        return Optional.of(itemStock);
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
        }
        catch (SQLException exception) {
            System.err.printf("%s : %s%n", DatabaseConstant.NOT_ACCESSIBLE, exception.getMessage());
            return Optional.empty();
        }
        catch (Exception exception) {
            System.err.printf("%s : %s%n", DatabaseConstant.UNKNOWN_ERROR, exception.getMessage());
            return Optional.empty();
        }
        return Optional.of(films);
    }

}
