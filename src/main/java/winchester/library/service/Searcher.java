package winchester.library.service;

import java.util.concurrent.atomic.AtomicReference;
import winchester.library.data.model.items.Item;

/**
 * A class that searches through lists of entities.
 */
public class Searcher {

    public Searcher() {

    }

    public Item searchItemFromIdentifier(String identifier) {
        AtomicReference<Item> searchedItem = new AtomicReference<>();
        DataPersistenceManager.getInstance().getBooks()
                .stream()
                .filter(book -> book.getIsbn().equals(identifier))
                .forEach(searchedItem::set);
        DataPersistenceManager.getInstance().getFilms()
                .stream()
                .filter(film -> film.getIdentifier().equals(identifier))
                .forEach(searchedItem::set);
        return searchedItem.get();
    }

}
