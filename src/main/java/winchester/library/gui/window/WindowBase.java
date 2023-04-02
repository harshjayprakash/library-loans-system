package winchester.library.gui.window;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import winchester.library.gui.style.StylesheetSetter;

public abstract class WindowBase extends Stage {
    protected Scene scene;
    protected BorderPane baseLayout;

    public WindowBase() {
        super();
        this.initialiseScene();
    }

    private void initialiseScene() {
        this.baseLayout = new BorderPane();
        this.baseLayout.setId("background-primary");
        this.scene = new Scene(this.baseLayout, 800, 600);
        StylesheetSetter.getInstance().setStyle(this.scene);
        this.setScene(scene);
    }

    protected abstract void initialiseControls();

    protected abstract void addComponentsToStage();
}
