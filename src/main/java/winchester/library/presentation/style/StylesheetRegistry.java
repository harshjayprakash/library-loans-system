package winchester.library.presentation.style;

import java.util.ArrayList;
import java.util.Objects;
import winchester.library.service.Logger;

public class StylesheetRegistry {

    private static StylesheetRegistry instance;
    private final ArrayList<String> stylesheetPaths;

    private StylesheetRegistry() {
        this.stylesheetPaths = new ArrayList<>();
    }

    public static StylesheetRegistry getInstance() {
        if (StylesheetRegistry.instance == null) {
            StylesheetRegistry.instance = new StylesheetRegistry();
        }
        return StylesheetRegistry.instance;
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
