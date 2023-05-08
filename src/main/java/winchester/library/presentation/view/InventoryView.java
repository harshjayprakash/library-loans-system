package winchester.library.presentation.view;

import java.util.ArrayList;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import winchester.library.data.model.items.Book;
import winchester.library.data.model.items.Film;
import winchester.library.presentation.component.card.ItemCard;
import winchester.library.presentation.component.pane.ActionPane;
import winchester.library.presentation.component.pane.BannerPane;
import winchester.library.presentation.component.pane.SearchPane;
import winchester.library.presentation.window.IndividualViewWindow;
import winchester.library.presentation.window.WindowBase;
import winchester.library.service.DataPersistenceManager;
import winchester.library.service.Searcher;

/**
 * A view that lists all available items within the library.
 */
public final class InventoryView extends View {

    private ActionPane actions;
    private SearchPane search;
    private Button addItemsButton;
    private ScrollPane scrollPane;
    private VBox itemsList;
    private ArrayList<ItemCard> items;
    private BannerPane banner;

    /**
     * The default constructor that passes the parent window.
     * @param parentWindow the parent window that the view can access.
     */
    public InventoryView(WindowBase parentWindow) {
        super(parentWindow, Views.INVENTORY.toString());
        this.setSpacing(20);
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
        this.itemsList = new VBox();
        this.itemsList.getStyleClass().add("background-primary");
        this.itemsList.setSpacing(20);
        this.scrollPane = new ScrollPane();
        this.scrollPane.getStyleClass().add("background-primary");
        this.scrollPane.setFitToWidth(true);
    }

    /**
     * A method to initialise any controls used within the view.
     */
    @Override
    protected void initialiseControls() {
        this.search = new SearchPane();
        this.actions = new ActionPane();
        this.addItemsButton = new Button();
        this.addItemsButton.setText("Add Item");
        this.initialiseItemCards();
    }

    /**
     * A method to initialise the cards displaying each item.
     */
    private void initialiseItemCards() {
        ArrayList<Book> books = DataPersistenceManager.getInstance().getBooks();
        ArrayList<Film> films = DataPersistenceManager.getInstance().getFilms();
        if (books.isEmpty() && films.isEmpty()) {
            this.banner = new BannerPane(
                    "No Items In Inventory", "");
            return;
        }
        this.items = new ArrayList<>();
        books.forEach(book -> this.items.add(new ItemCard(book)));
        films.forEach(film -> this.items.add(new ItemCard(film)));
    }

    /**
     * A method to bind and add event handlers to components.
     */
    private void bindEventHandlers() {
        this.search.getSearchButton().addEventHandler(MouseEvent.MOUSE_CLICKED, event -> this.updateItemCardsList());
        this.addItemsButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> this.startIndividualAddItemWindow());
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
        this.itemsList.getChildren().addAll(this.items);
        this.scrollPane.setContent(this.itemsList);
        this.actions.getLeftControls().getChildren().add(this.addItemsButton);
        this.actions.getRightControls().getChildren().add(this.search);
        this.getChildren().addAll(this.actions, this.scrollPane);
    }

    /**
     * A method to update the item cards shown based on the search.
     */
    private void updateItemCardsList() {
        Searcher searcher = new Searcher();
        this.itemsList.getChildren().clear();
        this.items.clear();
        searcher.searchItems(this.search.getSearchText()).forEach(item -> this.items.add(new ItemCard(item)));
        this.itemsList.getChildren().addAll(this.items);
    }

    /**
     * A method to start a window with the add item view.
     */
    private void startIndividualAddItemWindow() {
        IndividualViewWindow addItemView = new IndividualViewWindow(Views.ADD_ITEM);
        addItemView.show();
    }
}
