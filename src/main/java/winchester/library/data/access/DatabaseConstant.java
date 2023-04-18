package winchester.library.data.access;

public enum DatabaseConstant {
    CONNECTION_SUCCESSFUL(0, "Connection Successful"),
    CONNECTION_TIMEOUT(1, "Connection Timed Out"),
    CONNECTION_NOT_AVAILABLE(2, "Connection Not Available"),
    CONNECTION_CLOSE_ERROR(3, "Failed to Close Connection"),
    DRIVER_NOT_FOUND(2, "Failed To Load Driver"),
    DRIVER_FOUND(3, "Driver Loaded Successfully"),
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
