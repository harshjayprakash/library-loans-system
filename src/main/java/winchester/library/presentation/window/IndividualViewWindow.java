package winchester.library.presentation.window;

import javafx.geometry.Insets;
import winchester.library.data.model.items.Item;
import winchester.library.data.model.loans.Loan;
import winchester.library.data.model.users.User;
import winchester.library.presentation.component.pane.HeaderPane;
import winchester.library.presentation.view.Views;
import winchester.library.presentation.view.ViewsManager;

/**
 * A class to provide a window that display an individual view.
 */
public final class IndividualViewWindow extends WindowBase {

    private HeaderPane header;
    private ViewsManager viewsManager;
    private final Views windowContentView;
    private final Item item;
    private final Loan loan;
    private final User user;

    /**
     * The main constructor for the IndividualViewWindow.
     * @param windowContentView the view to be shown.
     * @param user the user to be passed through to the view.
     * @param item the item to be passed through to the view.
     * @param loan the loan to be passed through to the view.
     */
    public IndividualViewWindow(Views windowContentView, User user, Item item, Loan loan) {
        super();
        this.windowContentView = windowContentView;
        this.user = user;
        this.item = item;
        this.loan = loan;
        this.initialiseLayouts();
        this.initialiseControls();
        this.addComponentsToStage();
    }

    /**
     * An overloaded constructor for the IndividualViewWindow.
     * @param windowContentView the view to be shown.
     */
    public IndividualViewWindow(Views windowContentView) {
        this(windowContentView, null, null, null);
    }

    /**
     * An overloaded constructor for the IndividualViewWindow.
     * @param windowContentView the view to be shown.
     * @param user the user to be passed through to the view.
     */
    public IndividualViewWindow(Views windowContentView, User user) {
        this(windowContentView, user, null, null);
    }

    /**
     * An overloaded constructor for the IndividualViewWindow.
     * @param windowContentView the view to be shown.
     * @param item the item to be passed through to the view.
     */
    public IndividualViewWindow(Views windowContentView, Item item) {
        this(windowContentView, null, item, null);
    }

    /**
     * An overloaded constructor for the IndividualViewWindow.
     * @param windowContentView the view to be shown.
     * @param loan the loan to be passed through to the view.
     */
    public IndividualViewWindow(Views windowContentView, Loan loan) {
        this(windowContentView, null, null, loan);
    }

    /**
     * A method to set the text of the header pane object.
     * @param text the text to be shown.
     */
    @Override
    public void setHeaderText(String text) {
        this.header.setPageTitle(text);
    }

    /**
     * A method to initialise layouts of the window.
     */
    private void initialiseLayouts() {
        this.baseLayout.setPadding(new Insets(15));
    }

    /**
     * A method to initialise any controls used within the window.
     */
    private void initialiseControls() {
        this.header = new HeaderPane(this.windowContentView);
        this.viewsManager = new ViewsManager();
    }

    /**
     * A method to add any components to the window (stage).
     */
    private void addComponentsToStage() {
        this.viewsManager.showView(this.windowContentView, this, this.user, this.item, this.loan);
        this.baseLayout.setTop(this.header);
        this.baseLayout.setCenter(this.viewsManager);
    }
}
