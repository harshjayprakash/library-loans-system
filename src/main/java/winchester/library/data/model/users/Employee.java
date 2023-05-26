package winchester.library.data.model.users;

/**
 * A class to model an employee of the system.
 */
public class Employee extends User {
    
    private final String username;
    private String hashedPassword;
    private EmployeeStatus status;

    /**
     * The default constructor for the Employee class.
     * @param identifier the employee identifier.
     * @param type the user type.
     * @param firstName the first name of the employee.
     * @param lastName the last name of the employee.
     * @param postalCode the postal code of the employee.
     * @param username the employee's username.
     * @param hashedPassword the employee's hashed password.
     * @param status the employee's current status.
     */
    public Employee(int identifier, UserType type, String firstName, String lastName, String postalCode, String username,
                    String hashedPassword, EmployeeStatus status) {
        super(identifier, firstName, lastName, postalCode);
        this.type = type;
        this.username = username;
        this.hashedPassword = hashedPassword;
        this.status = status;
    }

    /**
     * Casts the base User class type to the Employee class type.
     * @param user the user to be cast.
     * @return the employee cast from the specified item
     */
    public static Employee castFrom(User user) {
        return (Employee) user;
    }

    /**
     * An accessor to retrieve the employee's username.
     * @return the employee's username.
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * An accessor to retrieve the employee's hashed password.
     * @return the employee's hashed password.
     */
    public String getHashedPassword() {
        return this.hashedPassword;
    }

    /**
     * A mutator to set the employee's password (hashed).
     * @param hashedPassword the new hashed password.
     */
    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    /**
     * An accessor to retrieve the employee's current status.
     * @return the status of the employee.
     */
    public EmployeeStatus getStatus() {
        return this.status;
    }

    /**
     * A mutator to set the employee's status.
     * @param status the new employee's status.
     */
    public void setStatus(EmployeeStatus status) {
        this.status = status;
    }

    /**
     * Returns a string of the employee's information.
     * @return a string of the employee's information.
     */
    @Override
    public String toString() {
        return """
                %s
                Type: %s
                Username: %s
                Status: %s
               """.formatted(super.toString(), this.getType().toString(), this.username, this.status.toString());
    }
}
