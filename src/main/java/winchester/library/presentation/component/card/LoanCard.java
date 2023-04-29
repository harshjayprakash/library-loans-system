package winchester.library.presentation.component.card;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import winchester.library.data.model.loans.Loan;
import winchester.library.presentation.view.Views;
import winchester.library.presentation.window.IndividualViewWindow;

/**
 * A class that provides a control to view loan information.
 */
public final class LoanCard extends Card {

    private VBox loanInformation;
    private Label loanIdentifierLabel;
    private Label customerNameLabel;
    private final Loan referencedLoan;

    public LoanCard(Loan loan) {
        super();
        this.referencedLoan = loan;
        this.initialiseLayouts();
        this.initialiseControls();
        this.bindEventHandlers();
        this.addComponentsToCard();
    }

    @Override
    protected void initialiseLayouts() {
        this.loanInformation = new VBox();
        this.loanInformation.setId("background-secondary");
        this.loanInformation.setPadding(new Insets(10));
    }

    @Override
    protected void initialiseControls() {
        this.loanIdentifierLabel = new Label();
        this.loanIdentifierLabel.setId("text-bold");
        this.loanIdentifierLabel.setText(String.valueOf(this.referencedLoan.getIdentifier()));
        this.customerNameLabel = new Label();
        this.customerNameLabel.setText(this.referencedLoan.getCustomer().getFullName());
    }

    @Override
    protected void bindEventHandlers() {
        this.viewDetailsLabel.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            IndividualViewWindow individualItemView = new IndividualViewWindow(Views.INDIVIDUAL_LOAN);
            individualItemView.show();
        });
    }

    @Override
    protected void addComponentsToCard() {
        this.loanInformation.getChildren().addAll(this.loanIdentifierLabel, this.customerNameLabel);
        this.setCenter(this.loanInformation);
    }
}