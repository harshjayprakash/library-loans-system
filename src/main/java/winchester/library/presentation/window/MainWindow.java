package winchester.library.presentation.window;

import javafx.geometry.Insets;
import javafx.scene.input.MouseEvent;
import winchester.library.presentation.component.HeaderPane;
import winchester.library.presentation.component.SidePane;
import winchester.library.presentation.component.StatusPane;
import winchester.library.presentation.view.NoneSidePaneView;
import winchester.library.presentation.view.Views;
import winchester.library.presentation.view.ViewsManager;
import winchester.library.service.DatabaseInteraction;

public class MainWindow extends WindowBase {

    private HeaderPane headerPane;
    private SidePane sidePane;
    private StatusPane statusPane;
    private ViewsManager viewsManager;

    public MainWindow() {
        super();
        this.setTitleText("Main Window");
        this.initialiseControls();
        this.bindEventHandlers();
        this.addComponentsToStage();
    }

    @Override
    protected void initialiseControls() {
        this.headerPane = new HeaderPane(Views.NONE_WITH_SIDEBAR, Views.NONE);
        this.sidePane = new SidePane(null);
        this.sidePane.setPrefWidth(150);
        this.statusPane = new StatusPane();
        this.statusPane.setDatabaseConnected(DatabaseInteraction.getInstance().getDatabaseAvailable());
        this.viewsManager = new ViewsManager();
        this.viewsManager.setPadding(new Insets(20));
    }

    @Override
    protected void bindEventHandlers() {
        this.sidePane.getToggleGroup().selectedToggleProperty().addListener(
                (value, toggle, newToggle) -> {
                    this.headerPane.setPageTitle(this.sidePane.getSelectedToggleAsView());
                    this.viewsManager.showView(this.sidePane.getSelectedToggleAsView(), this, null);
                    this.statusPane.setDatabaseConnected(DatabaseInteraction.getInstance().getDatabaseAvailable());
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
        this.viewsManager.setCenter(new NoneSidePaneView());
    }
}
