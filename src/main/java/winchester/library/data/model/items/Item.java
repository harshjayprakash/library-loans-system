package winchester.library.data.model.items;

import java.util.ArrayList;
import winchester.library.data.model.loans.Loan;

public abstract class Item {

    protected ArrayList<ItemStock> stockAvailable;
    protected ArrayList<Loan> loans;

    public Item() {
        this.stockAvailable = new ArrayList<>();
        this.loans = new ArrayList<>();
    }

    public ArrayList<ItemStock> getStockAvailable() {
        return this.stockAvailable;
    }

    public ArrayList<Loan> getLoans() {
        return this.loans;
    }

    public abstract ItemType getType();

    @Override
    public String toString() {
        return "Item{" +
                "stockAvailable=" + stockAvailable +
                '}';
    }
}
