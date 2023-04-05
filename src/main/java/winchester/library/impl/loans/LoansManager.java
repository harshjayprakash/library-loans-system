package winchester.library.impl.loans;

import java.util.ArrayList;
import java.util.Objects;

public class LoansManager {

    private final ArrayList<Loan> loans;

    public LoansManager() {
        this.loans = new ArrayList<>();
    }

    public void addLoan(Loan loan) {
        if (Objects.isNull(loan)) { return; }
        this.loans.add(loan);
    }

    public void addLoans(Loan... loans) {
        for (Loan loan : loans) {
            if (Objects.isNull(loan)) { continue; }
            this.loans.add(loan);
        }
    }

    public ArrayList<Loan> getLoans() {
        return this.loans;
    }

}
