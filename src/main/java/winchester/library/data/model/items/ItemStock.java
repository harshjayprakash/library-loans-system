package winchester.library.data.model.items;

public class ItemStock {

    private final Item item;
    private final ItemFormat subtype;
    private int copiesAvailable;
    private int copiesOnLoan;

    public ItemStock(Item item, ItemFormat itemFormat, int copiesAvailable, int copiesOnLoan) {
        this.item = item;
        this.subtype = itemFormat;
        this.copiesAvailable = copiesAvailable;
        this.copiesOnLoan = copiesOnLoan;
    }

    public ItemType getItemType() {
        return this.item.getType();
    }

    public Item getItem() {
        return switch (this.item.getType()) {
            case BOOK -> (Book) this.item;
            case FILM -> (Film) this.item;
        };
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
