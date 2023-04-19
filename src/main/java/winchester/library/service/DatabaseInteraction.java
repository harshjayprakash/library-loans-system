package winchester.library.service;

import java.util.ArrayList;
import java.util.Optional;
import winchester.library.data.access.*;
import winchester.library.data.model.items.Book;



public class DatabaseInteraction {

    private static DatabaseInteraction instance;
    private final DatabaseCredentials credentials;
    private final DatabaseConnectionTester connectionTester;
    private final DataMapper dataMapper;

    private DatabaseInteraction() {
        this.credentials = DatabaseCredentials.getInstance();
        this.connectionTester = DatabaseConnectionTester.getInstance();
        this.dataMapper = new DataMapper();
    }

    public static DatabaseInteraction getInstance() {
        if (DatabaseInteraction.instance == null) {
            DatabaseInteraction.instance = new DatabaseInteraction();
        }
        return DatabaseInteraction.instance;
    }

    public boolean getDatabaseAvailable() {
        return connectionTester.testCredentials(credentials) == DatabaseConstant.CONNECTION_SUCCESSFUL;
    }

    public DatabaseConstant getDatabaseStatus() {
        return connectionTester.testCredentials(credentials);
    }

    public Optional<ArrayList<Book>> getBooks() {
        DatabaseConnection connection = new DatabaseConnection();
        connection.establish(credentials);
        QueryBuilder query = QueryBuilder.createQuery(QueryType.GET_AND_FILTER)
                .select("isbn", "title", "author", "publication_year", "publisher", "image_url", "item_subtype_id",
                        "copies_available")
                .from("library.books", "library.item_copies")
                .where("library.books.isbn = library.item_copies.item_id");
        Optional<ArrayList<Book>> books = dataMapper.mapAsBooks(connection.executeQuery(query).orElse(null));
        connection.close();
        return books;
    }

}
