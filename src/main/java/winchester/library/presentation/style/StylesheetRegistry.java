package winchester.library.presentation.style;

import java.util.ArrayList;
import java.util.Objects;
import winchester.library.service.Logger;

/**
 * A singleton class that stores all the paths of stylesheets.
 */
public final class StylesheetRegistry {

    private static StylesheetRegistry instance;
    private final ArrayList<String> stylesheetPaths;

    /**
     * A private constructor to prevent multiple instances.
     */
    private StylesheetRegistry() {
        this.stylesheetPaths = new ArrayList<>();
    }

    /**
     * A static accessor to retrieve the single instance of the StylesheetRegistry class.
     * @return the instance of the class.
     */
    public static StylesheetRegistry getInstance() {
        if (StylesheetRegistry.instance == null) {
            StylesheetRegistry.instance = new StylesheetRegistry();
        }
        return StylesheetRegistry.instance;
    }

    /**
     * An accessor to retrieve the array list of the stylesheet paths.
     * @return the array list of the stylesheet paths.
     */
    public ArrayList<String> getStylesheetPaths() {
        return this.stylesheetPaths;
    }

    /**
     * A method to add stylesheets to the list stored.
     * @param path the stylesheet's path to be added.
     */
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

    /**
     * A method to add multiple stylesheets to the list stored.
     * @param paths the list of stylesheet paths to be added.
     */
    public void addMultipleStylesheets(String... paths) {
        for (String path : paths) {
            this.addStylesheet(path);
        }
    }
}
