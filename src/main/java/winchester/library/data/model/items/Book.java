package winchester.library.data.model.items;

import winchester.library.data.access.DatabaseEntity;
import winchester.library.data.model.util.Exportable;

/**
 * A class that models a book entity.
 */
@DatabaseEntity(table = "books")
public class Book extends Item implements Exportable {

    private final String isbn;
    private final String title;
    private final String author;
    private final int publicationYear;
    private final String publisher;

    /**
     * The default constructor for the Book class to specify the book information.
     * @param isbn the ISBN of the book.
     * @param title the title of the book.
     * @param author the author of the book.
     * @param publicationYear the publication year of the book.
     * @param publisher the publisher of the book.
     * @param imageUrl the image url of the for the cover of the book.
     */
    public Book(String isbn, String title, String author, int publicationYear, String publisher, String imageUrl) {
        super();
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.publisher = publisher;
        this.imageUrl = imageUrl;
    }

    /**
     * Casts the base Item class type to a Book class type.
     * @param item the item to be cast.
     * @return the book cast from the specified item.
     */
    public static Book castFrom(Item item) {
        return (Book) item;
    }

    /**
     * An accessor to retrieve the ISBN of the book.
     * @return the ISBN of the book.
     */
    public String getIsbn() {
        return this.isbn;
    }

    /**
     * An accessor to retrieve the title of the book.
     * @return the title of the book.
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * An accessor to retrieve the author of the book.
     * @return the author of the book.
     */
    public String getAuthor() {
        return this.author;
    }

    /**
     * An accessor to retrieve the publication year of the book.
     * @return the publication year of the book.
     */
    public int getPublicationYear() {
        return this.publicationYear;
    }

    /**
     * An accessor to retrieve the publisher of the book.
     * @return the publisher of the book.
     */
    public String getPublisher() {
        return this.publisher;
    }

    /**
     * An accessor to retrieve the item type.
     * @return the ItemType enumeration of BOOK.
     */
    @Override
    public ItemType getType() {
        return ItemType.BOOK;
    }

    /**
     * Converts all information to a string format.
     * @return a string of all the book information.
     */
    @Override
    public String toString() {
        return String.format(
                """
                Resource : Book
                
                ISBN : %s
                Title : %s
                Author : %s
                Publication Year : %d
                Publisher : %s
                """, this.isbn, this.title, this.author, this.publicationYear, this.publisher);
    }

    /**
     * Converts all information to a string format that can be exported to an external file.
     * @return a string of book and loan information.
     */
    @Override
    public String export() {
        StringBuilder itemCopies = new StringBuilder();
        for (ItemStock stock : this.getStockAvailable().getItemStock()) {
            itemCopies.append(stock.toString());
        }
        return String.format(
                """
                %s
                
                Copies:
                %s
                
                Earliest Return : %s
                """, this.toString(), itemCopies.toString(),
                (this.getLoansManager().getEarliestReturnDate().isEmpty())
                        ? "None" : this.getLoansManager().getEarliestReturnDate().get());
    }
}
