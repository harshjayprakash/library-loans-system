package winchester.library.impl.users;

public class Employee extends User {
    private final String username;
    private String password;
    private EmployeeStatus status;

    public Employee(String firstName, String lastName, String postalCode, String username, String password, 
                    EmployeeStatus status) {
        super(firstName, lastName, postalCode);
        this.username = username;
        this.password = password;
        this.status = status;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
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
                Password: %s
                Status: %s
               """.formatted(super.toString(), this.username, this.password, this.status.toString());
    }

    @Override
    public UserType getType() {
        return UserType.STANDARD;
    }
}
