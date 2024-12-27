module shiniproject.shinigo {
    requires jdk.compiler;
    requires java.sql;
    requires MaterialFX;
    requires mysql.connector.java;

    opens Shini to javafx.fxml;
    exports Shini;
}