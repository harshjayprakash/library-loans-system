package winchester.library.data.model.items;

import java.util.Optional;
import winchester.library.data.access.DatabaseEntity;

/**
 * An enumeration to represent all the different forms that the items are available in.
 */
@DatabaseEntity(table = "item_subtypes")
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

    public static Optional<ItemFormat> fromIdentifier(int identifier) {
        for (ItemFormat format : ItemFormat.values()) {
            if (format.identifier == identifier) {
                return Optional.of(format);
            }
        }
        return Optional.empty();
    }

    public int getIdentifier() {
        return this.identifier;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
