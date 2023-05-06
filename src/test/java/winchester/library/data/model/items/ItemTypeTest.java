package winchester.library.data.model.items;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ItemTypeTest {

    @Test
    void fromIdentifier() {
        Assertions.assertEquals(ItemType.fromIdentifier(1).orElse(null), ItemType.BOOK);
        Assertions.assertEquals(ItemType.fromIdentifier(2).orElse(null), ItemType.FILM);
    }

    @Test
    void getIdentifier() {
        Assertions.assertEquals(ItemType.BOOK.getIdentifier(), 1);
        Assertions.assertEquals(ItemType.FILM.getIdentifier(), 2);
    }
}