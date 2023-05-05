package winchester.library.data.model.items;

import winchester.library.data.access.DatabaseEntity;
import winchester.library.data.model.util.Exportable;

/**
 * A class that models the film entity.
 */
@DatabaseEntity(table = "films")
public class Film extends Item implements Exportable {

    public final String identifier;
    private final String title;
    private final String director;
    private final int releaseYear;
    private final String distributor;
    private final int durationMinutes;

    /**
     * The default constructor for the Film class to specify the film information.
     * @param identifier the identifier of the film.
     * @param title the title of the film.
     * @param director the director of the film.
     * @param releaseYear the release year of the film.
     * @param distributor the distributor of the film.
     * @param durationMinutes the duration of the film in minutes.
     * @param imageUrl the image url of the cover of the film.
     */
    public Film(String identifier, String title, String director, int releaseYear, String distributor, int durationMinutes,
                String imageUrl) {
        this.identifier = identifier;
        this.title = title;
        this.director = director;
        this.releaseYear = releaseYear;
        this.distributor = distributor;
        this.durationMinutes = durationMinutes;
        this.imageUrl = imageUrl;
    }

    /**
     * Casts the base Item class type to a Book class type.
     * @param item the item to be cast.
     * @return the book cast from the specified item.
     */
    public static Film castFrom(Item item) {
        return (Film) item;
    }

    /**
     * An accessor to retrieve the identifier of the film.
     * @return the identifier of the film.
     */
    public String getIdentifier() {
        return this.identifier;
    }

    /**
     * An accessor to retrieve the title of the film.
     * @return the title of the film.
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * An accessor to retrieve the director of the film.
     * @return the director of the film.
     */
    public String getDirector() {
        return this.director;
    }

    /**
     * An accessor to retrieve the release year of the film.
     * @return the release year of the film.
     */
    public int getReleaseYear() {
        return this.releaseYear;
    }

    /**
     * An accessor to retrieve the distributor of the film.
     * @return the distributor of the film.
     */
    public String getDistributor() {
        return this.distributor;
    }

    /**
     * An accessor to retrieve the duration of the film in minutes.
     * @return the duration of the film in minutes.
     */
    public int getDurationMinutes() {
        return this.durationMinutes;
    }

    /**
     * An accessor to retrieve the duration of the film in hours.
     * @return the duration of the film in hours.
     */
    public double getDurationHours() {
        return this.durationMinutes / 60.0;
    }

    /**
     * An accessor to retrieve the duration as a human-readable format.
     * @return the duration in a human-readable format.
     */
    public String getDurationToString() {
        return String.format("%d hours and %d minutes", this.durationMinutes / 60, this.durationMinutes % 60);
    }

    /**
     * An accessor to retrieve the type of the item.
     * @return an ItemType enumeration of FILM.
     */
    @Override
    public ItemType getType() {
        return ItemType.FILM;
    }

    /**
     * Converts all information to a string format.
     * @return a string of all the film information.
     */
    @Override
    public String toString() {
        return String.format(
                """
                Resource : Film
                
                Identifier : %s
                Title : %s
                Director : %s
                Release Year : %s
                Distributor : %s
                Duration : %s
                """, this.identifier, this.title, this.director, this.releaseYear, this.distributor,
                this.getDurationToString());
    }

    /**
     * Converts all information to a string format that can be exported to an external file.
     * @return a string of film and loan information.
     */
    @Override
    public String export() {
        StringBuilder itemCopies = new StringBuilder();
        for (ItemStock stock : this.getStockAvailable()) {
            itemCopies.append(stock.toString());
        }
        return String.format(
                """
                %s
                
                Copies:
                %s
                
                Earliest Return : %s
                """, this.toString(), itemCopies.toString(),
                (this.getLoansManager().getEarliestReturnDate().isEmpty())
                        ? "None" : this.getLoansManager().getEarliestReturnDate().get());
    }
}
