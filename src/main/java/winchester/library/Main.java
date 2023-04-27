package winchester.library;

import javafx.application.Application;
import javafx.stage.Stage;
import winchester.library.presentation.style.StylesheetRegistry;
import winchester.library.presentation.view.Views;
import winchester.library.presentation.window.IndividualViewWindow;
import winchester.library.service.Logger;

/**
 * The Main class specifies the entrypoint into the program, inheriting JavaFX Application to provide a start point.
 */
public final class Main extends Application {

    @Override
    public void start(Stage stage) {
        Logger.getInstance().setEnabled(true);
        StylesheetRegistry.getInstance().addMultipleStylesheets(
                "/winchester/library/presentation/style/base.css",
                "/winchester/library/presentation/style/components.css",
                "/winchester/library/presentation/style/misc.css",
                "/winchester/library/presentation/style/utility.css");
        IndividualViewWindow loginWindow = new IndividualViewWindow(Views.LOGIN);
        loginWindow.show();
    }

    public static void main(String[] args) {
        Main.launch(args);
    }

}
