package winchester.library.data.model.util;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import winchester.library.service.Logger;

public class Exporter {

    private static String exportOutputPath = "exports";

    public Exporter() {

    }

    public boolean export(Exportable exportable) {
        try {
            LocalDateTime dateTime = LocalDateTime.now();
            DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy HH-mm-ss");
            FileWriter exportFile = new FileWriter(
                    String.format("%s/%s WL Export.txt", Exporter.exportOutputPath, dateTime.format(dateTimeFormat)));
            exportFile.write(String.format(
                            """
                            --- Winchester Library Services ---
                            Exported %s
                            
                            %s
                            
                            """, dateTime.format(dateTimeFormat), exportable.toString()));
            exportFile.close();
        }
        catch (IOException exception) {
            Logger.getInstance().PrintError(
                    "Writing entity to text file",
                    "Error creating and opening a file",
                    "Ensure that the export directory exists");
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
