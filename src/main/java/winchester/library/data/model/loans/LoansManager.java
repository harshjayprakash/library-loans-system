package winchester.library.data.model.loans;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import java.util.Optional;

/**
 * A class that offers an abstraction from handling an array of loans.
 */
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

    public Optional<LocalDate> getEarliestReturnDate() {
        ArrayList<LocalDate> dates = new ArrayList<>();
        LocalDate currentDate = LocalDate.now();
        for (Loan loan : this.loans) {
            if (loan.getDueDate().isAfter(currentDate)) {
                dates.add(loan.getDueDate());
            }
        }
        Collections.sort(dates);
        return dates.stream().findFirst();
    }

}
