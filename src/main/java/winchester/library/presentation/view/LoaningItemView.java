package winchester.library.presentation.view;

import java.util.ArrayList;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import winchester.library.data.model.users.Customer;
import winchester.library.presentation.component.card.ItemToLoanCard;
import winchester.library.presentation.component.pane.ActionPane;
import winchester.library.presentation.component.pane.SearchPane;
import winchester.library.presentation.window.WindowBase;
import winchester.library.service.DataPersistenceManager;
import winchester.library.service.Searcher;

/**
 * A view to be able to loan out items to the given customer.
 */
public final class LoaningItemView extends View {

    private ActionPane actionPane;
    private SearchPane search;
    private ArrayList<ItemToLoanCard> loaningItemCardsList;
    private ScrollPane scrollPane;
    private Button refreshButton;
    private VBox loaningItemsList;
    private final Customer referencedCustomer;

    /**
     * The default constructor that passes the parent window and the customer to be referenced.
     * @param parentWindow the parent window that the view can access.
     * @param customer the customer referenced.
     */
    public LoaningItemView(WindowBase parentWindow, Customer customer) {
        super(parentWindow, Views.LOANING_ITEMS.toString());
        this.referencedCustomer = customer;
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
        this.actionPane = new ActionPane();
        this.scrollPane = new ScrollPane();
        this.scrollPane.getStyleClass().add("background-primary");
        this.scrollPane.setFitToWidth(true);
        this.loaningItemsList = new VBox();
        this.loaningItemsList.setSpacing(20);
        this.loaningItemsList.getStyleClass().add("background-primary");
    }

    /**
     * A method to initialise any controls used within the view.
     */
    @Override
    protected void initialiseControls() {
        this.search = new SearchPane();
        this.refreshButton = new Button();
        this.refreshButton.setText("Refresh");
        this.loaningItemCardsList = new ArrayList<>();
        DataPersistenceManager.getInstance().getBooks().forEach(
                book -> this.loaningItemCardsList.add(new ItemToLoanCard(book, this.referencedCustomer)));
        DataPersistenceManager.getInstance().getFilms().forEach(
                film -> this.loaningItemCardsList.add(new ItemToLoanCard(film, this.referencedCustomer)));
    }

    /**
     * A method to bind and add event handlers to components.
     */
    private void bindEventHandlers() {
        this.search.getSearchButton().addEventHandler(MouseEvent.MOUSE_CLICKED, event -> this.updateCardsList());
        this.refreshButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> this.updateCardsList());
    }

    /**
     * A method to add components to the view.
     */
    @Override
    protected void addComponentsToView() {
        this.loaningItemsList.getChildren().addAll(this.loaningItemCardsList);
        this.scrollPane.setContent(this.loaningItemsList);
        this.actionPane.getLeftControls().getChildren().add(refreshButton);
        this.actionPane.getRightControls().getChildren().add(this.search);
        this.getChildren().addAll(this.actionPane, this.scrollPane);
    }

    /**
     * A method to update the cards list based on the search.
     */
    private void updateCardsList() {
        Searcher searcher = new Searcher();
        this.loaningItemsList.getChildren().clear();
        this.loaningItemCardsList.clear();
        searcher.searchItems(this.search.getSearchText()).forEach(
                item -> this.loaningItemCardsList.add(new ItemToLoanCard(item, this.referencedCustomer)));
        this.loaningItemsList.getChildren().addAll(this.loaningItemCardsList);
    }
}
