package winchester.library.data.model.util;

public class Exporter {

    private static String exportOutputPath = "exports";

    public Exporter() {

    }

    public boolean export(Exportable exportable) {
        try {
            LocalDateTime dateTime = LocalDateTime.now();
            DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy HH mm ss");
            FileWriter exportFile = new FileWriter(
                    String.format("%s/%s WL Export.txt", Exporter.exportOutputPath, dateTime.format(dateTimeFormat)));
            exportFile.write(String.format(
                            """
                            --- Winchester Library Services ---
                            Exported %s
                            
                            %s
                            
                            """, dateTime.format(dateTimeFormat), exportable.export()));
            exportFile.close();
        }
        catch (IOException exception) {
            System.err.println(exception.toString());
        }
        return true;
    }

    public static String getExportOutputPath() {
        return Exporter.exportOutputPath;
    }

    public static void setExportOutputPath(String path) {
        Exporter.exportOutputPath = path;
    }

}
