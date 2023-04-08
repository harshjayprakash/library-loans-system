package winchester.library.data.model.users;

public class Employee extends User {
    private final String username;
    private String password;
    private EmployeeStatus status;

    public Employee(int identifier, String firstName, String lastName, String postalCode, String username,
                    String password, EmployeeStatus status) {
        super(identifier, firstName, lastName, postalCode);
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
               """.formatted(super.toString(), this.getType().toString(), this.username, this.password,
                this.status.toString());
    }

    @Override
    public UserType getType() {
        return UserType.STANDARD;
    }
}
