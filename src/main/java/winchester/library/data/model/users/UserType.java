package winchester.library.data.model.users;

public enum UserType {
    DEMO(0, "Demo"),
    CUSTOMER(1, "Customer"),
    STANDARD(2, "Standard"),
    ADMINISTRATOR(3, "Administrator");

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
