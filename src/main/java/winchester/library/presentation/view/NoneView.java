package winchester.library.presentation.view;

import winchester.library.presentation.window.WindowBase;

/**
 * An empty view.
 */
public final class NoneView extends View {

    /**
     * The default constructor that passes the parent window.
     * @param parentWindow the parent window that the view can access.
     */
    public NoneView(WindowBase parentWindow) {
        super(parentWindow, Views.NONE.toString());
    }

    /**
     * A method to initialise any layouts used within the view.
     */
    @Override
    protected void initialiseLayouts() {

    }

    /**
     * A method to initialise any controls used within the view.
     */
    @Override
    protected void initialiseControls() {

    }

    /**
     * A method to add components to the view.
     */
    @Override
    protected void addComponentsToView() {

    }
}
