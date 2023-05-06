package winchester.library.presentation.window;

import javafx.geometry.Insets;
import javafx.scene.input.MouseEvent;
import winchester.library.presentation.component.pane.HeaderPane;
import winchester.library.presentation.component.pane.SidePane;
import winchester.library.presentation.component.pane.StatusPane;
import winchester.library.presentation.view.NoneSidePaneView;
import winchester.library.presentation.view.Views;
import winchester.library.presentation.view.ViewsManager;
import winchester.library.service.DatabaseConnectivityChecker;

/**
 * A class that provides the implementation of the Main Window that the user will be interacting with.
 * This class has been marked as final to prevent extension.
 */
public final class MainWindow extends WindowBase {

    private HeaderPane headerPane;
    private SidePane sidePane;
    private StatusPane statusPane;
    private ViewsManager viewsManager;

    /**
     * A constructor for the MainWindow.
     */
    public MainWindow() {
        super();
        this.setTitleText("Main Window");
        this.initialiseControls();
        this.bindEventHandlers();
        this.addComponentsToStage();
    }

    /**
     * A method to initialise controls for the main window.
     */
    @Override
    protected void initialiseControls() {
        this.headerPane = new HeaderPane(Views.NONE_WITH_SIDEBAR);
        this.sidePane = new SidePane(null);
        this.sidePane.setPrefWidth(150);
        this.statusPane = new StatusPane();
        this.statusPane.setDatabaseConnected(DatabaseConnectivityChecker.getInstance().getDatabaseAvailable());
        this.viewsManager = new ViewsManager();
        this.viewsManager.setPadding(new Insets(20));
    }

    /**
     * A method to add and bind event handlers to controls.
     */
    private void bindEventHandlers() {
        this.sidePane.getToggleGroup().selectedToggleProperty().addListener(
                (value, toggle, newToggle) -> {
                    this.headerPane.setPageTitle(this.sidePane.getSelectedToggleAsView());
                    this.viewsManager.showView(this.sidePane.getSelectedToggleAsView(), this, null, null, null);
                    this.statusPane.setDatabaseConnected(
                            DatabaseConnectivityChecker.getInstance().getDatabaseAvailable());
                    this.setTitleText(this.sidePane.getSelectedToggleAsView().toString());
                });
        this.sidePane.getLogOutButton().addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            this.close();
            IndividualViewWindow loginView = new IndividualViewWindow(Views.LOGIN);
            loginView.show();
        });
    }

    /**
     * A method to add components to the window (stage).
     */
    @Override
    protected void addComponentsToStage() {
        this.baseLayout.setLeft(this.sidePane);
        this.baseLayout.setBottom(this.statusPane);
        this.baseLayout.setCenter(this.viewsManager);
        this.viewsManager.setTop(this.headerPane);
        this.viewsManager.setCenter(new NoneSidePaneView(this));
    }
}
