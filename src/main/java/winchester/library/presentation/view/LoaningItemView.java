package winchester.library.presentation.view;

import java.util.ArrayList;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import winchester.library.data.model.users.Customer;
import winchester.library.presentation.component.card.ItemToLoanCard;
import winchester.library.presentation.window.WindowBase;
import winchester.library.service.DataPersistenceManager;

public final class LoaningItemView extends View {

    private ArrayList<ItemToLoanCard> loaningItemCardsList;
    private ScrollPane scrollPane;
    private VBox loaningItemsList;
    private Customer referencedCustomer;

    public LoaningItemView(WindowBase parentWindow, Customer customer) {
        super(parentWindow, Views.LOANING_ITEMS.toString());
        this.referencedCustomer = customer;
        this.initialiseLayouts();
        this.initialiseControls();
        this.addComponentsToView();
    }

    @Override
    protected void initialiseLayouts() {
        this.setSpacing(20);
        this.scrollPane = new ScrollPane();
        this.scrollPane.getStyleClass().add("background-primary");
        this.scrollPane.setFitToWidth(true);
        this.loaningItemsList = new VBox();
        this.loaningItemsList.setSpacing(20);
        this.loaningItemsList.getStyleClass().add("background-primary");
    }

    @Override
    protected void initialiseControls() {
        this.loaningItemCardsList = new ArrayList<>();
        DataPersistenceManager.getInstance().getBooks().forEach(book -> this.loaningItemCardsList.add(new ItemToLoanCard(book, this.referencedCustomer)));
        DataPersistenceManager.getInstance().getFilms().forEach(film -> this.loaningItemCardsList.add(new ItemToLoanCard(film, this.referencedCustomer)));
    }

    @Override
    protected void addComponentsToView() {
        this.loaningItemsList.getChildren().addAll(this.loaningItemCardsList);
        this.scrollPane.setContent(this.loaningItemsList);
        this.getChildren().add(this.scrollPane);
    }
}
