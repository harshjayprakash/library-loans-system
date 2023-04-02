package winchester.library.impl.users;

public class Administrator extends Employee {
    public Administrator(String firstName, String lastName, String postalCode, String username, String password, 
                         EmployeeStatus status) {
        super(firstName, lastName, postalCode, username, password, status);
    }

    @Override
    public UserType getType() {
        return UserType.ADMINISTRATOR;
    }
}
