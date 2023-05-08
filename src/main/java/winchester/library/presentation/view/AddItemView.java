package winchester.library.presentation.view;

import java.util.ArrayList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import winchester.library.data.model.items.Book;
import winchester.library.data.model.items.Film;
import winchester.library.data.model.items.ItemFormat;
import winchester.library.data.model.items.ItemStock;
import winchester.library.presentation.alert.AlertFactory;
import winchester.library.presentation.window.WindowBase;
import winchester.library.service.DataPersistenceManager;
import winchester.library.service.IdentifierGenerator;
import winchester.library.service.Logger;

/**
 * A view that allows the adding of items to the data source.
 */
public final class AddItemView extends View {

    private Label descriptionLabel;
    private Label itemTitleLabel;
    private Label itemImageUrlLabel;
    private TextField itemTitleField;
    private TextField itemImageUrlField;
    private TabPane tabPaneLayout;
    private Tab bookTab;
    private GridPane bookTabLayout;
    private Label bookIsbnLabel;
    private Label bookAuthorLabel;
    private Label bookPublicationYearLabel;
    private Label bookPublisherLabel;
    private Label bookFormatAudioStockLabel;
    private Label bookFormatPhysicalStockLabel;
    private Label bookFormatLargePrintStockLabel;
    private Label bookFormatElectronicStockLabel;
    private TextField bookIsbnField;
    private TextField bookAuthorField;
    private TextField bookPublicationYearField;
    private TextField bookPublisherField;
    private TextField bookFormatAudioStockField;
    private TextField bookFormatPhysicalStockField;
    private TextField bookFormatLargePrintStockField;
    private TextField bookFormatElectronicStockField;
    private HBox bookButtonLayout;
    private Button createBookItemButton;
    private Tab filmTab;
    private GridPane filmTabLayout;
    private Label filmDirectorLabel;
    private Label filmReleaseYearLabel;
    private Label filmDistributorLabel;
    private Label filmDurationMinutesLabel;
    private Label filmFormatDvdStockLabel;
    private Label filmFormatBluRayStockLabel;
    private TextField filmDirectorField;
    private TextField filmReleaseYearField;
    private TextField filmDistributorField;
    private TextField filmDurationMinutesField;
    private TextField filmFormatDvdStockField;
    private TextField filmFormatBluRayStockField;
    private HBox filmButtonLayout;
    private Button createFilmItemButton;

    /**
     * The default constructor that takes in the parent window.
     * @param parentWindow the parent window the view can have access to.
     */
    public AddItemView(WindowBase parentWindow) {
        super(parentWindow, Views.ADD_ITEM.toString());
        this.parentWindow.setHeight(550);
        this.parentWindow.setWidth(700);
        this.initialiseLayouts();
        this.initialiseControls();
        this.initialiseConstraints();
        this.bindEventsHandlers();
        this.addComponentsToView();
    }

    /**
     * A method to initialise layouts used within this view.
     */
    @Override
    protected void initialiseLayouts() {
        this.tabPaneLayout = new TabPane();
        this.tabPaneLayout.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        this.tabPaneLayout.getStyleClass().add("background-primary");
        this.tabPaneLayout.setPadding(new Insets(10, 0, 0, 0));
        this.bookTab = new Tab();
        this.bookTab.setText("Book");
        this.bookTabLayout = new GridPane();
        this.bookButtonLayout = new HBox();
        this.bookButtonLayout.setAlignment(Pos.CENTER_RIGHT);
        this.filmTab = new Tab();
        this.filmTab.setText("Film");
        this.filmTabLayout = new GridPane();
        this.filmButtonLayout = new HBox();
        this.filmButtonLayout.setAlignment(Pos.CENTER_RIGHT);
    }

    /**
     * A method to initialise controls used within the view.
     */
    @Override
    protected void initialiseControls() {
        this.descriptionLabel = new Label();
        this.descriptionLabel.setText("Please fill in the information of the new item, choosing the type.");
        this.itemTitleLabel = new Label();
        this.itemTitleLabel.setText("Item Title:");
        this.itemTitleField = new TextField();
        this.itemImageUrlLabel = new Label();
        this.itemImageUrlLabel.setText("Item Image Url:");
        this.itemImageUrlField = new TextField();
        this.bookIsbnLabel = new Label();
        this.bookIsbnLabel.setText("Book ISBN:");
        this.bookIsbnField = new TextField();
        this.bookAuthorLabel = new Label();
        this.bookAuthorLabel.setText("Book Author:");
        this.bookAuthorField = new TextField();
        this.bookPublicationYearLabel = new Label();
        this.bookPublicationYearLabel.setText("Book Publication Year:");
        this.bookPublicationYearField = new TextField();
        this.bookPublisherLabel = new Label();
        this.bookPublisherLabel.setText("Book Publisher:");
        this.bookPublisherField = new TextField();
        this.bookFormatAudioStockLabel = new Label();
        this.bookFormatAudioStockLabel.setText("Available Stock of Audio Books:");
        this.bookFormatAudioStockField = new TextField();
        this.bookFormatPhysicalStockLabel = new Label();
        this.bookFormatPhysicalStockLabel.setText("Available Stock of Physical Books:");
        this.bookFormatPhysicalStockField = new TextField();
        this.bookFormatLargePrintStockLabel = new Label();
        this.bookFormatLargePrintStockLabel.setText("Available Stock of Large Print Books:");
        this.bookFormatLargePrintStockField = new TextField();
        this.bookFormatElectronicStockLabel = new Label();
        this.bookFormatElectronicStockLabel.setText("Available Stock of Electronic Books:");
        this.bookFormatElectronicStockField = new TextField();
        this.createBookItemButton = new Button();
        this.createBookItemButton.getStyleClass().add("button-accent");
        this.createBookItemButton.setText("Create Book");
        this.filmDirectorLabel = new Label();
        this.filmDirectorLabel.setText("Film Director:");
        this.filmDirectorField = new TextField();
        this.filmReleaseYearLabel = new Label();
        this.filmReleaseYearLabel.setText("Film Release Year:");
        this.filmReleaseYearField = new TextField();
        this.filmDistributorLabel = new Label();
        this.filmDistributorLabel.setText("Film Distributor:");
        this.filmDistributorField = new TextField();
        this.filmDirectorLabel = new Label();
        this.filmDirectorLabel.setText("Film Director:");
        this.filmDirectorField = new TextField();
        this.filmDurationMinutesLabel = new Label();
        this.filmDurationMinutesLabel.setText("Film Duration (in Minutes):");
        this.filmDurationMinutesField = new TextField();
        this.filmFormatDvdStockLabel = new Label();
        this.filmFormatDvdStockLabel.setText("Available Stock of DVDs:");
        this.filmFormatDvdStockField = new TextField();
        this.filmFormatBluRayStockLabel = new Label();
        this.filmFormatBluRayStockLabel.setText("Available Stock of Blu-Rays:");
        this.filmFormatBluRayStockField = new TextField();
        this.createFilmItemButton = new Button();
        this.createFilmItemButton.getStyleClass().add("button-accent");
        this.createFilmItemButton.setText("Create Film");
    }

    /**
     * A method to initialise the grid layout constraints.
     */
    private void initialiseConstraints() {
        GridPane.setConstraints(this.bookIsbnLabel, 1, 1, 1, 1,
                HPos.LEFT, VPos.CENTER, Priority.SOMETIMES, Priority.SOMETIMES, new Insets(3));
        GridPane.setConstraints(this.bookIsbnField, 1, 2, 1, 1,
                HPos.LEFT, VPos.CENTER, Priority.SOMETIMES, Priority.SOMETIMES, new Insets(3));
        GridPane.setConstraints(this.bookAuthorLabel, 1, 3, 1, 1,
                HPos.LEFT, VPos.CENTER, Priority.SOMETIMES, Priority.SOMETIMES, new Insets(3));
        GridPane.setConstraints(this.bookAuthorField, 1, 4, 1, 1,
                HPos.LEFT, VPos.CENTER, Priority.SOMETIMES, Priority.SOMETIMES, new Insets(3));
        GridPane.setConstraints(this.bookPublicationYearLabel, 1, 5, 1, 1,
                HPos.LEFT, VPos.CENTER, Priority.SOMETIMES, Priority.SOMETIMES, new Insets(3));
        GridPane.setConstraints(this.bookPublicationYearField, 1, 6, 1, 1,
                HPos.LEFT, VPos.CENTER, Priority.SOMETIMES, Priority.SOMETIMES, new Insets(3));
        GridPane.setConstraints(this.bookPublisherLabel, 1, 7, 1, 1,
                HPos.LEFT, VPos.CENTER, Priority.SOMETIMES, Priority.SOMETIMES, new Insets(3));
        GridPane.setConstraints(this.bookPublisherField, 1, 8, 1, 1,
                HPos.LEFT, VPos.CENTER, Priority.SOMETIMES, Priority.SOMETIMES, new Insets(3));
        GridPane.setConstraints(this.bookFormatAudioStockLabel, 2, 1, 1, 1);
        GridPane.setConstraints(this.bookFormatAudioStockField, 2, 2, 1, 1);
        GridPane.setConstraints(this.bookFormatPhysicalStockLabel, 2, 3, 1, 1);
        GridPane.setConstraints(this.bookFormatPhysicalStockField, 2, 4, 1, 1);
        GridPane.setConstraints(this.bookFormatLargePrintStockLabel, 2, 5, 1, 1);
        GridPane.setConstraints(this.bookFormatLargePrintStockField, 2, 6, 1, 1);
        GridPane.setConstraints(this.bookFormatElectronicStockLabel, 2, 7, 1, 1);
        GridPane.setConstraints(this.bookFormatElectronicStockField, 2, 8, 1, 1);
        GridPane.setConstraints(this.bookButtonLayout, 1, 9, 2, 1);
        GridPane.setConstraints(this.filmDirectorLabel, 1, 1, 1, 1,
                HPos.LEFT, VPos.CENTER, Priority.SOMETIMES, Priority.SOMETIMES, new Insets(3));
        GridPane.setConstraints(this.filmDirectorField, 1, 2, 1, 1,
                HPos.LEFT, VPos.CENTER, Priority.SOMETIMES, Priority.SOMETIMES, new Insets(3));
        GridPane.setConstraints(this.filmReleaseYearLabel, 1, 3, 1, 1,
                HPos.LEFT, VPos.CENTER, Priority.SOMETIMES, Priority.SOMETIMES, new Insets(3));
        GridPane.setConstraints(this.filmReleaseYearField, 1, 4, 1, 1,
                HPos.LEFT, VPos.CENTER, Priority.SOMETIMES, Priority.SOMETIMES, new Insets(3));
        GridPane.setConstraints(this.filmDistributorLabel, 1, 5, 1, 1,
                HPos.LEFT, VPos.CENTER, Priority.SOMETIMES, Priority.SOMETIMES, new Insets(3));
        GridPane.setConstraints(this.filmDistributorField, 1, 6, 1, 1,
                HPos.LEFT, VPos.CENTER, Priority.SOMETIMES, Priority.SOMETIMES, new Insets(3));
        GridPane.setConstraints(this.filmDirectorLabel, 1, 7, 1, 1,
                HPos.LEFT, VPos.CENTER, Priority.SOMETIMES, Priority.SOMETIMES, new Insets(3));
        GridPane.setConstraints(this.filmDirectorField, 1, 8, 1, 1,
                HPos.LEFT, VPos.CENTER, Priority.SOMETIMES, Priority.SOMETIMES, new Insets(3));
        GridPane.setConstraints(this.filmDurationMinutesLabel, 1, 9, 1, 1,
                HPos.LEFT, VPos.CENTER, Priority.SOMETIMES, Priority.SOMETIMES, new Insets(3));
        GridPane.setConstraints(this.filmDurationMinutesField, 1, 10, 1, 1,
                HPos.LEFT, VPos.CENTER, Priority.SOMETIMES, Priority.SOMETIMES, new Insets(3));
        GridPane.setConstraints(this.filmFormatDvdStockLabel, 2, 3, 1, 1);
        GridPane.setConstraints(this.filmFormatDvdStockField, 2, 4, 1, 1);
        GridPane.setConstraints(this.filmFormatBluRayStockLabel, 2, 5, 1, 1);
        GridPane.setConstraints(this.filmFormatBluRayStockField, 2, 6, 1, 1);
        GridPane.setConstraints(this.filmButtonLayout, 1, 11, 2, 1);
    }

    /**
     * A method to bind and add event handlers to components.
     */
    private void bindEventsHandlers() {
        this.createBookItemButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> this.createBook());
        this.createFilmItemButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> this.createFilm());
    }

    /**
     * A method to add components to the view.
     */
    @Override
    protected void addComponentsToView() {
        this.bookButtonLayout.getChildren().add(this.createBookItemButton);
        this.filmButtonLayout.getChildren().add(this.createFilmItemButton);
        this.filmTabLayout.getChildren().addAll(this.filmDirectorLabel, this.filmDirectorField,
                this.filmReleaseYearLabel, this.filmReleaseYearField, this.filmDistributorLabel,
                this.filmDistributorField, this.filmDurationMinutesLabel, this.filmDurationMinutesField,
                this.filmFormatDvdStockLabel, this.filmFormatDvdStockField, this.filmFormatBluRayStockLabel,
                this.filmFormatBluRayStockField, this.filmButtonLayout);
        this.filmTab.setContent(this.filmTabLayout);
        this.bookTabLayout.getChildren().addAll(this.bookIsbnLabel, this.bookIsbnField, this.bookAuthorLabel,
                this.bookAuthorField, this.bookPublicationYearLabel, this.bookPublicationYearField,
                this.bookPublisherLabel, this.bookPublisherField, this.bookFormatAudioStockLabel,
                this.bookFormatAudioStockField, this.bookFormatPhysicalStockLabel,
                this.bookFormatPhysicalStockField, this.bookFormatLargePrintStockLabel,
                this.bookFormatLargePrintStockField, this.bookFormatElectronicStockLabel,
                this.bookFormatElectronicStockField, this.bookButtonLayout);
        this.bookTab.setContent(this.bookTabLayout);
        this.tabPaneLayout.getTabs().addAll(this.bookTab, this.filmTab);
        this.getChildren().addAll(this.descriptionLabel, this.itemTitleLabel, this.itemTitleField,
                this.itemImageUrlLabel, this.itemImageUrlField, this.tabPaneLayout);
    }

    /**
     * A method to check if any fields to create the book are blank.
     * @return true if there is a blank field.
     */
    private boolean isFieldsToCreateBookBlank() {
        return this.itemTitleField.getText().isBlank()
                || this.itemImageUrlField.getText().isBlank()
                || this.bookIsbnField.getText().isBlank()
                || this.bookAuthorField.getText().isBlank()
                || this.bookPublicationYearField.getText().isBlank()
                || this.bookPublisherField.getText().isBlank()
                || this.bookFormatAudioStockField.getText().isBlank()
                || this.bookFormatPhysicalStockField.getText().isBlank()
                || this.bookFormatLargePrintStockField.getText().isBlank()
                || this.bookFormatElectronicStockField.getText().isBlank();
    }

    /**
     * A method to check if any fields to create the film are blank.
     * @return true if there is a blank field.
     */
    private boolean isFieldsToCreateFilmBlank() {
        return this.itemTitleField.getText().isBlank()
                || this.itemImageUrlField.getText().isBlank()
                || this.filmDirectorField.getText().isBlank()
                || this.filmReleaseYearField.getText().isBlank()
                || this.filmDistributorField.getText().isBlank()
                || this.filmDurationMinutesField.getText().isBlank()
                || this.filmFormatDvdStockField.getText().isBlank()
                || this.filmFormatBluRayStockField.getText().isBlank();
    }

    /**
     * A method to create a book and add it to the data source.
     */
    private void createBook() {
        if (this.isFieldsToCreateBookBlank()) {
            AlertFactory.createAlert(Alert.AlertType.ERROR, "Please ensure that all fields are filled.").show();
            return;
        }
        Book book;
        try {
            ArrayList<ItemStock> stocks = new ArrayList<>();
            book = new Book(this.bookIsbnField.getText(), this.itemTitleField.getText(),
                    this.bookAuthorField.getText(), Integer.parseInt(this.bookPublicationYearField.getText()),
                    this.bookPublisherField.getText(), this.itemImageUrlField.getText());
            stocks.add(new ItemStock(book.getIsbn(), ItemFormat.AUDIO_BOOK,
                    Integer.parseInt(this.bookFormatAudioStockField.getText()), 0));
            stocks.add(new ItemStock(book.getIsbn(), ItemFormat.PHYSICAL_BOOK,
                    Integer.parseInt(this.bookFormatPhysicalStockField.getText()), 0));
            stocks.add(new ItemStock(book.getIsbn(), ItemFormat.LARGE_PRINT_BOOK,
                    Integer.parseInt(this.bookFormatLargePrintStockField.getText()), 0));
            stocks.add(new ItemStock(book.getIsbn(), ItemFormat.ELECTRONIC_BOOK,
                    Integer.parseInt(this.bookFormatElectronicStockField.getText()), 0));
            book.getStockAvailable().getItemStock().addAll(stocks);
        }
        catch (NumberFormatException exception) {
            Logger.getInstance().PrintError(
                    this.getClass().getName(),
                    "Parsing text as an integer",
                    "Failed to parse string as number",
                    "Please ensure that a valid number is entered");
            return;
        }
        boolean success = DataPersistenceManager.getInstance().createBook(book);
        if (!success) {
            AlertFactory.createAlert(Alert.AlertType.ERROR, "Failed to create book.").show();
            return;
        }
        AlertFactory.createAlert(Alert.AlertType.INFORMATION, "Book successfully created").show();
    }

    /**
     * A method to create a film and add it to the data source.
     */
    private void createFilm() {
        if (this.isFieldsToCreateFilmBlank()) {
            AlertFactory.createAlert(Alert.AlertType.ERROR, "Please ensure that all fields are filled.").show();
            return;
        }
        Film film;
        try {
            ArrayList<ItemStock> stocks = new ArrayList<>();
            film = new Film(new IdentifierGenerator().generateForFilm(),
                    this.itemTitleField.getText(), this.filmDirectorField.getText(),
                    Integer.parseInt(this.filmReleaseYearField.getText()),
                    this.filmDistributorField.getText(),
                    Integer.parseInt(this.filmDurationMinutesField.getText()),
                    this.itemImageUrlField.getText());
            stocks.add(new ItemStock(film.getIdentifier(), ItemFormat.DVD_FILM,
                    Integer.parseInt(this.filmFormatDvdStockField.getText()), 0));
            stocks.add(new ItemStock(film.getIdentifier(), ItemFormat.BLU_RAY_FILM,
                    Integer.parseInt(this.filmFormatBluRayStockField.getText()), 0));
            film.getStockAvailable().getItemStock().addAll(stocks);
        }
        catch (NumberFormatException exception) {
            Logger.getInstance().PrintError(
                    this.getClass().getName(),
                    "Parsing text as an integer",
                    "Failed to parse string as number",
                    "Please ensure that a valid number is entered");
            return;
        }
        boolean success = DataPersistenceManager.getInstance().createFilm(film);
        if (!success) {
            AlertFactory.createAlert(Alert.AlertType.ERROR, "Failed to create film.").show();
            return;
        }
        AlertFactory.createAlert(Alert.AlertType.INFORMATION, "Film successfully created").show();
    }
}
