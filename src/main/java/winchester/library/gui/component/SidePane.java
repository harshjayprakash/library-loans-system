package winchester.library.gui.component;

import java.util.Objects;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import winchester.library.data.DemoAccount;
import winchester.library.gui.style.StylesheetSetter;
import winchester.library.gui.view.Views;
import winchester.library.impl.users.Employee;

public class SidePane extends VBox {
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
    private Employee currentEmployee;

    public SidePane(Employee employee) {
        super();
        this.currentEmployee = Objects.isNull(employee) ? DemoAccount.get() : employee;
        this.setPadding(new Insets(0, 0, 0, 20));
        this.setSpacing(5);
        this.setFillWidth(true);
        this.initialiseLayouts();
        this.initialiseControls();
        this.initialiseConstraints();
        this.loadStylesheets();
        this.addComponentsToPane();
    }

    public void initialiseLayouts() {
        this.accountPane = new VBox();
        this.accountPane.setAlignment(Pos.BOTTOM_LEFT);
        this.accountPane.prefHeightProperty().bind(this.prefHeightProperty());
    }

    private void initialiseControls() {
        this.serviceLabel = new Label();
        this.serviceLabel.setId("side-pane__service-label");
        this.serviceLabel.setPadding(new Insets(10, 5, 5, 5));
        this.serviceLabel.setText("Winchester\nLibrary Services");
        this.serviceLabel.setWrapText(true);
        this.privilegeModeLabel = new Label();
        this.privilegeModeLabel.setPadding(new Insets(2, 5, 10, 5));
        this.privilegeModeLabel.setText("Demo");
        this.pageButtons = new ToggleGroup();
        this.homePageButton = new ToggleButton();
        this.homePageButton.setAlignment(Pos.CENTER_LEFT);
        this.homePageButton.setId("toggle-button");
        this.homePageButton.setText("Home");
        this.homePageButton.setToggleGroup(this.pageButtons);
        this.homePageButton.prefWidthProperty().bind(this.prefWidthProperty());
        this.inventoryPageButton = new ToggleButton();
        this.inventoryPageButton.setAlignment(Pos.CENTER_LEFT);
        this.inventoryPageButton.setId("toggle-button");
        this.inventoryPageButton.setText("Inventory");
        this.inventoryPageButton.setToggleGroup(this.pageButtons);
        this.inventoryPageButton.prefWidthProperty().bind(this.prefWidthProperty());
        this.customersPageButton = new ToggleButton();
        this.customersPageButton.setAlignment(Pos.CENTER_LEFT);
        this.customersPageButton.setId("toggle-button");
        this.customersPageButton.setText("Customers");
        this.customersPageButton.setToggleGroup(this.pageButtons);
        this.customersPageButton.prefWidthProperty().bind(this.prefWidthProperty());
        this.loansPageButton = new ToggleButton();
        this.loansPageButton.setAlignment(Pos.CENTER_LEFT);
        this.loansPageButton.setId("toggle-button");
        this.loansPageButton.setText("Loans");
        this.loansPageButton.setToggleGroup(this.pageButtons);
        this.loansPageButton.prefWidthProperty().bind(this.prefWidthProperty());
        this.usersPageButton = new ToggleButton();
        this.usersPageButton.setAlignment(Pos.CENTER_LEFT);
        this.usersPageButton.setId("toggle-button");
        this.usersPageButton.setText("Users");
        this.usersPageButton.setToggleGroup(this.pageButtons);
        this.usersPageButton.prefWidthProperty().bind(this.prefWidthProperty());
        this.currentUserLabel = new Label();
        this.currentUserLabel.setAlignment(Pos.BOTTOM_LEFT);
        this.currentUserLabel.setText("Logged In As\n" + this.currentEmployee.getType().toString());
        this.logOutButton = new Button();
        this.logOutButton.setText("Log Out");
    }

    private void initialiseConstraints() {
        for (Node child : this.getChildren()) {
            VBox.setVgrow(child, Priority.NEVER);
        }
        VBox.setVgrow(this.accountPane, Priority.ALWAYS);
        VBox.setMargin(this.logOutButton, new Insets(10, 0, 10, 0));
    }

    private void loadStylesheets() {
        StylesheetSetter.getInstance().setStyle(this);
    }

    private void addComponentsToPane() {
        this.accountPane.getChildren().addAll(this.currentUserLabel, this.logOutButton);
        this.getChildren().addAll(
                this.serviceLabel, this.privilegeModeLabel, this.homePageButton,
                this.inventoryPageButton, this.customersPageButton, this.loansPageButton,
                this.usersPageButton, this.accountPane);
    }

    public ToggleGroup getToggleGroup() {
        return this.pageButtons;
    }

    public Button getLogOutButton() {
        return this.logOutButton;
    }

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
}
