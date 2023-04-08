package winchester.library.data.model.items;

public enum BookFormat {
    AUDIO(11, "Audio Book"),
    PHYSICAL(12, "Physical Book"),
    LARGE_PRINT(13, "Large Print Book"),
    ELECTRONIC(14, "Electronic Book");

    private final int identifier;
    private final String text;

    BookFormat(int identifier, String text) {
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
