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

    public static Film castFrom(Item item) {
        return (Film) item;
    }

    public String getIdentifier() {
        return this.identifier;
    }

    public String getTitle() {
        return this.title;
    }

    public String getDirector() {
        return this.director;
    }

    public int getReleaseYear() {
        return this.releaseYear;
    }

    public String getDistributor() {
        return this.distributor;
    }

    public int getDurationMinutes() {
        return this.durationMinutes;
    }

    public double getDurationHours() {
        return this.durationMinutes / 60.0;
    }

    public String getDurationToString() {
        return String.format("%d hours and %d minutes", this.durationMinutes / 60, this.durationMinutes % 60);
    }

    @Override
    public ItemType getType() {
        return ItemType.FILM;
    }

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
                """, this.toString(), itemCopies.toString(), this.getLoansManager().getEarliestReturnDate().toString());
    }
}
