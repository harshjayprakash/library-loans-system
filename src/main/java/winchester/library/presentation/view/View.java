package winchester.library.presentation.view;

import javafx.scene.layout.VBox;
import winchester.library.presentation.style.ComponentStyler;
import winchester.library.presentation.window.WindowBase;

/**
 * A base view class that provides the layout and access to the base window.
 */
public abstract class View extends VBox {

    protected final WindowBase parentWindow;

    public View(WindowBase parentWindow, String windowTitle) {
        this.parentWindow = parentWindow;
        this.parentWindow.setTitleText(windowTitle);
        this.loadStylesheets();
    }

    protected abstract void initialiseLayouts();

    protected abstract void initialiseControls();

    protected abstract void addComponentsToView();

    private void loadStylesheets() {
        ComponentStyler.getInstance().setStyle(this);
    }

}
