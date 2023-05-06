package winchester.library.presentation.component.pane;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 * A class to provide a banner component to display a message on a window.
 */
public final class BannerPane extends VBox {

    private final String message;
    private final String messageDetails;
    private Label messageLabel;
    private Label messageDetailsLabel;

    /**
     * The default constructor to set the message to be shown.
     * @param message the main short message.
     * @param messageDetails more details on the situation.
     */
    public BannerPane(String message, String messageDetails) {
        super();
        this.message = message;
        this.messageDetails = messageDetails;
        this.initialiseLayout();
        this.initialiseControls();
        this.addComponentsToPane();
    }

    /**
     * A method to initialise the layout used with this component.
     */
    private void initialiseLayout() {
        this.setAlignment(Pos.CENTER_LEFT);
        this.getStyleClass().add("background-secondary-border");
        this.setPadding(new Insets(10));
    }

    /**
     * A method to initialise the controls used within this component.
     */
    private void initialiseControls() {
        this.messageLabel = new Label();
        this.messageLabel.getStyleClass().add("text-bold");
        this.messageLabel.setText(this.message);
        this.messageDetailsLabel = new Label();
        this.messageDetailsLabel.setText(this.messageDetails);
        this.messageDetailsLabel.setWrapText(true);
    }

    /**
     * A method to add the components to the banner component.
     */
    private void addComponentsToPane() {
        this.getChildren().addAll(this.messageLabel, this.messageDetailsLabel);
    }

}
