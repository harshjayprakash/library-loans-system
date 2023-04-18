package winchester.library.presentation.view;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import winchester.library.data.model.items.Book;
import winchester.library.presentation.component.ItemCard;
import winchester.library.service.DatabaseInteraction;

public class InventoryView extends View {

    private ScrollPane scrollPane;
    private VBox itemsList;
    private ArrayList<ItemCard> items;

    public InventoryView() {
        super();
        this.setSpacing(20);
        this.initialiseLayouts();
        this.initialiseControls();
        this.addComponentsToView();
    }

    @Override
    protected void initialiseLayouts() {
        this.itemsList = new VBox();
        this.itemsList.setId("background-primary");
        this.itemsList.setSpacing(20);
        this.scrollPane = new ScrollPane();
        this.scrollPane.setId("background-primary");
        this.scrollPane.setFitToWidth(true);
    }

    @Override
    protected void initialiseControls() {
        Optional<HashSet<Book>> optionalBooks = DatabaseInteraction.getInstance().getBooks();
        if (optionalBooks.isEmpty()) {
            DatabaseNotConnectedView notConnectedView = new DatabaseNotConnectedView();
            this.getChildren().addAll(notConnectedView);
            return;
        }
        DatabaseNotConnectedView notConnectedView = new DatabaseNotConnectedView();
        this.getChildren().addAll(notConnectedView);
        this.items = new ArrayList<>();
        for (Book book : optionalBooks.get()) {
            this.items.add(new ItemCard(book));
        }
    }

    @Override
    protected void addComponentsToView() {
        for (ItemCard card : this.items) {
            this.itemsList.getChildren().add(card);
        }
        this.scrollPane.setContent(this.itemsList);
        this.getChildren().add(this.scrollPane);
    }
}
