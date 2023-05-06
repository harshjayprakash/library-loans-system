package winchester.library.data.model.users;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CustomerTest {

    Customer testCustomer;

    CustomerTest() {
        testCustomer = new Customer(5, "Edward", "Cooper", "JI439DE");
        testCustomer.setOverdueFees(50);
    }

    @Test
    void getOverdueFeesAsPence() {
        Assertions.assertEquals(testCustomer.getOverdueFeesAsPence(), 50);
    }

    @Test
    void getOverdueFeesAsPounds() {
        Assertions.assertEquals(testCustomer.getOverdueFeesAsPounds(), 0.5);
    }

    @Test
    void setOverdueFees() {
        testCustomer.setOverdueFees(60);
        Assertions.assertEquals(testCustomer.getOverdueFeesAsPence(), 60);
    }
}