package winchester.library.presentation.component;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import winchester.library.data.model.loans.Loan;
import winchester.library.presentation.style.StylesheetSetter;
import winchester.library.presentation.view.Views;
import winchester.library.presentation.window.IndividualViewWindow;

public class LoanCard extends BorderPane {

    private Loan loan;
    private VBox loanInformationLayout;
    private VBox optionsLayout;
    private Label loanIdentifierLabel;
    private Label customerLabel;
    private Label viewDetailsLinkLabel;

    public LoanCard(Loan loan) {
        super();
        this.loan = loan;
        this.setId("background-secondary-border");
        StylesheetSetter.getInstance().setStyle(this);
        this.initialiseLayouts();
        this.initialiseControls();
        this.bindEventHandlers();
        this.addComponentsToCard();
    }

    private void initialiseLayouts() {
        this.loanInformationLayout = new VBox();
        this.loanInformationLayout.setId("background-secondary");
        this.loanInformationLayout.setPadding(new Insets(10));
        this.optionsLayout = new VBox();
        this.optionsLayout.setId("background-secondary");
        this.optionsLayout.setPadding(new Insets(10));
    }

    private void initialiseControls() {
        this.loanIdentifierLabel = new Label();
        this.loanIdentifierLabel.setId("text-bold");
        this.loanIdentifierLabel.setText(String.valueOf(this.loan.getIdentifier()));
        this.customerLabel = new Label();
        this.customerLabel.setText(this.loan.getCustomer().getFullName());
        this.viewDetailsLinkLabel = new Label();
        this.viewDetailsLinkLabel.setId("link-label");
    }

    private void bindEventHandlers() {
        this.viewDetailsLinkLabel.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            IndividualViewWindow individualItemView = new IndividualViewWindow(Views.INDIVIDUAL_ITEM);
            individualItemView.show();
        });
    }

    private void addComponentsToCard() {
        this.setRight(this.optionsLayout);
        this.setCenter(this.loanInformationLayout);
    }

}
