package winchester.library.gui.style;

import javafx.scene.Scene;

public class StylesheetSetter {
    private final static StylesheetSetter instance = new StylesheetSetter();

    private StylesheetSetter() { }

    public static StylesheetSetter getInstance() {
        return StylesheetSetter.instance;
    }

    public void setStyle(Scene scene) {
        for (String path : StylesheetManager.getInstance().getStylesheetPaths()) {
            try {
                scene.getStylesheets().add(path);
            }
            catch (UnsupportedOperationException exception) {
                System.out.println("Add operation not supported: " + path);
            }
            catch (Exception exception) {
                System.out.println("Error assigning stylesheet: " + path);
            }
        }
    }
}
