package winchester.library.data.model.items;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ItemFormatTest {

    @Test
    void fromIdentifier() {
        Assertions.assertEquals(ItemFormat.fromIdentifier(11).orElse(null), ItemFormat.AUDIO_BOOK);
        Assertions.assertEquals(ItemFormat.fromIdentifier(12).orElse(null), ItemFormat.PHYSICAL_BOOK);
        Assertions.assertEquals(ItemFormat.fromIdentifier(13).orElse(null), ItemFormat.LARGE_PRINT_BOOK);
        Assertions.assertEquals(ItemFormat.fromIdentifier(14).orElse(null), ItemFormat.ELECTRONIC_BOOK);
        Assertions.assertEquals(ItemFormat.fromIdentifier(21).orElse(null), ItemFormat.DVD_FILM);
        Assertions.assertEquals(ItemFormat.fromIdentifier(22).orElse(null), ItemFormat.BLU_RAY_FILM);
    }

    @Test
    void getIdentifier() {
        Assertions.assertEquals(ItemFormat.AUDIO_BOOK.getIdentifier(), 11);
        Assertions.assertEquals(ItemFormat.PHYSICAL_BOOK.getIdentifier(), 12);
        Assertions.assertEquals(ItemFormat.LARGE_PRINT_BOOK.getIdentifier(), 13);
        Assertions.assertEquals(ItemFormat.ELECTRONIC_BOOK.getIdentifier(), 14);
        Assertions.assertEquals(ItemFormat.DVD_FILM.getIdentifier(), 21);
        Assertions.assertEquals(ItemFormat.BLU_RAY_FILM.getIdentifier(), 22);
    }
}