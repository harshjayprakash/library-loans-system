package winchester.library.presentation.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import winchester.library.data.model.users.Employee;
import winchester.library.data.model.users.EmployeeStatus;
import winchester.library.presentation.alert.AlertFactory;
import winchester.library.presentation.window.WindowBase;
import winchester.library.service.DataPersistenceManager;

/**
 * A view to display the employee's information, having the ability to approve or disable an account.
 */
public class IndividualEmployeeView extends View {

    private HBox employeeActionsLayout;
    private VBox employeeInformationLayout;
    private Label employeeInformation;
    private Button employeeApproveButton;
    private Button employeeSetStatusButton;
    private final Employee referencedEmployee;

    /**
     * The default constructor that passes the parent window and employee to be shown.
     * @param parentWindow the parent window that the view can access.
     * @param employee the referenced employee.
     */
    public IndividualEmployeeView(WindowBase parentWindow, Employee employee) {
        super(parentWindow, Views.INDIVIDUAL_EMPLOYEE + " View for " + employee.getFullName());
        this.parentWindow.setHeaderText(Views.INDIVIDUAL_EMPLOYEE + " View for " + employee.getFullName());
        this.referencedEmployee = employee;
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
        this.employeeActionsLayout = new HBox();
        this.employeeActionsLayout.getStyleClass().add("background-secondary-border");
        this.employeeActionsLayout.setPadding(new Insets(10));
        this.employeeActionsLayout.setAlignment(Pos.CENTER_LEFT);
        this.employeeActionsLayout.setSpacing(10);
        this.employeeInformationLayout = new VBox();
        this.employeeInformationLayout.getStyleClass().add("background-secondary-border");
        this.employeeInformationLayout.setPadding(new Insets(10));
        this.employeeInformationLayout.setSpacing(10);
    }

    /**
     * A method to initialise any controls used within the view.
     */
    @Override
    protected void initialiseControls() {
        this.employeeInformation = new Label();
        this.employeeInformation.setText(this.referencedEmployee.toString());
        this.employeeApproveButton = new Button();
        this.employeeApproveButton.setText("Approve");
        this.employeeApproveButton.setDisable(this.referencedEmployee.getStatus() != EmployeeStatus.NOT_APPROVED);
        this.employeeSetStatusButton = new Button();
        this.employeeSetStatusButton.setText(
                switch (this.referencedEmployee.getStatus()) {
                    case ACTIVE -> "Disable Account";
                    case DISABLED, NOT_APPROVED -> "Enable Account";
                });
        this.employeeSetStatusButton.setDisable(this.referencedEmployee.getStatus() == EmployeeStatus.NOT_APPROVED);
    }

    /**
     * A method to bind and add event handlers to components.
     */
    private void bindEventHandlers() {
        this.employeeApproveButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> this.approveEmployee());
        this.employeeSetStatusButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> this.changeEmployeeStatus());
    }

    /**
     * A method to add components to the view.
     */
    @Override
    protected void addComponentsToView() {
        this.employeeActionsLayout.getChildren().addAll(this.employeeApproveButton, this.employeeSetStatusButton);
        this.employeeInformationLayout.getChildren().add(this.employeeInformation);
        this.getChildren().addAll(this.employeeActionsLayout, this.employeeInformationLayout);
    }

    /**
     * A method to approve the given employee and sync it to the data source.
     */
    private void approveEmployee() {
        boolean result = DataPersistenceManager.getInstance().approveEmployee(this.referencedEmployee);
        if (!result) {
            AlertFactory.createAlert(Alert.AlertType.ERROR, "Failed to approve employee").show();
            return;
        }
        this.employeeApproveButton.setDisable(true);
        this.employeeSetStatusButton.setDisable(false);
    }

    /**
     * A method to change the employee status and sync it to the data source.
     */
    private void changeEmployeeStatus() {
        switch (this.referencedEmployee.getStatus()) {
            case NOT_APPROVED ->
                AlertFactory.createAlert(Alert.AlertType.WARNING,
                        "Failed to change employee status",
                        "Please ensure that the employee is approved before enabling or disabling their account")
                        .show();
            case ACTIVE -> {
                if (!DataPersistenceManager.getInstance().disableEmployee(this.referencedEmployee)) {
                    AlertFactory.createAlert(Alert.AlertType.ERROR, "Failed to disable employee account");
                }
                this.referencedEmployee.setStatus(EmployeeStatus.DISABLED);
            }
            case DISABLED -> {
                if (!DataPersistenceManager.getInstance().enableEmployee(this.referencedEmployee)) {
                    AlertFactory.createAlert(Alert.AlertType.ERROR, "Failed to enable employee account");
                }
                this.referencedEmployee.setStatus(EmployeeStatus.ACTIVE);
            }
        }
        this.updateEmployeeInformation();
    }

    /**
     * A method to update the employee information shown within the view.
     */
    private void updateEmployeeInformation() {
        this.employeeSetStatusButton.setText(
                switch (this.referencedEmployee.getStatus()) {
                    case ACTIVE -> "Disable Account";
                    case DISABLED, NOT_APPROVED -> "Enable Account";
                });
        this.employeeInformation.setText(this.referencedEmployee.toString());
    }
}
