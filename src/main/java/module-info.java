module winchester.library {
    requires javafx.controls;
    requires transitive javafx.graphics;
    requires org.jetbrains.annotations;
    requires java.base;
    requires java.sql;


    opens winchester.library to javafx.fxml;
    exports winchester.library;
}