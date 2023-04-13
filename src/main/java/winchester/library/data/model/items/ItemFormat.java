package winchester.library.data.model.items;

public enum ItemFormat {
    AUDIO_BOOK(11, "Audio Book"),
    PHYSICAL_BOOK(12, "Physical Book"),
    LARGE_PRINT_BOOK(13, "Large Print Book"),
    ELECTRONIC_BOOK(14, "Electronic Book"),
    DVD_FILM(21, "DVD"),
    BLU_RAY_FILM(22, "Blu-Ray");

    private final int identifier;
    private final String value;

    ItemFormat(int identifier, String value) {
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
