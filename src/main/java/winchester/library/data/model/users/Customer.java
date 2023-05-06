package winchester.library.data.model.users;

import java.util.ArrayList;
import winchester.library.data.model.loans.Loan;
import winchester.library.data.model.loans.LoansManager;

/**
 * A class to model a customer that will be loaning items.
 */
public class Customer extends User  {
    
    private int overdueFeesPence;

    /**
     * The default constructor for the customer.
     * @param identifier the customer's identifier.
     * @param firstName the first name of the customer.
     * @param lastName the last name of the customer.
     * @param postalCode the postal code of the customer.
     */
    public Customer(int identifier, String firstName, String lastName, String postalCode) {
        super(identifier, firstName, lastName, postalCode);
        this.type = UserType.CUSTOMER;
        this.overdueFeesPence = 0;
    }

    /**
     * Casts the base User class type to the Customer class type.
     * @param user the user to be cast.
     * @return the customer cast from the specified item
     */
    public static Customer castFrom(User user) {
        return (Customer) user;
    }

    /**
     * An accessor to retrieve the overdue fees that the customer has as pence.
     * @return the overdue fees as pence.
     */
    public int getOverdueFeesAsPence() {
        return this.overdueFeesPence;
    }

    /**
     * An accessor to retrieve the overdue fees that the customer has as pounds.
     * @return the overdue fees as pounds.
     */
    public double getOverdueFeesAsPounds() {
        return this.overdueFeesPence * 0.01;
    }

    /**
     * A mutator to set the overdue fees that customer has.
     * @param feesPence the overdue fees as pence.
     */
    public void setOverdueFees(int feesPence) {
        this.overdueFeesPence = feesPence;
    }

    /**
     * Returns a string of the customer's information.
     * @return a string of the customer's information.
     */
    @Override
    public String toString() {
        return """
               %s
               Type: %s
               Overdue Fees: £%.2f
               """.formatted(super.toString(), this.getType().toString(), this.getOverdueFeesAsPounds());
    }
}
