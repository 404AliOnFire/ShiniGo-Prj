module shiniproject.shinigo {
    requires javafx.controls;
    requires javafx.fxml;

    requires jdk.compiler;

    opens Shini to javafx.fxml;
    exports Shini;
}