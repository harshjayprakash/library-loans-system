package winchester.library.data.model.items;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FilmTest {

    Film testFilm;

    FilmTest() {
        testFilm = new Film(
                "2305001", "Dungeons & Dragons: Honor Among Thieves",
                "Jonathan M. Goldstein, John Francis Daley",
                2023,
                "Paramount Pictures",
                134,
                "Image Url");
    }

    @Test
    void getIdentifier() {
        Assertions.assertEquals(testFilm.getIdentifier(), "2305001");
    }

    @Test
    void getTitle() {
        Assertions.assertEquals(testFilm.getTitle(), "Dungeons & Dragons: Honor Among Thieves");
    }

    @Test
    void getDirector() {
        Assertions.assertEquals(testFilm.getDirector(), "Jonathan M. Goldstein, John Francis Daley");
    }

    @Test
    void getReleaseYear() {
        Assertions.assertEquals(testFilm.getReleaseYear(), 2023);
    }

    @Test
    void getDistributor() {
        Assertions.assertEquals(testFilm.getDistributor(), "Paramount Pictures");
    }

    @Test
    void getDurationMinutes() {
        Assertions.assertEquals(testFilm.getDurationMinutes(), 134);
    }

    @Test
    void getDurationHours() {
        Assertions.assertEquals(testFilm.getDurationHours(), 2.2333333333333334);
    }

    @Test
    void getDurationToString() {
        Assertions.assertEquals(testFilm.getDurationToString(), "2 hours and 14 minutes");
    }

    @Test
    void getType() {
        Assertions.assertEquals(testFilm.getType(), ItemType.FILM);
    }
}