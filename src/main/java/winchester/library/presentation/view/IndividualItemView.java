package winchester.library.presentation.view;

import java.util.ArrayList;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import winchester.library.data.model.items.*;
import winchester.library.data.model.util.Exporter;
import winchester.library.presentation.alert.AlertFactory;
import winchester.library.presentation.window.WindowBase;

public final class IndividualItemView extends View {

    private HBox actions;
    private Button exportToFileButton;
    private HBox itemDetails;
    private ImageView itemImageView;
    private final int itemImageViewHeight;
    private final int itemImageViewWidth;
    private Text itemInformation;
    private VBox itemAvailability;
    private Label itemAvailabilityTitleLabel;
    private Label itemFormatLabel;
    private Label itemStockAvailableLabel;
    private Label itemOnLoanLabel;
    private ArrayList<Label> itemFormatLabelList;
    private ArrayList<Label> itemStockAvailableLLabelList;
    private ArrayList<Label> itemsOnLoanLabelList;

    private final Item referencedItem;
    private final Exporter exporter;

    public IndividualItemView(WindowBase parentWindow, Item referencedItem) {
        super(parentWindow, Views.INDIVIDUAL_ITEM.toString());
        this.referencedItem = referencedItem;
        this.itemImageViewWidth = 110;
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
        this.actions.getStyleClass().add("background-secondary");
        this.actions.setPadding(new Insets(10));
        this.itemDetails = new HBox();
        this.itemDetails.getStyleClass().add("background-secondary-border");
        this.itemDetails.setPadding(new Insets(15));
        this.itemAvailability = new VBox();
        this.itemAvailability.setPadding(new Insets(15));
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
        this.itemInformation.setTextAlignment(TextAlignment.LEFT);
        this.itemInformation.getStyleClass().add("background-secondary");
        this.itemInformation.setText(switch (this.referencedItem.getType()) {
            case BOOK -> Book.castFrom(this.referencedItem).toString();
            case FILM -> Film.castFrom(this.referencedItem).toString();
        });
        HBox.setMargin(this.itemImageView, new Insets(0, 15, 0, 0));
        this.itemAvailabilityTitleLabel = new Label();
        this.itemAvailabilityTitleLabel.getStyleClass().add("text-bold");
        this.itemAvailabilityTitleLabel.setText("Item Availability");
        this.itemFormatLabel = new Label();
        this.itemFormatLabel.setText("Format");
        this.itemStockAvailableLabel = new Label();
        this.itemStockAvailableLabel.setText("Stock Available");
        this.itemOnLoanLabel = new Label();
        this.itemOnLoanLabel.setText("On Loan");
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
                    Alert.AlertType.INFORMATION, "Item exported successfully");
            exportSuccess.show();
        });
    }

    @Override
    protected void addComponentsToView() {
        this.actions.getChildren().addAll(this.exportToFileButton);
        this.itemDetails.getChildren().addAll(this.itemImageView, this.itemInformation);
        this.getChildren().addAll(this.actions, this.itemDetails, this.itemAvailability);
    }
}
