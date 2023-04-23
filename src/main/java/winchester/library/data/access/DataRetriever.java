package winchester.library.data.access;

import java.util.ArrayList;
import winchester.library.data.model.items.Book;
import winchester.library.data.model.items.Film;
import winchester.library.data.model.items.ItemStock;
import winchester.library.data.model.items.ItemType;
import winchester.library.data.model.users.Customer;

public class DataRetriever {

    private final DatabaseCredentials credentials;
    private final DataMapper dataMapper;

    public DataRetriever() {
        this.credentials = DatabaseCredentials.getInstance();
        this.dataMapper = new DataMapper();
    }

    public ArrayList<Book> getBooks() {
        DatabaseConnection connection = new DatabaseConnection();
        connection.establish(credentials);
        ArrayList<Book> books = dataMapper.mapAsBooks(connection.executeQuery(
                QueryBuilder.createQuery(QueryType.GET)
                        .select("*")
                        .from("library.books")
        ).orElse(null)).orElse(new ArrayList<>());
        ArrayList<ItemStock> stocks = dataMapper.mapAsItemStock(connection.executeQuery(
                QueryBuilder.createQuery(QueryType.GET_AND_FILTER)
                        .select("isbn", "item_subtype_id", "copies_available")
                        .from("library.books", "library.item_copies")
                        .where("library.books.isbn = library.item_copies.item_id")
        ).orElse(null), ItemType.BOOK).orElse(new ArrayList<>());
        for (Book book : books) {
            for (ItemStock stock : stocks) {
                if (stock.getItemIdentifier().equals(book.getIsbn())) {
                    book.getStockAvailable().add(stock);
                }
            }
        }
        connection.close();
        return books;
    }

    public ArrayList<Film> getFilms() {
        DatabaseConnection connection = new DatabaseConnection();
        connection.establish(credentials);
        ArrayList<Film> films = dataMapper.mapAsFilms(connection.executeQuery(
                QueryBuilder.createQuery(QueryType.GET)
                       .select("*")
                       .from("library.films")
        ).orElse(null)).orElse(new ArrayList<>());
        ArrayList<ItemStock> stocks = dataMapper.mapAsItemStock(connection.executeQuery(
                QueryBuilder.createQuery(QueryType.GET_AND_FILTER)
                       .select("film_id", "item_subtype_id", "copies_available")
                       .from("library.films", "library.item_copies")
                       .where("library.films.film_id = library.item_copies.item_id")
        ).orElse(null), ItemType.FILM).orElse(new ArrayList<>());
        for (Film film : films) {
            for (ItemStock stock : stocks) {
                if (stock.getItemIdentifier().equals(film.getIdentifier())) {
                    film.getStockAvailable().add(stock);
                }
            }
        }
        connection.close();
        return films;
    }

    public ArrayList<Customer> getCustomers() {
        DatabaseConnection connection = new DatabaseConnection();
        connection.establish(credentials);
        ArrayList<Customer> customers = dataMapper.mapAsCustomers(connection.executeQuery(
                QueryBuilder.createQuery(QueryType.GET)
                      .select("users_id", "first_name", "last_name", "postal_code")
                      .from("library.users")
                      .where("library.users.user_type_id = 1")
        ).orElse(null)).orElse(new ArrayList<>());
        connection.close();
        return customers;
    }

}
