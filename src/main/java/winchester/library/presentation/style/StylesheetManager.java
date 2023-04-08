package winchester.library.presentation.style;

import java.util.ArrayList;
import java.util.Objects;

public class StylesheetManager {
    private final static StylesheetManager instance = new StylesheetManager();
    private final ArrayList<String> stylesheetPaths;

    private StylesheetManager() {
        this.stylesheetPaths = new ArrayList<>();
    }

    public static StylesheetManager getInstance() {
        return StylesheetManager.instance;
    }

    public ArrayList<String> getStylesheetPaths() {
        return this.stylesheetPaths;
    }

    public void addStylesheet(String path) {
        try {
            this.stylesheetPaths.add(Objects.requireNonNull(getClass().getResource(path)).toExternalForm());
        }
        catch (NullPointerException exception) {
            System.out.println("Could not find stylesheet: " + path);
        }
    }

    public void addMultipleStylesheets(String... paths) {
        for (String path : paths) {
            this.addStylesheet(path);
        }
    }
}
