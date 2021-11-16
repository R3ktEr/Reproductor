module controller {
    requires transitive javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;
	requires transitive java.sql;
	requires java.base;
	requires org.jfxtras.styles.jmetro;
	requires java.xml.bind;

	opens model to javafx.base;
	opens utils to javafx.base, java.xml.bind;
    opens controller to javafx.fxml;
    exports model;
    exports controller;
    exports utils;
}
