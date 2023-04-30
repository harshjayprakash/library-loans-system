package winchester.library.presentation.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import winchester.library.data.model.users.UserType;
import winchester.library.presentation.alert.AlertFactory;
import winchester.library.presentation.window.IndividualViewWindow;
import winchester.library.presentation.window.WindowBase;
import winchester.library.service.DataPersistenceManager;
import winchester.library.service.PasswordValidator;
import winchester.library.service.UsernameValidator;

public final class RegisterView extends View {

    private HBox buttonLayout;
    private Label descriptionLabel;
    private Label firstNameLabel;
    private Label lastNameLabel;
    private Label postalCodeLabel;
    private Label usernameLabel;
    private Label usernameDescriptionLabel;
    private Label passwordLabel;
    private Label passwordDescriptionLabel;
    private Label passwordConfirmationLabel;
    private Label passwordConfirmationEqualCheckLabel;
    private Label accountTypeLabel;
    private TextField firstNameField;
    private TextField lastNameField;
    private TextField postalCodeField;
    private TextField usernameField;
    private PasswordField passwordField;
    private PasswordField passwordConfirmationField;
    private ComboBox<String> accountTypeComboBox;
    private Button backButton;
    private Button requestButton;

    public RegisterView(WindowBase parentWindow) {
        super(parentWindow, Views.REGISTER.toString());
        this.parentWindow.setHeight(750);
        this.parentWindow.setWidth(500);
        this.initialiseLayouts();
        this.initialiseControls();
        this.bindEventHandlers();
        this.addComponentsToView();
    }

    @Override
    protected void initialiseLayouts() {
        this.getStyleClass().add("background-primary");
        this.buttonLayout = new HBox();
        this.buttonLayout.setAlignment(Pos.CENTER_RIGHT);
        this.buttonLayout.getStyleClass().add("background-primary");
        this.buttonLayout.setPadding(new Insets(15, 0, 0, 0));
    }

    @Override
    protected void initialiseControls() {
        this.descriptionLabel = new Label();
        this.descriptionLabel.setPadding(new Insets(0, 0, 10, 0));
        this.descriptionLabel.setText(
                "Please enter your details. "
                + "You can start using your account when you are approved by an administrator");
        this.descriptionLabel.setWrapText(true);
        this.firstNameLabel = new Label();
        this.firstNameLabel.setText("First Name: ");
        this.firstNameField = new TextField();
        this.lastNameLabel = new Label();
        this.lastNameLabel.setPadding(new Insets(10, 0, 0, 0));
        this.lastNameLabel.setText("Last Name: ");
        this.lastNameField = new TextField();
        this.postalCodeLabel = new Label();
        this.postalCodeLabel.setPadding(new Insets(10, 0, 0, 0));
        this.postalCodeLabel.setText("Postal Code: ");
        this.postalCodeField = new TextField();
        this.usernameLabel = new Label();
        this.usernameLabel.setPadding(new Insets(10, 0, 0, 0));
        this.usernameLabel.setText("Username: ");
        this.usernameDescriptionLabel = new Label();
        this.usernameDescriptionLabel.setPadding(new Insets(0, 0, 4, 0));
        this.usernameDescriptionLabel.setText("This must be a unique name that you will login with.");
        this.usernameField = new TextField();
        this.passwordLabel = new Label();
        this.passwordLabel.setPadding(new Insets(10, 0, 0, 0));
        this.passwordLabel.setText("Password: ");
        this.passwordDescriptionLabel = new Label();
        this.passwordDescriptionLabel.setPadding(new Insets(0, 0, 4, 0));
        this.passwordDescriptionLabel.setText(new PasswordValidator().getPasswordRequirements());
        this.passwordField = new PasswordField();
        this.passwordConfirmationLabel = new Label();
        this.passwordConfirmationLabel.setPadding(new Insets(10, 0, 0, 0));
        this.passwordConfirmationLabel.setText("Confirm Password: ");
        this.passwordConfirmationField = new PasswordField();
        this.passwordConfirmationEqualCheckLabel = new Label();
        this.passwordConfirmationEqualCheckLabel.setText("Passwords empty");
        this.passwordConfirmationEqualCheckLabel.setPadding(new Insets(5, 0, 0, 0));
        this.accountTypeLabel = new Label();
        this.accountTypeLabel.setPadding(new Insets(10, 0, 0, 0));
        this.accountTypeLabel.setText("Account Type: ");
        this.accountTypeComboBox = new ComboBox<>();
        this.accountTypeComboBox.getItems().addAll(
                UserType.STANDARD.toString(), UserType.ADMINISTRATOR.toString());
        this.backButton = new Button();
        this.backButton.setText("Back");
        this.requestButton = new Button();
        this.requestButton.getStyleClass().add("button-accent");
        this.requestButton.setText("Request Access");
        HBox.setMargin(this.requestButton, new Insets(0, 0, 0, 10));
    }

    private void bindEventHandlers() {
        this.backButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            this.parentWindow.close();
            IndividualViewWindow loginView = new IndividualViewWindow(Views.LOGIN);
            loginView.show();
        });
        this.passwordConfirmationField.textProperty().addListener((observable, oldValue, newValue) -> {
            this.passwordConfirmationEqualCheckLabel.setText(
                    (this.passwordField.getText().equals(this.passwordConfirmationField.getText()))
                    ? "Passwords match" : "Passwords do not match");
        });
        this.requestButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            PasswordValidator passwordValidator = new PasswordValidator();
            UsernameValidator usernameValidator = new UsernameValidator();
            if (!this.passwordField.getText().equals(this.passwordConfirmationField.getText())) {
                AlertFactory.createAlert(Alert.AlertType.ERROR, "Passwords do not match").show();
                return;
            }
            if (!passwordValidator.meetsAllRequirements(this.passwordField.getText())) {
                AlertFactory.createAlert(
                        Alert.AlertType.ERROR, "Passwords do not meet the specified requirements").show();
                return;
            }
            if (usernameValidator.usernameExists(this.usernameField.getText())) {
                AlertFactory.createAlert(
                        Alert.AlertType.ERROR,
                        "This username already exists in the system.", "Please choose another username").show();
                return;
            }
            if (this.accountTypeComboBox.getValue().equals(UserType.STANDARD.toString())) {
                DataPersistenceManager.getInstance().createEmployee(
                        this.firstNameField.getText(),
                        this.lastNameField.getText(),
                        this.postalCodeField.getText(),
                        this.usernameField.getText(),
                        this.passwordField.getText());
            }
            else if (this.accountTypeComboBox.getValue().equals(UserType.ADMINISTRATOR.toString())) {
                DataPersistenceManager.getInstance().createAdministrator(
                        this.firstNameField.getText(),
                        this.lastNameField.getText(),
                        this.postalCodeField.getText(),
                        this.usernameField.getText(),
                        this.passwordField.getText());
            }
        });
    }

    @Override
    protected void addComponentsToView() {
        this.buttonLayout.getChildren().addAll(this.backButton, this.requestButton);
        this.getChildren().addAll(
                this.descriptionLabel, this.firstNameLabel, this.firstNameField, this.lastNameLabel, this.lastNameField,
                this.postalCodeLabel, this.postalCodeField, this.usernameLabel, this.usernameDescriptionLabel,
                this.usernameField, this.passwordLabel, this.passwordDescriptionLabel, this.passwordField,
                this.passwordConfirmationLabel, this.passwordConfirmationField, this.passwordConfirmationEqualCheckLabel,
                this.accountTypeLabel, this.accountTypeComboBox, this.buttonLayout);
    }

}
