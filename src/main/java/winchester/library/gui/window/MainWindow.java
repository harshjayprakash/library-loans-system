package winchester.library.gui.window;

import javafx.geometry.Insets;
import javafx.scene.input.MouseEvent;
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
        this.bindEventHandlers();
        this.addComponentsToStage();
    }

    @Override
    protected void initialiseLayouts() {

    }

    @Override
    protected void initialiseControls() {
        this.headerPane = new HeaderPane(Views.NONE_WITH_SIDEBAR, Views.NONE);
        this.sidePane = new SidePane(null);
        this.sidePane.setPrefWidth(150);
        this.statusPane = new StatusPane();
        this.viewsManager = new ViewsManager();
        this.viewsManager.setPadding(new Insets(20));
    }

    private void bindEventHandlers() {
        this.sidePane.getToggleGroup().selectedToggleProperty().addListener(
                (value, toggle, newToggle) -> {
                    this.headerPane.setPageTitle(this.sidePane.getSelectedToggleAsView());
                    this.viewsManager.showView(this.sidePane.getSelectedToggleAsView(), this, null);
                });
        this.sidePane.getLogOutButton().addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            this.close();
            IndividualViewWindow loginView = new IndividualViewWindow(Views.LOGIN, null);
            loginView.show();
        });
    }

    @Override
    protected void addComponentsToStage() {
        this.baseLayout.setLeft(this.sidePane);
        this.baseLayout.setBottom(this.statusPane);
        this.baseLayout.setCenter(this.viewsManager);
        this.viewsManager.setTop(this.headerPane);
    }
}
