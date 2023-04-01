module winchester.library {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens winchester.library to javafx.fxml;
    exports winchester.library;
}