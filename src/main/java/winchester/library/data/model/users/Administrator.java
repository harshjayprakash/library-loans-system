package winchester.library.data.model.users;

/**
 * A class to represent an administrator with higher privileges than a normal employee.
 */
public class Administrator extends Employee implements UserManagement {
    
    public Administrator(int identifier, String firstName, String lastName, String postalCode, String username,
                         String password, EmployeeStatus status) {
        super(identifier, firstName, lastName, postalCode, username, password, status);
        this.type = UserType.ADMINISTRATOR;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public void approveUser(Employee employee) {
        employee.setStatus(EmployeeStatus.ACTIVE);
    }

    @Override
    public void disableUser(Employee employee) {
        employee.setStatus(EmployeeStatus.DISABLED);
    }
}
