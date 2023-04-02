package winchester.library.impl.users;

import java.util.ArrayList;

public class Customer extends User {
    private int overdueFeesPence;
    private final ArrayList<?> loans;

    public Customer(int identifier, String firstName, String lastName, String postalCode) {
        super(identifier, firstName, lastName, postalCode);
        this.overdueFeesPence = 0;
        this.loans = new ArrayList<>();
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

    public ArrayList<?> getLoans() {
        return this.loans;
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
