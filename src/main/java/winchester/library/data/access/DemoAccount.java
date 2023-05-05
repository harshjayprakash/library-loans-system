package winchester.library.data.access;

import winchester.library.data.model.users.Employee;
import winchester.library.data.model.users.EmployeeStatus;
import winchester.library.data.model.users.UserType;

/**
 * A class that has a static method to get a demo account. This has been deprecated.
 */
@Deprecated
public class DemoAccount {
    /**
     * A static accessor get a new instance of a demo employee.
     * @return an employee instance.
     */
    public static Employee get() {
        return new Employee(0, "Demo", "Account", "AB123CD", "Username", "Password", EmployeeStatus.ACTIVE);
    }
}
