package winchester.library.presentation.component.pane;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import winchester.library.presentation.style.ComponentStyler;
import winchester.library.presentation.view.Views;

/**
 * A class that provides the header title text for a window.
 */
public final class HeaderPane extends VBox {

    private Views page;
    private Label pageLabel;

    public HeaderPane(Views page) {
        super();
        this.page = page;
        this.initialiseLayout();
        this.initialiseControl();
        this.addControlToPane();
        this.updateLabel();
    }

    public Views getPageTitle() {
        return this.page;
    }

    public void setPageTitle(Views page) {
        this.page = page;
        this.updateLabel();
    }

    private void initialiseLayout() {
        ComponentStyler.getInstance().setStyle(this);
        this.getStyleClass().add("background-primary");
        this.setPadding(new Insets(0, 0, 10, 0));
    }

    private void initialiseControl() {
        this.pageLabel = new Label();
        this.pageLabel.setText(this.page.toString());
        this.pageLabel.setId("header__page-label");
    }

    private void addControlToPane() {
        this.getChildren().add(this.pageLabel);
    }

    private void updateLabel() {
        this.pageLabel.setText(this.page.toString());
    }
}
