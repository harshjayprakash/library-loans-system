package winchester.library.data.access;

/**
 * A set of values to provide an easy way to show whether the program is connected to the database.
 */
public enum DatabaseStatus {
    CONNECTED(1, "Connected to data source"),
    NOT_CONNECTED(2, "Data source not found");

    private final int identifier;
    private final String value;

    DatabaseStatus(int identifier, String value) {
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
