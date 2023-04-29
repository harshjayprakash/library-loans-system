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

    public StatusPane() {
        super();
        this.setPadding(new Insets(5));
        this.getStyleClass().add("background-secondary");
        this.initialiseControls();
        this.loadStylesheets();
        this.bindEventHandlers();
        this.addComponentsToPane();
    }

    public void setDatabaseConnected(boolean connected) {
        this.databaseStatusLabel.setText(
                connected ? DatabaseStatus.CONNECTED.toString() : DatabaseStatus.NOT_CONNECTED.toString());
    }

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

    private void loadStylesheets() {
        ComponentStyler.getInstance().setStyle(this);
    }

    private void bindEventHandlers() {
        this.settingsLabel.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            IndividualViewWindow settingsView = new IndividualViewWindow(Views.SETTINGS);
            settingsView.show();
        });
    }

    private void addComponentsToPane() {
        this.setLeft(this.settingsLabel);
        this.setRight(this.databaseStatusLabel);
    }
}
