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
    }
}
