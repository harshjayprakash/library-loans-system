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

    /**
     * The default constructor to create the ItemStock class.
     * @param itemIdentifier the item identifier.
     * @param itemFormat the ItemType enumeration type.
     * @param copiesAvailable the number of copies available for that item.
     * @param copiesOnLoan the number of copies currently on loan for that item.
     */
    public ItemStock(String itemIdentifier, ItemFormat itemFormat, int copiesAvailable, int copiesOnLoan) {
        this.itemIdentifier = itemIdentifier;
        this.subtype = itemFormat;
        this.copiesAvailable = copiesAvailable;
        this.copiesOnLoan = copiesOnLoan;
    }

    /**
     * An accessor to retrieve the item identifier referenced.
     * @return the item identifier.
     */
    public String getItemIdentifier() {
        return this.itemIdentifier;
    }

    /**
     * An accessor to retrieve the item's format.
     * @return the ItemFormat enumeration constant.
     */
    public ItemFormat getItemFormat() {
        return this.subtype;
    }

    /**
     * An accessor to retrieve the number of copies available for that item.
     * @return the number of copies available.
     */
    public int getCopiesAvailable() {
        return this.copiesAvailable;
    }

    /**
     * A mutator to set the number of available copies for an item.
     * @param copiesAvailable the number of available copies for the item.
     */
    public void setCopiesAvailable(int copiesAvailable) {
        this.copiesAvailable = copiesAvailable;
    }

    /**
     * An accessor to retrieve the number of copies on loan for an item.
     * @return the number of copies on loan.
     */
    public int getCopiesOnLoan() {
        return this.copiesOnLoan;
    }

    /**
     * A mutator to set the number of copies on loan for an item.
     * @param copiesOnLoan the number of copies on loan.
     */
    public void setCopiesOnLoan(int copiesOnLoan) {
        this.copiesOnLoan = copiesOnLoan;
    }

    /**
     * A method to return loan information in a human-readable format.
     * @return loan information in a human-readable format.
     */
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
