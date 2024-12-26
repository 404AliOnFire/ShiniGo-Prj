package Shini;

import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXSlider;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class LoginController implements Initializable {
    @FXML
    private  ComboBox<String> addressCB;

    @FXML
    private Text failedText;

    @FXML
    private MFXPasswordField passwordTF;

    @FXML
    private MFXTextField phoneTF;

    @FXML
    private MFXSlider slider;

    @FXML
    private Text successText;


    public static LoginController loginController;

    public static FXMLLoader loader;

    Clip clip;
    String number = "";
    public static Stage createAccountStage = new Stage();
    String addressNumber = "";
    @FXML
    public void CreateAccountUI() throws IOException {
        // Implement logic to create account UI
        Driver.loginStage.close();

        // _____________________________________________________________
        FXMLLoader fxmlLoader = new FXMLLoader(Driver.class.getResource("/Shini/FXML/CreateAccount.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 606, 600);
        createAccountStage.setTitle("Create Account");
        createAccountStage.setScene(scene);
        createAccountStage.show();
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loader = Driver.fxmlLoader;
        loginController = loader.getController();
        loginController.addressCB.getItems().addAll("+972","+970");
        // passwordTF logic
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

        // addressCB logic
        addressCB.setOnAction((ActionEvent e) -> {
            addressNumber = addressCB.getValue();
        });

        // phoneTF logic
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
        try {
            URL soundURL = getClass().getResource("/Shini/Sounds/ჩუბინა - Chub1na.Ge ｜ slowed.wav");
            if (soundURL == null) {
                System.out.println("Sound file not found.");
                return;
            }

            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundURL);
            clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();

        } catch (Exception e) {
            e.printStackTrace();
        }

        slider.setMin(0);
        slider.setMax(100);
        slider.setValue(50);
        slider.valueProperty().addListener((observable, oldValue, newValue) -> {

            float volume = (float) (newValue.doubleValue() / 100.0);
            setVolume(volume);
        });
    }

    private void setVolume(float volume) {
        if (clip != null) {
            try {
                FloatControl volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);

                float min = volumeControl.getMinimum();
                float max = volumeControl.getMaximum();
                float gain = min + (max - min) * volume;
                volumeControl.setValue(gain);


            } catch (IllegalArgumentException e) {
                System.out.println("Volume control not supported.");
            }
        }
    }

    @FXML
    void loginButton(ActionEvent event) {
        // Implement logic to handle login
        String phone = phoneTF.getText();
        String password = passwordTF.getText();

        if (phone.isEmpty() || password.isEmpty()) {
            successText.setVisible(false);
            failedText.setVisible(true);
            return;
        }

        // Check if user exists in the database
//       // if (!Database.checkUserExists(phone, password)) {
//            System.out.println("Invalid credentials.");
//            return;
//        }

        // Open the main UI
        Driver.loginStage.close();
        clip.stop();
       // Stages.openMain();
    }
    @FXML
    public void adressHandle() {
        addressNumber = addressCB.getSelectionModel().getSelectedItem();
        System.out.println(addressNumber);
    }
    @FXML
    public void skipLogin() throws IOException {
        Driver.loginStage.close();
        clip.stop();
        Stages.openGuest();
    }
}
