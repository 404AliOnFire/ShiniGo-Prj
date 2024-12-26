package Shini;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.io.IOException;

public class Controller {
    public Stage createAccountStage = new Stage();
    public void CreateAccountUI() throws IOException {
        // Implement logic to create account UI
        Driver.loginStage.close();



        // Delete the Fonts Codes imported, because they are useless here
        // _____________________________________________________________
        FXMLLoader fxmlLoader = new FXMLLoader(Driver.class.getResource("/Shini/FXML/CreateAccount.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 606, 600);
        createAccountStage.setTitle("Create Account");
        createAccountStage.setScene(scene);
        createAccountStage.show();
    }

    public void BackToLoginText() {
        returnToLogin();
    }

    public void BackToLoginArrow() {
        returnToLogin();
    }
    public void returnToLogin(){
        createAccountStage.close();
        Driver.loginStage.show();
    }
}
