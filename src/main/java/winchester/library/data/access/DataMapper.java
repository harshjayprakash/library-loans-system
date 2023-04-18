package winchester.library.data.access;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Optional;
import winchester.library.data.model.items.Book;
import winchester.library.data.model.items.Film;

public class DataMapper {

    public DataMapper() { }

    public Optional<HashSet<Book>> mapAsBooks(ResultSet data) {
        HashSet<Book> books = new HashSet<>();
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

    public Optional<HashSet<Film>> mapAsFilms(ResultSet data) {
        HashSet<Film> films = new HashSet<>();
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
