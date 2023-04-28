package winchester.library.data.model.items;

import java.util.ArrayList;
import winchester.library.data.model.loans.LoansManager;

public abstract class Item {

    protected ArrayList<ItemStock> stockAvailable;
    protected LoansManager loans;
    protected String imageUrl;

    public Item() {
        this.stockAvailable = new ArrayList<>();
        this.loans = new LoansManager();
    }

    public ArrayList<ItemStock> getStockAvailable() {
        return this.stockAvailable;
    }

    public LoansManager getLoansManager() {
        return this.loans;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    public void setImageUrl(String url) {
        this.imageUrl = url;
    }

    public abstract ItemType getType();

}
