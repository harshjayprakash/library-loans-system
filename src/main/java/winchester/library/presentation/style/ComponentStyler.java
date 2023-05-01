package winchester.library.presentation.style;

import javafx.scene.Parent;
import javafx.scene.Scene;
import winchester.library.service.Logger;

/**
 * A class that provides abstraction to add stylesheets to a component.
 */
public final class ComponentStyler {

    private static ComponentStyler instance;

    private ComponentStyler() { }

    public static ComponentStyler getInstance() {
        if (ComponentStyler.instance == null) {
            ComponentStyler.instance = new ComponentStyler();
        }
        return ComponentStyler.instance;
    }

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
