package winchester.library.data.model.util;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import winchester.library.service.Logger;

/**
 * A class that exports item information to an external file.
 */
public class Exporter {

    /**
     * The default constructor.
     */
    public Exporter() { }

    /**
     * A method that exports an entity to an external file.
     * @param exportable a class that implements the Exportable interface that is to be exported.
     * @return a boolean value whether the operation was successful.
     */
    public boolean export(Exportable exportable) {
        try {
            LocalDateTime dateTime = LocalDateTime.now();
            DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy HH-mm-ss");
            FileWriter exportFile = new FileWriter(
                    String.format("exports/%s WL Export.txt", dateTime.format(dateTimeFormat)));
            exportFile.write(String.format(
                            """
                            --- Winchester Library Services ---
                            Exported %s
                            
                            %s
                            
                            """, dateTime.format(dateTimeFormat), exportable.export()));
            exportFile.close();
        }
        catch (IOException exception) {
            Logger.getInstance().PrintError(
                    this.getClass().getName(),
                    "Writing entity to text file",
                    "Error creating and opening a file",
                    "Ensure that the export directory exists");
        }
        return true;
    }

}
