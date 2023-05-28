package winchester.library.presentation.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import winchester.library.data.access.DatabaseConstant;
import winchester.library.data.access.DatabaseCredentials;
import winchester.library.presentation.alert.AlertFactory;
import winchester.library.presentation.window.WindowBase;
import winchester.library.service.DatabaseConnectivityChecker;

/**
 * A view that allows the setting of data source credentials.
 */
public final class DatabaseConfigurationView extends View {

    private final DatabaseCredentials credentials;
    private HBox buttonLayout;
    private Label descriptionLabel;
    private Label urlLabel;
    private Label usernameLabel;
    private Label passwordLabel;
    private TextField urlField;
    private TextField usernameField;
    private TextField passwordField;
    private Button cancelButton;
    private Button saveAndTestButton;

    /**
     * The default constructor that passes the parent window.
     * @param parentWindow the parent window that the view can access.
     */
    public DatabaseConfigurationView(WindowBase parentWindow) {
        super(parentWindow, Views.DATABASE_CONFIGURATION.toString());
        this.parentWindow.setWidth(550);
        this.parentWindow.setHeight(375);
        this.credentials = DatabaseCredentials.getInstance();
        this.initialiseLayouts();
        this.initialiseControls();
        this.bindEventHandlers();
        this.addComponentsToView();
    }

    /**
     * A method to initialise any layouts used within the view.
     */
    private void initialiseLayouts() {
        this.buttonLayout = new HBox();
        this.buttonLayout.setAlignment(Pos.CENTER_RIGHT);
        this.buttonLayout.setPadding(new Insets(15, 0, 0, 0));
    }

    /**
     * A method to initialise any controls used within the view.
     */
    private void initialiseControls() {
        this.descriptionLabel = new Label();
        this.descriptionLabel.setText(
            "Setting database connection information can stop the program from functioning."
            + " This view is for advanced users only.");
        this.descriptionLabel.setWrapText(true);
        this.urlLabel = new Label();
        this.urlLabel.setPadding(new Insets(10, 0, 0, 0));
        this.urlLabel.setText("Database URL: ");
        this.urlField = new TextField();
        this.urlField.setText(this.credentials.getUrl());
        this.usernameLabel = new Label();
        this.usernameLabel.setPadding(new Insets(10, 0, 0 , 0));
        this.usernameLabel.setText("Database Username: ");
        this.usernameField = new TextField();
        this.usernameField.setText(this.credentials.getUsername());
        this.passwordLabel = new Label();
        this.passwordLabel.setPadding(new Insets(10, 0, 0, 0));
        this.passwordLabel.setText("Database Password: ");
        this.passwordField = new TextField();
        this.passwordField.setText(this.credentials.getPassword());
        this.cancelButton = new Button();
        this.cancelButton.setText("Cancel");
        this.saveAndTestButton = new Button();
        this.saveAndTestButton.setText("Test Connection and Save");
        HBox.setMargin(this.saveAndTestButton, new Insets(0, 0, 0, 10));
    }

    /**
     * A method to bind and add event handlers to components.
     */
    private void bindEventHandlers() {
        this.cancelButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> this.parentWindow.close());
        this.saveAndTestButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> this.testAndSaveNewConnection());
    }

    /**
     * A method to add components to the view.
     */
    private void addComponentsToView() {
        this.buttonLayout.getChildren().addAll(this.cancelButton, this.saveAndTestButton);
        this.getChildren().addAll(
            this.descriptionLabel, this.urlLabel, this.urlField, this.usernameLabel, this.usernameField, 
            this.passwordLabel, this.passwordField, this.buttonLayout);
    }

    /**
     * A method to test the new data source credentials, saving them if a connection is successful.
     */
    private void testAndSaveNewConnection() {
        DatabaseConstant testResult = DatabaseConnectivityChecker.getInstance().getDatabaseStatus(
                this.urlField.getText(), this.usernameField.getText(), this.passwordField.getText());
        if (testResult != DatabaseConstant.CONNECTION_SUCCESSFUL) {
            AlertFactory.createAlert(Alert.AlertType.WARNING,
                    "Failed to connect to data source", """
                                Please ensure the details are correct.

                                The credentials will be reset to their defaults.
                                """).show();
            return;
        }
        this.credentials.setUrl(this.urlField.getText());
        this.credentials.setUsername(this.usernameField.getText());
        this.credentials.setPassword(this.passwordField.getText());
        AlertFactory.createAlert(Alert.AlertType.INFORMATION, "Connection to data source was successful",
                "The credentials will be changed for this session.").show();
        this.parentWindow.close();
    }

}
