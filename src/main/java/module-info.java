/**
 * Represents the libraries required for the winchester library module.
 */
module winchester.library {
    requires java.base;
    requires java.sql;
    requires javafx.controls;
    requires transitive javafx.graphics;
    requires org.jetbrains.annotations;
    requires mysql.connector.j;

    opens winchester.library to javafx.controls;
    exports winchester.library;
    exports winchester.library.data.access;
    exports winchester.library.data.model.items;
    exports winchester.library.data.model.users;
    exports winchester.library.data.model.loans;
    exports winchester.library.data.model.util;
    exports winchester.library.meta;
    exports winchester.library.service;
    exports winchester.library.presentation.component.pane;
    exports winchester.library.presentation.component.card;
    exports winchester.library.presentation.view;
    exports winchester.library.presentation.window;
    exports winchester.library.presentation.alert;
    exports winchester.library.presentation.style;
}