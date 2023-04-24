package winchester.library.presentation.style;

import javafx.scene.Parent;
import javafx.scene.Scene;
import winchester.library.service.ConsolePrinter;

public class StylesheetSetter {
    private static StylesheetSetter instance;

    private StylesheetSetter() { }

    public static StylesheetSetter getInstance() {
        if (StylesheetSetter.instance == null) {
            StylesheetSetter.instance = new StylesheetSetter();
        }
        return StylesheetSetter.instance;
    }

    public void setStyle(Scene scene) {
        for (String path : StylesheetManager.getInstance().getStylesheetPaths()) {
            try {
                scene.getStylesheets().add(path);
            }
            catch (UnsupportedOperationException exception) {
                ConsolePrinter.getInstance().WriteLineError("Add operation not supported", exception.getMessage());
            }
            catch (Exception exception) {
                ConsolePrinter.getInstance().WriteLineError(
                        "Error assigning stylesheet at " + path, exception.getMessage());
            }
        }
    }

    public void setStyle(Parent parent) {
        for (String path : StylesheetManager.getInstance().getStylesheetPaths()) {
            try {
                parent.getStylesheets().add(path);
            }
            catch (UnsupportedOperationException exception) {
                ConsolePrinter.getInstance().WriteLineError("Add operation not supported", exception.getMessage());
            }
            catch (Exception exception) {
                ConsolePrinter.getInstance().WriteLineError(
                        "Error assigning stylesheet at " + path, exception.getMessage());
            }
        }
    }
}
