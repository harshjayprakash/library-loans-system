package winchester.library.data.access;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Optional;
import winchester.library.data.model.items.Book;
import winchester.library.data.model.items.Film;

public class DatabaseConnectionManager {
    
    private final static DatabaseConnectionManager instance = new DatabaseConnectionManager();

    private DatabaseConnectionManager() {

    }

    public static DatabaseConnectionManager getInstance() {
        return DatabaseConnectionManager.instance;
    }

    public int test(DatabaseCredentialsManager credentials) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    credentials.getUrl(), credentials.getUsername(), credentials.getPassword());
            connection.close();
        }
        catch (ClassNotFoundException exception) {
            return DatabaseConstant.DRIVER_NOT_FOUND.getIdentifier();
        }
        catch (SQLException exception) {
            return DatabaseConstant.NOT_ACCESSIBLE.getIdentifier();
        }
        catch (Exception exception) {
            System.out.println(exception.getMessage());
            return DatabaseConstant.UNKNOWN_ERROR.getIdentifier();
        }
        return DatabaseConstant.CONNECTION_SUCCESSFUL.getIdentifier();
    }

    public Optional<ArrayList<Book>> getBooks(DatabaseCredentialsManager credentials) {
        ArrayList<Book> books = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    credentials.getUrl(), credentials.getUsername(), credentials.getPassword());
            Statement statement = connection.createStatement();
            ResultSet data = statement.executeQuery(QueryBuilder.createQuery(SQLQueryType.GET_AND_FILTER)
                    .select("isbn", "title", "author", "publication_year", "publisher", "image_url", "item_subtype_id",
                            "copies_available")
                    .from("library.books", "library.item_copies")
                    .where("library.books.isbn = library.item_copies.item_id").toString());
            while (data.next()) {
                Book individualBook = new Book(
                        data.getString("isbn"), data.getString("title"), data.getString("author"),
                        data.getInt("publication_year"), data.getString("publisher"), data.getString("image_url"));
                books.add(individualBook);
            }
            connection.close();
        }
        catch (ClassNotFoundException exception) {
            System.out.printf("%s : %s%n", DatabaseConstant.DRIVER_NOT_FOUND.toString(), exception.getMessage());
            return Optional.empty();
        }
        catch (SQLException exception) {
            System.out.printf("%s : %s%n", DatabaseConstant.NOT_ACCESSIBLE.toString(), exception.getMessage());
            return Optional.empty();
        }
        catch (Exception exception) {
            System.out.printf("%s : %s%n", DatabaseConstant.UNKNOWN_ERROR.toString(), exception.getMessage());
            return Optional.empty();
        }
        return Optional.of(books);
    }

    public Optional<ArrayList<Film>> getFilms(DatabaseCredentialsManager credentials) {
        ArrayList<Film> films = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    credentials.getUrl(), credentials.getUsername(), credentials.getPassword());
            Statement statement = connection.createStatement();
            ResultSet data = statement.executeQuery(QueryBuilder.createQuery(SQLQueryType.GET_AND_FILTER)
                    .select("film_id", "title", "director", "release_year", "distributor", "duration_minutes", "image_url",
                            "item_subtype_id", "copies_available")
                    .from("library.films", "library.item_copies")
                    .where("library.films.films_id = library.item_copies.item_id").toString());
            while (data.next()) {
                Film individualFilm = new Film(
                        data.getString("film_id"), data.getString("title"), data.getString("director"),
                        data.getInt("release_year"), data.getString("distributor"), data.getInt("duration_minutes"),
                        data.getString("image_url"));
                films.add(individualFilm);
            }
            connection.close();
        }
        catch (ClassNotFoundException exception) {
            System.out.printf("%s : %s%n", DatabaseConstant.DRIVER_NOT_FOUND.toString(), exception.getMessage());
            return Optional.empty();
        }
        catch (SQLException exception) {
            System.out.printf("%s : %s%n", DatabaseConstant.NOT_ACCESSIBLE.toString(), exception.getMessage());
            return Optional.empty();
        }
        catch (Exception exception) {
            System.out.printf("%s : %s%n", DatabaseConstant.UNKNOWN_ERROR.toString(), exception.getMessage());
            return Optional.empty();
        }
        return Optional.of(films);
    }
}
