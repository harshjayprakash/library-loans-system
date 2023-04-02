package winchester.library.gui.style;

import java.util.ArrayList;

public class StylesheetManager {
    private final static StylesheetManager instance = new StylesheetManager();
    private final ArrayList<String> stylesheetPaths;

    private StylesheetManager() {
        this.stylesheetPaths = new ArrayList<>();
    }

    public StylesheetManager getInstance() {
        return StylesheetManager.instance;
    }

    public ArrayList<String> getStylesheetPaths() {
        return this.stylesheetPaths;
    }

    public void addStylesheet(String path) {
        this.stylesheetPaths.add(path);
    }
}
