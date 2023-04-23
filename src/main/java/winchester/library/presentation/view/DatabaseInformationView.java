package winchester.library.presentation.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import winchester.library.data.access.DatabaseCredentials;
import winchester.library.presentation.window.IndividualViewWindow;
import winchester.library.presentation.window.WindowBase;
import winchester.library.service.DatabaseInteraction;

public class DatabaseInformationView extends View {

    private final WindowBase parentWindow;
    private Label databaseUrlLabel;
    private Label databaseStatusLabel;
    private HBox buttonLayout;
    private Button configurationButton;
    private Button closeButton;

    public DatabaseInformationView(WindowBase parentWindow) {
        super();
        this.parentWindow = parentWindow;
        this.parentWindow.setTitleText("Database Information");
        this.parentWindow.setHeight(200);
        this.parentWindow.setWidth(300);
        this.initialiseLayouts();
        this.initialiseControls();
        this.bindEventHandlers();
        this.addComponentsToView();
    }

    @Override
    protected void initialiseLayouts() {
        this.buttonLayout = new HBox();
        this.buttonLayout.setAlignment(Pos.CENTER_RIGHT);
        this.buttonLayout.setPadding(new Insets(15, 0, 0, 0));
    }

    @Override
    protected void initialiseControls() {
        this.databaseUrlLabel = new Label();
        this.databaseUrlLabel.setText("Url: " + DatabaseCredentials.getInstance().getUrl());
        this.databaseStatusLabel = new Label();
        this.databaseStatusLabel.setText("Status: " + DatabaseInteraction.getInstance().getDatabaseStatus().toString());
        this.configurationButton = new Button();
        this.configurationButton.setText("Configure Database");
        this.closeButton = new Button();
        this.closeButton.setText("Close");
        HBox.setMargin(this.configurationButton, new Insets(0, 0, 0, 10));
    }

    private void bindEventHandlers() {
        this.configurationButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            IndividualViewWindow databaseConfigurationView = new IndividualViewWindow(Views.DATABASE_CONFIGURATION);
            databaseConfigurationView.show();
        });
        this.closeButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> this.parentWindow.close());
    }

    @Override
    protected void addComponentsToView() {
        this.buttonLayout.getChildren().addAll(this.closeButton, this.configurationButton);
        this.getChildren().addAll(this.databaseUrlLabel, this.databaseStatusLabel, this.buttonLayout);
    }

}
