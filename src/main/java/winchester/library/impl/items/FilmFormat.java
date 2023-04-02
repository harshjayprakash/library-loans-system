package winchester.library.impl.items;

public enum FilmFormat {
    DVD(21, "DVD"),
    BLU_RAY(22, "Blu-Ray");

    private final int identifier;
    private final String text;

    FilmFormat(int identifier, String text) {
        this.identifier = identifier;
        this.text = text;
    }

    public int getIdentifier() {
        return this.identifier;
    }

    @Override
    public String toString() {
        return this.text;
    }
}
