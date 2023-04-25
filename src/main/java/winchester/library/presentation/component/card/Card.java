package winchester.library.presentation.component.card;

import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import winchester.library.presentation.style.ComponentStyler;

public abstract class Card extends BorderPane {

    protected VBox actionsLayout;
    protected Label viewDetailsLinkLabel;

    public Card() {
        this.setId("background-secondary-border");
        this.loadStylesheets();
        this.initialiseLayouts();
        this.initialiseControls();
        this.addComponentsToCard();
    }

    private void loadStylesheets() {
        ComponentStyler.getInstance().setStyle(this);
    }

    protected void initialiseLayouts() {
        this.actionsLayout = new VBox();
    }

    protected void initialiseControls() {
        this.viewDetailsLinkLabel = new Label();
        this.viewDetailsLinkLabel.setId("link-label");
        this.viewDetailsLinkLabel.setText("View Details");
    }

    protected abstract void bindEventHandlers();

    protected void addComponentsToCard() {
        this.actionsLayout.getChildren().add(this.viewDetailsLinkLabel);
        this.setRight(this.actionsLayout);
    }

}
