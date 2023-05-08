package winchester.library.presentation.view;

import java.util.ArrayList;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import winchester.library.data.model.users.Employee;
import winchester.library.presentation.component.card.UserCard;
import winchester.library.presentation.component.pane.ActionPane;
import winchester.library.presentation.component.pane.BannerPane;
import winchester.library.presentation.component.pane.SearchPane;
import winchester.library.presentation.window.WindowBase;
import winchester.library.service.DataPersistenceManager;
import winchester.library.service.Searcher;

/**
 * A view to list all the employees registered within the system.
 */
public final class UsersView extends View {

    private ActionPane actionsLayout;
    private BannerPane banner;
    private SearchPane search;
    private ScrollPane scrollPane;
    private VBox employeeList;
    private ArrayList<UserCard> usersCardList;

    /**
     * The default constructor that passes the parent window.
     * @param parentWindow the parent window that the view can access.
     */
    public UsersView(WindowBase parentWindow) {
        super(parentWindow, Views.USERS.toString());
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
        this.actionsLayout = new ActionPane();
        this.employeeList = new VBox();
        this.employeeList.getStyleClass().add("background-primary");
        this.employeeList.setSpacing(20);
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
        this.initialiseUserCards();
    }

    /**
     * A method to initialise all the cards displaying the user information.
     */
    private void initialiseUserCards() {
        ArrayList<Employee> employees = DataPersistenceManager.getInstance().getEmployees();
        if (employees.isEmpty()) {
            this.banner = new BannerPane("No Employees", "");
            return;
        }
        this.usersCardList = new ArrayList<>();
        employees.forEach(employee -> this.usersCardList.add(new UserCard(employee)));
    }

    /**
     * A method to bind and add event handlers to components.
     */
    private void bindEventHandlers() {
        this.search.getSearchButton().addEventHandler(MouseEvent.MOUSE_CLICKED, event -> this.updateCardsList());
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
        this.employeeList.getChildren().addAll(this.usersCardList);
        this.scrollPane.setContent(this.employeeList);
        this.getChildren().addAll(this.actionsLayout, this.scrollPane);
    }

    /**
     * A method to update the cards list based on the search.
     */
    private void updateCardsList() {
        Searcher searcher = new Searcher();
        this.employeeList.getChildren().clear();
        this.usersCardList.clear();
        searcher.searchEmployees(this.search.getSearchText()).forEach(
                employee -> this.usersCardList.add(new UserCard(employee)));
        this.employeeList.getChildren().addAll(this.usersCardList);
    }

}
