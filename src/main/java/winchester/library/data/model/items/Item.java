package winchester.library.data.model.items;

import java.util.ArrayList;

public abstract class Item {

    protected ArrayList<ItemStock> stockAvailable;

    public Item() {
        this.stockAvailable = new ArrayList<>();
    }

    public ArrayList<ItemStock> getStockAvailable() {
        return this.stockAvailable;
    }

    public abstract ItemType getType();

    @Override
    public String toString() {
        return "Item{" +
                "stockAvailable=" + stockAvailable +
                '}';
    }
}
