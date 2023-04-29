package winchester.library.data.model.items;

import winchester.library.data.access.DatabaseEntity;

/**
 * A class to represent the stock available for each form of item.
 */
@DatabaseEntity(table = "item_copies")
public class ItemStock {

    private final String itemIdentifier;
    private final ItemFormat subtype;
    private int copiesAvailable;
    private int copiesOnLoan;

    public ItemStock(String itemIdentifier, ItemFormat itemFormat, int copiesAvailable, int copiesOnLoan) {
        this.itemIdentifier = itemIdentifier;
        this.subtype = itemFormat;
        this.copiesAvailable = copiesAvailable;
        this.copiesOnLoan = copiesOnLoan;
    }

    public String getItemIdentifier() {
        return this.itemIdentifier;
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

    @Override
    public String toString() {
        return String.format(
                """
                [Format : %s]
                Copies Available : %d
                Loaned Copies : %d
                """, this.subtype.toString(), this.copiesAvailable, this.copiesOnLoan);
    }
}
