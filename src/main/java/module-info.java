module winchester.library {
    requires javafx.controls;
    requires transitive javafx.graphics;


    opens winchester.library to javafx.fxml;
    exports winchester.library;
}