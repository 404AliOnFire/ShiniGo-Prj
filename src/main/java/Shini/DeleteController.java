package Shini;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class DeleteController {
    @FXML
    private Button cancle;

    @FXML
    private Button confirm;

    @FXML
    void cancel(ActionEvent event) {
        Stage s=(Stage)cancle.getScene().getWindow();
        s.close();
    }

    @FXML
    void confirmm(ActionEvent event) {
        String phone="1234567891";
        DatabaseHelper h=new DatabaseHelper();
        h.delete(phone);
        Stage s=(Stage)confirm.getScene().getWindow();
        s.close();
    }
}
