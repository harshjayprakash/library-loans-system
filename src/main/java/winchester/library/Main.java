package winchester.library;

import javafx.application.Application;
import javafx.stage.Stage;
import winchester.library.presentation.style.StylesheetManager;
import winchester.library.presentation.view.Views;
import winchester.library.presentation.window.IndividualViewWindow;
import winchester.library.service.Logger;

/**
 * The Main class specifies the entrypoint into the program, inheriting JavaFX Application to provide a start point.
 */
public class Main extends Application {

    @Override
    public void start(Stage stage) {
        Logger.getInstance().setEnabled(true);
        StylesheetManager.getInstance().addMultipleStylesheets(
                "/winchester/library/presentation/style/base.css", "/winchester/library/presentation/style/style.css");
        IndividualViewWindow loginWindow = new IndividualViewWindow(Views.LOGIN);
        loginWindow.show();
    }

    public static void main(String[] args) {
        Main.launch(args);
    }

}
