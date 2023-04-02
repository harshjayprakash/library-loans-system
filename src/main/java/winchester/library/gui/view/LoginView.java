package winchester.library.gui.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class LoginView extends View {

    private final Stage parentWindow;
    private HBox buttonLayout;
    private Label descriptionLabel;
    private Label usernameLabel;
    private Label passwordLabel;
    private Label databaseConfigurationLinkLabel;
    private TextField usernameField;
    private PasswordField passwordField;
    private Button loginButton;
    private Button registerButton;

    public LoginView(Stage parentWindow) {
        super();
        this.parentWindow = parentWindow;
        this.parentWindow.setTitle("Login In - Winchester Library Services");
        this.parentWindow.setWidth(440);
        this.parentWindow.setHeight(315);
        this.initialiseLayouts();
        this.initialiseControls();
        this.initialiseConstraints();
        this.bindEventHandlers();
        this.addComponentsToView();
    }

    @Override
    protected void initialiseLayouts() {
        this.buttonLayout = new HBox();
        this.buttonLayout.setAlignment(Pos.CENTER_RIGHT);
        this.buttonLayout.setPadding(new Insets(15, 0, 0, 0));
    }

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
        this.databaseConfigurationLinkLabel.setText("Configure Database");
        this.databaseConfigurationLinkLabel.setId("link-label");
        this.loginButton = new Button();
        this.loginButton.setText("Log In");
        this.loginButton.setId("button-accent");
        this.registerButton = new Button();
        this.registerButton.setText("Register");
        this.registerButton.setId("button-standard");
    }

    private void initialiseConstraints() {
        HBox.setMargin(this.loginButton, new Insets(0, 0, 0, 10));
        HBox.setMargin(this.registerButton, new Insets(0, 0, 0, 20));
    }

    @Override
    protected void bindEventHandlers() {

    }

    @Override
    protected void addComponentsToView() {
        this.buttonLayout.getChildren().addAll(
                this.databaseConfigurationLinkLabel, this.registerButton, this.loginButton);
        this.getChildren().addAll(
                this.descriptionLabel, this.usernameLabel, this.usernameField, this.passwordLabel,
                this.passwordField, this.buttonLayout);
    }
}