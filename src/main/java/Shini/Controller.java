package Shini;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {
    public Stage createAccountStage;
    public void CreateAccountUI(ActionEvent actionEvent) throws IOException {
        // Implement logic to create account UI
        Driver.loginStage.close();
        createAccountStage = new Stage();

        Font.loadFont(Driver.class.getResource("/Shini/Fonts/Cairo-Regular.ttf").toExternalForm(), 10);
        Font.loadFont(Driver.class.getResource("/Shini/Fonts/Cairo-Bold.ttf").toExternalForm(), 10);
        Font.loadFont(Driver.class.getResource("/Shini/Fonts/Cairo-SemiBold.ttf").toExternalForm(), 10);
        Font.loadFont(Driver.class.getResource("/Shini/Fonts/Cairo-Black.ttf").toExternalForm(), 10);
        FXMLLoader fxmlLoader = new FXMLLoader(Driver.class.getResource("/Shini/FXML/CreateAccount.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 606, 600);
        createAccountStage.setTitle("Create Account");
        createAccountStage.setScene(scene);
        createAccountStage.show();
    }
}
