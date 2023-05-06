package winchester.library.data.model.users;

import winchester.library.data.access.DatabaseEntity;

/**
 * A class to model an employee of the system.
 */
@DatabaseEntity(table = "employees")
public class Employee extends User {
    
    private final String username;
    private String hashedPassword;
    private EmployeeStatus status;

    public Employee(int identifier, String firstName, String lastName, String postalCode, String username,
                    String hashedPassword, EmployeeStatus status) {
        super(identifier, firstName, lastName, postalCode);
        this.type = UserType.STANDARD;
        this.username = username;
        this.hashedPassword = hashedPassword;
        this.status = status;
    }

    public static Employee castFrom(User user) {
        return (Employee) user;
    }

    public String getUsername() {
        return this.username;
    }

    public String getHashedPassword() {
        return this.hashedPassword;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public EmployeeStatus getStatus() {
        return this.status;
    }

    public void setStatus(EmployeeStatus status) {
        this.status = status;
    }

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
