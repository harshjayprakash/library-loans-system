package winchester.library.presentation.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import winchester.library.presentation.window.IndividualViewWindow;
import winchester.library.presentation.window.MainWindow;
import winchester.library.presentation.window.WindowBase;

public class LoginView extends View {

    private HBox buttonLayout;
    private Label descriptionLabel;
    private Label usernameLabel;
    private Label passwordLabel;
    private Label databaseConfigurationLinkLabel;
    private TextField usernameField;
    private PasswordField passwordField;
    private Button loginButton;
    private Button registerButton;

    public LoginView(WindowBase parentWindow) {
        super(parentWindow, Views.LOGIN.toString());
        this.parentWindow.setWidth(440);
        this.parentWindow.setHeight(315);
        this.setId("background-primary");
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

    protected void bindEventHandlers() {
        this.loginButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            this.parentWindow.close();
            MainWindow mainWindow = new MainWindow();
            mainWindow.show();
        });
        this.registerButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            this.parentWindow.close();
            IndividualViewWindow registerView = new IndividualViewWindow(Views.REGISTER);
            registerView.show();
        });
        this.databaseConfigurationLinkLabel.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            IndividualViewWindow databaseConfigView = new IndividualViewWindow(Views.DATABASE_CONFIGURATION);
            databaseConfigView.show();
        });
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
