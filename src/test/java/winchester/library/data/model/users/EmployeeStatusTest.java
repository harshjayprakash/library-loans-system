package winchester.library.data.model.users;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class EmployeeStatusTest {

    @Test
    void fromIdentifier() {
        Assertions.assertEquals(EmployeeStatus.fromIdentifier(-1).orElse(null), EmployeeStatus.DISABLED);
        Assertions.assertEquals(EmployeeStatus.fromIdentifier(0).orElse(null), EmployeeStatus.NOT_APPROVED);
        Assertions.assertEquals(EmployeeStatus.fromIdentifier(1).orElse(null), EmployeeStatus.ACTIVE);
    }

    @Test
    void getIdentifier() {
        Assertions.assertEquals(EmployeeStatus.DISABLED.getIdentifier(), -1);
        Assertions.assertEquals(EmployeeStatus.NOT_APPROVED.getIdentifier(), 0);
        Assertions.assertEquals(EmployeeStatus.ACTIVE.getIdentifier(), 1);
    }
}