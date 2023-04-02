package winchester.library.gui.window;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import winchester.library.gui.style.StylesheetManager;

public abstract class WindowBase extends Stage {
    protected Scene scene;
    protected Parent baseLayout;

    public WindowBase(Parent baseLayout, int weight, int height) {
        super();
        this.baseLayout = baseLayout;
        this.initialiseScene(weight, height);
    }

    private void initialiseScene(int weight, int height) {
        this.scene = new Scene(this.baseLayout, weight, height);
    }

    private void loadStylesheets() {
        for (String path : StylesheetManager.getInstance().getStylesheetPaths()) {
            this.scene.getStylesheets().add(path);
        }
    }

    protected abstract void initialiseLayouts();

    protected abstract void initialiseControls();

    protected abstract void addComponentsToStage();
}
