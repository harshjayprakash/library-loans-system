package winchester.library.presentation.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import winchester.library.data.model.users.Employee;
import winchester.library.data.model.users.UserType;
import winchester.library.presentation.alert.AlertFactory;
import winchester.library.presentation.window.WindowBase;
import winchester.library.service.DataPersistenceManager;
import winchester.library.service.PasswordValidator;

/**
 * A view that allows an employee to change their password.
 */
public final class ChangePasswordView extends View {

    private HBox actionButtons;
    private Label descriptionLabel;
    private Label currentUserLabel;
    private Label currentPasswordLabel;
    private Label passwordRequirementsLabel;
    private Label newPasswordLabel;
    private Label confirmNewPasswordLabel;
    private Label passwordMatchLabel;
    private TextField currentUserField;
    private PasswordField currentPasswordField;
    private PasswordField newPasswordField;
    private PasswordField confirmNewPasswordField;
    private Button cancelButton;
    private Button changePasswordButton;
    private final Employee currentEmployee;
    private final PasswordValidator passwordValidator;

    /**
     * The default constructor that passes the parent window and employee that needs to change the password.
     * @param parentWindow the parent window that the view can access.
     * @param employee the referenced employee.
     */
    public ChangePasswordView(WindowBase parentWindow, Employee employee) {
        super(parentWindow, Views.CHANGE_PASSWORD.toString());
        this.currentEmployee = employee;
        this.parentWindow.setWidth(500);
        this.parentWindow.setHeight(520);
        this.passwordValidator = new PasswordValidator();
        this.initialiseLayouts();
        this.initialiseControls();
        this.bindEventHandlers();
        this.addComponentsToView();
    }

    /**
     * A method to initialise any layouts used within the view.
     */
    @Override
    protected void initialiseLayouts() {
        this.actionButtons = new HBox();
        this.actionButtons.setAlignment(Pos.CENTER_RIGHT);
        this.actionButtons.setPadding(new Insets(15, 0, 0, 0));
    }

    /**
     * A method to initialise any controls used within the view.
     */
    @Override
    protected void initialiseControls() {
        this.descriptionLabel = new Label();
        this.descriptionLabel.setText("Please enter your previous password and your new password");
        this.currentUserLabel = new Label();
        this.currentUserLabel.setPadding(new Insets(10, 0, 0, 0));
        this.currentUserLabel.setText("Username: ");
        this.currentUserField = new TextField();
        this.currentUserField.setText(this.currentEmployee.getUsername());
        if (this.currentEmployee.getType() != UserType.ADMINISTRATOR) {
            this.currentUserField.setDisable(true);
        }
        this.currentPasswordLabel = new Label();
        this.currentPasswordLabel.setPadding(new Insets(10, 0, 0, 0));
        this.currentPasswordLabel.setText("Current Password: ");
        this.currentPasswordField = new PasswordField();
        this.passwordRequirementsLabel = new Label();
        this.passwordRequirementsLabel.setPadding(new Insets(5, 0, 0, 0));
        this.passwordRequirementsLabel.setText(this.passwordValidator.getPasswordRequirements());
        this.newPasswordLabel = new Label();
        this.newPasswordLabel.setPadding(new Insets(10, 0, 0, 0));
        this.newPasswordLabel.setText("New Password: ");
        this.newPasswordField = new PasswordField();
        this.confirmNewPasswordLabel = new Label();
        this.confirmNewPasswordLabel.setPadding(new Insets(10, 0, 0, 0));
        this.confirmNewPasswordLabel.setText("Confirm New Password: ");
        this.confirmNewPasswordField = new PasswordField();
        this.passwordMatchLabel = new Label();
        this.passwordMatchLabel.setText("Password do not match.");
        this.cancelButton = new Button();
        this.cancelButton.setText("Cancel");
        this.changePasswordButton = new Button();
        this.changePasswordButton.setText("Change Password");
        this.changePasswordButton.getStyleClass().add("button-accent");
        HBox.setMargin(this.changePasswordButton, new Insets(0, 0, 0, 10));
    }

    /**
     * A method to bind and add event handlers to components.
     */
    private void bindEventHandlers() {
        this.cancelButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> this.parentWindow.close());
        this.confirmNewPasswordField.textProperty().addListener((observable, oldValue, newValue) ->
                this.checkIfPasswordsMatch());
        this.changePasswordButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> this.changePassword());
    }

    /**
     * A method to add components to the view.
     */
    @Override
    protected void addComponentsToView() {
        this.actionButtons.getChildren().addAll(this.cancelButton, this.changePasswordButton);
        this.getChildren().addAll(
                this.descriptionLabel, this.currentUserLabel, this.currentUserField, this.currentPasswordLabel,
                this.currentPasswordField, this.newPasswordLabel, this.passwordRequirementsLabel,
                this.newPasswordField, this.confirmNewPasswordLabel, this.confirmNewPasswordField,
                this.passwordMatchLabel, this.actionButtons);
    }

    /**
     * A method to check if the new passwords match.
     */
    private void checkIfPasswordsMatch() {
        this.passwordMatchLabel.setText(
                (this.confirmNewPasswordField.getText().equals(this.newPasswordField.getText()))
                        ? "Passwords match" : "Passwords do not match");
    }

    /**
     * A method to check whether there is a blank field.
     * @return true if there is a blank field.
     */
    private boolean blankFieldExists() {
        return this.currentUserField.getText().isBlank()
                || this.currentPasswordField.getText().isBlank()
                || this.newPasswordField.getText().isBlank()
                || this.confirmNewPasswordField.getText().isBlank();
    }

    private void changePassword() {
        if (this.blankFieldExists()) {
            AlertFactory.createAlert(Alert.AlertType.ERROR, "Please ensure that all fields are filled in").show();
            return;
        }
        if (this.confirmNewPasswordField.getText().equals(this.newPasswordField.getText())) {
            AlertFactory.createAlert(Alert.AlertType.ERROR, "New passwords do not match").show();
            return;
        }
        if (!this.currentEmployee.getHashedPassword().equals(
                String.valueOf(this.currentPasswordField.getText().hashCode()))) {
            AlertFactory.createAlert(Alert.AlertType.ERROR, "Current password does not match").show();
            return;
        }
        if (!this.passwordValidator.meetsAllRequirements(this.newPasswordField.getText())) {
            AlertFactory.createAlert(Alert.AlertType.ERROR, "New password does not meet the requirements").show();
            return;
        }
        this.currentEmployee.setHashedPassword(String.valueOf(this.confirmNewPasswordField.getText().hashCode()));
        boolean success = DataPersistenceManager.getInstance().updatePassword(this.currentEmployee);
        if (!success) {
            AlertFactory.createAlert(Alert.AlertType.ERROR, "Failed to update password").show();
            return;
        }
        AlertFactory.createAlert(Alert.AlertType.INFORMATION, "Password updated successfully.").show();
        this.parentWindow.close();
    }
}
