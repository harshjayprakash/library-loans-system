package winchester.library.presentation.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import winchester.library.presentation.window.WindowBase;

/**
 * An informative view that provides a method of displaying another view if no view is being shown in the Main Window.
 */
public final class NoneSidePaneView extends View {

    private Label messageLabel;
    private Label informationLabel;

    /**
     * The default constructor that passes the parent window.
     * @param parentWindow the parent window that the view can access.
     */
    public NoneSidePaneView(WindowBase parentWindow) {
        super(parentWindow, Views.NONE_WITH_SIDEBAR.toString());
        this.initialiseLayouts();
        this.initialiseControls();
        this.addComponentsToView();
    }

    /**
     * A method to initialise any layouts used within the view.
     */
    private void initialiseLayouts() {
        this.setAlignment(Pos.CENTER);
        this.setPadding(new Insets(15));
    }

    /**
     * A method to initialise any controls used within the view.
     */
    private void initialiseControls() {
        this.messageLabel = new Label();
        this.messageLabel.getStyleClass().add("text-bold");
        this.messageLabel.setText("No Content Shown.");
        this.informationLabel = new Label();
        this.informationLabel.setText("Please click on one of the views on the side.");
    }

    /**
     * A method to add components to the view.
     */
    private void addComponentsToView() {
        this.getChildren().addAll(this.messageLabel, this.informationLabel);
    }
}
