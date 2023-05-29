package winchester.library.presentation.component.card;

import java.util.Optional;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import winchester.library.data.model.items.Book;
import winchester.library.data.model.items.Film;
import winchester.library.data.model.items.Item;
import winchester.library.data.model.loans.Loan;
import winchester.library.presentation.alert.AlertFactory;
import winchester.library.presentation.view.Views;
import winchester.library.presentation.window.IndividualViewWindow;
import winchester.library.service.DataPersistenceManager;
import winchester.library.service.Searcher;

/**
 * A class that provides a control to loan out an item to a customer, specifying the format.
 */
public final class CustomerLoanCard extends Card {

    private VBox loanInformation;
    private Label itemTitleLabel;
    private Label itemFormatLabel;
    private Label itemLoanDateLabel;
    private Label itemDueDateLabel;
    private Label itemReturnLinkLabel;
    private final Item referencedItem;
    private final Loan referencedLoan;

    /**
     * A constructor that takes in a loan to be referenced.
     * @param loan the loan to be referenced.
     */
    public CustomerLoanCard(Loan loan) {
        super();
        this.referencedLoan = loan;
        this.referencedItem = new Searcher().searchItemFromIdentifier(this.referencedLoan.getLoanedItemIdentifier());
        this.initialiseLayouts();
        this.initialiseControls();
        this.bindEventHandlers();
        this.addComponentsToCard();
    }

    /**
     * A method to initialise any layouts used within the card.
     */
    private void initialiseLayouts() {
        this.setPadding(new Insets(10));
        this.actions.setSpacing(10);
        this.loanInformation = new VBox();
        this.loanInformation.getStyleClass().add("background-secondary");
    }

    /**
     * A method initialise any controls used within the card.
     */
    private void initialiseControls() {
        this.itemTitleLabel = new Label();
        this.itemTitleLabel.setText(
                switch(this.referencedItem.getType()) {
                    case BOOK -> Book.castFrom(this.referencedItem).getTitle();
                    case FILM -> Film.castFrom(this.referencedItem).getTitle();
                });
        this.itemFormatLabel = new Label();
        this.itemFormatLabel.setText("Format: " + this.referencedLoan.getLoanedItemFormat().toString());
        this.itemLoanDateLabel = new Label();
        this.itemLoanDateLabel.setText("Loaned On: " + this.referencedLoan.getLoanDate().toString());
        this.itemDueDateLabel = new Label();
        this.itemDueDateLabel.setText("Due On: " + this.referencedLoan.getDueDate().toString());
        this.itemReturnLinkLabel = new Label();
        this.itemReturnLinkLabel.getStyleClass().add("link-label");
        this.itemReturnLinkLabel.setText("Return");
    }

    /**
     * A method to bind and add event handlers to controls.
     */
    private void bindEventHandlers() {
        this.itemReturnLinkLabel.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> this.returnItem());
        this.viewDetailsLabel.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> this.startIndividualLoansWindow());
    }

    /**
     * A method to add the components to the card.
     */
    private void addComponentsToCard() {
        this.actions.getChildren().add(this.itemReturnLinkLabel);
        this.loanInformation.getChildren().addAll(this.itemTitleLabel, this.itemFormatLabel, this.itemLoanDateLabel,
                this.itemDueDateLabel);
        this.setCenter(this.loanInformation);
    }

    /**
     * A method to return the given item, confirming the action with an alert window.
     */
    private void returnItem() {
        Alert messageConfirmation = AlertFactory.createAlert(Alert.AlertType.CONFIRMATION, "Return this item",
                "Item Title: " + this.itemTitleLabel.getText(), ButtonType.NO, ButtonType.YES);
        Optional<ButtonType> optionalButtonType = messageConfirmation.showAndWait();
        if (optionalButtonType.isEmpty()) { return; }
        if (optionalButtonType.get() == ButtonType.YES) {
            if (!DataPersistenceManager.getInstance().returnItem(this.referencedLoan)) {
                AlertFactory.createAlert(Alert.AlertType.ERROR, "Failed to return item.").show();
                return;
            }
            AlertFactory.createAlert(Alert.AlertType.INFORMATION, "Item has been returned").show();
            this.itemReturnLinkLabel.setDisable(true);
            this.itemReturnLinkLabel.setText("Returned");
            this.referencedLoan.setReturned(true);
        }
        else if (optionalButtonType.get() == ButtonType.NO) {
            AlertFactory.createAlert(Alert.AlertType.INFORMATION, "Item return has been cancelled").show();
        }
    }

    /**
     * A method to start a window with the individual loans view.
     */
    private void startIndividualLoansWindow() {
        IndividualViewWindow individualLoanView = new IndividualViewWindow(Views.INDIVIDUAL_LOAN, this.referencedLoan);
        individualLoanView.show();
    }
}
