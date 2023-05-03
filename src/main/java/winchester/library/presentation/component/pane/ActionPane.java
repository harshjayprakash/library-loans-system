package winchester.library.presentation.component.pane;

import javafx.geometry.Insets;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

/**
 * A class that provides an abstraction for the styling of the actions panel.
 */
public class ActionPane extends BorderPane {

    HBox leftControls;
    HBox rightControls;

    public ActionPane() {
        super();
        this.initialiseLayouts();
        this.addComponentsToPane();
    }

    public HBox getLeftControls() {
        return this.leftControls;
    }

    public HBox getRightControls() {
        return this.rightControls;
    }

    private void initialiseLayouts() {
        this.setPadding(new Insets(10));
        this.getStyleClass().addAll("background-secondary-border");
        this.leftControls = new HBox();
        this.leftControls.setSpacing(10);
        this.rightControls = new HBox();
        this.rightControls.setSpacing(10);
    }

    private void addComponentsToPane() {
        this.setLeft(this.leftControls);
        this.setRight(this.rightControls);
    }

}
