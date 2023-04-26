package winchester.library.data.model.items;

import winchester.library.data.access.DatabaseEntity;

@DatabaseEntity(table = "films")
public class Film extends Item {

    public final String identifier;
    private final String title;
    private final String director;
    private final int releaseYear;
    private final String distributor;
    private final int durationMinutes;
    private String imageUrl;

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

    public String getImageUrl() {
        return this.imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public ItemType getType() {
        return ItemType.FILM;
    }

    @Override
    public String toString() {
        StringBuilder itemCopies = new StringBuilder();
        for (ItemStock stock : this.getStockAvailable()) {
            itemCopies.append(stock.toString());
        }
        return String.format(
                """
                Resource : Book
                
                Identifier : %s
                Title : %s
                Director : %s
                Release Year : %d
                Distributor : %s
                Duration : %s
                
                Copies:
                %s
                
                Earliest Return : %s
                """, this.identifier, this.title, this.director, this.releaseYear, this.distributor,
                this.getDurationToString(), itemCopies.toString(), this.getEarliestReturnDate());
    }

    public static Film castFrom(Item item) {
        return (Film) item;
    }
}
