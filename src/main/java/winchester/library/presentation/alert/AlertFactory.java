package winchester.library.presentation.alert;

import javafx.scene.control.Alert;

/**
 * A factory class that abstracts the creation of alert message boxes.
 */
public abstract class AlertFactory {

    public static Alert createAlert(Alert.AlertType type, String headerText, String contextText) {
        Alert alert = new Alert(type);
        alert.setHeaderText(headerText);
        alert.setContentText(contextText);
        return alert;
    }

    public static Alert createAlert(Alert.AlertType type, String headerText) {
        Alert alert = new Alert(type);
        alert.setHeaderText(headerText);
        return alert;
    }

}
