package winchester.library;

import javafx.application.Application;
import javafx.stage.Stage;
import winchester.library.gui.style.StylesheetManager;

public class Main extends Application {

    @Override
    public void start(Stage stage) {
        StylesheetManager.getInstance().addStylesheet("/winchester/library/gui/style/Base.css");
    }

    public static void main(String[] args) {
        Main.launch(args);
    }

}
