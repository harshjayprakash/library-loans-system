package winchester.library.data;

import winchester.library.impl.users.Employee;
import winchester.library.impl.users.EmployeeStatus;

@Deprecated
public class DemoAccount {
    public static Employee get() {
        return new Employee(0, "Demo", "Account", "AB123CD", "Username", "Password", EmployeeStatus.ACTIVE);
    }
}
