module shiniproject.shinigo {
    requires javafx.controls;
    requires javafx.fxml;
    requires jdk.compiler;
    requires java.sql;

    opens Shini to javafx.fxml;
    exports Shini;
}