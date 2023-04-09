package winchester.library.presentation.window;

import javafx.geometry.Insets;
import javafx.stage.WindowEvent;
import winchester.library.data.model.users.Employee;
import winchester.library.presentation.component.HeaderPane;
import winchester.library.presentation.view.Views;
import winchester.library.presentation.view.ViewsManager;

public class IndividualViewWindow extends WindowBase {

    private static int instanceCount = 0;
    private HeaderPane header;
    private ViewsManager viewsManager;
    private final Views windowContentView;
    private final Employee employee;

    public IndividualViewWindow(Views windowContentView, Employee employee) {
        super();
        this.windowContentView = windowContentView;
        this.employee = employee;
        this.initialiseLayouts();
        this.initialiseControls();
        this.addComponentsToStage();
        IndividualViewWindow.instanceCount += 1;
    }

    protected void initialiseLayouts() {
        this.baseLayout.setPadding(new Insets(15));
    }

    @Override
    protected void initialiseControls() {
        this.header = new HeaderPane(this.windowContentView, Views.NONE);
        this.viewsManager = new ViewsManager();
    }

    @Override
    protected void bindEventHandlers() {
        this.addEventHandler(WindowEvent.WINDOW_CLOSE_REQUEST, event -> IndividualViewWindow.instanceCount -= 1);
    }

    @Override
    protected void addComponentsToStage() {
        this.viewsManager.showView(this.windowContentView, this, this.employee);
        this.baseLayout.setTop(this.header);
        this.baseLayout.setCenter(this.viewsManager);
    }

    public static int getInstanceCount() {
        return IndividualViewWindow.instanceCount;
    }
}
