package winchester.library.data.access;

/**
 * Provides a more readable format to any error or success messages during the database connection.
 */
public enum DatabaseConstant {
    /**
     *Represents that the connection to the database was successful.
     */
    CONNECTION_SUCCESSFUL(0, "Connection Successful"),

    /**
     * Represents that the database connection has timed out.
     */
    CONNECTION_TIMEOUT(1, "Connection Timed Out"),

    /**
     * Represents that the database connection is not currently available.
     */
    CONNECTION_NOT_AVAILABLE(2, "Connection Not Available"),

    /**
     * Represents that a failure occur when attempting to close the database connection.
     */
    CONNECTION_CLOSE_ERROR(3, "Failed to Close Connection"),

    /**
     * Represents that the MySQL driver was not found.
     */
    DRIVER_NOT_FOUND(2, "MySQL Driver Not Found."),

    /**
     * Represents that the driver was found and loaded successfully.
     */
    DRIVER_FOUND(3, "Driver Loaded Successfully"),

    /**
     * Represents that the database was not accessible.
     */
    DATABASE_NOT_ACCESSIBLE(7, "Error Accessing the Database"),

    /**
     * Represents that the driver failed to access the data.
     */
    DATA_NOT_ACCESSIBLE(8, "Error Accessing Data"),

    /**
     * Represents an error inserting into the database.
     */
    INSERTION_ERROR(9, "Error Inserting into the Database"),

    /**
     * Represents data insertion to the database was successful.
     */
    INSERTION_SUCCESSFUL(10, "Insertion Successful"),

    /**
     * Represents an error updating information in the database.
     */
    UPDATE_ERROR(11, "Update Error"),

    /**
     * Represents updating the information in the database was successful.
     */
    UPDATE_SUCCESSFUL(12, "Update Successful"),

    /**
     * Represents an unknown error that occurred.
     */
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
