package winchester.library;

import javafx.application.Application;
import javafx.stage.Stage;
import winchester.library.gui.style.StylesheetManager;
import winchester.library.gui.view.Views;
import winchester.library.gui.window.IndividualViewWindow;

/**
 * The Main class specifies the entrypoint into the program, inheriting JavaFX Application to provide a start point.
 */
public class Main extends Application {

    @Override
    public void start(Stage stage) {
        StylesheetManager.getInstance().addMultipleStylesheets("/winchester/library/gui/style/base.css", "/winchester/library/gui/style/style.css");
        IndividualViewWindow loginWindow = new IndividualViewWindow(Views.LOGIN, null);
        loginWindow.show();
    }

    public static void main(String[] args) {
        Main.launch(args);
    }

}
