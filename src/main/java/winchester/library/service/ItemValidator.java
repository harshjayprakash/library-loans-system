package winchester.library.service;

import winchester.library.data.model.items.Book;
import winchester.library.data.model.items.Film;

/**
 * A class to check item information.
 */
public class ItemValidator {

    /**
     * The default constructor
     */
    public ItemValidator() { }

    /**
     * Checks whether the isbn is valid by checking the length. This is for a 13 digit isbn.
     * @param isbn the isbn to be checked.
     * @return true if the isbn is the valid length.
     */
    public boolean validIsbn(String isbn) {
        return isbn.length() == 13;
    }

    /**
     * Checks if an isbn already exists.
     * @param isbn the isbn to be checked.
     * @return true if the book already exists in the data source.
     */
    public boolean isbnExists(String isbn) {
        for (Book book : DataPersistenceManager.getInstance().getBooks()) {
            if (book.getIsbn().equals(isbn)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if a film already exists.
     * @param filmToCheck the film to be checked.
     * @return true if the film already exists in the data source.
     */
    public boolean filmExists(Film filmToCheck) {
        for (Film film : DataPersistenceManager.getInstance().getFilms()) {
            if (film.getTitle().equals(filmToCheck.getTitle())
                    && film.getDirector().equals(filmToCheck.getDirector())
                    && film.getReleaseYear() == filmToCheck.getReleaseYear()) {
                return true;
            }
        }
        return false;
    }

}
