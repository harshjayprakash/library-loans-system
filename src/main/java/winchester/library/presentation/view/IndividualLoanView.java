package winchester.library.presentation.view;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import winchester.library.data.model.loans.Loan;
import winchester.library.presentation.alert.AlertFactory;
import winchester.library.presentation.window.WindowBase;
import winchester.library.service.DataPersistenceManager;
import winchester.library.service.Searcher;

/**
 * A view that shows information about a single loan.
 */
public final class IndividualLoanView extends View {

    private HBox loanActionsLayout;
    private VBox loanInformationLayout;
    private GridPane loanDurationLayout;
    private VBox relatedCustomerLayout;
    private Label loanIdentifierLabel;
    private Text loanItemInformation;
    private Label overdueTitleLabel;
    private Label overdueDayCount;
    private Label returnDateTitleLabel;
    private Label returnDateLabel;
    private Label customerInformation;
    private Button extendLoanButton;
    private final Loan referencedLoan;

    /**
     * The default constructor that passes the parent window and the loan to be shown.
     * @param parentWindow the parent window that the view can access.
     * @param loan the loan to be referenced.
     */
    public IndividualLoanView(WindowBase parentWindow, Loan loan) {
        super(parentWindow, Views.INDIVIDUAL_LOAN.toString());
        this.referencedLoan = loan;
        this.initialiseLayouts();
        this.initialiseControls();
        this.initialiseConstraints();
        this.bindEventHandlers();
        this.addComponentsToView();
    }

    /**
     * A method to initialise any layouts used within the view.
     */
    @Override
    protected void initialiseLayouts() {
        this.setSpacing(10);
        this.loanActionsLayout = new HBox();
        this.loanActionsLayout.getStyleClass().add("background-secondary-border");
        this.loanActionsLayout.setAlignment(Pos.CENTER_LEFT);
        this.loanActionsLayout.setSpacing(10);
        this.loanActionsLayout.setPadding(new Insets(10));
        this.loanInformationLayout = new VBox();
        this.loanInformationLayout.getStyleClass().add("background-secondary-border");
        this.loanInformationLayout.setPadding(new Insets(10));
        this.loanDurationLayout = new GridPane();
        this.loanDurationLayout.getStyleClass().add("background-secondary-border");
        this.loanDurationLayout.setPadding(new Insets(10));
        this.relatedCustomerLayout = new VBox();
        this.relatedCustomerLayout.getStyleClass().add("background-secondary-border");
        this.relatedCustomerLayout.setPadding(new Insets(10));
    }

    /**
     * A method to initialise any controls used within the view.
     */
    @Override
    protected void initialiseControls() {
        this.extendLoanButton = new Button();
        this.extendLoanButton.setText("Extend loan by 7 days");
        if (this.referencedLoan.getReturned()) { this.extendLoanButton.setDisable(true); }
        this.loanIdentifierLabel = new Label();
        this.loanIdentifierLabel.getStyleClass().add("text-bold");
        this.loanIdentifierLabel.setText(String.format("Loan Id : %d", this.referencedLoan.getIdentifier()));
        this.loanItemInformation = new Text();
        this.loanItemInformation.setText(
                new Searcher().searchItemFromIdentifier(this.referencedLoan.getLoanedItemIdentifier()).toString());
        this.overdueTitleLabel = new Label();
        this.overdueTitleLabel.setText("Days Left");
        this.overdueDayCount = new Label();
        this.overdueDayCount.setText(
                (this.referencedLoan.getReturned())
                        ? "Returned" : String.format("%d day(s)", this.referencedLoan.calculateDaysRemaining()));
        this.returnDateTitleLabel = new Label();
        this.returnDateTitleLabel.setText("Return Date");
        this.returnDateLabel = new Label();
        this.returnDateLabel.setText(this.referencedLoan.getDueDate().toString());
        this.customerInformation = new Label();
        this.customerInformation.setText(this.referencedLoan.getCustomer().toString());
    }

    private void initialiseConstraints() {
        GridPane.setConstraints(this.overdueTitleLabel, 1, 1, 1, 1,
                HPos.LEFT, VPos.CENTER, Priority.SOMETIMES, Priority.SOMETIMES, new Insets(3));
        GridPane.setConstraints(this.overdueDayCount, 1, 2, 1, 1,
                HPos.LEFT, VPos.CENTER, Priority.SOMETIMES, Priority.SOMETIMES, new Insets(3));
        GridPane.setConstraints(this.returnDateTitleLabel, 2, 1, 1, 1,
                HPos.LEFT, VPos.CENTER, Priority.SOMETIMES, Priority.SOMETIMES, new Insets(3));
        GridPane.setConstraints(this.returnDateLabel, 2, 2, 1, 1,
                HPos.LEFT, VPos.CENTER, Priority.SOMETIMES, Priority.SOMETIMES, new Insets(3));
    }

    /**
     * A method to bind and add event handlers to components.
     */
    private void bindEventHandlers() {
        this.extendLoanButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> this.extendLoan());
    }

    /**
     * A method to add components to the view.
     */
    @Override
    protected void addComponentsToView() {
        this.loanActionsLayout.getChildren().add(this.extendLoanButton);
        this.loanInformationLayout.getChildren().addAll(this.loanIdentifierLabel, this.loanItemInformation);
        this.loanDurationLayout.getChildren().addAll(this.overdueTitleLabel, this.overdueDayCount,
                this.returnDateTitleLabel, this.returnDateLabel);
        this.relatedCustomerLayout.getChildren().add(this.customerInformation);
        this.getChildren().addAll(
                this.loanActionsLayout, this.loanInformationLayout, this.loanDurationLayout,
                this.relatedCustomerLayout);
    }

    /**
     * A method to extend the loan by seven days and sync it to the database.
     */
    private void extendLoan() {
        this.referencedLoan.extendLoan(7);
        boolean success = DataPersistenceManager.getInstance().extendLoan(this.referencedLoan);
        if (!success) {
            AlertFactory.createAlert(Alert.AlertType.ERROR, "Failed to extend loan").show();
            return;
        }
        AlertFactory.createAlert(Alert.AlertType.INFORMATION, "Loan has been successfully extended.").show();
        this.overdueDayCount.setText(String.format("%d day(s)", this.referencedLoan.calculateDaysRemaining()));
        this.returnDateLabel.setText(this.referencedLoan.getDueDate().toString());
    }

}
