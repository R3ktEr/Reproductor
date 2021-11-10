module controller {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;
	requires java.sql;

    opens controller to javafx.fxml;
    exports controller;
}
