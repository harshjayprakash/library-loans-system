package winchester.library.presentation.view;

import java.util.ArrayList;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import winchester.library.data.model.users.Customer;
import winchester.library.presentation.component.card.UserCard;
import winchester.library.presentation.component.pane.ActionPane;
import winchester.library.presentation.component.pane.BannerPane;
import winchester.library.presentation.component.pane.SearchPane;
import winchester.library.presentation.window.IndividualViewWindow;
import winchester.library.presentation.window.WindowBase;
import winchester.library.service.DataPersistenceManager;
import winchester.library.service.Searcher;

/**
 * A view that displays a list of all the customers registered.
 */
public final class CustomerView extends View {

    private ActionPane actionsLayout;
    private BannerPane banner;
    private SearchPane search;
    private ScrollPane scrollPane;
    private VBox customerList;
    private ArrayList<UserCard> customersCardList;
    private Button addCustomerButton;

    /**
     * The default constructor that passes the parent window.
     * @param parentWindow the parent window that the view can access.
     */
    public CustomerView(WindowBase parentWindow) {
        super(parentWindow, Views.CUSTOMERS.toString());
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
        this.setSpacing(20);
        this.actionsLayout = new ActionPane();
        this.customerList = new VBox();
        this.customerList.getStyleClass().add("background-primary");
        this.customerList.setSpacing(20);
        this.scrollPane = new ScrollPane();
        this.scrollPane.getStyleClass().add("background-primary");
        this.scrollPane.setFitToWidth(true);
    }

    /**
     * A method to initialise any controls used within the view.
     */
    @Override
    protected void initialiseControls() {
        this.addCustomerButton = new Button();
        this.addCustomerButton.setText("Add Customer");
        this.search = new SearchPane();
        this.customersCardList = new ArrayList<>();
        this.initialiseCustomerCardControls();
    }

    /**
     * A method to initialise all the cards that will be displaying the customers.
     */
    private void initialiseCustomerCardControls() {
        ArrayList<Customer> customers = DataPersistenceManager.getInstance().getCustomers();
        if (customers.isEmpty()) {
            this.banner = new BannerPane("No Customers", "Please ensure that you are connect to the data source.");
            return;
        }
        customers.forEach(customer -> this.customersCardList.add(new UserCard(customer)));
    }

    /**
     * A method to bind and add event handlers to components.
     */
    private void bindEventHandlers() {
        this.search.getSearchButton().addEventHandler(MouseEvent.MOUSE_CLICKED, event -> this.updateCardsList());
        this.addCustomerButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> this.startAddCustomerWindow());
    }

    /**
     * A method to add components to the view.
     */
    @Override
    protected void addComponentsToView() {
        if (this.banner != null) {
            this.getChildren().add(this.banner);
            return;
        }
        this.actionsLayout.getRightControls().getChildren().add(this.search);
        this.actionsLayout.getLeftControls().getChildren().add(this.addCustomerButton);
        this.customersCardList.forEach(card -> this.customerList.getChildren().add(card));
        this.scrollPane.setContent(this.customerList);
        this.getChildren().addAll(this.actionsLayout, this.scrollPane);
    }

    /**
     * A method to update the cards list shown based on the search.
     */
    private void updateCardsList() {
        Searcher searcher = new Searcher();
        this.customerList.getChildren().clear();
        this.customersCardList.clear();
        searcher.searchCustomers(this.search.getSearchText()).forEach(
                customer -> this.customersCardList.add(new UserCard(customer)));
        this.customersCardList.forEach(card -> this.customerList.getChildren().add(card));
    }

    /**
     * A method to start a window with the add users view.
     */
    private void startAddCustomerWindow() {
        IndividualViewWindow addUserView = new IndividualViewWindow(Views.ADD_USER);
        addUserView.show();
    }
}
