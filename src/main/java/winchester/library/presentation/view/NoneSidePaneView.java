package winchester.library.presentation.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import winchester.library.presentation.window.WindowBase;

public class NoneSidePaneView extends View {

    private Label messageLabel;
    private Label informationLabel;

    public NoneSidePaneView(WindowBase parentWindow) {
        super(parentWindow, Views.NONE_WITH_SIDEBAR.toString());
        this.initialiseLayouts();
        this.initialiseControls();
        this.addComponentsToView();
    }

    @Override
    protected void initialiseLayouts() {
        this.setAlignment(Pos.CENTER);
        this.setPadding(new Insets(15));
    }

    @Override
    protected void initialiseControls() {
        this.messageLabel = new Label();
        this.messageLabel.setId("text-bold");
        this.messageLabel.setText("No Content Shown.");
        this.informationLabel = new Label();
        this.informationLabel.setText("Please click on one of the views on the side.");
    }

    @Override
    protected void addComponentsToView() {
        this.getChildren().addAll(this.messageLabel, this.informationLabel);
    }
}
