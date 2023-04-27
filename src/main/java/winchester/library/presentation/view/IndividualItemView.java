package winchester.library.presentation.view;

import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import winchester.library.data.model.items.Book;
import winchester.library.data.model.items.Film;
import winchester.library.data.model.items.Item;
import winchester.library.data.model.items.ItemType;
import winchester.library.data.model.util.Exporter;
import winchester.library.presentation.alert.AlertFactory;
import winchester.library.presentation.window.WindowBase;

public final class IndividualItemView extends View {

    private HBox actions;
    private Button exportToFileButton;
    private BorderPane itemDetails;
    private ImageView itemImageView;
    private final int itemImageViewHeight;
    private final int itemImageViewWidth;
    private Text itemInformation;
    private GridPane itemAvailability;
    private Label itemFormatLabel;
    private Label stockAvailableLabel;
    private Label onLoanLabel;
    private final Item referencedItem;
    private final Exporter exporter;

    public IndividualItemView(WindowBase parentWindow, Item referencedItem) {
        super(parentWindow, Views.INDIVIDUAL_ITEM.toString());
        this.referencedItem = referencedItem;
        this.itemImageViewWidth = 120;
        this.itemImageViewHeight = 150;
        this.exporter = new Exporter();
        this.initialiseLayouts();
        this.initialiseControls();
        this.bindEventHandlers();
        this.addComponentsToView();
    }

    @Override
    protected void initialiseLayouts() {
        this.setSpacing(10);
        this.actions = new HBox();
        this.actions.setId("background-secondary");
        this.actions.setPadding(new Insets(15));
        this.itemDetails = new BorderPane();
        this.itemDetails.setId("background-secondary-border");
        this.itemDetails.setPadding(new Insets(15));
    }

    @Override
    protected void initialiseControls() {
        this.exportToFileButton = new Button();
        this.exportToFileButton.setText("Export to File");
        this.itemImageView = new ImageView();
        this.itemImageView.setFitWidth(this.itemImageViewWidth);
        this.itemImageView.setFitHeight(this.itemImageViewHeight);
        this.itemImageView.setImage(new Image(
                this.referencedItem.getImageUrl(), this.itemImageViewWidth, this.itemImageViewHeight, false, false));
        this.itemInformation = new Text();
        this.itemInformation.setId("background-secondary");
        this.itemInformation.setText(switch (this.referencedItem.getType()) {
            case BOOK -> Book.castFrom(this.referencedItem).toString();
            case FILM -> Film.castFrom(this.referencedItem).toString();
        });
    }

    private void bindEventHandlers() {
        this.exportToFileButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            boolean exportResult = switch (this.referencedItem.getType()) {
                case BOOK -> exporter.export(Book.castFrom(this.referencedItem));
                case FILM -> exporter.export(Film.castFrom(this.referencedItem));
            };
            if (!exportResult) {
                Alert exportError = AlertFactory.createAlert(
                        Alert.AlertType.ERROR, "Failed to export item to file.",
                        "Please ensure that the program has permissions to the file system.");
                exportError.show();
                return;
            }
            Alert exportSuccess = AlertFactory.createAlert(
                    Alert.AlertType.INFORMATION, "Item exported successfully", "");
            exportSuccess.show();
        });
    }

    @Override
    protected void addComponentsToView() {
        this.actions.getChildren().addAll(this.exportToFileButton);
        this.itemDetails.setLeft(this.itemImageView);
        this.getChildren().addAll(this.actions, this.itemDetails);
    }
}
