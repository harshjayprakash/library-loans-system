package winchester.library.data.model.users;

public abstract class User {
    private final int identifier;
    private final String firstName;
    private final String lastName;
    private final String postalCode;

    public User(int identifier, String firstName, String lastName, String postalCode) {
        this.identifier = identifier;
        this.firstName = firstName;
        this.lastName = lastName;
        this.postalCode = postalCode;
    }

    public int getIdentifier() {
        return this.identifier;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getFullName() {
        return this.firstName + " " + this.lastName;
    }

    public String getPostalCode() {
        return this.postalCode;
    }

    @Override
    public String toString() {
        return """
                ::User
                Name: %s
                Postal Code: %s
               """.formatted(this.getFullName(), this.postalCode);
    }

    public abstract UserType getType();
}
