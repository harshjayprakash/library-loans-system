package winchester.library.presentation.view;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import winchester.library.data.model.loans.Loan;
import winchester.library.presentation.window.WindowBase;
import winchester.library.service.Searcher;

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
    private Button printToFileButton;
    private Button extendLoanButton;
    private Loan referencedLoan;

    public IndividualLoanView(WindowBase parentWindow, Loan loan) {
        super(parentWindow, Views.INDIVIDUAL_LOAN.toString());
        this.referencedLoan = loan;
        this.initialiseLayouts();
        this.initialiseControls();
        this.initialiseConstraints();
        this.addComponentsToView();
    }

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

    @Override
    protected void initialiseControls() {
        this.printToFileButton = new Button();
        this.printToFileButton.setText("Print to file");
        this.extendLoanButton = new Button();
        this.extendLoanButton.setText("Extend loan");
        this.loanIdentifierLabel = new Label();
        this.loanIdentifierLabel.getStyleClass().add("text-bold");
        this.loanIdentifierLabel.setText(String.format("Loan Id : %d", this.referencedLoan.getIdentifier()));
        this.loanItemInformation = new Text();
        this.loanItemInformation.setText(
                new Searcher().searchItemFromIdentifier(this.referencedLoan.getLoanedItemIdentifier()).toString());
        this.overdueTitleLabel = new Label();
        this.overdueTitleLabel.setText("Days Left");
        this.overdueDayCount = new Label();
        this.overdueDayCount.setText(String.format("%d day(s)", this.referencedLoan.calculateDaysRemaining()));
        this.returnDateTitleLabel = new Label();
        this.returnDateTitleLabel.setText("Return Date");
        this.returnDateLabel = new Label();
        this.returnDateLabel.setText(this.referencedLoan.getDueDate().toString());
        this.customerInformation = new Label();
        this.customerInformation.setText(this.referencedLoan.getCustomer().toString());
    }

    private void initialiseConstraints() {
        HPos horizontalAlign = HPos.LEFT;
        VPos verticalAlign = VPos.CENTER;
        Priority resizePriority = Priority.SOMETIMES;
        Insets padding = new Insets(3);
        GridPane.setConstraints(this.overdueTitleLabel, 1, 1, 1, 1,
                horizontalAlign, verticalAlign, resizePriority, resizePriority, padding);
        GridPane.setConstraints(this.overdueDayCount, 1, 2, 1, 1,
                horizontalAlign, verticalAlign, resizePriority, resizePriority, padding);
        GridPane.setConstraints(this.returnDateTitleLabel, 2, 1, 1, 1,
                horizontalAlign, verticalAlign, resizePriority, resizePriority, padding);
        GridPane.setConstraints(this.returnDateLabel, 2, 2, 1, 1,
                horizontalAlign, verticalAlign, resizePriority, resizePriority, padding);
    }

    @Override
    protected void addComponentsToView() {
        this.loanActionsLayout.getChildren().addAll(this.printToFileButton, this.extendLoanButton);
        this.loanInformationLayout.getChildren().addAll(this.loanIdentifierLabel, this.loanItemInformation);
        this.loanDurationLayout.getChildren().addAll(this.overdueTitleLabel, this.overdueDayCount,
                this.returnDateTitleLabel, this.returnDateLabel);
        this.relatedCustomerLayout.getChildren().add(this.customerInformation);
        this.getChildren().addAll(
                this.loanActionsLayout, this.loanInformationLayout, this.loanDurationLayout,
                this.relatedCustomerLayout);
    }

}
