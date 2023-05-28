package winchester.library.presentation.window;

import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.MouseEvent;
import winchester.library.data.model.users.Employee;
import winchester.library.presentation.alert.AlertFactory;
import winchester.library.presentation.component.pane.HeaderPane;
import winchester.library.presentation.component.pane.SidePane;
import winchester.library.presentation.component.pane.StatusPane;
import winchester.library.presentation.view.HomeView;
import winchester.library.presentation.view.Views;
import winchester.library.presentation.view.ViewsManager;
import winchester.library.service.DatabaseConnectivityChecker;

import java.util.Optional;

/**
 * A class that provides the implementation of the Main Window that the user will be interacting with.
 * This class has been marked as final to prevent extension.
 */
public final class MainWindow extends WindowBase {

    private HeaderPane headerPane;
    private SidePane sidePane;
    private StatusPane statusPane;
    private ViewsManager viewsManager;
    private final Employee currentEmployee;

    /**
     * A constructor for the MainWindow.
     * @param currentEmployee the current logged in employee.
     */
    public MainWindow(Employee currentEmployee) {
        super();
        this.setTitleText("Main Window");
        this.currentEmployee = currentEmployee;
        this.initialiseControls();
        this.bindEventHandlers();
        this.addComponentsToStage();
    }

    /**
     * A method to set the text of the header pane object.
     * @param text the text to be shown.
     */
    @Override
    public void setHeaderText(String text) {
        this.headerPane.setPageTitle(text);
    }

    /**
     * A method to initialise controls for the main window.
     */
    @Override
    protected void initialiseControls() {
        this.headerPane = new HeaderPane(Views.HOME);
        this.sidePane = new SidePane(this.currentEmployee);
        this.sidePane.setPrefWidth(150);
        Optional<ToggleButton> defaultSelectedButton = this.sidePane.getToggleButton(Views.HOME.toString());
        defaultSelectedButton.ifPresent(toggleButton -> toggleButton.setSelected(true));
        this.statusPane = new StatusPane();
        this.statusPane.setDatabaseConnected(DatabaseConnectivityChecker.getInstance().isDatabaseAvailable());
        this.viewsManager = new ViewsManager();
        this.viewsManager.setPadding(new Insets(20));
    }

    /**
     * A method to add and bind event handlers to controls.
     */
    private void bindEventHandlers() {
        this.sidePane.getToggleGroup().selectedToggleProperty().addListener(
                (value, toggle, newToggle) -> this.handleUpdate());
        this.sidePane.getLogOutButton().addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            Optional<ButtonType> result = AlertFactory.createAlert(
                    Alert.AlertType.CONFIRMATION, "Log Out?", "", ButtonType.NO, ButtonType.YES).showAndWait();
            if (result.isEmpty()) { return; }
            if (result.get() == ButtonType.NO) { return; }
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
        this.viewsManager.setCenter(new HomeView(this, this.currentEmployee));
    }

    /**
     * A method to update the component content shown within the window.
     */
    private void handleUpdate() {
        this.headerPane.setPageTitle(this.sidePane.getSelectedToggleAsView());
        this.viewsManager.showView(this.sidePane.getSelectedToggleAsView(), this, this.currentEmployee, null, null);
        this.statusPane.setDatabaseConnected(
                DatabaseConnectivityChecker.getInstance().isDatabaseAvailable());
        this.setTitleText(this.sidePane.getSelectedToggleAsView().toString());
    }
}
