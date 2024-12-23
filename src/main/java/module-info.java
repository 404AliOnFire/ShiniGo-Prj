module shiniproject.shinigo {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires jdk.compiler;

    opens Shini to javafx.fxml;
    exports Shini;
}