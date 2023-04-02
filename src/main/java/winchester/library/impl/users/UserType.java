package winchester.library.impl.users;

public enum UserType {
    DEMO(0, "Demo Account"),
    CUSTOMER(1, "Customer Account"),
    STANDARD(2, "Standard Account"),
    ADMINISTRATOR(3, "Administrator Account");

    private final int identifier;
    private final String text;

    UserType(int identifier, String text) {
        this.identifier = identifier;
        this.text = text;
    }

    public int getIdentifier() {
        return this.identifier;
    }

    @Override
    public String toString() {
        return this.text;
    }
}
