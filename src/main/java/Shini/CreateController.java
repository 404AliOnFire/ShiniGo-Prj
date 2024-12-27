package Shini;

import io.github.palexdev.materialfx.controls.MFXPasswordField;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CreateController implements Initializable {

    @FXML
    private  ComboBox<String> adressCB;

    @FXML
    private ComboBox<String> genderTF;

    @FXML
    private TextField nameField;

    @FXML
    private MFXPasswordField passwordTF;

    @FXML
    private TextField phoneTF;

    @FXML
    private Text successText;

    @FXML
    private Text failedText;

    String addressNumber = "+972";

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        genderTF.getItems().addAll("M", "F");
        adressCB.getItems().addAll("+972", "+970");
        // Customizing the color of items in the ComboBox
        genderTF.setCellFactory(param -> new ListCell<String>() {
                    @Override
                    protected void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty || item == null) {
                            setText(null);
                            setStyle("");
                        } else {
                            setText(item);
                            // Set the color based on the item value
                            if (item.equals("M")) {
                                setStyle("-fx-text-fill: blue;");
                            } else if (item.equals("F")) {
                                setStyle("-fx-text-fill: pink;");
                            }
                        }
                    }
                });
        passwordTF.addEventFilter(KeyEvent.KEY_TYPED, event -> {
            String currentText = passwordTF.getText();
            String input = event.getCharacter();

            if (!input.matches("[a-zA-Z0-9!@#$%^&*(),.?\":{}|<>]")) {
                event.consume();
                return;
            }

            if (currentText.length() >= 20) {
                event.consume();
            }
        });
        phoneTF.addEventFilter(KeyEvent.KEY_TYPED, event -> {
            String currentText = phoneTF.getText();
            String input = event.getCharacter();


            if (!input.matches("\\d")) {
                event.consume();
                return;
            }


            if (currentText.isEmpty() && input.equals("0")) {
                event.consume();
                return;
            }

            if (currentText.isEmpty() && !input.equals("5")) {
                event.consume();
            }
        });
    }

    @FXML
    void genderHandle(ActionEvent event) {

    }
    @FXML
    public void BackToLoginText() {
        returnToLogin();
    }

    @FXML
    public void BackToLoginArrow() {
        returnToLogin();
    }
    public void returnToLogin(){
        LoginController.createAccountStage.close();
        Driver.loginStage.show();
    }

    @FXML
    void createHandle(ActionEvent event) throws IOException {
        // Implement logic to handle login
        String phone = phoneTF.getText();
        String password = passwordTF.getText();
        String gender = genderTF.getSelectionModel().getSelectedItem();
        String name = nameField.getText();

        if (phone.isEmpty() || password.isEmpty() || phone.length() < 9
                || password.length() < 6 || nameField.getText().length() >= 50 || gender == null) {
            successText.setVisible(false);
            failedText.setVisible(true);
            failedText.setText("تأكد من البيانات المدخلة!");
            return;
        }

        // Check if user exists in the database
        if (DatabaseHelper.checkPhoneExists(addressNumber + phone)) {
            successText.setVisible(false);
            failedText.setVisible(true);
            failedText.setText("هذا الحساب موجود من قبل!");
            return;
        }
        DatabaseHelper.createAccount(name, addressNumber + phone, gender, password);
        // Display success message with the user's name
        successText.setVisible(true);
        failedText.setVisible(false);

    }
}
