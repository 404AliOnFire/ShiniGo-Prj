module shiniproject.shinigo {
    requires jdk.compiler;
    requires MaterialFX;
    requires java.sql;
    requires javafx.controls;

    opens Shini to javafx.fxml;
    exports Shini;
}