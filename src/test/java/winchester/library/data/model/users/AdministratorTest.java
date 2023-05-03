package winchester.library.data.model.users;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

class AdministratorTest {

    @Test
    void approveUser() {
        Administrator administrator = new Administrator(1, "Dominic", "Mccann", "JK218SV", "dominic.mccann",
                "&3hr54&MCz", EmployeeStatus.ACTIVE);
        Employee employee = new Employee(2, "John", "Smith", "DJ320HD", "john.smith", "ho&wi2io#",
                EmployeeStatus.NOT_APPROVED);
        Assertions.assertNotEquals(employee, null);
        Assertions.assertNotEquals(administrator, null);
        Assertions.assertEquals(employee.getStatus(), EmployeeStatus.NOT_APPROVED);
        administrator.approveUser(employee);
        Assertions.assertEquals(employee.getStatus(), EmployeeStatus.ACTIVE);
    }

    @Test
    void disableUser() {
        Administrator administrator = new Administrator(1, "Dominic", "Mccann", "JK218SV", "dominic.mccann",
                "&3hr54&MCz", EmployeeStatus.ACTIVE);
        Employee employee = new Employee(2, "John", "Smith", "DJ320HD", "john.smith", "ho&wi2io#",
                EmployeeStatus.ACTIVE);
        Assertions.assertNotEquals(employee, null);
        Assertions.assertNotEquals(administrator, null);
        Assertions.assertEquals(employee.getStatus(), EmployeeStatus.ACTIVE);
        administrator.disableUser(employee);
        Assertions.assertEquals(employee.getStatus(), EmployeeStatus.DISABLED);
    }
}