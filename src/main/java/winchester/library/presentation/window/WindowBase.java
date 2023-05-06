package winchester.library.presentation.window;

import java.util.Objects;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import winchester.library.meta.Metadata;
import winchester.library.presentation.style.ComponentStyler;
import winchester.library.service.Logger;

/**
 * A base window class that provides the base components including the scene, layout and styling.
 */
public abstract class WindowBase extends Stage {

    protected Scene scene;
    protected BorderPane baseLayout;

    /**
     * The constructor for the WindowBase class.
     */
    public WindowBase() {
        super();
        this.initialiseScene();
        this.loadIcon();
    }

    /**
     * a mutator to set the title of the window with the program's name.
     * @param text the new window title.
     */
    public void setTitleText(String text) {
        this.setTitle("%s - %s".formatted(text, Metadata.getInstance().getProgramName()));
    }

    /**
     * An abstract method to initialise controls within the window.
     */
    protected abstract void initialiseControls();

    /**
     * An abstract method to add components to the window.
     */
    protected abstract void addComponentsToStage();

    /**
     * A method to initialise and style the scene.
     */
    private void initialiseScene() {
        this.baseLayout = new BorderPane();
        this.baseLayout.getStyleClass().add("background-primary");
        this.scene = new Scene(this.baseLayout, 800, 600);
        ComponentStyler.getInstance().setStyle(this.scene);
        this.setScene(scene);
    }

    /**
     * A method to load the program icon.
     */
    private void loadIcon() {
        try {
            this.getIcons().add(new Image(Objects.requireNonNull(
                    getClass().getResourceAsStream("/winchester/library/presentation/images/icon.png"))));
        }
        catch (NullPointerException exception) {
            Logger.getInstance().PrintError(
                    this.getClass().getName(),
                    "Loading Program Icon",
                    "Object Not Found",
                    "Ensure the availability of the icon");
        }
    }
}
