package winchester.library.presentation.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import winchester.library.data.model.users.Employee;
import winchester.library.data.model.users.EmployeeStatus;
import winchester.library.presentation.alert.AlertFactory;
import winchester.library.presentation.window.IndividualViewWindow;
import winchester.library.presentation.window.MainWindow;
import winchester.library.presentation.window.WindowBase;
import winchester.library.service.CredentialsChecker;

import java.util.Optional;

/**
 * A view that provides a login system.
 */
public final class LoginView extends View {

    private HBox buttonLayout;
    private Label descriptionLabel;
    private Label usernameLabel;
    private Label passwordLabel;
    private Label databaseConfigurationLinkLabel;
    private TextField usernameField;
    private PasswordField passwordField;
    private Button loginButton;
    private Button registerButton;

    /**
     * The default constructor that passes the parent window.
     * @param parentWindow the parent window that the view can access.
     */
    public LoginView(WindowBase parentWindow) {
        super(parentWindow, Views.LOGIN.toString());
        this.parentWindow.setWidth(440);
        this.parentWindow.setHeight(315);
        this.getStyleClass().add("background-primary");
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
        this.buttonLayout = new HBox();
        this.buttonLayout.setAlignment(Pos.CENTER_RIGHT);
        this.buttonLayout.setPadding(new Insets(15, 0, 0, 0));
    }

    /**
     * A method to initialise any controls used within the view.
     */
    @Override
    protected void initialiseControls() {
        this.descriptionLabel = new Label();
        this.descriptionLabel.setText(
                "Please enter your credentials to login.\n"
                        + "Don't have an account? Register with the system.");
        this.descriptionLabel.setPadding(new Insets(0, 0, 10, 0));
        this.usernameLabel = new Label();
        this.usernameLabel.setText("Username: ");
        this.usernameField = new TextField();
        this.passwordLabel = new Label();
        this.passwordLabel.setText("Password: ");
        this.passwordLabel.setPadding(new Insets(10, 0, 0, 0));
        this.passwordField = new PasswordField();
        this.databaseConfigurationLinkLabel = new Label();
        this.databaseConfigurationLinkLabel.setText("Configure Data Source");
        this.databaseConfigurationLinkLabel.getStyleClass().add("link-label");
        this.loginButton = new Button();
        this.loginButton.setText("Log In");
        this.loginButton.getStyleClass().add("button-accent");
        this.registerButton = new Button();
        this.registerButton.setText("Register");
        this.registerButton.getStyleClass().add("button-standard");
    }

    private void initialiseConstraints() {
        HBox.setMargin(this.loginButton, new Insets(0, 0, 0, 10));
        HBox.setMargin(this.registerButton, new Insets(0, 0, 0, 20));
    }

    /**
     * A method to bind and add event handlers to components.
     */
    private void bindEventHandlers() {
        this.loginButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> this.checkCredentials());
        this.registerButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> this.closeAndStartRegisterWindow());
        this.databaseConfigurationLinkLabel.addEventHandler(MouseEvent.MOUSE_CLICKED, event ->
                this.startDatabaseConfigurationWindow());
    }

    /**
     * A method to add components to the view.
     */
    @Override
    protected void addComponentsToView() {
        this.buttonLayout.getChildren().addAll(
                this.databaseConfigurationLinkLabel, this.registerButton, this.loginButton);
        this.getChildren().addAll(
                this.descriptionLabel, this.usernameLabel, this.usernameField, this.passwordLabel,
                this.passwordField, this.buttonLayout);
    }

    private void checkCredentials() {
        if (this.usernameField.getText().isBlank() && this.passwordField.getText().isBlank()) {
            AlertFactory.createAlert(Alert.AlertType.ERROR, "Failed to login",
                    "There are no credentials entered. Please enter your username and password and try again.").show();
            return;
        }
        Optional<Employee> employee = new CredentialsChecker().AreCredentialsCorrect(
                this.usernameField.getText(),
                String.valueOf(this.passwordField.getText().hashCode()));
        if (employee.isEmpty()) {
            AlertFactory.createAlert(Alert.AlertType.ERROR, "Failed to login",
                    "Please check your credentials and try again").show();
            return;
        }
        if (employee.get().getStatus() == EmployeeStatus.NOT_APPROVED) {
            AlertFactory.createAlert(Alert.AlertType.ERROR, "Failed to login",
                    "This account needs to be approved by an administrator.").show();
            return;
        }
        closeAndStartMainWindow(employee.get());
    }

    /**
     * A method to close the current window and start the main window.
     */
    private void closeAndStartMainWindow(Employee employee) {
        this.parentWindow.close();
        MainWindow mainWindow = new MainWindow(employee);
        mainWindow.show();
    }

    /**
     * A method to close the current window and start a window with the register view.
     */
    private void closeAndStartRegisterWindow() {
        this.parentWindow.close();
        IndividualViewWindow registerView = new IndividualViewWindow(Views.REGISTER);
        registerView.show();
    }

    /**
     * A method to start a window with the database configuration view.
     */
    private void startDatabaseConfigurationWindow() {
        IndividualViewWindow databaseConfigView = new IndividualViewWindow(Views.DATABASE_CONFIGURATION);
        databaseConfigView.show();
    }
}
