package winchester.library;

import javafx.application.Application;
import javafx.stage.Stage;
import winchester.library.gui.style.StylesheetManager;
import winchester.library.gui.view.Views;
import winchester.library.gui.window.IndividualViewWindow;

public class Main extends Application {

    @Override
    public void start(Stage stage) {
        StylesheetManager.getInstance().addStylesheet("style.css");
        IndividualViewWindow loginWindow = new IndividualViewWindow(Views.LOGIN, null);
        loginWindow.show();
    }

    public static void main(String[] args) {
        Main.launch(args);
    }

}
