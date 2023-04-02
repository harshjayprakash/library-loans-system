package winchester.library.impl.users;

public class Administrator extends Employee {
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
}
