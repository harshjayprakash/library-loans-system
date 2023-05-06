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

public class IndividualEmployeeView extends View {

    private HBox employeeActionsLayout;
    private VBox employeeInformationLayout;
    private Label employeeInformation;
    private Button employeeApproveButton;
    private Button employeeSetStatusButton;
    private Employee referencedEmployee;

    public IndividualEmployeeView(WindowBase parentWindow, Employee employee) {
        super(parentWindow, Views.INDIVIDUAL_EMPLOYEE.toString());
        this.referencedEmployee = employee;
        this.initialiseLayouts();
        this.initialiseControls();
        this.bindEventHandlers();
        this.addComponentsToView();
    }

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
    }

    private void bindEventHandlers() {
        this.employeeApproveButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            boolean result = DataPersistenceManager.getInstance().approveEmployee(this.referencedEmployee);
            if (!result) {
                AlertFactory.createAlert(Alert.AlertType.ERROR, "Failed to approve employee").show();
                return;
            }
            this.employeeApproveButton.setDisable(true);
        });
    }

    @Override
    protected void addComponentsToView() {
        this.employeeActionsLayout.getChildren().addAll(this.employeeApproveButton, this.employeeSetStatusButton);
        this.employeeInformationLayout.getChildren().add(this.employeeInformation);
        this.getChildren().addAll(this.employeeActionsLayout, this.employeeInformationLayout);
    }
}
