package winchester.library.presentation.view;

import javafx.scene.layout.VBox;
import winchester.library.presentation.style.StylesheetSetter;

public abstract class View extends VBox {

    public View() {
        this.loadStylesheets();
    }

    private void loadStylesheets() {
        StylesheetSetter.getInstance().setStyle(this);
    }

    protected abstract void initialiseLayouts();

    protected abstract void initialiseControls();

    protected abstract void addComponentsToView();

}
