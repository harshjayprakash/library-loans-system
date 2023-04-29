package winchester.library.data.model.loans;

/**
 * An interface for allowing the loaning of items.
 */
public interface LoanManagement {
    void loanItem();
    void returnItem();
    void extendItem();
}
