module winchester.library {
    requires java.base;
    requires java.sql;
    requires javafx.controls;
    requires transitive javafx.graphics;
    requires mysql.connector.j;
    requires org.jetbrains.annotations;

    opens winchester.library to javafx.controls;
    exports winchester.library;
}