package winchester.library.data.model.items;

public class ItemStock {

    private final ItemFormat subtype;
    private int copiesAvailable;
    private int copiesOnLoan;

    public ItemStock(ItemFormat itemFormat, int copiesAvailable, int copiesOnLoan) {
        this.subtype = itemFormat;
        this.copiesAvailable = copiesAvailable;
        this.copiesOnLoan = copiesOnLoan;
    }

    public ItemFormat getItemFormat() {
        return this.subtype;
    }

    public int getCopiesAvailable() {
        return this.copiesAvailable;
    }

    public void setCopiesAvailable(int copiesAvailable) {
        this.copiesAvailable = copiesAvailable;
    }

    public int getCopiesOnLoan() {
        return this.copiesOnLoan;
    }

    public void setCopiesOnLoan(int copiesOnLoan) {
        this.copiesOnLoan = copiesOnLoan;
    }
}
