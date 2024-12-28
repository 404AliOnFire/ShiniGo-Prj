package Shini;


import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Driver extends Application {

    public static FXMLLoader fxmlLoader;

    public static Stage loginStage;
    @FXML

    /* Shini Extra Online **/
    /*  Developed by Birzeit Students
    -> Ali Hassoneh (1221758)
    -> Hanade Zareer (1222729)
    -> Afnan Manasrah (1221150)
    **/
    @Override
    public void start(Stage stage) throws IOException {
        loginStage = stage;

        /* Compile JavaFX Application class with Java 20 **/
        /* Load Fonts, Cairo initialized to Intellij **/
        initializeFonts();
         fxmlLoader = new FXMLLoader(getClass().getResource("/Shini/FXML/animalCare.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 606, 600);
        loginStage.setTitle("Shini Extra Online");
        loginStage.setScene(scene);
        loginStage.show();
    }

    private void initializeFonts() {
        Font.loadFont(Objects.requireNonNull(getClass().getResource("/Shini/Fonts/Cairo-Regular.ttf")).toExternalForm(), 10);
        Font.loadFont(Objects.requireNonNull(getClass().getResource("/Shini/Fonts/Cairo-Bold.ttf")).toExternalForm(), 10);
        Font.loadFont(Objects.requireNonNull(getClass().getResource("/Shini/Fonts/Cairo-SemiBold.ttf")).toExternalForm(), 10);
        Font.loadFont(Objects.requireNonNull(getClass().getResource("/Shini/Fonts/Cairo-Black.ttf")).toExternalForm(), 10);
    }

    public static void main(String[] args) {
        launch();
    }
}