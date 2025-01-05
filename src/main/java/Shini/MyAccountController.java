package Shini;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class MyAccountController {
    @FXML
    private VBox account;

    @FXML
    private AnchorPane ancppan;

    @FXML
    private Button brn1button;

    @FXML
    private Button brnbutton;

    @FXML
    private Label brnlab;

    @FXML
    private VBox cart;

    @FXML
    private Button hel1button;

    @FXML
    private Button helbutton;

    @FXML
    private Label hellab;

    @FXML
    private Button logoutbutton;

    @FXML
    private Label logoutlab;

    @FXML
    private Button mag1button;

    @FXML
    private Button magbutton;

    @FXML
    private Label maglab;

    @FXML
    private VBox main;

    @FXML
    private HBox myacchb1;

    @FXML
    private HBox myacchb2;

    @FXML
    private HBox myacchb3;

    @FXML
    private HBox myacchb4;

    @FXML
    private HBox myacchb5;

    @FXML
    private HBox myacchb6;

    @FXML
    private HBox myacchb7;

    @FXML
    private HBox myacchb8;

    @FXML
    private HBox myacchb9;

    @FXML
    private Pane myaccpane;

    @FXML
    private VBox myacv;

    @FXML
    private Button myex1button;

    @FXML
    private Button myexbutton;

    @FXML
    private Label myexlab;

    @FXML
    private VBox order;

    @FXML
    private Button pri1button;

    @FXML
    private Button pributton;

    @FXML
    private Label prilab;

    @FXML
    private Button tec1button;

    @FXML
    private Button tecbutton;

    @FXML
    private Label teclab;

    @FXML
    private Button ter1button;

    @FXML
    private Button terbutton;

    @FXML
    private Label terlab;

    @FXML
    private Button user1lab;

    @FXML
    private Button userbutton;

    @FXML
    private Label userlab;

    FXMLLoader fx =new FXMLLoader();
    Parent ap;
    public void useracchandle(ActionEvent actionEvent) {
        try {
            ap=fx.load(getClass().getResource("/Shini/FXML/UserProfile.fxml"));
            Stage a= new Stage();
            a.setScene(new Scene(ap));
            a.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void myexacchandle(ActionEvent actionEvent) {
        try {
            ap=fx.load(getClass().getResource("/Shini/FXML/MyExtra.fxml"));
            Stage a= new Stage();
            a.setScene(new Scene(ap));
            a.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void magacchandle(ActionEvent actionEvent) {
        try {
            ap=fx.load(getClass().getResource("/Shini/FXML/Magazine.fxml"));
            Stage a= new Stage();
            a.setScene(new Scene(ap));
            a.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void brnacchandle(ActionEvent actionEvent) {
        //fx.setLocation(getClass().getResource("/Shini/FXML/Magazine.fxml"));
    }

    public void teracchandle(ActionEvent actionEvent) {
        try {
            ap=fx.load(getClass().getResource("/Shini/FXML/Terms.fxml"));
            Stage a= new Stage();
            a.setScene(new Scene(ap));
            a.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void priacchandle(ActionEvent actionEvent) {
        try {
            ap=fx.load(getClass().getResource("/Shini/FXML/Privacy.fxml"));
            Stage a= new Stage();
            a.setScene(new Scene(ap));
            a.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void helacchandle(ActionEvent actionEvent) {
        try {
            ap=fx.load(getClass().getResource("/Shini/FXML/helpcenter.fxml"));
            Stage a= new Stage();
            a.setScene(new Scene(ap));
            a.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void tecacchandle(ActionEvent actionEvent) {
        try {
            ap=fx.load(getClass().getResource("/Shini/FXML/TechnicalSupport.fxml"));
            Stage a= new Stage();
            a.setScene(new Scene(ap));
            a.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void logoutacchandle(ActionEvent actionEvent) {
        try {
            FXMLLoader fx1 = new FXMLLoader(getClass().getResource("/Shini/FXML/LoginUI.fxml"));
            Scene scene = new Scene(fx1.load());
            Stage a= new Stage();
            a.setScene(scene);
            a.show();

    } catch (IOException e) {
        throw new RuntimeException(e);
    }
    }
}
