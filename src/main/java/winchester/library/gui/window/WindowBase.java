package winchester.library.gui.window;

import java.util.Objects;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import winchester.library.gui.style.StylesheetSetter;

public abstract class WindowBase extends Stage {
    protected Scene scene;
    protected BorderPane baseLayout;

    public WindowBase() {
        super();
        this.initialiseScene();
        this.loadIcon();
    }

    private void initialiseScene() {
        this.baseLayout = new BorderPane();
        this.baseLayout.setId("background-primary");
        this.scene = new Scene(this.baseLayout, 800, 600);
        StylesheetSetter.getInstance().setStyle(this.scene);
        this.setScene(scene);
    }

    private void loadIcon() {
        try {
            this.getIcons().add(new Image(
                    Objects.requireNonNull(getClass().getResourceAsStream("/winchester/library/gui/images/icon.png"))));
        }
        catch (NullPointerException exception) {
            System.out.println("Failed to load program icon");
        }
    }

    protected abstract void initialiseControls();

    protected abstract void addComponentsToStage();
}
