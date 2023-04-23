package winchester.library.presentation.component;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import winchester.library.data.model.items.Book;
import winchester.library.data.model.items.Film;
import winchester.library.data.model.items.Item;
import winchester.library.data.model.items.ItemType;
import winchester.library.presentation.style.StylesheetSetter;

public class ItemCard extends BorderPane {

    private ImageView itemImage;
    private VBox itemInformationLayout;
    private VBox optionsLayout;
    private Label itemNameLabel;
    private Label authorOrDirectorLabel;
    private Label itemCategoryLabel;
    private Label stockCountLabel;
    private Label detailsLabel;
    private Item item;

    public ItemCard(Item item) {
        super();
        this.item = item;
        this.setId("background-secondary");
        StylesheetSetter.getInstance().setStyle(this);
        this.initialiseLayouts();
        this.initialiseControls();
        this.bindEventHandlers();
        this.addComponentsToCard();
    }

    private void initialiseLayouts() {
        this.itemInformationLayout = new VBox();
        this.itemInformationLayout.setId("background-secondary");
        this.optionsLayout = new VBox();
        this.optionsLayout.setId("background-secondary");
    }

    private void initialiseControls() {
        this.itemImage = new ImageView();
        this.itemImage.setFitWidth(45);
        this.itemImage.setFitHeight(45);
        this.itemNameLabel = new Label();
        this.itemNameLabel.setText("ItemCard->itemNameLabel");
        this.authorOrDirectorLabel = new Label();
        this.authorOrDirectorLabel.setText("ItemCard->authorOrDirectorLabel");
        this.itemCategoryLabel = new Label();
        this.itemCategoryLabel.setText(this.item.getType().toString());
        this.stockCountLabel = new Label();
        this.detailsLabel = new Label();
        this.detailsLabel.setId("link-label");
        this.detailsLabel.setText("View Details");
        if (this.item.getType() == ItemType.BOOK) {
            Book book = (Book) this.item;
            this.itemImage.setImage(new Image(book.getImageUrl(), 100, 100, false, false));
            this.itemNameLabel.setText(book.getTitle());
            this.authorOrDirectorLabel.setText(book.getAuthor());
        }
        if (this.item.getType() == ItemType.FILM) {
            Film film = (Film) this.item;
            this.itemNameLabel.setText(film.getTitle());
            this.authorOrDirectorLabel.setText(film.getDirector());
        }
    }

    private void bindEventHandlers() {
        this.detailsLabel.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            IndividualViewWindow itemView = new IndividualViewWindow(Views.INDIVIDUAL_ITEM);
            itemView.show();
        });
    }

    private void addComponentsToCard() {
        this.optionsLayout.getChildren().addAll(this.detailsLabel);
        this.itemInformationLayout.getChildren().addAll(
                this.itemNameLabel, this.authorOrDirectorLabel, this.itemCategoryLabel, this.stockCountLabel);
        this.setRight(this.optionsLayout);
        this.setCenter(this.itemInformationLayout);
    }

}
