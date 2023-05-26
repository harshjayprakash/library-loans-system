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

    /**
     * The default constructor the HeaderPane setting the title based on the view displayed.
     * @param page the page's view enumeration reference.
     */
    public HeaderPane(Views page) {
        super();
        this.page = page;
        this.initialiseLayout();
        this.initialiseControl();
        this.addControlToPane();
        this.updateLabel();
    }

    /**
     * An accessor to retrieve the page title.
     * @return the page title as the Views enumeration.
     */
    public Views getPageTitle() {
        return this.page;
    }

    /**
     * A mutator to set the page title and update the label shown.
     * @param page the page title as a Views enumeration.
     */
    public void setPageTitle(Views page) {
        this.page = page;
        this.updateLabel();
    }

    /**
     * A mutator to set the page title and update the label shown.
     * @param text the text to be shown.
     */
    public void setPageTitle(String text) {
        this.pageLabel.setText(text);
    }

    /**
     * A method to initialise the layout within the component.
     */
    private void initialiseLayout() {
        ComponentStyler.getInstance().setStyle(this);
        this.getStyleClass().add("background-primary");
        this.setPadding(new Insets(0, 0, 10, 0));
    }

    /**
     * A method to initialise the control within the component.
     */
    private void initialiseControl() {
        this.pageLabel = new Label();
        this.pageLabel.setText(this.page.toString());
        this.pageLabel.setId("header__page-label");
    }

    /**
     * A method to add the control to the header pane.
     */
    private void addControlToPane() {
        this.getChildren().add(this.pageLabel);
    }

    /**
     * A private method to update the label's text.
     */
    private void updateLabel() {
        this.pageLabel.setText(this.page.toString());
    }
}
