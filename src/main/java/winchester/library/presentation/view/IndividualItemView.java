package winchester.library.presentation.view;

import java.util.ArrayList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import winchester.library.data.model.items.Book;
import winchester.library.data.model.items.Film;
import winchester.library.data.model.items.Item;
import winchester.library.data.model.items.ItemStock;
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
    private GridPane itemAvailability;
    private Label itemFormatLabel;
    private Label itemStockAvailableLabel;
    private Label itemOnLoanLabel;
    private ArrayList<Label> itemFormatLabelList;
    private ArrayList<Label> itemStockAvailableLLabelList;
    private ArrayList<Label> itemOnLoanLabelList;
    private VBox itemReturnDate;
    private Label itemEarliestReturnTitleLabel;
    private Label itemEarliestReturnLabel;
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
        this.initialiseConstraints();
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
        this.itemAvailability = new GridPane();
        this.itemAvailability.getStyleClass().add("background-secondary-border");
        this.itemAvailability.setPadding(new Insets(15));
        this.itemReturnDate = new VBox();
        this.itemReturnDate.getStyleClass().add("background-secondary-border");
        this.itemReturnDate.setPadding(new Insets(15));
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
        this.itemFormatLabel = new Label();
        this.itemFormatLabel.setText("Format");
        this.itemStockAvailableLabel = new Label();
        this.itemStockAvailableLabel.setText("Stock Available");
        this.itemOnLoanLabel = new Label();
        this.itemOnLoanLabel.setText("On Loan");
        this.itemEarliestReturnTitleLabel = new Label();
        this.itemEarliestReturnTitleLabel.getStyleClass().add("text-bold");
        this.itemEarliestReturnTitleLabel.setText("Earliest Return Date");
        this.itemEarliestReturnLabel = new Label();
        this.itemEarliestReturnLabel.setText(
                (this.referencedItem.getLoansManager().getEarliestReturnDate().isEmpty())
                ? "None" : this.referencedItem.getLoansManager().getEarliestReturnDate().get().toString());
        this.itemFormatLabelList = new ArrayList<>();
        this.itemStockAvailableLLabelList = new ArrayList<>();
        this.itemOnLoanLabelList = new ArrayList<>();
        for (ItemStock stock : this.referencedItem.getStockAvailable()) {
            this.itemFormatLabelList.add(new Label(stock.getItemFormat().toString()));
            this.itemStockAvailableLLabelList.add(new Label(String.valueOf(stock.getCopiesAvailable())));
            this.itemOnLoanLabelList.add(new Label(String.valueOf(stock.getCopiesOnLoan())));
        }
    }

    private void initialiseConstraints() {
        HPos horizontalAlign = HPos.LEFT;
        VPos verticalAlign = VPos.CENTER;
        Priority resizePriority = Priority.SOMETIMES;
        Insets padding = new Insets(3);
        GridPane.setConstraints(this.itemFormatLabel, 1, 1, 1, 1,
                horizontalAlign, verticalAlign, resizePriority, resizePriority, padding);
        GridPane.setConstraints(this.itemStockAvailableLabel, 2, 1, 1, 1,
                horizontalAlign, verticalAlign, resizePriority, resizePriority, padding);
        GridPane.setConstraints(this.itemOnLoanLabel, 3, 1, 1, 1,
                horizontalAlign, verticalAlign, resizePriority, resizePriority, padding);
        for (int i = 0; i < this.referencedItem.getStockAvailable().size(); i++) {
            GridPane.setConstraints(this.itemFormatLabelList.get(i), 1, i+2, 1, 1,
                    horizontalAlign, verticalAlign, resizePriority, resizePriority, padding);
            GridPane.setConstraints(this.itemStockAvailableLLabelList.get(i), 2, i+2, 1, 1,
                    horizontalAlign, verticalAlign, resizePriority, resizePriority, padding);
            GridPane.setConstraints(this.itemOnLoanLabelList.get(i), 3, i+2, 1, 1,
                    horizontalAlign, verticalAlign, resizePriority, resizePriority, padding);
        }
    }

    private void bindEventHandlers() {
        this.exportToFileButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            boolean exportResult = switch (this.referencedItem.getType()) {
                case BOOK -> exporter.export(Book.castFrom(this.referencedItem));
                case FILM -> exporter.export(Film.castFrom(this.referencedItem));
            };
            if (!exportResult) {
                AlertFactory.createAlert(
                        Alert.AlertType.ERROR, "Failed to export item to file.",
                        "Please ensure that the program has permissions to the file system.").show();
                return;
            }
            AlertFactory.createAlert(
                    Alert.AlertType.INFORMATION, "Item exported successfully",
                    String.format("The export can be found at %s", "the Project Directory Exports Folder")).show();
        });
    }

    @Override
    protected void addComponentsToView() {
        this.itemReturnDate.getChildren().addAll(this.itemEarliestReturnTitleLabel, this.itemEarliestReturnLabel);
        this.itemAvailability.getChildren().addAll(
                this.itemFormatLabel, this.itemStockAvailableLabel,
                this.itemOnLoanLabel);
        this.itemAvailability.getChildren().addAll(this.itemFormatLabelList);
        this.itemAvailability.getChildren().addAll(this.itemStockAvailableLLabelList);
        this.itemAvailability.getChildren().addAll(this.itemOnLoanLabelList);
        this.actions.getChildren().addAll(this.exportToFileButton);
        this.itemDetails.getChildren().addAll(this.itemImageView, this.itemInformation);
        this.getChildren().addAll(this.actions, this.itemDetails, this.itemAvailability, this.itemReturnDate);
    }
}
