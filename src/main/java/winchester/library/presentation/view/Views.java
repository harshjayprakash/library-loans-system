package winchester.library.presentation.view;

/**
 * An enumeration of all possible views that can be displayed, with a title and identifier (name converted to ascii).
 */
public enum Views {
    /**
     * Represents the add item view.
     */
    ADD_ITEM(599, "Add Item"),

    /**
     * Represents the add user view.
     */
    ADD_USER(615, "Add User"),

    /**
     * Represents the change password view.
     */
    CHANGE_PASSWORD(1144, "Change Password"),

    /**
     * Represents the customers list view.
     */
    CUSTOMERS(709, "Customers"),

    /**
     * Represents the database configuration view.
     */
    DATABASE_CONFIGURATION(1644, "Database Configuration"),

    /**
     * Represents the home statistical view.
     */
    HOME(267, "Home"),

    /**
     * Represents the individual customer view.
     */
    INDIVIDUAL_CUSTOMER(1466, "Individual Customer"),

    /**
     * Represents the individual employee view.
     */
    INDIVIDUAL_EMPLOYEE(1448, "Individual Employee"),

    /**
     * Represents the individual item view.
     */
    INDIVIDUAL_ITEM(1143, "Individual Item"),

    /**
     * Represents the individual loans view.
     */
    INDIVIDUAL_LOAN(1138, "Individual Loans"),

    /**
     * Represents the inventory list view.
     */
    INVENTORY(718, "Inventory"),

    /**
     * Represents the loaning items view.
     */
    LOANING_ITEMS(1001, "Loaning Items"),

    /**
     * Represents the loans list view.
     */
    LOANS(381, "Loans"),

    /**
     * Represents the employee login view.
     */
    LOGIN(377, "Log In"),

    /**
     * Represents view where nothing is selected.
     */
    NONE_WITH_SIDEBAR(1316, " "),

    /**
     * Represents the register view.
     */
    REGISTER(613, "Register"),

    /**
     * Represents the settings view.
     */
    SETTINGS(625, "Settings"),

    /**
     * Represents the employees list view.
     */
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
