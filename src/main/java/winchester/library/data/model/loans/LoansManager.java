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

    /**
     * The default constructor to initialise the array list of loans.
     */
    public LoansManager() {
        this.loans = new ArrayList<>();
    }

    /**
     * Adds a loan into the array list.
     * @param loan the loan to be added.
     */
    public void addLoan(Loan loan) {
        if (loan == null) { return; }
        this.loans.add(loan);
    }

    /**
     * An accessor to retrieve the array list of loans.
     * @return the list of loans.
     */
    public ArrayList<Loan> getLoans() {
        return this.loans;
    }

    /**
     * A method to calculate the earliest return date for an item.
     * @return an optional date or if there is not an available date, returns empty.
     */
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
