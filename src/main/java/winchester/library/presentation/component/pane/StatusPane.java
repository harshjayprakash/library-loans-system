package winchester.library.presentation.component.pane;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.TextAlignment;
import winchester.library.data.access.DatabaseStatus;
import winchester.library.presentation.style.ComponentStyler;
import winchester.library.presentation.view.Views;
import winchester.library.presentation.window.IndividualViewWindow;

/**
 * A class that provides the status bar at the bottom of the Main Window.
 */
public final class StatusPane extends BorderPane {

    private Label settingsLabel;
    private Label databaseStatusLabel;

    /**
     * The default constructor for the StatusPane component.
     */
    public StatusPane() {
        super();
        this.initialiseLayout();
        this.initialiseControls();
        this.loadStylesheets();
        this.bindEventHandlers();
        this.addComponentsToPane();
    }

    /**
     * A mutator to set the database status.
     * @param connected whether the program is connected to the database.
     */
    public void setDatabaseConnected(boolean connected) {
        this.databaseStatusLabel.setText(
                connected ? DatabaseStatus.CONNECTED.toString() : DatabaseStatus.NOT_CONNECTED.toString());
    }

    /**
     * A method to initialise the layout of the component.
     */
    private void initialiseLayout() {
        this.setPadding(new Insets(5));
        this.getStyleClass().add("background-secondary");
    }

    /**
     * A method to initialise controls of the component.
     */
    private void initialiseControls() {
        this.settingsLabel = new Label();
        this.settingsLabel.getStyleClass().add("link-label");
        this.settingsLabel.setPadding(new Insets(0, 0, 0, 15));
        this.settingsLabel.setText("Settings");
        this.databaseStatusLabel = new Label();
        this.databaseStatusLabel.setAlignment(Pos.CENTER_RIGHT);
        this.databaseStatusLabel.setPadding(new Insets(5));
        this.databaseStatusLabel.setText("Data Source Status");
        this.databaseStatusLabel.setTextAlignment(TextAlignment.RIGHT);
    }

    /**
     * A method to load the stylesheet to style the components.
     */
    private void loadStylesheets() {
        ComponentStyler.getInstance().setStyle(this);
    }

    /**
     * A method to add and bind event handlers to components.
     */
    private void bindEventHandlers() {
        this.settingsLabel.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> this.startSettingsWindow());
    }

    /**
     * A method to add the components to the status pane.
     */
    private void addComponentsToPane() {
        this.setLeft(this.settingsLabel);
        this.setRight(this.databaseStatusLabel);
    }

    /**
     * A method to start a window with the settings view.
     */
    private void startSettingsWindow() {
        IndividualViewWindow settingsView = new IndividualViewWindow(Views.SETTINGS);
        settingsView.show();
    }
}
