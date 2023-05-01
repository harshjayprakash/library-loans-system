package winchester.library.presentation.component;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class Search extends HBox {

    private TextField searchField;
    private Button searchButton;

    public Search() {
        this.initialiseLayout();
        this.initialiseControls();
        this.addComponents();
    }

    public Button getSearchButton() {
        return this.searchButton;
    }

    private void initialiseLayout() {
        this.setAlignment(Pos.CENTER_RIGHT);
        this.setPadding(new Insets(2));
    }

    private void initialiseControls() {
        this.searchField = new TextField();
        this.searchButton = new Button();
        this.searchButton.setText("Search");
    }

    private void addComponents() {
        this.getChildren().addAll(this.searchButton, this.searchField);
    }

}
