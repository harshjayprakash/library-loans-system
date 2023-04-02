package winchester.library.impl.loans;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import winchester.library.impl.items.Item;
import winchester.library.impl.users.Customer;

public class Loan {
    private final Customer customer;
    private final Item loanedItem;
    private final LocalDate loanDate;
    private LocalDate dueDate;
    private boolean returned;

    public Loan(Customer customer, Item loanedItem, LocalDate loanDate, LocalDate dueDate, boolean returned) {
        this.customer = customer;
        this.loanedItem = loanedItem;
        this.loanDate = loanDate;
        this.dueDate = dueDate;
        this.returned = returned;
    }

    public Customer getCustomer() {
        return this.customer;
    }

    public Item getLoanedItem() {
        return this.loanedItem;
    }

    public LocalDate getLoanDate() {
        return this.loanDate;
    }

    public LocalDate getDueDate() {
        return this.dueDate;
    }

    public boolean getReturned() {
        return this.returned;
    }

    public void setReturned(boolean returned) {
        this.returned = returned;
    }

    public void returnItem() {
        this.returned = true;
    }

    public void extendLoan(long additionalDays) {
        this.dueDate = this.dueDate.plusDays(additionalDays);
    }

    public long calculateDaysRemaining() {
        return ChronoUnit.DAYS.between(this.loanDate, this.dueDate);
    }
}
