package winchester.library.presentation.component;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 * A class that provides a control to display a message on a window.
 */
@Deprecated
public final class Banner extends VBox {

    private final String message;
    private final String messageDetails;
    private Label messageLabel;
    private Label messageDetailsLabel;

    public Banner(String message, String messageDetails) {
        super();
        this.message = message;
        this.messageDetails = messageDetails;
        this.initialiseLayouts();
        this.initialiseControls();
        this.addComponentsToBanner();
    }

    private void initialiseLayouts() {
        this.setAlignment(Pos.CENTER_LEFT);
        this.getStyleClass().add("background-secondary");
        this.setPadding(new Insets(15));
    }

    private void initialiseControls() {
        this.messageLabel = new Label();
        this.messageLabel.getStyleClass().add("text-bold");
        this.messageLabel.setText(this.message);
        this.messageDetailsLabel = new Label();
        this.messageDetailsLabel.setText(this.messageDetails);
        this.messageDetailsLabel.setWrapText(true);
    }

    private void addComponentsToBanner() {
        this.getChildren().addAll(this.messageLabel, this.messageDetailsLabel);
    }

}
