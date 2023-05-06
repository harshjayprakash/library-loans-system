package winchester.library.presentation.view;

/**
 * An enumeration of all possible views that can be displayed, with a title and identifier (name converted to ascii).
 */
public enum Views {
    ADD_ITEM(599, "Add Item"),
    ADD_USER(615, "Add User"),
    CHANGE_PASSWORD(1144, "Change Password"),
    CUSTOMERS(709, "Customers"),
    DATABASE_CONFIGURATION(1644, "Database Configuration"),
    HOME(267, "Home"),
    INDIVIDUAL_CUSTOMER(1466, "Individual Customer"),
    INDIVIDUAL_EMPLOYEE(1448, "Individual Employee"),
    INDIVIDUAL_ITEM(1143, "Individual Item"),
    INDIVIDUAL_LOAN(1138, "Individual Loans"),
    INVENTORY(718, "Inventory"),
    LOANING_ITEMS(1001, "Loaning Items"),
    LOANS(381, "Loans"),
    LOGIN(377, "Log In"),
    NONE(304, " "),
    NONE_WITH_SIDEBAR(1316, " "),
    REGISTER(613, "Register"),
    SETTINGS(625, "Settings"),
    USERS(402, "Users");

    private final int identifier;
    private final String value;

    /**
     * A constructor to specify additional information for each constant, such as an identifier and string value.
     * @param identifier the identifier that can be referred to.
     * @param value the string value of the constant.
     */
    Views(int identifier, String value) {
        this.identifier = identifier;
        this.value = value;
    }

    /**
     * An accessor to retrieve the identifier corresponding to the constant.
     * @return the identifier corresponding to the constant.
     */
    public int getIdentifier() {
        return this.identifier;
    }

    /**
     * An accessor to retrieve the string value associated to the constant.
     * @return the string value corresponding to the constant.
     */
    @Override
    public String toString() {
        return this.value;
    }
}
