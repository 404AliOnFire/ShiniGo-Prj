package Shini;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class UserProfileController implements Initializable {
    @FXML
    private Button backbutton;

    @FXML
    private Button chanpass1button;

    @FXML
    private Button chanpassbutton;

    @FXML
    private Label chanpasslab;

    @FXML
    private Button deleteaccountbutton;

    @FXML
    private Label deleteaccountlab;

    @FXML
    private Button location1button;

    @FXML
    private Button locationbutton;

    @FXML
    private Label locationlab;

    @FXML
    private Label phonenumlab;

    @FXML
    private Button updaitpro1button;

    @FXML
    private Button updaitprobutton;

    @FXML
    private Label updaitprolab;

    @FXML
    private Label usernamelb;

    //change name
    @FXML
    private Button back1button;

    @FXML
    private AnchorPane changenamepane;

    @FXML
    private RadioButton femalerbutton;

    @FXML
    private HBox hbox32;

    @FXML
    private RadioButton malerbutton;

    @FXML
    private TextField namefield;

    @FXML
    private Button savebutton;

    //change location
    @FXML
    private Button back2button;

    @FXML
    private TextField locationf;

    @FXML
    private AnchorPane loctaionnnpane;

    @FXML
    private Button save1button;


    //changepassword
    @FXML
    private Button back11button;

    @FXML
    private AnchorPane changepasswordpane;

    @FXML
    private TextField pass1f;

    @FXML
    private TextField pass2f;

    @FXML
    private TextField pass3f;

    @FXML
    private Button save11button;

    @FXML
    private Label pass4f;

    @FXML
    private Label pass5f;
    @FXML
    private AnchorPane userprofilepane;
    ToggleGroup g = new ToggleGroup();
    FXMLLoader fx ;
    AnchorPane ap;
    //LoginController l= new LoginController();
    //CreateController c= new CreateController();
    String phone="1234567891";
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DatabaseHelper databaseHelper = new DatabaseHelper();
        Customer customer = databaseHelper.getCustomer(phone);
        usernamelb.setText(customer.getName());
        System.out.println(customer.getName());
        phonenumlab.setText(customer.getPhone());
        malerbutton=new RadioButton("ذكر");
        femalerbutton=new RadioButton("انثى");
        malerbutton.setToggleGroup(g);
        femalerbutton.setToggleGroup(g);
    }
    @FXML
    void deleteaccounthandle(ActionEvent event) {
        try {
            ap=fx.load(getClass().getResource("/Shini/FXML/DeleteAccount.fxml"));
            Stage a= new Stage();
            a.setScene(new Scene(ap));
            a.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void updatelocation(ActionEvent event) {
        try {
            ap=fx.load(getClass().getResource("/Shini/FXML/Location.fxml"));
            Stage a= new Stage();
            a.setScene(new Scene(ap));
            a.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void updatenamehandle(ActionEvent event) throws IOException {
        FXMLLoader fx1=new FXMLLoader((getClass().getResource("/Shini/FXML/UpdateProfile.fxml")));
            Scene scene = new Scene(fx1.load());
            Stage a= new Stage();
            a.setScene(scene);
            a.show();

    }

    @FXML
    void updatepasshandle(ActionEvent event) {
        try {
            ap=fx.load(getClass().getResource("/Shini/FXML/ChangePassword.fxml"));
            Stage a= new Stage();
            a.setScene(new Scene(ap));
            a.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //change name
    @FXML
    void backkk(ActionEvent event) {
        try {
            ap=fx.load(getClass().getResource("/Shini/FXML/UserProfile.fxml"));
            Stage a= new Stage();
            a.setScene(new Scene(ap));
            a.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void savechanges(ActionEvent event) {
        malerbutton.setToggleGroup(g);
        femalerbutton.setToggleGroup(g);
        DatabaseHelper databaseHelper = new DatabaseHelper();
        Customer c=databaseHelper.getCustomer(phone);
        if(namefield != null && g.getSelectedToggle() != null ){
                databaseHelper.updatename(namefield.getText(),phone);
                RadioButton rr= (RadioButton) g.getSelectedToggle();
                String rr1=rr.getText();
                System.out.println(rr1);
                if(rr1.equals("ذكر")){
                    databaseHelper.updategender("ذكر",phone);}
                else if (rr1.equals("انثى")) {
                    databaseHelper.updategender("انثى",phone);
                }
        }else if(namefield != null){
                databaseHelper.updatename(namefield.getText(),phone);
        } else if (g.getSelectedToggle() != null) {
            RadioButton rr= (RadioButton) g.getSelectedToggle();
            String rr1=rr.getText();
            System.out.println(rr1);
            if(rr1.equals("ذكر")){
                databaseHelper.updategender("ذكر",phone);}
            else if (rr1.equals("انثى")) {
                databaseHelper.updategender("انثى",phone);
            }
        } else if (pass1f.getText() !=null && pass2f.getText() != null && pass3f.getText() != null) {
            if(c.getPassword().equals(pass1f.getText()) && pass2f.getText().equals(pass3f.getText())){
                databaseHelper.updatepassword(pass2f.getText(),phone);
            } else if (!c.getPassword().equals(pass1f.getText())) {
                pass4f.setText("كلمة السر خاطئة");
            } else if (!pass2f.getText().equals(pass3f.getText())) {
                pass5f.setText("التأكيد غير متطابق");
            }
        }else if(locationf != null){
            databaseHelper.updatelocation(locationf.getText(),phone);
        }
    }
}
