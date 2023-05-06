package winchester.library.data.model.loans;

import java.time.LocalDate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import winchester.library.data.model.items.ItemFormat;
import winchester.library.data.model.users.Customer;

class LoanTest {

    Loan testLoan;

    LoanTest() {
        testLoan = new Loan(243, new Customer(5, "Edward", "Cooper", "JI439DE"), "9781593767136",
                ItemFormat.PHYSICAL_BOOK, LocalDate.parse("2023-03-01"), LocalDate.parse("2023-04-01"), false);
    }

    @Test
    void getIdentifier() {
        Assertions.assertEquals(testLoan.getIdentifier(), 243);
    }

    @Test
    void getCustomer() {
        Assertions.assertEquals(testLoan.getCustomer().getIdentifier(), 5);
        Assertions.assertEquals(testLoan.getCustomer().getFirstName(), "Edward");
        Assertions.assertEquals(testLoan.getCustomer().getLastName(), "Cooper");
        Assertions.assertEquals(testLoan.getCustomer().getPostalCode(), "JI439DE");
    }

    @Test
    void getLoanedItemIdentifier() {
        Assertions.assertEquals(testLoan.getLoanedItemIdentifier(), "9781593767136");
    }

    @Test
    void getLoanedItemFormat() {
        Assertions.assertEquals(testLoan.getLoanedItemFormat(), ItemFormat.PHYSICAL_BOOK);
    }

    @Test
    void getLoanDate() {
        Assertions.assertEquals(testLoan.getLoanDate(), LocalDate.of(2023, 3, 1));
    }

    @Test
    void getDueDate() {
        Assertions.assertEquals(testLoan.getDueDate(), LocalDate.of(2023, 4, 1));
    }

    @Test
    void getReturned() {
        Assertions.assertFalse(testLoan.getReturned());
    }

    @Test
    void setReturned() {
        testLoan.setReturned(true);
        Assertions.assertTrue(testLoan.getReturned());
    }
}