package winchester.library.presentation.view;

import java.util.ArrayList;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import winchester.library.data.model.loans.Loan;
import winchester.library.data.model.users.Customer;
import winchester.library.presentation.component.pane.ActionPane;
import winchester.library.presentation.window.IndividualViewWindow;
import winchester.library.presentation.window.WindowBase;
import winchester.library.service.DataPersistenceManager;

public final class IndividualCustomerView extends View {

    private ActionPane actionsLayout;
    private VBox customerInformationLayout;
    private Text customerInformation;
    private Button addLoansButton;
    private VBox customerLoansLayoutContainer;
    private GridPane customerLoansLayout;
    private final Customer referencedCustomer;

    public IndividualCustomerView(WindowBase parentWindow, Customer customer) {
        super(parentWindow, Views.INDIVIDUAL_CUSTOMER.toString());
        this.referencedCustomer = customer;
        this.initialiseLayouts();
        this.initialiseControls();
        this.bindEventHandlers();
        this.addComponentsToView();
    }

    @Override
    protected void initialiseLayouts() {
        this.setSpacing(10);
        this.actionsLayout = new ActionPane();
        this.customerInformationLayout = new VBox();
        this.customerInformationLayout.getStyleClass().add("background-secondary-border");
        this.customerInformationLayout.setSpacing(10);
        this.customerInformationLayout.setPadding(new Insets(10));
        this.customerLoansLayoutContainer = new VBox();
        this.customerLoansLayoutContainer.getStyleClass().add("background-secondary-border");
        this.customerLoansLayoutContainer.setPadding(new Insets(10));
        this.customerLoansLayout = new GridPane();
    }

    @Override
    protected void initialiseControls() {
        this.customerInformation = new Text();
        this.customerInformation.setText(this.referencedCustomer.toString());
        this.addLoansButton = new Button();
        this.addLoansButton.setText("Add Loans");
        ArrayList<Loan> loans = DataPersistenceManager.getInstance().getLoans();
        if (loans.isEmpty()) { return; }
    }
    private void bindEventHandlers() {
        this.addLoansButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            IndividualViewWindow loaningView = new IndividualViewWindow(Views.LOANING_ITEMS, this.referencedCustomer);
            loaningView.show();
        });
    }

    @Override
    protected void addComponentsToView() {
        this.actionsLayout.getLeftControls().getChildren().add(this.addLoansButton);
        this.customerInformationLayout.getChildren().add(this.customerInformation);
        this.customerLoansLayoutContainer.getChildren().add(this.customerLoansLayout);
        this.getChildren().addAll(this.actionsLayout, this.customerInformationLayout,
                this.customerLoansLayoutContainer);
    }

}
