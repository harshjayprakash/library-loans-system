package winchester.library.data.access;

public enum DatabaseConstant {
    CONNECTION_SUCCESSFUL(0, "Database connection successful"),
    DRIVER_NOT_FOUND(1, "Failed to find MySQL JDBC Connector Driver"),
    NOT_ACCESSIBLE(7, "Error accessing the database"),
    UNKNOWN_ERROR(10, "Error");

    private final int identifier;
    private final String value;

    DatabaseConstant(int identifier, String value) {
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

    public static DatabaseConstant getFromIdentifier(int identifier) {
        for (DatabaseConstant constant : DatabaseConstant.values()) {
            if (constant.identifier == identifier) {
                return constant;
            }
        }
        return UNKNOWN_ERROR;
    }
}
