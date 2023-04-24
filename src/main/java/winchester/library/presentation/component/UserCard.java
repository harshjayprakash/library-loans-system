package winchester.library.presentation.component;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import winchester.library.data.model.users.Employee;
import winchester.library.data.model.users.User;
import winchester.library.data.model.users.UserType;
import winchester.library.presentation.view.Views;
import winchester.library.presentation.window.IndividualViewWindow;

public class UserCard extends BorderPane {
 
    private VBox userDetailsLayout;
    private HBox actionsLayout;
    private Label fullNameLabel;
    private Label usernameLabel;
    private Label accountTypeLabel;
    private Label accountStatusLabel;
    private Label viewDetailsLinkLabel;
    private User user;

    public UserCard(User user) {
        super();
        this.user = user;
        this.setId("background-secondary-border");
        this.initialiseLayouts();
        this.initialiseControls();
        this.bindEventHandlers();
        this.addComponentsToCard();
    }

    private void initialiseLayouts() {
        this.userDetailsLayout = new VBox();
        this.userDetailsLayout.setPadding(new Insets(10));
        this.actionsLayout = new HBox();
        this.actionsLayout.setPadding(new Insets(10));
    }

    private void initialiseControls() {
        this.fullNameLabel = new Label();
        this.fullNameLabel.setText(this.user.getFullName());
        this.viewDetailsLinkLabel = new Label();
        this.viewDetailsLinkLabel.setText("View Details");
        if (this.user.getType() != UserType.CUSTOMER) {
            this.usernameLabel = new Label();
            this.usernameLabel.setText(Employee.castFrom(user).getUsername());
            this.accountTypeLabel = new Label();
            this.accountTypeLabel.setText(user.getType().toString() + " Account");
            this.accountStatusLabel = new Label();
            this.accountStatusLabel.setText(Employee.castFrom(user).getStatus().toString());
        }
    }

    private void bindEventHandlers() {
        this.viewDetailsLinkLabel.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            IndividualViewWindow individualCustomerView = new IndividualViewWindow(Views.INDIVIDUAL_CUSTOMER);
            individualCustomerView.show();
        });
    }

    private void addComponentsToCard() {
        this.userDetailsLayout.getChildren().add(this.fullNameLabel);
        if (this.user.getType() != UserType.CUSTOMER) {
            this.userDetailsLayout.getChildren().addAll(
                    this.usernameLabel, this.accountTypeLabel, this.accountStatusLabel);
        }
        this.setRight(this.actionsLayout);
        this.actionsLayout.getChildren().add(this.viewDetailsLinkLabel);
        this.setCenter(this.userDetailsLayout);
    }

}
