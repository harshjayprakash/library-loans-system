package winchester.library.presentation.component.card;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import winchester.library.data.model.items.Book;
import winchester.library.data.model.items.Film;
import winchester.library.data.model.items.Item;
import winchester.library.data.model.items.ItemType;
import winchester.library.presentation.view.Views;
import winchester.library.presentation.window.IndividualViewWindow;

public final class ItemCard extends Card {

    private ImageView itemImageView;
    private final int itemImageViewWidth;
    private final int itemImageViewHeight;
    private VBox itemInformation;
    private Label itemNameLabel;
    private Label itemCreatorLabel;
    private Label itemCategoryLabel;
    private Item referencedItem;

    public ItemCard(Item item) {
        super();
        this.referencedItem = item;
        this.itemImageViewWidth = 60;
        this.itemImageViewHeight = 85;
        this.initialiseLayouts();
        this.initialiseControls();
        this.bindEventHandlers();
        this.addComponentsToCard();
    }

    @Override
    protected void initialiseLayouts() {
        this.itemInformation = new VBox();
        this.itemInformation.setId("background-secondary");
        this.itemInformation.setPadding(new Insets(10));
    }

    @Override
    protected void initialiseControls() {
        this.itemImageView = new ImageView();
        this.itemImageView.setFitWidth(this.itemImageViewWidth);
        this.itemImageView.setFitHeight(this.itemImageViewHeight);
        this.itemNameLabel = new Label();
        this.itemCreatorLabel = new Label();
        this.itemCategoryLabel = new Label();
        this.itemCategoryLabel.setText(this.referencedItem.getType().toString());
        if (this.referencedItem.getType() == ItemType.BOOK) {
            Book castedBook = Book.castFrom(this.referencedItem);
            this.itemImageView.setImage(new Image(
                    castedBook.getImageUrl(), this.itemImageViewWidth, this.itemImageViewHeight, false, false));
            this.itemNameLabel.setText(castedBook.getTitle());
            this.itemCreatorLabel.setText(castedBook.getAuthor());
        }
        else if (this.referencedItem.getType() == ItemType.FILM) {
            Film castedFilm = Film.castFrom(this.referencedItem);
            this.itemImageView.setImage(new Image(
                    castedFilm.getImageUrl(), this.itemImageViewWidth, this.itemImageViewHeight, false, false));
            this.itemNameLabel.setText(castedFilm.getTitle());
            this.itemCreatorLabel.setText(castedFilm.getDirector());
        }
    }

    @Override
    protected void bindEventHandlers() {
        this.viewDetailsLabel.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            IndividualViewWindow individualItemView = new IndividualViewWindow(Views.INDIVIDUAL_ITEM);
            individualItemView.show();
        });
    }

    @Override
    protected void addComponentsToCard() {
        this.itemInformation.getChildren().addAll(this.itemNameLabel, this.itemCreatorLabel, this.itemCategoryLabel);
        this.setLeft(this.itemImageView);
        this.setCenter(this.itemInformation);
    }

}
