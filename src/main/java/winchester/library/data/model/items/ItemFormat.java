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

    /**
     * A constructor to provide more information such as an identifier and a string value.
     * @param identifier an identifier that can be referred to.
     * @param value a string value of the constant.
     */
    ItemFormat(int identifier, String value) {
        this.identifier = identifier;
        this.value = value;
    }

    /**
     * A method to find the ItemFormat constant based on the identifier.
     * @param identifier the identifier of the ItemFormat to be found.
     * @return an optional of ItemFormat if it corresponds to a constant or empty if not.
     */
    public static Optional<ItemFormat> fromIdentifier(int identifier) {
        for (ItemFormat format : ItemFormat.values()) {
            if (format.identifier == identifier) {
                return Optional.of(format);
            }
        }
        return Optional.empty();
    }

    /**
     * An accessor to retrieve the identifier of the ItemFormat constant.
     * @return the identifier of the ItemFormat constant.
     */
    public int getIdentifier() {
        return this.identifier;
    }

    /**
     * An accessor to retrieve the corresponding string value assigned to the ItemFormat.
     * @return the string value associated to the constant.
     */
    @Override
    public String toString() {
        return this.value;
    }
}
