package winchester.library.presentation.view;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import winchester.library.data.access.DatabaseCredentials;
import winchester.library.meta.Metadata;
import winchester.library.presentation.window.IndividualViewWindow;
import winchester.library.presentation.window.WindowBase;
import winchester.library.service.DatabaseConnectivityChecker;

/**
 * A view to view program information and change the program's configuration.
 */
public final class SettingsView extends View {

    private VBox dataSourceSettingsPane;
    private Label dataSourceTitleLabel;
    private Label dataSourceUrlLabel;
    private Label dataSourceStatusLabel;
    private Label dataSourceEditCredentialsLinkLabel;
    private Label descriptionLabel;
    private VBox fileExporterSettingsPane;
    private Label fileExporterTitleLabel;
    private Label fileExporterOutputPath;
    private VBox programMetadata;
    private Label programMetadataTitleLabel;
    private Label programNameLabel;
    private Label programVersionLabel;
    private final DatabaseCredentials credentials;

    /**
     * The default constructor that passes the parent window.
     * @param parentWindow the parent window that the view can access.
     */
    public SettingsView(WindowBase parentWindow) {
        super(parentWindow, Views.SETTINGS.toString());
        this.credentials = DatabaseCredentials.getInstance();
        this.setSpacing(20);
        this.initialiseLayouts();
        this.initialiseControls();
        this.bindEventHandlers();
        this.addComponentsToView();
    }

    /**
     * A method to initialise any layouts used within the view.
     */
    private void initialiseLayouts() {
        this.dataSourceSettingsPane = new VBox();
        this.dataSourceSettingsPane.getStyleClass().add("background-secondary-border");
        this.dataSourceSettingsPane.setPadding(new Insets(15));
        this.fileExporterSettingsPane = new VBox();
        this.fileExporterSettingsPane.getStyleClass().add("background-secondary-border");
        this.fileExporterSettingsPane.setPadding(new Insets(15));
        this.programMetadata = new VBox();
        this.programMetadata.getStyleClass().add("background-secondary-border");
        this.programMetadata.setPadding(new Insets(15));
    }

    /**
     * A method to initialise any controls used within the view.
     */
    private void initialiseControls() {
        this.descriptionLabel = new Label();
        this.descriptionLabel.setText("From here you can edit the program's configuration.");
        this.dataSourceTitleLabel = new Label();
        this.dataSourceTitleLabel.getStyleClass().add("text-bold");
        this.dataSourceTitleLabel.setText("Data Source");
        this.dataSourceUrlLabel = new Label();
        this.dataSourceUrlLabel.setText("Url: " + credentials.getUrl());
        this.dataSourceStatusLabel = new Label();
        this.dataSourceStatusLabel.setText(
                "Status: " + DatabaseConnectivityChecker.getInstance().getDatabaseStatus().toString());
        this.dataSourceEditCredentialsLinkLabel = new Label();
        this.dataSourceEditCredentialsLinkLabel.getStyleClass().add("link-label");
        this.dataSourceEditCredentialsLinkLabel.setText("Edit Data Source Credentials");
        this.fileExporterTitleLabel = new Label();
        this.fileExporterTitleLabel.getStyleClass().add("text-bold");
        this.fileExporterTitleLabel.setText("Exporter");
        this.fileExporterOutputPath = new Label();
        this.fileExporterOutputPath.setText("./exports/");
        this.programMetadataTitleLabel = new Label();
        this.programMetadataTitleLabel.getStyleClass().add("text-bold");
        this.programMetadataTitleLabel.setText("Program Information");
        this.programNameLabel = new Label();
        this.programNameLabel.setText(Metadata.getInstance().getProgramName());
        this.programVersionLabel = new Label();
        this.programVersionLabel.setText(Metadata.getInstance().getProgramVersionNumber());
    }

    /**
     * A method to bind and add event handlers to components.
     */
    private void bindEventHandlers() {
        this.dataSourceEditCredentialsLinkLabel.addEventHandler(MouseEvent.MOUSE_CLICKED, event ->
                this.startDatabaseConfigurationWindow());
    }

    /**
     * A method to add components to the view.
     */
    private void addComponentsToView() {
        this.dataSourceSettingsPane.getChildren().addAll(this.dataSourceTitleLabel, this.dataSourceUrlLabel,
                this.dataSourceStatusLabel, this.dataSourceEditCredentialsLinkLabel);
        this.fileExporterSettingsPane.getChildren().addAll(this.fileExporterTitleLabel, this.fileExporterOutputPath);
        this.programMetadata.getChildren().addAll(this.programMetadataTitleLabel, this.programNameLabel,
                this.programVersionLabel);
        this.getChildren().addAll(this.descriptionLabel, this.dataSourceSettingsPane, this.fileExporterSettingsPane,
                this.programMetadata);
    }

    /**
     * A method to start a window with the database configuration view.
     */
    private void startDatabaseConfigurationWindow() {
        IndividualViewWindow databaseConfigurationView = new IndividualViewWindow(Views.DATABASE_CONFIGURATION);
        databaseConfigurationView.show();
    }

}
