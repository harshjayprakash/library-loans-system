package winchester.library.data.model.items;

import winchester.library.data.access.DatabaseEntity;
import winchester.library.data.model.util.Exportable;

@DatabaseEntity(table = "books")
public class Book extends Item implements Exportable {

    private final String isbn;
    private final String title;
    private final String author;
    private final int publicationYear;
    private final String publisher;
    private String imageUrl;

    public Book(String isbn, String title, String author, int publicationYear, String publisher, String imageUrl) {
        super();
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.publisher = publisher;
        this.imageUrl = imageUrl;
    }

    public String getIsbn() {
        return this.isbn;
    }

    public String getTitle() {
        return this.title;
    }

    public String getAuthor() {
        return this.author;
    }

    public int getPublicationYear() {
        return this.publicationYear;
    }

    public String getPublisher() {
        return this.publisher;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public ItemType getType() {
        return ItemType.BOOK;
    }

    @Override
    public String toString() {
        StringBuilder itemCopies = new StringBuilder();
        for (ItemStock stock : this.getStockAvailable()) {
            itemCopies.append(stock.toString());
        }
        return String.format(
                """
                Resource : Book
                
                ISBN : %s
                Title : %s
                Author : %s
                Publication Year : %d
                Publisher : %s
                
                Copies:
                %s
                
                Earliest Return : %s
                """, this.isbn, this.title, this.author, this.publicationYear, this.publisher,
                itemCopies.toString(), this.getLoans().getEarliestReturnDate().toString());
    }

    public static Book castFrom(Item item) {
        return (Book) item;
    }
}
