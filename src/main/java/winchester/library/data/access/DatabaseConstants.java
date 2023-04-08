package winchester.library.data.access;

public enum DatabaseConstants {
    CONNECTION_SUCCESSFUL(0, "Database connection successful"),
    DRIVER_NOT_FOUND(1, "Failed to find MySQL JDBC Connector Driver"),
    NOT_ACCESSIBLE(2, "Error accessing the database"),
    UNKNOWN_ERROR(3, "Unknown Error");

    private final int identifier;
    private final String value;

    DatabaseConstants(int identifier, String value) {
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
