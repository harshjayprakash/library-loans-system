module winchester.library {
    requires javafx.base;
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;
    requires mysql.connector.j;
    requires org.jetbrains.annotations;
            
                            
    opens winchester.library to javafx.fxml;
    exports winchester.library;
}