package winchester.library.presentation.component.pane;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

/**
 * A class that provides a control for the search interface.
 */
public final class SearchPane extends HBox {

    private TextField searchField;
    private Button searchButton;

    /**
     * The default constructor for the SearchPane component.
     */
    public SearchPane() {
        super();
        this.initialiseLayout();
        this.initialiseControls();
        this.bindEventHandlers();
        this.addComponentsToPane();
    }

    /**
     * An accessor to retrieve the search button.
     * This can be used for binding events to the button.
     * @return the search button.
     */
    public Button getSearchButton() {
        return this.searchButton;
    }

    /**
     * An accessor to retrieve the text entered within the search field.
     * @return the search field text.
     */
    public String getSearchText() {
        return this.searchField.getText();
    }

    /**
     * A method to initialise the layout within the component.
     */
    private void initialiseLayout() {
        this.setSpacing(10);
        this.setPadding(new Insets(1));
        this.setAlignment(Pos.CENTER_RIGHT);
        this.getStyleClass().add("background-secondary");
    }

    /**
     * A method to initialise the controls used within the component.
     */
    private void initialiseControls() {
        this.searchButton = new Button();
        this.searchButton.setText("Search");
        this.searchField = new TextField();
    }

    /**
     * A method to add and bind event handlers.
     */
    private void bindEventHandlers() {
        this.searchField.textProperty().addListener((observable, oldValue, newValue) ->
            this.searchButton.setText((newValue.isBlank()) ? "Clear Search" : "Search"));
        this.searchButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> this.searchButton.setText("Search"));
    }

    /**
     * A method to add the component to the search pane.
     */
    private void addComponentsToPane() {
        this.getChildren().addAll(this.searchField, this.searchButton);
    }

}
