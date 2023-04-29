package winchester.library.presentation.window;

import javafx.geometry.Insets;
import winchester.library.data.model.items.Item;
import winchester.library.data.model.users.Employee;
import winchester.library.presentation.component.pane.HeaderPane;
import winchester.library.presentation.view.Views;
import winchester.library.presentation.view.ViewsManager;

public final class IndividualViewWindow extends WindowBase {

    private HeaderPane header;
    private ViewsManager viewsManager;
    private final Views windowContentView;
    private final Employee employee;
    private final Item item;

    public IndividualViewWindow(Views windowContentView, Employee employee, Item item) {
        super();
        this.windowContentView = windowContentView;
        this.employee = employee;
        this.item = item;
        this.initialiseLayouts();
        this.initialiseControls();
        this.addComponentsToStage();
    }

    public IndividualViewWindow(Views windowContentView, Item item) {
        this(windowContentView, null, item);
    }

    public IndividualViewWindow(Views windowContentView, Employee employee) {
        this(windowContentView, employee, null);
    }

    public IndividualViewWindow(Views windowContentView) {
        this(windowContentView, null, null);
    }

    private void initialiseLayouts() {
        this.baseLayout.setPadding(new Insets(15));
    }

    @Override
    protected void initialiseControls() {
        this.header = new HeaderPane(this.windowContentView, Views.NONE);
        this.viewsManager = new ViewsManager();
    }

    @Override
    protected void bindEventHandlers() {

    }

    @Override
    protected void addComponentsToStage() {
        this.viewsManager.showView(this.windowContentView, this, this.employee, this.item);
        this.baseLayout.setTop(this.header);
        this.baseLayout.setCenter(this.viewsManager);
    }
}
