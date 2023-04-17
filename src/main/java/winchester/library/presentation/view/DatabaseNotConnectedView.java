package winchester.library.presentation.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;

public class DatabaseNotConnectedView extends View {

    private Label messageLabel;
    private Label informationLabel;

    public DatabaseNotConnectedView() {
        super();
        this.initialiseLayouts();
        this.initialiseControls();
        this.addComponentsToView();
    }

    @Override
    protected void initialiseLayouts() {
        this.setAlignment(Pos.CENTER_LEFT);
        this.setId("background-secondary");
        this.setPadding(new Insets(15));
    }

    @Override
    protected void initialiseControls() {
        this.messageLabel = new Label();
        this.messageLabel.setId("text-bold");
        this.messageLabel.setText("Data not accessible.");
        this.informationLabel = new Label();
        this.informationLabel.setText("Please check the database configuration by clicking on 'Database Status'.");
    }

    @Override
    protected void addComponentsToView() {
        this.getChildren().addAll(this.messageLabel, this.informationLabel);
    }
}
