module shiniproject.shinigo {
    requires jdk.compiler;
    requires MaterialFX;
    requires mysql.connector.java;
    requires javafx.controls;
    requires java.sql;


    opens Shini to javafx.fxml;
    exports Shini;
    exports Shini.Admin;
    opens Shini.Admin to javafx.fxml;
}