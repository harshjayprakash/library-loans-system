package winchester.library.data.model.users;

/**
 * A base class to represent any user of the system.
 */
public abstract class User {

    protected final int identifier;
    protected final String firstName;
    protected final String lastName;
    protected final String postalCode;
    protected UserType type;

    /**
     * The constructor for the user class.
     * @param identifier the user's identifier.
     * @param firstName the first name of the user.
     * @param lastName the last name of the user.
     * @param postalCode the postal code of the user.
     */
    public User(int identifier, String firstName, String lastName, String postalCode) {
        this.identifier = identifier;
        this.firstName = firstName;
        this.lastName = lastName;
        this.postalCode = postalCode;
    }

    /**
     * An accessor to retrieve the user's identifier.
     * @return the user's identifier.
     */
    public int getIdentifier() {
        return this.identifier;
    }

    /**
     * An accessor to retrieve the first name of the user.
     * @return the user's first name.
     */
    public String getFirstName() {
        return this.firstName;
    }

    /**
     * An accessor to retrieve the last name of the user.
     * @return the user's last name.
     */
    public String getLastName() {
        return this.lastName;
    }

    /**
     * An accessor to retrieve the full name of the user.
     * @return the user's full name.
     */
    public String getFullName() {
        return this.firstName + " " + this.lastName;
    }

    /**
     * An accessor to retrieve the postal code of the user.
     * @return the user's postal code.
     */
    public String getPostalCode() {
        return this.postalCode;
    }

    /**
     * An accessor to return the type of the user.
     * @return a UserType enumeration constant that describes the user type.
     */
    public UserType getType() {
        return this.type;
    }

    /**
     * Returns a string of the user's information.
     * @return a string of the user's information.
     */
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
