package winchester.library.data.model.loans;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import winchester.library.data.model.items.Item;
import winchester.library.data.model.items.ItemType;
import winchester.library.data.model.users.Customer;

public class Loan {
    private final int identifier;
    private final Customer customer;
    private final Item loanedItem;
    private final ItemType loanedItemFormat;
    private final LocalDate loanDate;
    private LocalDate dueDate;
    private boolean returned;

    public Loan(int identifier, Customer customer, Item loanedItem, ItemType loanedItemSubType, LocalDate loanDate,
                LocalDate dueDate, boolean returned) {
        this.identifier = identifier;
        this.customer = customer;
        this.loanedItem = loanedItem;
        this.loanedItemFormat = loanedItemSubType;
        this.loanDate = loanDate;
        this.dueDate = dueDate;
        this.returned = returned;
    }

    public int getIdentifier() {
        return this.identifier;
    }

    public Customer getCustomer() {
        return this.customer;
    }

    public Item getLoanedItem() {
        return this.loanedItem;
    }

    public ItemType getLoanedItemFormat() {
        return this.loanedItemFormat;
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
