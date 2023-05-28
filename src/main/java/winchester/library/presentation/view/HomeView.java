package winchester.library.presentation.view;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import winchester.library.data.model.users.Employee;
import winchester.library.presentation.window.IndividualViewWindow;
import winchester.library.presentation.window.WindowBase;
import winchester.library.service.DataPersistenceManager;

/**
 * A view that shows a summary to the employee logged in.
 */
public final class HomeView extends View {

    private VBox userPanel;
    private VBox inventoryPanel;
    private Label userLabel;
    private Label userRoleLabel;
    private Label changePasswordLinkLabel;
    private Label inventoryCountLabel;
    private Label customerCountLabel;
    private Label loanCountLabel;
    private final Employee currentEmployee;
    private final DataPersistenceManager dataPersistenceManager;

    /**
     * The default constructor that passes the parent window and employee logged in.
     * @param parentWindow the parent window that the view can access.
     * @param currentEmployee the currently logged in employee.
     */
    public HomeView(WindowBase parentWindow, Employee currentEmployee) {
        super(parentWindow, Views.HOME.toString());
        this.currentEmployee = currentEmployee;
        this.dataPersistenceManager = DataPersistenceManager.getInstance();
        this.initialiseLayouts();
        this.initialiseControls();
        this.bindEventHandlers();
        this.addComponentsToView();
    }

    /**
     * A method to initialise any layouts used within the view.
     */
    private void initialiseLayouts() {
        this.setSpacing(15);
        this.userPanel = new VBox();
        this.userPanel.getStyleClass().add("background-secondary-border");
        this.userPanel.setPadding(new Insets(10));
        this.inventoryPanel = new VBox();
        this.inventoryPanel.getStyleClass().add("background-secondary-border");
        this.inventoryPanel.setPadding(new Insets(10));
    }

    /**
     * A method to initialise any controls used within the view.
     */
    private void initialiseControls() {
        this.userLabel = new Label();
        this.userLabel.setText(this.currentEmployee.getFullName());
        this.userRoleLabel = new Label();
        this.userRoleLabel.setText(String.format("%s Account", this.currentEmployee.getType().toString()));
        this.changePasswordLinkLabel = new Label();
        this.changePasswordLinkLabel.getStyleClass().add("link-label");
        this.changePasswordLinkLabel.setText("Change Password");
        this.inventoryCountLabel = new Label();
        this.inventoryCountLabel.setText(
                String.format("Number of Items in Inventory: %d",
                        dataPersistenceManager.getBooks().size() + dataPersistenceManager.getFilms().size()));
        this.customerCountLabel = new Label();
        this.customerCountLabel.setText(
                String.format("Number of Customers: %d", dataPersistenceManager.getCustomers().size()));
        this.loanCountLabel = new Label();
        this.loanCountLabel.setText(
                String.format("Number of Loans: %d", dataPersistenceManager.getLoans().size()));
    }

    /**
     * A method to bind and add event handlers to components.
     */
    private void bindEventHandlers() {
        this.changePasswordLinkLabel.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> this.startChangePasswordWindow());
    }

    /**
     * A method to add components to the view.
     */
    private void addComponentsToView() {
        this.userPanel.getChildren().addAll(this.userLabel, this.userRoleLabel, this.changePasswordLinkLabel);
        this.inventoryPanel.getChildren().addAll(
                this.inventoryCountLabel, this.customerCountLabel, this.loanCountLabel);
        this.getChildren().addAll(this.userPanel, this.inventoryPanel);
    }

    /**
     * A method to start a window with the change password view.
     */
    private void startChangePasswordWindow() {
        IndividualViewWindow changePasswordView = new IndividualViewWindow(Views.CHANGE_PASSWORD, this.currentEmployee);
        changePasswordView.show();
    }

}
