package winchester.library.data.model.users;

import java.util.Optional;

/**
 * An enumeration to represent the types of users modelled within the system.
 */
public enum UserType {
    CUSTOMER(1, "Customer"),
    STANDARD(2, "Standard"),
    ADMINISTRATOR(3, "Administrator");

    private final int identifier;
    private final String text;

    /**
     * A constructor to provide additional information to each constant such as a string value and identifier.
     * @param identifier an identifier that can be referred to.
     * @param text a string value corresponding to the constant.
     */
    UserType(int identifier, String text) {
        this.identifier = identifier;
        this.text = text;
    }

    /**
     * A method to find the UserType constant based on the identifier.
     * @param identifier the identifier of the UserType to be found.
     * @return an optional of UserType if it corresponds to a constant or empty if not.
     */
    public static Optional<UserType> fromIdentifier(int identifier) {
        for (UserType type : UserType.values()) {
            if (type.identifier == identifier) {
                return Optional.of(type);
            }
        }
        return Optional.empty();
    }

    /**
     * An accessor to retrieve the identifier corresponding to the constant.
     * @return the identifier corresponding to the constant.
     */
    public int getIdentifier() {
        return this.identifier;
    }

    /**
     * An access to retrieve the string value corresponding to the constant.
     * @return the string value corresponding to the constant.
     */
    @Override
    public String toString() {
        return this.text;
    }
}
