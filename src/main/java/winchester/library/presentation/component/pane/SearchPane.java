package winchester.library.presentation.component.pane;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public final class SearchPane extends HBox {

    private TextField searchField;
    private Button searchButton;

    public SearchPane() {
        super();
        this.initialiseLayouts();
        this.initialiseControls();
        this.addComponentsToPane();
    }

    public Button getSearchButton() {
        return this.searchButton;
    }

    public String getSearchText() {
        return this.searchField.getText();
    }

    private void initialiseLayouts() {
        this.setSpacing(10);
        this.setPadding(new Insets(1));
        this.setAlignment(Pos.CENTER_RIGHT);
        this.getStyleClass().add("background-secondary");
    }

    private void initialiseControls() {
        this.searchButton = new Button();
        this.searchButton.setText("Search");
        this.searchField = new TextField();
    }

    private void addComponentsToPane() {
        this.getChildren().addAll(this.searchField, this.searchButton);
    }

}
