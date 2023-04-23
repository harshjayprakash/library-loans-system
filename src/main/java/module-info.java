module winchester.library {
    requires java.base;
    requires java.sql;
    requires javafx.controls;
    requires transitive javafx.graphics;
    requires org.jetbrains.annotations;

    opens winchester.library to javafx.controls;
    exports winchester.library;
}