package Shini;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {
    public Stage createAccountStage;
    public void CreateAccountUI(ActionEvent actionEvent) throws IOException {
        // Implement logic to create account UI
        Driver.loginStage.close();
        createAccountStage = new Stage();

        // Delete the Fonts Codes imported, because they are useless here
        // _____________________________________________________________
        FXMLLoader fxmlLoader = new FXMLLoader(Driver.class.getResource("/Shini/FXML/CreateAccount.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 606, 600);
        createAccountStage.setTitle("Create Account");
        createAccountStage.setScene(scene);
        createAccountStage.show();
    }

    public void BackToLoginText(MouseEvent mouseEvent) {
        returnToLogin();
    }

    public void BackToLoginArrow(MouseEvent mouseEvent) {
        returnToLogin();
    }
    public void returnToLogin(){
        createAccountStage.close();
        Driver.loginStage.show();
    }
}
