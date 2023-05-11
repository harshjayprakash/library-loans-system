package winchester.library.presentation.component.pane;

import java.util.Optional;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import winchester.library.data.model.users.Employee;
import winchester.library.data.model.users.UserType;
import winchester.library.presentation.style.ComponentStyler;
import winchester.library.presentation.view.Views;

/**
 * A class that provides the sidebar operations for the Main Window.
 */
public final class SidePane extends VBox {

    private Label serviceLabel;
    private Label privilegeModeLabel;
    private ToggleGroup pageButtons;
    private ToggleButton homePageButton;
    private ToggleButton inventoryPageButton;
    private ToggleButton customersPageButton;
    private ToggleButton loansPageButton;
    private ToggleButton usersPageButton;
    private VBox accountPane;
    private Label currentUserLabel;
    private Button logOutButton;
    private final Employee currentEmployee;

    /**
     * The default constructor for the SidePane component.
     * @param employee the employee that will be referenced.
     */
    public SidePane(Employee employee) {
        super();
        this.currentEmployee = employee;
        this.initialiseLayouts();
        this.initialiseControls();
        this.initialiseConstraints();
        this.loadStylesheets();
        this.addComponentsToPane();
    }

    /**
     * An accessor that retrieves the toggle group of the toggle buttons.
     * @return the toggle group of the toggle buttons.
     */
    public ToggleGroup getToggleGroup() {
        return this.pageButtons;
    }

    /**
     * An accessor that retrieves the logout button.
     * @return the logout button.
     */
    public Button getLogOutButton() {
        return this.logOutButton;
    }

    /**
     * An accessor that retrieves the selected toggle button as the Views enumeration constant.
     * @return a Views enumeration constant.
     */
    public Views getSelectedToggleAsView() {
        if (this.pageButtons.getSelectedToggle() == null ) { return Views.NONE_WITH_SIDEBAR; }
        return switch (((ToggleButton)this.pageButtons.getSelectedToggle()).getText()) {
            case "Home" -> Views.HOME;
            case "Inventory" -> Views.INVENTORY;
            case "Customers" -> Views.CUSTOMERS;
            case "Loans" -> Views.LOANS;
            case "Users" -> Views.USERS;
            default -> Views.NONE_WITH_SIDEBAR;
        };
    }

    /**
     * An accessor that retrieves a specific toggle button based on value.
     * @param value the string value of the toggle button.
     * @return an optional toggle button or empty if not found.
     */
    public Optional<ToggleButton> getToggleButton(String value) {
        return switch (value) {
            case "Home" -> Optional.of(homePageButton);
            case "Inventory" -> Optional.of(inventoryPageButton);
            case "Customers" -> Optional.of(customersPageButton);
            case "Loans" -> Optional.of(loansPageButton);
            case "Users" -> Optional.of(usersPageButton);
            default -> Optional.empty();
        };
    }

    /**
     * A method to initialise the layouts within the component.
     */
    private void initialiseLayouts() {
        this.setPadding(new Insets(0, 0, 0, 20));
        this.setSpacing(5);
        this.setFillWidth(true);
        this.getStyleClass().add("background-secondary");
        this.accountPane = new VBox();
        this.accountPane.setAlignment(Pos.BOTTOM_LEFT);
        this.accountPane.prefHeightProperty().bind(this.prefHeightProperty());
    }

    /**
     * A method to initialise the controls within the component.
     */
    private void initialiseControls() {
        this.serviceLabel = new Label();
        this.serviceLabel.getStyleClass().add("text-bold");
        this.serviceLabel.setPadding(new Insets(10, 5, 5, 5));
        this.serviceLabel.setText("Winchester\nLibrary Services");
        this.serviceLabel.setWrapText(true);
        this.privilegeModeLabel = new Label();
        this.privilegeModeLabel.setPadding(new Insets(2, 5, 10, 5));
        this.privilegeModeLabel.setText(this.currentEmployee.getType().toString() + " Mode");
        this.privilegeModeLabel.setWrapText(true);
        this.pageButtons = new ToggleGroup();
        this.homePageButton = new ToggleButton();
        this.homePageButton.setAlignment(Pos.CENTER_LEFT);
        this.homePageButton.getStyleClass().add("toggle-button");
        this.homePageButton.setText("Home");
        this.homePageButton.setToggleGroup(this.pageButtons);
        this.homePageButton.prefWidthProperty().bind(this.prefWidthProperty());
        this.inventoryPageButton = new ToggleButton();
        this.inventoryPageButton.setAlignment(Pos.CENTER_LEFT);
        this.inventoryPageButton.getStyleClass().add("toggle-button");
        this.inventoryPageButton.setText("Inventory");
        this.inventoryPageButton.setToggleGroup(this.pageButtons);
        this.inventoryPageButton.prefWidthProperty().bind(this.prefWidthProperty());
        this.customersPageButton = new ToggleButton();
        this.customersPageButton.setAlignment(Pos.CENTER_LEFT);
        this.customersPageButton.getStyleClass().add("toggle-button");
        this.customersPageButton.setText("Customers");
        this.customersPageButton.setToggleGroup(this.pageButtons);
        this.customersPageButton.prefWidthProperty().bind(this.prefWidthProperty());
        this.loansPageButton = new ToggleButton();
        this.loansPageButton.setAlignment(Pos.CENTER_LEFT);
        this.loansPageButton.getStyleClass().add("toggle-button");
        this.loansPageButton.setText("Loans");
        this.loansPageButton.setToggleGroup(this.pageButtons);
        this.loansPageButton.prefWidthProperty().bind(this.prefWidthProperty());
        this.usersPageButton = new ToggleButton();
        this.usersPageButton.setAlignment(Pos.CENTER_LEFT);
        this.usersPageButton.getStyleClass().add("toggle-button");
        this.usersPageButton.setText("Users");
        this.usersPageButton.setToggleGroup(this.pageButtons);
        this.usersPageButton.prefWidthProperty().bind(this.prefWidthProperty());
        this.currentUserLabel = new Label();
        this.currentUserLabel.setAlignment(Pos.BOTTOM_LEFT);
        this.currentUserLabel.setText("Logged In As\n" + this.currentEmployee.getUsername());
        this.logOutButton = new Button();
        this.logOutButton.setText("Log Out");
    }

    /**
     * A method to initialise the constraints for margins and resizing.
     */
    private void initialiseConstraints() {
        for (Node child : this.getChildren()) {
            VBox.setVgrow(child, Priority.NEVER);
        }
        VBox.setVgrow(this.accountPane, Priority.ALWAYS);
        VBox.setMargin(this.logOutButton, new Insets(10, 0, 10, 0));
    }

    /**
     * A method to load the stylesheets with the component.
     */
    private void loadStylesheets() {
        ComponentStyler.getInstance().setStyle(this);
    }

    /**
     * A method to add the components to the side pane.
     */
    private void addComponentsToPane() {
        this.accountPane.getChildren().addAll(this.currentUserLabel, this.logOutButton);
        this.getChildren().addAll(
                this.serviceLabel, this.privilegeModeLabel, this.homePageButton,
                this.inventoryPageButton, this.customersPageButton, this.loansPageButton);
        if (this.currentEmployee.getType() == UserType.ADMINISTRATOR) {
            this.getChildren().add(this.usersPageButton);
        }
        this.getChildren().add(this.accountPane);
    }
}
