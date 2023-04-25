package winchester.library.presentation.component.card;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import winchester.library.presentation.style.ComponentStyler;

public abstract class Card extends BorderPane {

    protected VBox actions;
    protected Label viewDetailsLabel;

    public Card() {
        this.setId("background-secondary-border");
        this.setPadding(new Insets(5));
        this.loadStylesheets();
        this.initialiseDefaultComponents();
    }

    private void loadStylesheets() {
        ComponentStyler.getInstance().setStyle(this);
    }

    private void initialiseDefaultComponents() {
        this.actions = new VBox();
        this.actions.setPadding(new Insets(10));
        this.viewDetailsLabel = new Label();
        this.viewDetailsLabel.setId("link-label");
        this.viewDetailsLabel.setText("View Details");
    }

    protected abstract void initialiseLayouts();

    protected abstract void initialiseControls();

    protected abstract void bindEventHandlers();

    protected abstract void addComponentsToCard();

}