package Shini;

import com.sun.tools.javac.Main;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;

public class Driver extends Application {
    public static Stage loginStage;
    /* Shini Extra Online **/
    /*  Developed by Birzeit Students
    -> Ali Hassoneh (1221758)
    -> Hanade Zareer (???????)
    -> Afnan Manasrah (??????)
    **/
    @Override
    public void start(Stage stage) throws IOException {
        loginStage = stage;
        /* Compile JavaFX Application class with Java 20 **/
        /* Load Fonts, Cairo initialized to Intellij **/
        Font.loadFont(Driver.class.getResource("/Shini/Fonts/Cairo-Regular.ttf").toExternalForm(), 10);
        Font.loadFont(Driver.class.getResource("/Shini/Fonts/Cairo-Bold.ttf").toExternalForm(), 10);
        Font.loadFont(Driver.class.getResource("/Shini/Fonts/Cairo-SemiBold.ttf").toExternalForm(), 10);
        Font.loadFont(Driver.class.getResource("/Shini/Fonts/Cairo-Black.ttf").toExternalForm(), 10);
        FXMLLoader fxmlLoader = new FXMLLoader(Driver.class.getResource("/Shini/FXML/LoginUI.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 606, 600);
        loginStage.setTitle("Shini Extra Online");
        loginStage.setScene(scene);
        loginStage.show();
    }

    public static void main(String[] args) {

        launch();
    }
}