package winchester.library.presentation.view;

import java.util.ArrayList;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import winchester.library.data.model.loans.Loan;
import winchester.library.data.model.users.Customer;
import winchester.library.presentation.component.card.CustomerLoanCard;
import winchester.library.presentation.component.pane.ActionPane;
import winchester.library.presentation.window.IndividualViewWindow;
import winchester.library.presentation.window.WindowBase;
import winchester.library.service.DataPersistenceManager;

/**
 * A view that shows a customer's information with addition to their loans.
 */
public final class IndividualCustomerView extends View {

    private ActionPane actionsLayout;
    private VBox customerInformationLayout;
    private Text customerInformation;
    private Label customerOverduePrice;
    private Button addLoansButton;
    private ScrollPane customerLoansLayoutScrollPane;
    private VBox customerLoansLayout;
    private ArrayList<CustomerLoanCard> customerLoanCardsList;
    private final Customer referencedCustomer;

    /**
     * The default constructor that passes the parent window and customer that should be viewed.
     * @param parentWindow the parent window that the view can access.
     * @param customer the referenced customer.
     */
    public IndividualCustomerView(WindowBase parentWindow, Customer customer) {
        super(parentWindow, Views.INDIVIDUAL_CUSTOMER.toString());
        this.referencedCustomer = customer;
        this.initialiseLayouts();
        this.initialiseControls();
        this.bindEventHandlers();
        this.addComponentsToView();
    }

    /**
     * A method to initialise any layouts used within the view.
     */
    @Override
    protected void initialiseLayouts() {
        this.setSpacing(10);
        this.actionsLayout = new ActionPane();
        this.customerInformationLayout = new VBox();
        this.customerInformationLayout.getStyleClass().add("background-secondary-border");
        this.customerInformationLayout.setSpacing(10);
        this.customerInformationLayout.setPadding(new Insets(10));
        this.customerLoansLayoutScrollPane = new ScrollPane();
        this.customerLoansLayoutScrollPane.getStyleClass().add("background-primary");
        this.customerLoansLayoutScrollPane.setFitToWidth(true);
        this.customerLoansLayout = new VBox();
        this.customerLoansLayout.getStyleClass().add("background-primary");
        this.customerLoansLayout.setSpacing(10);
    }

    /**
     * A method to initialise any controls used within the view.
     */
    @Override
    protected void initialiseControls() {
        this.customerInformation = new Text();
        this.customerInformation.setText(this.referencedCustomer.toString());
        this.customerOverduePrice = new Label();
        this.customerOverduePrice.setText(
                String.format("Overdue Fees: £%.2f", this.referencedCustomer.getOverdueFeesAsPounds()));
        this.addLoansButton = new Button();
        this.addLoansButton.setText("Add Loans");
        this.customerLoanCardsList = new ArrayList<>();
        ArrayList<Loan> loans = DataPersistenceManager.getInstance().getLoans();
        if (loans.isEmpty()) { return; }
        loans.stream()
                .filter(loan -> loan.getCustomer().getIdentifier() == this.referencedCustomer.getIdentifier()
                        && !loan.getReturned())
                .forEach(loan -> this.customerLoanCardsList.add(new CustomerLoanCard(loan)));
    }

    /**
     * A method to bind and add event handlers to components.
     */
    private void bindEventHandlers() {
        this.addLoansButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> this.startLoaningItemsWindow());
    }

    /**
     * A method to add components to the view.
     */
    @Override
    protected void addComponentsToView() {
        this.customerLoansLayout.getChildren().addAll(this.customerLoanCardsList);
        this.actionsLayout.getLeftControls().getChildren().add(this.addLoansButton);
        this.customerInformationLayout.getChildren().addAll(this.customerInformation, this.customerOverduePrice);
        this.customerLoansLayoutScrollPane.setContent(this.customerLoansLayout);
        this.getChildren().addAll(this.actionsLayout, this.customerInformationLayout,
                this.customerLoansLayoutScrollPane);
    }

    /**
     * A method to start a window with the loaning items view.
     */
    private void startLoaningItemsWindow() {
        IndividualViewWindow loaningView = new IndividualViewWindow(Views.LOANING_ITEMS, this.referencedCustomer);
        loaningView.show();
    }

}
