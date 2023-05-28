package winchester.library.presentation.component.card;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import winchester.library.data.model.users.Employee;
import winchester.library.data.model.users.User;
import winchester.library.data.model.users.UserType;
import winchester.library.presentation.view.Views;
import winchester.library.presentation.window.IndividualViewWindow;

/**
 * A class that provides a control to view any user type of the system.
 */
public final class UserCard extends Card {

    private VBox userDetails;
    private Label nameLabel;
    private Label accountTypeLabel;
    private Label accountStatusLabel;
    private final User referencedUser;

    /**
     * The default constructor for the UserCard.
     * @param user the user that will be referenced to display the information.
     */
    public UserCard(User user) {
        super();
        this.referencedUser = user;
        this.initialiseLayouts();
        this.initialiseControls();
        this.bindEventHandlers();
        this.addComponentsToCard();
    }

    /**
     * A method to initialise the layouts that will be used within the card component.
     */
    private void initialiseLayouts() {
        this.userDetails = new VBox();
        this.userDetails.getStyleClass().add("background-secondary");
        this.userDetails.setPadding(new Insets(10));
    }

    /**
     * A method to initialise the controls that will be shown within the card component.
     */
    private void initialiseControls() {
        this.nameLabel = new Label();
        this.nameLabel.setText(this.referencedUser.getFullName());
        if (this.referencedUser.getType() != UserType.CUSTOMER) {
            this.accountTypeLabel = new Label();
            this.accountTypeLabel.setText("Account Type: " + this.referencedUser.getType().toString());
            this.accountStatusLabel = new Label();
            this.accountStatusLabel.setText(
                "Account Status: " + Employee.castFrom(this.referencedUser).getStatus().toString());
        }
    }

    /**
     * A method to add event handlers to any controls.
     */
    private void bindEventHandlers() {
        this.viewDetailsLabel.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> this.startIndividualUserView());
    }

    /**
     * A method to add any layouts and controls initialised to the card.
     */
    private void addComponentsToCard() {
        this.userDetails.getChildren().add(this.nameLabel);
        if (this.referencedUser.getType() != UserType.CUSTOMER) {
            this.userDetails.getChildren().addAll(this.accountTypeLabel, this.accountStatusLabel);
        }
        this.setCenter(this.userDetails);
    }

    /**
     * A method to start a window with individual customer or employee view, given the user's type.
     */
    private void startIndividualUserView() {
        IndividualViewWindow individualCustomerView = new IndividualViewWindow(
                switch (this.referencedUser.getType()) {
                    case CUSTOMER -> Views.INDIVIDUAL_CUSTOMER;
                    case STANDARD, ADMINISTRATOR -> Views.INDIVIDUAL_EMPLOYEE;
                },
                this.referencedUser);
        individualCustomerView.show();
    }

}
