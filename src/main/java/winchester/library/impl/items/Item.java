package winchester.library.impl.items;

public abstract class Item {
    protected final int identifier;
    protected final String name;
    protected final int releaseYear;

    public Item(int identifier, String name, int releaseYear) {
        this.identifier = identifier;
        this.name = name;
        this.releaseYear = releaseYear;
    }

    public int getIdentifier() {
        return this.identifier;
    }

    public String getName() {
        return this.name;
    }

    public int getReleaseYear() {
        return this.releaseYear;
    }

    public abstract ItemType getType();
}
