package winchester.library;

import javafx.application.Application;
import javafx.stage.Stage;
import winchester.library.data.model.users.Administrator;
import winchester.library.data.model.users.Employee;
import winchester.library.data.model.users.EmployeeStatus;
import winchester.library.presentation.style.StylesheetRegistry;
import winchester.library.presentation.view.Views;
import winchester.library.presentation.window.IndividualViewWindow;
import winchester.library.service.Logger;

/**
 * The Main class specifies the entrypoint into the program, inheriting JavaFX Application to provide a start point.
 * @author Harsh Jayprakash
 * @version 1.0
 */
public final class Main extends Application {

    /**
     * This method provides a starting point for specifically for starting a JavaFX window (Stage)
     * @param stage the primary stage for this application, onto which
     * the application scene can be set.
     * Applications may create other stages, if needed, but they will not be
     * primary stages.
     */
    @Override
    public void start(Stage stage) {
        Logger.getInstance().setEnabled(true);
        Logger.getInstance().setWhereEnabled(true);
        StylesheetRegistry.getInstance().addMultipleStylesheets(
                "/winchester/library/presentation/style/base.css",
                "/winchester/library/presentation/style/components.css",
                "/winchester/library/presentation/style/misc.css",
                "/winchester/library/presentation/style/utility.css");
        IndividualViewWindow loginWindow = new IndividualViewWindow(Views.LOGIN);
        loginWindow.show();
    }

    /**
     * This is the starting point of the java program.
     * @param args - Any arguments specified when running the application.
     */
    public static void main(String[] args) {
        System.out.println("Mark Harrison  " + "wcJczEr36UqEF$".hashCode());
        System.out.println("Chris Adams  " + "BUL4cF#GV&$p@z".hashCode());
        System.out.println("Dominic Mccann  " + "fv5YPaJec*TUb&".hashCode());
        Main.launch(args);
    }

}
