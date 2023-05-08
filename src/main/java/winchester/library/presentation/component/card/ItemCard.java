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
import winchester.library.service.Logger;

/**
 * A class that provide a control for displaying item information.
 */
public final class ItemCard extends Card {

    private ImageView itemImageView;
    private final int itemImageViewWidth;
    private final int itemImageViewHeight;
    private VBox itemInformation;
    private Label itemNameLabel;
    private Label itemCreatorLabel;
    private Label itemCategoryLabel;
    private final Item referencedItem;

    /**
     * The default constructor for the ItemCard class.
     * @param item the item that will be referenced to display details.
     */
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

    /**
     * A method to initialise the layouts that will be used within the card component.
     */
    @Override
    protected void initialiseLayouts() {
        this.itemInformation = new VBox();
        this.itemInformation.getStyleClass().add("background-secondary");
        this.itemInformation.setPadding(new Insets(10));
    }

    /**
     * A method to initialise the controls that will be shown within the card component.
     */
    @Override
    protected void initialiseControls() {
        this.itemImageView = new ImageView();
        this.itemImageView.setFitWidth(this.itemImageViewWidth);
        this.itemImageView.setFitHeight(this.itemImageViewHeight);
        try {
            this.itemImageView.setImage(new Image(
                    this.referencedItem.getImageUrl(), this.itemImageViewWidth, this.itemImageViewHeight, false, false));
        }
        catch (NullPointerException exception) {
            Logger.getInstance().PrintError(
                    this.getClass().getName(),
                    "Loading Item Image",
                    "Url is not available",
                    "Please ensure that the url provided is valid");
        }
        catch (IllegalArgumentException exception) {
            Logger.getInstance().PrintError(
                    this.getClass().getName(),
                    "Loading Item Image",
                    "Url is not available or unsupported image type",
                    "Please ensure that the url is valid has a standard image format such as png or jpeg");
        }
        this.itemNameLabel = new Label();
        this.itemCreatorLabel = new Label();
        this.itemCategoryLabel = new Label();
        this.itemCategoryLabel.setText(this.referencedItem.getType().toString());
        if (this.referencedItem.getType() == ItemType.BOOK) {
            Book castedBook = Book.castFrom(this.referencedItem);
            this.itemNameLabel.setText(castedBook.getTitle());
            this.itemCreatorLabel.setText(castedBook.getAuthor());
        }
        else if (this.referencedItem.getType() == ItemType.FILM) {
            Film castedFilm = Film.castFrom(this.referencedItem);
            this.itemNameLabel.setText(castedFilm.getTitle());
            this.itemCreatorLabel.setText(castedFilm.getDirector());
        }
    }

    /**
     * A method to add event handlers to any controls.
     */
    @Override
    protected void bindEventHandlers() {
        this.viewDetailsLabel.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> this.startIndividualItemWindow());
    }

    /**
     * A method to add any layouts and controls initialised to the card.
     */
    @Override
    protected void addComponentsToCard() {
        this.itemInformation.getChildren().addAll(this.itemNameLabel, this.itemCreatorLabel, this.itemCategoryLabel);
        this.setLeft(this.itemImageView);
        this.setCenter(this.itemInformation);
    }

    /**
     * A method to start a window with the individual item view.
     */
    private void startIndividualItemWindow() {
        IndividualViewWindow individualItemView = new IndividualViewWindow(Views.INDIVIDUAL_ITEM, this.referencedItem);
        individualItemView.show();
    }

}
