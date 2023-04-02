package winchester.library.gui.window;

import winchester.library.gui.component.HeaderPane;
import winchester.library.gui.component.SidePane;
import winchester.library.gui.component.StatusPane;
import winchester.library.gui.view.Views;
import winchester.library.gui.view.ViewsManager;

public class MainWindow extends WindowBase {

    private HeaderPane headerPane;
    private SidePane sidePane;
    private StatusPane statusPane;
    private ViewsManager viewsManager;

    public MainWindow() {
        super();
        this.initialiseLayouts();
        this.initialiseControls();
        this.addComponentsToStage();
    }

    @Override
    protected void initialiseLayouts() {

    }

    @Override
    protected void initialiseControls() {
        this.headerPane = new HeaderPane(Views.NONE_WITH_SIDEBAR, Views.NONE);
        this.sidePane = new SidePane(null);
        this.statusPane = new StatusPane();
        this.viewsManager = new ViewsManager();
    }

    @Override
    protected void addComponentsToStage() {
        this.baseLayout.setLeft(this.sidePane);
        this.baseLayout.setBottom(this.statusPane);
        this.baseLayout.setTop(this.headerPane);
        this.baseLayout.setCenter(this.viewsManager);
    }
}
