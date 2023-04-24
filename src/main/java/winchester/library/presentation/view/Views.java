package winchester.library.presentation.view;

public enum Views {
    ADD_ITEM(599, "Add Item"),
    ADD_USER(615, "Add User"),
    CHANGE_PASSWORD(1144, "Change Password"),
    CUSTOMERS(709, "Customers"),
    DATABASE_CONFIGURATION(1644, "Database Configuration"),
    HOME(267, "Home"),
    INDIVIDUAL_CUSTOMER(1466, "Individual Customer"),
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

    Views(int identifier, String value) {
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
