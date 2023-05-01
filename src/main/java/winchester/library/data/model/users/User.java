package winchester.library.data.model.users;

import winchester.library.data.access.DatabaseEntity;

/**
 * A base class to represent any user of the system.
 */
@DatabaseEntity(table = "users")
public abstract class User {

    private final int identifier;
    private final String firstName;
    private final String lastName;
    private final String postalCode;
    protected UserType type;

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

    public UserType getType() {
        return this.type;
    }

    @Override
    public String toString() {
        return String.format(
                """
                First Name : %s
                Last Name : %s
                Postal Code : %s
                """, this.firstName, this.lastName, this.postalCode);
    }
}
