package winchester.library.presentation.window;

import java.util.Objects;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import winchester.library.meta.Metadata;
import winchester.library.presentation.style.StylesheetSetter;
import winchester.library.service.ConsolePrinter;

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
            this.getIcons().add(new Image(Objects.requireNonNull(
                    getClass().getResourceAsStream("/winchester/library/presentation/images/icon.png"))));
        }
        catch (NullPointerException exception) {
            ConsolePrinter.getInstance().WriteLineError("Failed to load program icon.");
        }
    }

    public void setTitleText(String text) {
        this.setTitle("%s - %s".formatted(text, Metadata.getInstance().getProgramName()));
    }

    protected abstract void initialiseControls();

    protected abstract void bindEventHandlers();

    protected abstract void addComponentsToStage();
}
