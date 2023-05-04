package winchester.library.data.access;

/**
 * Provides a more readable format to any error or success messages during the database connection.
 */
public enum DatabaseConstant {
    CONNECTION_SUCCESSFUL(0, "Connection Successful"),
    CONNECTION_TIMEOUT(1, "Connection Timed Out"),
    CONNECTION_NOT_AVAILABLE(2, "Connection Not Available"),
    CONNECTION_CLOSE_ERROR(3, "Failed to Close Connection"),
    DRIVER_NOT_FOUND(2, "MySQL Driver Not Found."),
    DRIVER_FOUND(3, "Driver Loaded Successfully"),
    DATABASE_NOT_ACCESSIBLE(7, "Error Accessing the Database"),
    DATA_NOT_ACCESSIBLE(8, "Error Accessing Data"),
    INSERTION_ERROR(9, "Error Inserting into the Database"),
    INSERTION_SUCCESSFUL(10, "Insertion Successful"),
    UNKNOWN_ERROR(10, "Error");

    private final int identifier;
    private final String value;

    /**
     * A constructor that allows extra information to be stored in this enumeration such as an identifier and string
     * value.
     * @param identifier a message identifier to refer to.
     * @param value a string version of the enumeration constant.
     */
    DatabaseConstant(int identifier, String value) {
        this.identifier = identifier;
        this.value = value;
    }

    /**
     * A static method that allows the ability to get the enumeration constant from the identifier.
     * @param identifier the identifier to find the DatabaseConstant enumeration
     * @return the DatabaseConstant equivalent to the inputted identifier.
     */
    public static DatabaseConstant getFromIdentifier(int identifier) {
        for (DatabaseConstant constant : DatabaseConstant.values()) {
            if (constant.identifier == identifier) {
                return constant;
            }
        }
        return UNKNOWN_ERROR;
    }

    /**
     * An accessor to retrieve the identifier.
     * @return the DatabaseConstant identifier.
     */
    public int getIdentifier() {
        return this.identifier;
    }

    /**
     * An accessor to retrieve the string value of the constant.
     * @return the string value of the DatabaseConstant.
     */
    @Override
    public String toString() {
        return this.value;
    }
}
