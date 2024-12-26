package Shini;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class CreateAccountControl {

    public void BackToLoginText() {
        returnToLogin();
    }

    public void BackToLoginArrow() {
        returnToLogin();
    }
    public void returnToLogin(){
        LoginController.createAccountStage.close();
        Driver.loginStage.show();
    }

}
