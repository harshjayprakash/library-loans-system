package winchester.library.presentation.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import winchester.library.presentation.window.WindowBase;

public final class AddUserView extends View {

    private Label descriptionLabel;
    private Label firstNameLabel;
    private Label lastNameLabel;
    private Label postalCodeLabel;
    private TextField firstNameField;
    private TextField lastNameField;
    private TextField postalCodeField;
    private HBox actionButtonsLayout;
    private Button cancelButton;
    private Button createButton;

    public AddUserView(WindowBase parentWindow) {
        super(parentWindow, Views.ADD_USER.toString());
        this.initialiseLayouts();
        this.initialiseControls();
        this.bindEventHandlers();
        this.addComponentsToView();
    }

    @Override
    protected void initialiseLayouts() {
        this.actionButtonsLayout = new HBox();
        this.actionButtonsLayout.setAlignment(Pos.CENTER_RIGHT);
        this.actionButtonsLayout.setSpacing(10);
        this.actionButtonsLayout.setPadding(new Insets(10, 0, 0, 0));
    }

    @Override
    protected void initialiseControls() {
        this.descriptionLabel = new Label();
        this.descriptionLabel.setText("Please enter the details of the new customer.");
        this.descriptionLabel.setPadding(new Insets(0, 0, 10, 0));
        this.firstNameLabel = new Label();
        this.firstNameLabel.setText("First Name: ");
        this.firstNameField = new TextField();
        this.lastNameLabel = new Label();
        this.lastNameLabel.setText("Last Name: ");
        this.lastNameLabel.setPadding(new Insets(10, 0, 0, 0));
        this.lastNameField = new TextField();
        this.postalCodeLabel = new Label();
        this.postalCodeLabel.setText("Postal Code: ");
        this.postalCodeLabel.setPadding(new Insets(10, 0, 0, 0));
        this.postalCodeField = new TextField();
        this.cancelButton = new Button();
        this.cancelButton.setText("Cancel");
        this.createButton = new Button();
        this.createButton.setText("Create");
    }

    private void bindEventHandlers() {
        this.cancelButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            this.parentWindow.close();
        });
        this.createButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {

        });
    }

    @Override
    protected void addComponentsToView() {
        this.actionButtonsLayout.getChildren().addAll(this.cancelButton, this.createButton);
        this.getChildren().addAll(this.descriptionLabel, this.firstNameLabel, this.firstNameField, this.lastNameLabel, this.lastNameField,
                this.postalCodeLabel, this.postalCodeField, this.actionButtonsLayout);
    }
}
