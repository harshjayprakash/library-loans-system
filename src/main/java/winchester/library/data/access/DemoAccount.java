package winchester.library.data.access;

import winchester.library.data.model.users.Employee;
import winchester.library.data.model.users.EmployeeStatus;

@Deprecated
public class DemoAccount {
    public static Employee get() {
        return new Employee(0, "Demo", "Account", "AB123CD", "Username", "Password", EmployeeStatus.ACTIVE);
    }
}
