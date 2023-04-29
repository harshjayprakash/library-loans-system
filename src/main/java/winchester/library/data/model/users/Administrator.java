package winchester.library.data.model.users;

/**
 * A class to represent an administrator with higher privileges than a normal employee.
 */
public class Administrator extends Employee 
    implements UserManagement {
    
    public Administrator(int identifier, String firstName, String lastName, String postalCode, String username,
                         String password, EmployeeStatus status) {
        super(identifier, firstName, lastName, postalCode, username, password, status);
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public UserType getType() {
        return UserType.ADMINISTRATOR;
    }

    @Override
    public void addUser() {

    }

    @Override
    public void removeUser() {

    }

    @Override
    public void approveUser() {

    }
}
