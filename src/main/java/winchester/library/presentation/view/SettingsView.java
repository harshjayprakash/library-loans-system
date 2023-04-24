package winchester.library.presentation.view;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import winchester.library.data.access.DatabaseCredentials;
import winchester.library.presentation.window.IndividualViewWindow;
import winchester.library.presentation.window.WindowBase;
import winchester.library.service.DatabaseInteraction;

public class SettingsView extends View {

    private VBox dataSourceSettingsPane;
    private Label dataSourceTitleLabel;
    private Label dataSourceUrlLabel;
    private Label dataSourceStatusLabel;
    private Label dataSourceEditCredentialsLinkLabel;
    private Label descriptionLabel;
    private VBox fileExporterSettingsPane;
    private Label fileExporterTitleLabel;
    private Label fileExporterOutputPath;
    private Label fileExporterEditOutputPath;
    private final DatabaseCredentials credentials;

    public SettingsView(WindowBase parentWindow) {
        super(parentWindow, Views.SETTINGS.toString());
        this.credentials = DatabaseCredentials.getInstance();
        this.setSpacing(20);
        this.initialiseLayouts();
        this.initialiseControls();
        this.bindEventHandlers();
        this.addComponentsToView();
    }

    @Override
    protected void initialiseLayouts() {
        this.dataSourceSettingsPane = new VBox();
        this.dataSourceSettingsPane.setId("background-secondary-border");
        this.dataSourceSettingsPane.setPadding(new Insets(15));
        this.fileExporterSettingsPane = new VBox();
        this.fileExporterSettingsPane.setId("background-secondary-border");
        this.fileExporterSettingsPane.setPadding(new Insets(15));
    }

    @Override
    protected void initialiseControls() {
        this.descriptionLabel = new Label();
        this.descriptionLabel.setText("From here you can edit the program's configuration.");
        this.dataSourceTitleLabel = new Label();
        this.dataSourceTitleLabel.setId("text-bold");
        this.dataSourceTitleLabel.setText("Data Source");
        this.dataSourceUrlLabel = new Label();
        this.dataSourceUrlLabel.setText("Url: " + credentials.getUrl());
        this.dataSourceStatusLabel = new Label();
        this.dataSourceStatusLabel.setText(
                "Status: " + DatabaseInteraction.getInstance().getDatabaseStatus().toString());
        this.dataSourceEditCredentialsLinkLabel = new Label();
        this.dataSourceEditCredentialsLinkLabel.setId("link-label");
        this.dataSourceEditCredentialsLinkLabel.setText("Edit Data Source Credentials");
        this.fileExporterTitleLabel = new Label();
        this.fileExporterTitleLabel.setId("text-bold");
        this.fileExporterTitleLabel.setText("Exporter");
        this.fileExporterOutputPath = new Label();
        this.fileExporterOutputPath.setText("C:/Users/harsh/Cloud/Code/uow-library-loans/exports/");
        this.fileExporterEditOutputPath = new Label();
        this.fileExporterEditOutputPath.setId("link-label");
        this.fileExporterEditOutputPath.setText("Edit Export Path");
    }

    private void bindEventHandlers() {
        this.dataSourceEditCredentialsLinkLabel.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            IndividualViewWindow databaseConfigurationView = new IndividualViewWindow(Views.DATABASE_CONFIGURATION);
            databaseConfigurationView.show();
        });
    }

    @Override
    protected void addComponentsToView() {
        this.dataSourceSettingsPane.getChildren().addAll(
                this.dataSourceTitleLabel, this.dataSourceUrlLabel, this.dataSourceStatusLabel,
                this.dataSourceEditCredentialsLinkLabel);
        this.fileExporterSettingsPane.getChildren().addAll(
                this.fileExporterTitleLabel, this.fileExporterOutputPath, this.fileExporterEditOutputPath);
        this.getChildren().addAll(this.descriptionLabel, this.dataSourceSettingsPane, this.fileExporterSettingsPane);
    }

}
