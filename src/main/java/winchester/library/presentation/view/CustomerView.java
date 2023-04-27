package winchester.library.presentation.view;

import java.util.ArrayList;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import winchester.library.data.model.users.Customer;
import winchester.library.presentation.component.Banner;
import winchester.library.presentation.component.card.UserCard;
import winchester.library.presentation.window.WindowBase;
import winchester.library.service.DatabaseInteraction;

public final class CustomerView extends View {

    private ScrollPane scrollPane;
    private VBox customerList;
    private ArrayList<UserCard> customersCardList;
    private Banner banner;

    public CustomerView(WindowBase parentWindow) {
        super(parentWindow, Views.CUSTOMERS.toString());
        setSpacing(20);
        this.initialiseLayouts();
        this.initialiseControls();
        this.addComponentsToView();
    }

    @Override
    protected void initialiseLayouts() {
        this.customerList = new VBox();
        this.customerList.setId("background-primary");
        this.customerList.setSpacing(20);
        this.scrollPane = new ScrollPane();
        this.scrollPane.setId("background-primary");
        this.scrollPane.setFitToWidth(true);
    }

    @Override
    protected void initialiseControls() {
        ArrayList<Customer> customers = DatabaseInteraction.getInstance().getCustomers();
        if (customers.isEmpty()) {
            this.banner = new Banner(
                    "No Customers",
                    ""
            );
            return;
        }
        this.customersCardList = new ArrayList<>();
        for (Customer customer : customers) {
            this.customersCardList.add(new UserCard(customer));
        }
    }

    @Override
    protected void addComponentsToView() {
        if (this.banner != null) {
            this.getChildren().add(this.banner);
            return;
        }
        for (UserCard card : this.customersCardList) {
            this.customerList.getChildren().add(card);
        }
        this.scrollPane.setContent(this.customerList);
        this.getChildren().add(this.scrollPane);
    }
}
