package winchester.library.presentation.view;

import java.util.Objects;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import winchester.library.data.access.DemoAccount;
import winchester.library.data.model.users.Employee;
import winchester.library.presentation.window.WindowBase;
import winchester.library.service.DatabaseInteraction;

public class HomeView extends View {

    private VBox userPanel;
    private VBox inventoryPanel;
    private Label userLabel;
    private Label userRoleLabel;
    private Label inventoryCountLabel;
    private Label customerCountLabel;
    private Label loanCountLabel;
    private Label overdueCountLabel;
    private final Employee currentEmployee;
    private final DatabaseInteraction databaseInteraction;

    public HomeView(WindowBase parentWindow, Employee currentEmployee) {
        super(parentWindow, Views.HOME.toString());
        this.currentEmployee = Objects.isNull(currentEmployee) ? DemoAccount.get() : currentEmployee;
        this.databaseInteraction = DatabaseInteraction.getInstance();
        this.initialiseLayouts();
        this.initialiseControls();
        this.addComponentsToView();
    }

    @Override
    protected void initialiseLayouts() {
        this.setSpacing(15);
        this.userPanel = new VBox();
        this.userPanel.setId("background-secondary");
        this.userPanel.setPadding(new Insets(10));
        this.inventoryPanel = new VBox();
        this.inventoryPanel.setId("background-secondary");
        this.inventoryPanel.setPadding(new Insets(10));
    }

    @Override
    protected void initialiseControls() {
        this.userLabel = new Label();
        this.userLabel.setText(this.currentEmployee.getFullName());
        this.userRoleLabel = new Label();
        this.userRoleLabel.setText(this.currentEmployee.getType().toString());
        this.inventoryCountLabel = new Label();
        this.inventoryCountLabel.setText("Number of Items in Inventory: %d".formatted(0));
        this.customerCountLabel = new Label();
        this.customerCountLabel.setText("Number of Customers: %d".formatted(0));
        this.loanCountLabel = new Label();
        this.loanCountLabel.setText("Number of Loans: %d".formatted(0));
        this.overdueCountLabel = new Label();
        this.overdueCountLabel.setText("Number of Overdue Items: %d".formatted(0));
    }

    @Override
    protected void addComponentsToView() {
        this.userPanel.getChildren().addAll(this.userLabel, this.userRoleLabel);
        this.inventoryPanel.getChildren().addAll(
                this.inventoryCountLabel, this.customerCountLabel, this.loanCountLabel, this.overdueCountLabel);
        this.getChildren().addAll(this.userPanel, this.inventoryPanel);
    }

}
