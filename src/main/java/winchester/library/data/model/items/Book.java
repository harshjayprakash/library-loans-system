package winchester.library.data.model.items;

public class Book extends Item {
    private final BookFormat format;
    private final String isbn;
    private final String author;
    private final int edition;

    public Book(int identifier, BookFormat format, String isbn, String name, String author, int releaseYear,
                int edition) {
        super(identifier, name, releaseYear);
        this.format = format;
        this.isbn = isbn;
        this.author = author;
        this.edition = edition;
    }

    public BookFormat getFormat() {
        return this.format;
    }

    public String getIsbn() {
        return this.isbn;
    }

    public String getAuthor() {
        return this.author;
    }

    public int getEdition() {
        return this.edition;
    }

    @Override
    public ItemType getType() {
        return ItemType.BOOK;
    }
}
