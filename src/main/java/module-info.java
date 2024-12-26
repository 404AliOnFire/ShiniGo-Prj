module shiniproject.shinigo {
    requires jdk.compiler;
    requires MaterialFX;

    opens Shini to javafx.fxml;
    exports Shini;
}