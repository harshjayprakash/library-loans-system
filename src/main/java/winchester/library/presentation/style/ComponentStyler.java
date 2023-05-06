package winchester.library.presentation.style;

import javafx.scene.Parent;
import javafx.scene.Scene;
import winchester.library.service.Logger;

/**
 * A singleton class that provides abstraction to add stylesheets to a component.
 */
public final class ComponentStyler {

    private static ComponentStyler instance;

    /**
     * A private constructor to prevent multiple instances.
     */
    private ComponentStyler() { }

    /**
     * A static accessor to retrieve the single instance of the ComponentStyler class.
     * @return an instance of the class.
     */
    public static ComponentStyler getInstance() {
        if (ComponentStyler.instance == null) {
            ComponentStyler.instance = new ComponentStyler();
        }
        return ComponentStyler.instance;
    }

    /**
     * A method to set add the stylesheets to the given component.
     * @param scene the scene to be styled.
     */
    public void setStyle(Scene scene) {
        for (String path : StylesheetRegistry.getInstance().getStylesheetPaths()) {
            try {
                scene.getStylesheets().add(path);
            }
            catch (UnsupportedOperationException exception) {
                Logger.getInstance().PrintError(
                        this.getClass().getName(),
                        "Setting Styles To Scene",
                        "Add option not supported on given object",
                        "Ensure the correct type is given");
            }
            catch (Exception exception) {
                Logger.getInstance().PrintError(
                        this.getClass().getName(),
                        "Setting Styles To Scene",
                        String.format("Error Assigning Stylesheet '%s' due to %s", path, exception.getMessage()),
                        "^ Please find solution to problem described above");
            }
        }
    }

    /**
     * A method to set add the stylesheets to the given component.
     * @param parent the parent component to be styled.
     */
    public void setStyle(Parent parent) {
        for (String path : StylesheetRegistry.getInstance().getStylesheetPaths()) {
            try {
                parent.getStylesheets().add(path);
            }
            catch (UnsupportedOperationException exception) {
                Logger.getInstance().PrintError(
                        this.getClass().getName(),
                        "Setting Styles To Scene",
                        "Add option not supported on given object",
                        "Ensure the correct type is given");
            }
            catch (Exception exception) {
                Logger.getInstance().PrintError(
                        this.getClass().getName(),
                        "Setting Styles To Scene",
                        String.format("Error Assigning Stylesheet '%s' due to %s", path, exception.getMessage()),
                        "^ Please find solution to problem described above");
            }
        }
    }
}
