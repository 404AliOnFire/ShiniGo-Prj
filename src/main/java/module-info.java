module shiniproject.shinigo {
    requires jdk.compiler;
    requires java.sql;
    requires MaterialFX;

    opens Shini to javafx.fxml;
    exports Shini;
}