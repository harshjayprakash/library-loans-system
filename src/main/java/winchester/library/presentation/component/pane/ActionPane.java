package winchester.library.presentation.component.pane;

import javafx.geometry.Insets;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

/**
 * A class that provides an abstraction for the styling and handling controls in the left and right in the BorderPane
 * layout.
 */
public class ActionPane extends BorderPane {

    HBox leftControls;
    HBox rightControls;

    /**
     * The default constructor to create the ActionPane.
     */
    public ActionPane() {
        super();
        this.initialiseLayouts();
        this.addLayoutsToPane();
    }

    /**
     * An accessor to retrieve the horizontal box layout on the left side of the pane.
     * @return a HBox layout.
     */
    public HBox getLeftControls() {
        return this.leftControls;
    }

    /**
     * An accessor to retrieve the horizontal box layout of the right side of the pane.
     * @return a HBox layout.
     */
    public HBox getRightControls() {
        return this.rightControls;
    }

    /**
     * A method to initialise layouts used within the pane.
     */
    private void initialiseLayouts() {
        this.setPadding(new Insets(10));
        this.getStyleClass().addAll("background-secondary-border");
        this.leftControls = new HBox();
        this.leftControls.setSpacing(10);
        this.rightControls = new HBox();
        this.rightControls.setSpacing(10);
    }

    /**
     * A method to add the layouts to the pane.
     */
    private void addLayoutsToPane() {
        this.setLeft(this.leftControls);
        this.setRight(this.rightControls);
    }

}
