package winchester.library.presentation.component;

import javafx.scene.layout.BorderPane;

public class Banner extends BorderPane {

    private String message;
    private String messageDetails;

    public Banner(String message, String messageDetails) {
        this.message = message;
        this.messageDetails = messageDetails;
    }

}
