package Shini;

import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXProgressSpinner;
import io.github.palexdev.materialfx.controls.MFXSlider;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

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

    public MFXTextField getPhoneTF() {
        return phoneTF;
    }

    @FXML
    private MFXSlider slider;

    @FXML
    private Text successText;

    @FXML
    private MFXProgressSpinner spinner;

    public static LoginController loginController;

    public static FXMLLoader loader;

    public static FXMLLoader createLoader;

    Clip clip;
    String number = "";
    public static Stage createAccountStage = new Stage();
    String addressNumber = "+972";
    @FXML
    public void CreateAccountUI() throws IOException {
        // Implement logic to create account UI
        Driver.loginStage.close();

        // _____________________________________________________________
        createLoader = new FXMLLoader(Driver.class.getResource("/Shini/FXML/CreateAccount.fxml"));

        Scene scene = new Scene(createLoader.load(), 606, 600);
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
    void loginButton(ActionEvent event) throws IOException {
        // Implement logic to handle login
        String phone = phoneTF.getText();
        String password = passwordTF.getText();


        if (phone.isEmpty() || password.isEmpty() || phone.length() < 9 || password.length() < 6) {
            successText.setVisible(false);
            failedText.setVisible(true);
            failedText.setText("تأكد من البيانات المدخلة!");
            return;
        }

        // Check if user exists in the database
        if (!DatabaseHelper.checkUserExists(addressNumber + phone, password)) {
            successText.setVisible(false);
            failedText.setVisible(true);
            failedText.setText("لا يوجد مستخدم بهذه البيانات.");
            return;
        }
        // Retrieve the user's name from the database
        String userName = DatabaseHelper.getUserName(addressNumber + phone, password);

        // Display success message with the user's name
        successText.setVisible(true);
        failedText.setVisible(false);
        spinner.setVisible(true);
        successText.setText("مرحبًا بعودتك " + userName + "!");


        PauseTransition pause = new PauseTransition(Duration.seconds(2.6));
        pause.setOnFinished(e -> {
            try {
                Driver.loginStage.close();
                clip.stop();
                Stages.openUser();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        pause.play();

    }
    @FXML
    public void addressHandle() {
        addressNumber = addressCB.getSelectionModel().getSelectedItem();
    }

    @FXML
    public void skipLogin() throws IOException {
        Driver.loginStage.close();
        clip.stop();
        Stages.openGuest();
    }
}
