package winchester.library.presentation.view;

import javafx.scene.layout.VBox;
import winchester.library.presentation.style.ComponentStyler;
import winchester.library.presentation.window.WindowBase;

/**
 * A base view class that provides the layout and access to the base window.
 */
public abstract class View extends VBox {

    protected final WindowBase parentWindow;

    /**
     * The main constructor to set the parent window and title.
     * @param parentWindow the parent window of the view.
     * @param windowTitle the window title to be displayed.
     */
    public View(WindowBase parentWindow, String windowTitle) {
        this.parentWindow = parentWindow;
        this.parentWindow.setTitleText(windowTitle);
        this.loadStylesheets();
    }

    /**
     * A method to load stylesheets to style the view.
     */
    private void loadStylesheets() {
        ComponentStyler.getInstance().setStyle(this);
    }

}
