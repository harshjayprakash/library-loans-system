package winchester.library.data.model.items;

import java.util.Optional;
import winchester.library.data.access.DatabaseEntity;

/**
 * An enumeration to represent the categories of items available.
 */
@DatabaseEntity(table = "item_types")
public enum ItemType {
    BOOK(1, "Book"),
    FILM(2, "Film");

    private final int identifier;
    private final String value;

    /**
     * A constructor to provided additional information to each constant such as an identifier and a string value.
     * @param identifier an identifier that can be referred to.
     * @param value a string value of the constant.
     */
    ItemType(int identifier, String value) {
        this.identifier = identifier;
        this.value = value;
    }

    /**
     * Finds the ItemType constant specified by passed in identifier.
     * @param identifier the identifier of ItemType to be found.
     * @return an Optional version of ItemType, including the constant if found else returns empty.
     */
    public static Optional<ItemType> fromIdentifier(int identifier) {
        for (ItemType type : ItemType.values()) {
            if (type.identifier == identifier) {
                return Optional.of(type);
            }
        }
        return Optional.empty();
    }

    /**
     * An accessor to retrieve the identifier corresponding to the ItemType constant.
     * @return an identifier corresponding to the constant.
     */
    public int getIdentifier() {
        return this.identifier;
    }

    /**
     * An accessor to retrieve the string value corresponding to the ItemType constant.
     * @return a string value corresponding to the constant.
     */
    @Override
    public String toString() {
        return this.value;
    }
}
