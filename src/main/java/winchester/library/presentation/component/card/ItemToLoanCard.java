package winchester.library.presentation.component.card;

import java.util.Optional;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import winchester.library.data.model.items.Book;
import winchester.library.data.model.items.Film;
import winchester.library.data.model.items.Item;
import winchester.library.data.model.items.ItemFormat;
import winchester.library.data.model.items.ItemStock;
import winchester.library.data.model.items.ItemType;
import winchester.library.data.model.users.Customer;
import winchester.library.presentation.alert.AlertFactory;
import winchester.library.service.DataPersistenceManager;

/**
 * A class that provides a control to display item information and the ability to loan a specific format.
 */
public class ItemToLoanCard extends Card {

    private VBox itemInformationLayout;
    private Text itemInformation;
    private Label addLoanLinkLabel;
    private Label itemFormatLabel;
    private Label itemStockAvailable;
    private ComboBox<String> itemFormatOptionsComboBox;
    private final Customer referencedCustomer;
    private final Item referencedItem;

    /**
     * The default constructor for the ItemToLoan class.
     * @param item the item that will be referenced to display details or loan out.
     * @param customer the customer that will be loaning out the referenced item.
     */
    public ItemToLoanCard(Item item, Customer customer) {
        super();
        this.referencedItem = item;
        this.referencedCustomer = customer;
        this.initialiseLayouts();
        this.initialiseControls();
        this.bindEventHandlers();
        this.addComponentsToCard();
    }

    /**
     * A method to initialise the layouts that will be used within the card component.
     */
    private void initialiseLayouts() {
        this.actions.setSpacing(20);
        this.itemInformationLayout = new VBox();
        this.itemInformationLayout.setPadding(new Insets(10));
    }

    /**
     * A method to initialise the controls that will be shown within the card component.
     */
    private void initialiseControls() {
        this.itemInformation = new Text();
        this.addLoanLinkLabel = new Label();
        this.addLoanLinkLabel.getStyleClass().add("link-label");
        this.addLoanLinkLabel.setText("Add Loan");
        this.itemFormatLabel = new Label();
        this.itemFormatLabel.setText("Select a format");
        this.itemFormatOptionsComboBox = new ComboBox<>();
        this.itemStockAvailable = new Label();
        if (this.referencedItem.getType() == ItemType.BOOK) {
            this.itemInformation.setText(Book.castFrom(this.referencedItem).toString());
            this.itemFormatOptionsComboBox.getItems().addAll(
                    ItemFormat.AUDIO_BOOK.toString(),
                    ItemFormat.PHYSICAL_BOOK.toString(),
                    ItemFormat.LARGE_PRINT_BOOK.toString(),
                    ItemFormat.ELECTRONIC_BOOK.toString());
        }
        else if (this.referencedItem.getType() == ItemType.FILM) {
            this.itemInformation.setText(Film.castFrom(this.referencedItem).toString());
            this.itemFormatOptionsComboBox.getItems().addAll(
                    ItemFormat.DVD_FILM.toString(),
                    ItemFormat.BLU_RAY_FILM.toString());
        }
    }

    /**
     * A method to add event handlers to any controls.
     */
    private void bindEventHandlers() {
        this.itemFormatOptionsComboBox.valueProperty().addListener((options, oldValue, newValue) ->
                this.displayStockAvailableForFormat(newValue));
        this.addLoanLinkLabel.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> this.loanItem());
    }

    /**
     * A method to add any layouts and controls initialised to the card.
     */
    private void addComponentsToCard() {
        this.actions.getChildren().clear();
        this.actions.getChildren().addAll(this.itemFormatLabel, this.itemFormatOptionsComboBox, this.itemStockAvailable,
                this.addLoanLinkLabel);
        this.itemInformationLayout.getChildren().add(this.itemInformation);
        this.setCenter(this.itemInformationLayout);
    }

    /**
     * A method to get the format chosen from the combo box as a ItemFormat enumeration constant.
     * @return a ItemFormat enumeration corresponding to the combo box value selected.
     */
    private Optional<ItemFormat> getFormatChosen() {
        String value = this.itemFormatOptionsComboBox.getValue();
        if (value == null) { return Optional.empty(); }
        for (ItemFormat format : ItemFormat.values()) {
            if (value.equals(format.toString())) {
                return Optional.of(format);
            }
        }
        return Optional.empty();
    }

    /**
     * A method to display the stock available for the given item and format.
     */
    private void displayStockAvailableForFormat(String comboboxValue) {
        ItemStock itemStock = this.referencedItem.getStockAvailable().getItemStockFromItemFormat(
                ItemFormat.fromString(comboboxValue).orElse(null)).orElse(null);
        if (itemStock == null) { return; }
        this.addLoanLinkLabel.setDisable(itemStock.getCopiesOnLoan() == itemStock.getCopiesAvailable());
        this.itemStockAvailable.setText(String.format(
                "Stock Available: %d", (itemStock.getCopiesAvailable() - itemStock.getCopiesOnLoan())));
    }

    /**
     * A method to loan out the given item, checking the chosen format.
     */
    private void loanItem() {
        if (this.getFormatChosen().isEmpty()) {
            AlertFactory.createAlert(Alert.AlertType.ERROR, "Please choose a format to loan out").show();
            return;
        }
        boolean success = DataPersistenceManager.getInstance().createLoan(
                this.referencedItem, this.getFormatChosen().get(), this.referencedCustomer);
        if (!success) {
            AlertFactory.createAlert(Alert.AlertType.ERROR, "Failed to loan item out").show();
            return;
        }
        AlertFactory.createAlert(Alert.AlertType.INFORMATION, "Item successfully loan out.").show();
    }
}
