package winchester.library;

import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import winchester.library.presentation.alert.AlertFactory;
import winchester.library.presentation.style.StylesheetRegistry;
import winchester.library.presentation.view.Views;
import winchester.library.presentation.window.IndividualViewWindow;
import winchester.library.service.DatabaseConnectivityChecker;
import winchester.library.service.IdentifierGenerator;
import winchester.library.service.Logger;

/**
 * The Main class specifies the entrypoint into the program, inheriting JavaFX Application to provide a start point.
 * @author Harsh Jayprakash
 * @version 1.0
 */
public final class Main extends Application {

    /**
     * This method provides a starting point for specifically for starting a JavaFX window (Stage)
     * @param stage the primary stage for this application, onto which the application scene can be set.
     */
    @Override
    public void start(Stage stage) {
        Logger.getInstance().setEnabled(true);
        Logger.getInstance().setWhereEnabled(false);
        System.out.println(new IdentifierGenerator().generateForUser());
        StylesheetRegistry.getInstance().addMultipleStylesheets(
                "/winchester/library/presentation/style/base.css",
                "/winchester/library/presentation/style/components.css",
                "/winchester/library/presentation/style/misc.css",
                "/winchester/library/presentation/style/utility.css");
        if (!DatabaseConnectivityChecker.getInstance().isDriverAvailable()) {
            AlertFactory.createAlert(Alert.AlertType.ERROR, "Cannot find database driver.").show();
        }
        if (!DatabaseConnectivityChecker.getInstance().isDatabaseAvailable()) {
            AlertFactory.createAlert(Alert.AlertType.ERROR, "Cannot connect to database.",
                    "Ensure that the credentials are correct.").show();
        }
        IndividualViewWindow loginWindow = new IndividualViewWindow(Views.LOGIN);
        loginWindow.show();
    }

    /**
     * This is the starting point of the java program.
     * @param args - Any arguments specified when running the application.
     */
    public static void main(String[] args) {
        Main.launch(args);
    }

}
