package winchester.library.data.model.users;

import java.util.ArrayList;
import winchester.library.data.model.loans.Loan;
import winchester.library.data.model.loans.LoansManager;

public class Customer extends User {
    private int overdueFeesPence;
    private final LoansManager loansManager;

    public Customer(int identifier, String firstName, String lastName, String postalCode) {
        super(identifier, firstName, lastName, postalCode);
        this.overdueFeesPence = 0;
        this.loansManager = new LoansManager();
    }

    public int getOverdueFeesAsPence() {
        return this.overdueFeesPence;
    }

    public double getOverdueFeesAsPounds() {
        return this.overdueFeesPence * 0.01;
    }

    public void setOverdueFees(int feesPence) {
        this.overdueFeesPence = feesPence;
    }

    public ArrayList<Loan> getLoans() {
        return this.loansManager.getLoans();
    }

    @Override
    public String toString() {
        return """
                %s
                Type: %s
                Overdue Fees: £%.2f
                Loans: null
               """.formatted(super.toString(), this.getType().toString(), this.getOverdueFeesAsPounds());
    }

    @Override
    public UserType getType() {
        return UserType.CUSTOMER;
    }
}