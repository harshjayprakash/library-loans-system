package winchester.library.data.model.util;

/**
 * An interface for items able to export to an external file.
 */
public interface Exportable {
    /**
     * Returns an appropriately formatted string value that can be exported to an external file.
     * @return a formatted string value to be exported.
     */
    String export();
}
