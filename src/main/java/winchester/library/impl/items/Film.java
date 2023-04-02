package winchester.library.impl.items;

public class Film extends Item {
    private final FilmFormat format;
    private final String director;
    private final String leadActor;
    private final int durationMinutes;

    public Film(int identifier, FilmFormat format, String name, int releaseYear, String director, String leadActor,
                int durationMinutes) {
        super(identifier, name, releaseYear);
        this.format = format;
        this.director = director;
        this.leadActor = leadActor;
        this.durationMinutes = durationMinutes;
    }

    public FilmFormat getFormat() {
        return this.format;
    }

    public String getDirector() {
        return this.director;
    }

    public String getLeadActor() {
        return this.leadActor;
    }

    public int getDurationInMinutes() {
        return this.durationMinutes;
    }

    public double getDurationInHours() {
        return this.durationMinutes / 60.0;
    }

    @Override
    public ItemType getType() {
        return ItemType.FILM;
    }
}
