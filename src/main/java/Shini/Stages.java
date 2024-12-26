package Shini;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Stages {
    public static Stage userStage;

    public static Stage adminStage;

    public static Stage guestStage;

    static FXMLLoader fxmlLoader;

    public static void openUser() throws IOException {
         fxmlLoader = new FXMLLoader(Stages.class.getResource("/Shini/FXML/main.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 500, 595);
        userStage.setTitle("Shini Extra Online");
        userStage.setScene(scene);
        userStage.show();
    }

    public static void openAdmin(){
//        fxmlLoader = new FXMLLoader(Stages.class.getResource("/Shini/FXML/main.fxml"));
//
//        Scene scene = new Scene(fxmlLoader.load(), 500, 595);
//        userStage.setTitle("Shini Extra Online");
//        userStage.setScene(scene);
//        userStage.show();
    }

    public static void openGuest() throws IOException {
        fxmlLoader = new FXMLLoader(Stages.class.getResource("/Shini/FXML/guest.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 500, 595);
        userStage.setTitle("Shini Extra Online");
        userStage.setScene(scene);
        userStage.show();
    }
}
