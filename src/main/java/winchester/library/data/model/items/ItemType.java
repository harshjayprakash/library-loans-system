package winchester.library.data.model.items;

public enum ItemType {
    BOOK(1, "Book"),
    FILM(2, "Film");

    private final int identifier;
    private final String value;

    ItemType(int identifier, String value) {
        this.identifier = identifier;
        this.value = value;
    }

    public int getIdentifier() {
        return this.identifier;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
