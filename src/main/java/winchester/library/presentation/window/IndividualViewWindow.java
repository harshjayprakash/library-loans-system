package winchester.library.presentation.window;

import javafx.geometry.Insets;
import winchester.library.data.model.users.Employee;
import winchester.library.presentation.component.HeaderPane;
import winchester.library.presentation.view.Views;
import winchester.library.presentation.view.ViewsManager;

public class IndividualViewWindow extends WindowBase {
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
    protected void addComponentsToStage() {
        this.viewsManager.showView(this.windowContentView, this, this.employee);
        this.baseLayout.setTop(this.header);
        this.baseLayout.setCenter(this.viewsManager);
    }
}
