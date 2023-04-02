package winchester.library.gui.view;

import javafx.scene.layout.VBox;
import winchester.library.gui.style.StylesheetSetter;

public abstract class View extends VBox {

    public View() {
        this.loadStylesheets();
    }

    private void loadStylesheets() {
        StylesheetSetter.getInstance().setStyle(this);
    }

    protected abstract void initialiseLayouts();

    protected abstract void initialiseControls();

    protected abstract void bindEventHandlers();

    protected abstract void addComponentsToView();

}
