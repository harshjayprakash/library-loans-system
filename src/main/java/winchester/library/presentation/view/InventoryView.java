package winchester.library.presentation.view;

import java.util.ArrayList;
import java.util.Optional;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import winchester.library.data.model.items.Book;
import winchester.library.presentation.component.Banner;
import winchester.library.presentation.component.ItemCard;
import winchester.library.service.DatabaseInteraction;

public class InventoryView extends View {

    private ScrollPane scrollPane;
    private VBox itemsList;
    private ArrayList<ItemCard> items;
    private Banner banner;

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
        Optional<ArrayList<Book>> optionalBooks = DatabaseInteraction.getInstance().getBooks();
        if (optionalBooks.isEmpty()) {
            this.banner = new Banner(
                "Data is not accessible", 
                "Please check the database configuration by clicking on the database status on the bottom right hand "
                + "corner.");
                return;
        } 
        this.items = new ArrayList<>();
        for (Book book : optionalBooks.get()) {
            this.items.add(new ItemCard(book));
        }
    }

    @Override
    protected void addComponentsToView() {
        if (this.banner != null) {
            this.getChildren().add(this.banner);
            return;
        }
        for (ItemCard card : this.items) {
            this.itemsList.getChildren().add(card);
        }
        this.scrollPane.setContent(this.itemsList);
        this.getChildren().add(this.scrollPane);
    }
}
