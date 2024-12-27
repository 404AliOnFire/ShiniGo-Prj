module shiniproject.shinigo {
    requires jdk.compiler;
    requires MaterialFX;
    requires mysql.connector.java;
    requires javafx.controls;


    opens Shini to javafx.fxml;
    exports Shini;
}