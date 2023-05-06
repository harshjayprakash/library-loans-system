package winchester.library.data.model.items;

import java.util.ArrayList;
import java.util.Optional;

/**
 * A class that offers an abstraction handling the item's stock.
 */
public class ItemStockManager {

    private final ArrayList<ItemStock> itemStockList;

    /**
     * The default constructor to initialise the array list.
     */
    public ItemStockManager() {
        this.itemStockList = new ArrayList<>();
    }

    /**
     * An accessor to retrieve the item stock array list.
     * @return the array list of item stock.
     */
    public ArrayList<ItemStock> getItemStock() {
        return this.itemStockList;
    }

    /**
     * A method to find a specific format of item in the array list of item stock.
     * @param format the format of item to be found.
     * @return an individual item's stock based on the format given.
     */
    public Optional<ItemStock> getItemStockFromItemFormat(ItemFormat format) {
        for (ItemStock itemStock : this.itemStockList) {
            if (itemStock.getItemFormat() == format) {
                return Optional.of(itemStock);
            }
        }
        return Optional.empty();
    }

    /**
     * Adds to the item stock array list.
     * @param itemStock the item stock instance to be added.
     */
    public void addItemStock(ItemStock itemStock) {
        if (itemStock == null) { return; }
        this.itemStockList.add(itemStock);
    }
}
