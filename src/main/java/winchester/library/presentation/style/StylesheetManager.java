package winchester.library.presentation.style;

import java.util.ArrayList;
import java.util.Objects;
import winchester.library.service.Logger;

public class StylesheetManager {

    private static StylesheetManager instance;
    private final ArrayList<String> stylesheetPaths;

    private StylesheetManager() {
        this.stylesheetPaths = new ArrayList<>();
    }

    public static StylesheetManager getInstance() {
        if (StylesheetManager.instance == null) {
            StylesheetManager.instance = new StylesheetManager();
        }
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
            Logger.getInstance().PrintError(
                    this.getClass().getName(),
                    String.format("Loading Stylesheet '%s'", path),
                    "Stylesheet Not Found",
                    "Ensure availability of stylesheet");
        }
    }

    public void addMultipleStylesheets(String... paths) {
        for (String path : paths) {
            this.addStylesheet(path);
        }
    }
}
