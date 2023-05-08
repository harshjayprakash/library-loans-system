package winchester.library.data.model.items;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BookTest {

    Book testBook;

    BookTest() {
        testBook = new Book(
                "9780063021426",
                "Babel: An Arcane History",
                "R.F. Kuang",
                2022,
                "Harper Voyager",
                "Image Url");
    }

    @Test
    void getIsbn() {
        Assertions.assertEquals(testBook.getIsbn(), "9780063021426");
    }

    @Test
    void getTitle() {
        Assertions.assertEquals(testBook.getTitle(), "Babel: An Arcane History");
    }

    @Test
    void getAuthor() {
        Assertions.assertEquals(testBook.getAuthor(), "R.F. Kuang");
    }

    @Test
    void getPublicationYear() {
        Assertions.assertEquals(testBook.getPublicationYear(), 2022);
    }

    @Test
    void getPublisher() {
        Assertions.assertEquals(testBook.getPublisher(), "Harper Voyager");
    }

    @Test
    void getType() {
        Assertions.assertEquals(testBook.getType(), ItemType.BOOK);
    }
}