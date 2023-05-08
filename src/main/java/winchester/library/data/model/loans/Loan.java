package winchester.library.data.model.loans;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import winchester.library.data.model.items.ItemFormat;
import winchester.library.data.model.users.Customer;

/**
 * A class to model the information of a loan.
 */
public class Loan {
    private final long identifier;
    private final Customer customer;
    private final String loanedItemIdentifier;
    private final ItemFormat loanedItemFormat;
    private final LocalDate loanDate;
    private LocalDate dueDate;
    private boolean returned;

    /**
     * The default constructor for the loan entity, providing item and customer information as well as dates loaned and
     * to be returned.
     * @param identifier the loan identifier.
     * @param customer the customer involved in the loan.
     * @param loanedItemIdentifier the item identifier associated with the loan.
     * @param loanedItemSubType the item subtype loaned out.
     * @param loanDate the date the item was loaned out.
     * @param dueDate the due date of the loan.
     * @param returned whether the item has been returned.
     */
    public Loan(long identifier, Customer customer, String loanedItemIdentifier, ItemFormat loanedItemSubType, LocalDate loanDate,
                LocalDate dueDate, boolean returned) {
        this.identifier = identifier;
        this.customer = customer;
        this.loanedItemIdentifier = loanedItemIdentifier;
        this.loanedItemFormat = loanedItemSubType;
        this.loanDate = loanDate;
        this.dueDate = dueDate;
        this.returned = returned;
    }

    /**
     * An accessor to retrieve the loan identifier.
     * @return the loan identifier.
     */
    public long getIdentifier() {
        return this.identifier;
    }

    /**
     * An accessor to retrieve the customer associated with the loan.
     * @return the customer associated with the loan.
     */
    public Customer getCustomer() {
        return this.customer;
    }

    /**
     * An accessor to retrieve the item identifier associated with the loan.
     * @return the loaned item identifier.
     */
    public String getLoanedItemIdentifier() {
        return this.loanedItemIdentifier;
    }

    /**
     * An accessor to retrieve the item format that has been loaned out.
     * @return the item format loaned.
     */
    public ItemFormat getLoanedItemFormat() {
        return this.loanedItemFormat;
    }

    /**
     * An accessor to retrieve the loaned out date of the item.
     * @return the loaned out date.
     */
    public LocalDate getLoanDate() {
        return this.loanDate;
    }

    /**
     * An accessor to retrieve the due date of the loaned out item.
     * @return the due date of the loaned out item.
     */
    public LocalDate getDueDate() {
        return this.dueDate;
    }

    /**
     * An accessor to retrieve whether the item has been returned.
     * @return whether the item has been returned.
     */
    public boolean getReturned() {
        return this.returned;
    }

    /**
     * A mutator to be able to set whether the item has been returned.
     * @param returned whether the item has been returned.
     */
    public void setReturned(boolean returned) {
        this.returned = returned;
    }

    /**
     * A method to extends the amount of days that an item is loaned for.
     * @param additionalDays the additional days of loan.
     */
    public void extendLoan(long additionalDays) {
        this.dueDate = this.dueDate.plusDays(additionalDays);
    }

    /**
     * Calculates the number of days remaining until the loan is due.
     * @return the number of days left on loan.
     */
    public long calculateDaysRemaining() {
        return ChronoUnit.DAYS.between(LocalDate.now(), this.dueDate);
    }
}
