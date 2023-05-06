package winchester.library.data.model.users;

/**
 * A class to represent an administrator with higher privileges than a normal employee.
 */
public class Administrator extends Employee {

    /**
     * The default constructor for the administrator class.
     * @param identifier the administrator's identifier.
     * @param firstName the first name of the administrator.
     * @param lastName the last name of the administrator.
     * @param postalCode the postal code of the administrator.
     * @param username the username of the administrator.
     * @param hashedPassword the hashed password of the administrator.
     * @param status the current status of the administrator.
     */
    public Administrator(int identifier, String firstName, String lastName, String postalCode, String username,
                         String hashedPassword, EmployeeStatus status) {
        super(identifier, firstName, lastName, postalCode, username, hashedPassword, status);
        this.type = UserType.ADMINISTRATOR;
    }

    /**
     * Returns a string of the administrator's information.
     * @return a string of the administrator's information.
     */
    @Override
    public String toString() {
        return super.toString();
    }
}
