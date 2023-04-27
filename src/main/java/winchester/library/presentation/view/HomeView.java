package winchester.library.presentation.view;

import java.util.Objects;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import winchester.library.data.access.DemoAccount;
import winchester.library.data.model.users.Employee;
import winchester.library.presentation.window.IndividualViewWindow;
import winchester.library.presentation.window.WindowBase;
import winchester.library.service.DatabaseInteraction;

public class HomeView extends View {

    private VBox userPanel;
    private VBox inventoryPanel;
    private Label userLabel;
    private Label userRoleLabel;
    private Label changePasswordLinkLabel;
    private Label inventoryCountLabel;
    private Label customerCountLabel;
    private Label loanCountLabel;
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
        this.userPanel.setId("background-secondary-border");
        this.userPanel.setPadding(new Insets(10));
        this.inventoryPanel = new VBox();
        this.inventoryPanel.setId("background-secondary-border");
        this.inventoryPanel.setPadding(new Insets(10));
    }

    @Override
    protected void initialiseControls() {
        this.userLabel = new Label();
        this.userLabel.setText(this.currentEmployee.getFullName());
        this.userRoleLabel = new Label();
        this.userRoleLabel.setText(String.format("%s Account", this.currentEmployee.getType().toString()));
        this.changePasswordLinkLabel = new Label();
        this.changePasswordLinkLabel.setId("link-label");
        this.changePasswordLinkLabel.setText("Change Password");
        this.inventoryCountLabel = new Label();
        this.inventoryCountLabel.setText(
                String.format("Number of Items in Inventory: %d",
                        databaseInteraction.getBooks().size() + databaseInteraction.getFilms().size()));
        this.customerCountLabel = new Label();
        this.customerCountLabel.setText(
                String.format("Number of Customers: %d", databaseInteraction.getCustomers().size()));
        this.loanCountLabel = new Label();
        this.loanCountLabel.setText(
                String.format("Number of Loans: %d", databaseInteraction.getLoans().size()));
    }

    private void bindEventHandlers() {
        this.changePasswordLinkLabel.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            IndividualViewWindow changePasswordView = new IndividualViewWindow(Views.CHANGE_PASSWORD);
            changePasswordView.show();
        });
    }

    @Override
    protected void addComponentsToView() {
        this.userPanel.getChildren().addAll(this.userLabel, this.userRoleLabel);
        this.inventoryPanel.getChildren().addAll(
                this.inventoryCountLabel, this.customerCountLabel, this.loanCountLabel);
        this.getChildren().addAll(this.userPanel, this.inventoryPanel);
    }

}
