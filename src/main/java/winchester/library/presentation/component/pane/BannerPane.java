package winchester.library.presentation.component.pane;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public final class BannerPane extends VBox {

    private final String message;
    private final String messageDetails;
    private Label messageLabel;
    private Label messageDetailsLabel;

    public BannerPane(String message, String messageDetails) {
        super();
        this.message = message;
        this.messageDetails = messageDetails;
        this.initialiseLayouts();
        this.initialiseControls();
        this.addComponentsToPane();
    }

    private void initialiseLayouts() {
        this.setAlignment(Pos.CENTER_LEFT);
        this.getStyleClass().add("background-secondary-border");
        this.setPadding(new Insets(10));
    }

    private void initialiseControls() {
        this.messageLabel = new Label();
        this.messageLabel.getStyleClass().add("text-bold");
        this.messageLabel.setText(this.message);
        this.messageDetailsLabel = new Label();
        this.messageDetailsLabel.setText(this.messageDetails);
        this.messageDetailsLabel.setWrapText(true);
    }

    private void addComponentsToPane() {
        this.getChildren().addAll(this.messageLabel, this.messageDetailsLabel);
    }

}
