package winchester.library.data.model.items;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ItemStockTest {

    ItemStock testItemStock;

    ItemStockTest() {
        testItemStock = new ItemStock("2305001", ItemFormat.DVD_FILM, 10, 2);
    }

    @Test
    void getItemIdentifier() {
        Assertions.assertEquals(testItemStock.getItemIdentifier(), "2305001");
    }

    @Test
    void getItemFormat() {
        Assertions.assertEquals(testItemStock.getItemFormat(), ItemFormat.DVD_FILM);
    }

    @Test
    void getCopiesAvailable() {
        Assertions.assertEquals(testItemStock.getCopiesAvailable(), 10);
    }

    @Test
    void setCopiesAvailable() {
        testItemStock.setCopiesAvailable(20);
        Assertions.assertEquals(testItemStock.getCopiesAvailable(), 20);
    }

    @Test
    void getCopiesOnLoan() {
        Assertions.assertEquals(testItemStock.getCopiesOnLoan(), 2);
    }

    @Test
    void setCopiesOnLoan() {
        testItemStock.setCopiesOnLoan(5);
        Assertions.assertEquals(testItemStock.getCopiesOnLoan(), 5);
    }
}