package winchester.library.data.model.users;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class EmployeeTest {

    Employee testEmployee;

    EmployeeTest() {
        testEmployee = new Employee(3, "Mark", "Harrison", "JI320DE", "mark.harrison",
                    "1216985755", EmployeeStatus.NOT_APPROVED);
    }

    @Test
    void getUsername() {
        Assertions.assertEquals(testEmployee.getUsername(), "mark.harrison");
    }

    @Test
    void getPassword() {
        Assertions.assertEquals(testEmployee.getHashedPassword(), String.valueOf("password".hashCode()));
    }

    @Test
    void setPassword() {
        testEmployee.setHashedPassword(String.valueOf("newPassword123#".hashCode()));
        Assertions.assertEquals(testEmployee.getHashedPassword(), String.valueOf("newPassword123#".hashCode()));
    }

    @Test
    void getStatus() {
        Assertions.assertEquals(testEmployee.getStatus(), EmployeeStatus.NOT_APPROVED);
    }

    @Test
    void setStatus() {
        testEmployee.setStatus(EmployeeStatus.ACTIVE);
        Assertions.assertEquals(testEmployee.getStatus(), EmployeeStatus.ACTIVE);
    }
}