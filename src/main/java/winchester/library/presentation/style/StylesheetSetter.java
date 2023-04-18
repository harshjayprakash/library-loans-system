package winchester.library.presentation.style;

import javafx.scene.Parent;
import javafx.scene.Scene;

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
                System.err.println("Add operation not supported: " + path);
            }
            catch (Exception exception) {
                System.err.println("Error assigning stylesheet: " + path);
            }
        }
    }

    public void setStyle(Parent parent) {
        for (String path : StylesheetManager.getInstance().getStylesheetPaths()) {
            try {
                parent.getStylesheets().add(path);
            }
            catch (UnsupportedOperationException exception) {
                System.err.println("Add operation not supported: " + path);
            }
            catch (Exception exception) {
                System.err.println("Error assigning stylesheet: " + path);
            }
        }
    }
}
