package winchester.library.presentation.component;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import winchester.library.presentation.style.StylesheetSetter;
import winchester.library.presentation.view.Views;

public class HeaderPane extends VBox {
    private Views page;
    private Views parentPage;
    private Label pageLabel;
    private Label parentPageLabel;

    public HeaderPane(Views page, Views parentPage) {
        super();
        this.page = page;
        this.parentPage = parentPage;
        this.setId("background-primary");
        StylesheetSetter.getInstance().setStyle(this);
        this.setPadding(new Insets(0, 0, 10, 0));
        this.initialiseControls();
        this.addControlsToPane();
        this.updateLabels();
    }

    public Views getPageTitle() {
        return this.page;
    }

    public void setPageTitle(Views page) {
        this.page = page;
        this.updateLabels();
    }

    public Views getParentPageTitle() {
        return this.parentPage;
    }

    public void setParentPageLabel(Views parentPage) {
        this.parentPage = parentPage;
        this.updateLabels();
    }

    private void initialiseControls() {
        this.pageLabel = new Label();
        this.pageLabel.setText(this.page.toString());
        this.pageLabel.setId("header__page-label");
        this.parentPageLabel = new Label();
        this.parentPageLabel.setText(this.parentPageLabel.toString());
    }

    private void addControlsToPane() {
        this.getChildren().add(this.pageLabel);
        if (parentPage.toString().equals(Views.NONE.toString())) { return; }
        this.getChildren().add(this.parentPageLabel);
    }

    private void updateLabels() {
        this.pageLabel.setText(this.page.toString());
        this.parentPageLabel.setText(this.parentPageLabel.toString());
    }
}
