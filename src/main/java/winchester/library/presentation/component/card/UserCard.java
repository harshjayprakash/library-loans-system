package winchester.library.presentation.component.card;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import winchester.library.data.model.users.*;
import winchester.library.presentation.view.Views;
import winchester.library.presentation.window.IndividualViewWindow;

/**
 * A class that provides a control to view any user type of the system.
 */
public final class UserCard extends Card {

    private VBox userDetails;
    private Label nameLabel;
    private Label usernameLabel;
    private Label accountTypeLabel;
    private Label accountStatusLabel;
    private User referencedUser;

    public UserCard(User user) {
        super();
        this.referencedUser = user;
        this.initialiseLayouts();
        this.initialiseControls();
        this.bindEventHandlers();
        this.addComponentsToCard();
    }

    @Override
    protected void initialiseLayouts() {
        this.userDetails = new VBox();
        this.userDetails.getStyleClass().add("background-secondary");
        this.userDetails.setPadding(new Insets(10));
    }

    @Override
    protected void initialiseControls() {
        this.nameLabel = new Label();
        this.nameLabel.setText(this.referencedUser.getFullName());
        if (this.referencedUser.getType() != UserType.CUSTOMER) {
            this.usernameLabel = new Label();
            this.usernameLabel.setText(Employee.castFrom(this.referencedUser).getUsername());
            this.accountTypeLabel = new Label();
            this.accountTypeLabel.setText("Account Type: " + this.referencedUser.getType().toString());
            this.accountStatusLabel = new Label();
            this.accountStatusLabel.setText(
                "Account Status: " + Employee.castFrom(this.referencedUser).getStatus().toString());
        }
    }

    @Override
    protected void bindEventHandlers() {
        this.viewDetailsLabel.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            IndividualViewWindow individualCustomerView = new IndividualViewWindow(
                    switch (this.referencedUser.getType()) {
                        case CUSTOMER -> Views.INDIVIDUAL_CUSTOMER;
                        case STANDARD, ADMINISTRATOR -> Views.INDIVIDUAL_EMPLOYEE;
                        default -> Views.NONE;
                    },
                    this.referencedUser);
            individualCustomerView.show();
        });
    }

    @Override
    protected void addComponentsToCard() {
        this.userDetails.getChildren().add(this.nameLabel);
        if (this.referencedUser.getType() != UserType.CUSTOMER) {
            this.userDetails.getChildren().addAll(this.usernameLabel, this.accountTypeLabel, this.accountStatusLabel);
        }
        this.setCenter(this.userDetails);
    }

}
