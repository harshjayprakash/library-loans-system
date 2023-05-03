package winchester.library.presentation.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import winchester.library.data.model.users.Employee;
import winchester.library.data.model.users.UserType;
import winchester.library.presentation.window.WindowBase;
import winchester.library.service.PasswordValidator;

public final class ChangePasswordView extends View {

    private HBox actionButtons;
    private Label descriptionLabel;
    private Label currentUserLabel;
    private Label currentPasswordLabel;
    private Label passwordRequirementsLabel;
    private Label newPasswordLabel;
    private Label confirmNewPasswordLabel;
    private TextField currentUserField;
    private PasswordField currentPasswordField;
    private PasswordField newPasswordField;
    private PasswordField confirmNewPasswordField;
    private Button cancelButton;
    private Button changePasswordButton;
    private final Employee currentEmployee;
    private final PasswordValidator passwordPolicyManager;

    public ChangePasswordView(WindowBase parentWindow, Employee employee) {
        super(parentWindow, Views.CHANGE_PASSWORD.toString());
        this.currentEmployee = employee;
        this.passwordPolicyManager = new PasswordValidator();
        this.initialiseLayouts();
        this.initialiseControls();
        this.bindEventHandlers();
        this.addComponentsToView();
    }

    @Override
    protected void initialiseLayouts() {
        this.actionButtons = new HBox();
        this.actionButtons.setAlignment(Pos.CENTER_RIGHT);
        this.actionButtons.setPadding(new Insets(15, 0, 0, 0));
    }

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
        this.passwordRequirementsLabel.setText(this.passwordPolicyManager.getPasswordRequirements());
        this.newPasswordLabel = new Label();
        this.newPasswordLabel.setPadding(new Insets(10, 0, 0, 0));
        this.newPasswordLabel.setText("New Password: ");
        this.newPasswordField = new PasswordField();
        this.confirmNewPasswordLabel = new Label();
        this.confirmNewPasswordLabel.setPadding(new Insets(10, 0, 0, 0));
        this.confirmNewPasswordLabel.setText("Confirm New Password: ");
        this.confirmNewPasswordField = new PasswordField();
        this.cancelButton = new Button();
        this.cancelButton.setText("Cancel");
        this.changePasswordButton = new Button();
        this.changePasswordButton.setText("Change Password");
        HBox.setMargin(this.changePasswordButton, new Insets(0, 0, 0, 10));
    }

    private void bindEventHandlers() {
        this.cancelButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> this.parentWindow.close());
    }

    @Override
    protected void addComponentsToView() {
        this.actionButtons.getChildren().addAll(this.cancelButton, this.changePasswordButton);
        this.getChildren().addAll(
                this.descriptionLabel, this.currentUserLabel, this.currentUserField, this.currentPasswordLabel,
                this.currentPasswordField, this.newPasswordLabel, this.passwordRequirementsLabel,
                this.newPasswordField, this.confirmNewPasswordLabel, this.confirmNewPasswordField, this.actionButtons);
    }

}
