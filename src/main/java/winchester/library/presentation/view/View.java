package winchester.library.presentation.view;

import javafx.scene.layout.VBox;
import winchester.library.presentation.style.StylesheetSetter;
import winchester.library.presentation.window.WindowBase;

public abstract class View extends VBox {

    protected final WindowBase parentWindow;

    public View(WindowBase parentWindow, String windowTitle) {
        this.parentWindow = parentWindow;
        this.parentWindow.setTitleText(windowTitle);
        this.loadStylesheets();
    }

    private void loadStylesheets() {
        StylesheetSetter.getInstance().setStyle(this);
    }

    protected abstract void initialiseLayouts();

    protected abstract void initialiseControls();

    protected abstract void addComponentsToView();

}
