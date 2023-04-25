package winchester.library.data.model.util;

public class Exporter {

    private static String exportOutputPath;

    public Exporter() {

    }

    public boolean export(Exportable entity) {
        return entity.export();
    }

    public static String getExportOutputPath() {
        return Exporter.exportOutputPath;
    }

    public static void setExportOutputPath(String path) {
        Exporter.exportOutputPath = path;
    }

}
