module shiniproject.shinigo {
    requires jdk.compiler;
    requires MaterialFX;
    requires mysql.connector.java;
    requires javafx.controls;
    requires java.sql;
    requires MaterialFX;


    opens Shini to javafx.fxml;
    exports Shini;
}