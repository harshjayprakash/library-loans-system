package winchester.library.data.access;

/**
 * A set of constant values to provide an easy way to show whether the program is connected to the database.
 */
public enum DatabaseStatus {
    CONNECTED(1, "Connected to data source"),
    NOT_CONNECTED(2, "Data source not found");

    private final int identifier;
    private final String value;

    /**
     * A constructor that allows extra information to be stored in this enumeration such as an identifier and string
     * value.
     * @param identifier the identifier.
     * @param value an equivalent string value.
     */
    DatabaseStatus(int identifier, String value) {
        this.identifier = identifier;
        this.value = value;
    }

    /**
     * An accessor that retrieves the identifier corresponding to the constant.
     * @return the identifier corresponding to the constant.
     */
    public int getIdentifier() {
        return this.identifier;
    }

    /**
     * An accessor that retrieves the string value assigned to the constant
     * @return the string value corresponding to the constant.
     */
    @Override
    public String toString() {
        return this.value;
    }
}
