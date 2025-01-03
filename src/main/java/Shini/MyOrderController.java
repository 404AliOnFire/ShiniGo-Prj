package Shini;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import java.text.SimpleDateFormat;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class MyOrderController implements Initializable {

    @FXML
    private VBox account;

    @FXML
    private VBox cart;

    @FXML
    private GridPane grpane;

    @FXML
    private VBox main;

    @FXML
    private Button marbut;

    @FXML
    private Button onlinebut;

    @FXML
    private VBox order;

    @FXML
    private ScrollPane scpane;

    //LoginController l= new LoginController();
    //CreateController c= new CreateController();
    AnchorPane ap;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String phone="1234567891";
        DatabaseHelper databaseHelper = new DatabaseHelper();
//        if(l.getPhoneTF().getText()==null){
//            phone=c.getPhoneTF().getText();
//        }else {
//            phone=l.getPhoneTF().getText();
//        }
        List<Order> orders = databaseHelper.getOrders(phone);
        int column = 0;
        int row = 0;
        try{
            for(Order order : orders){
                FXMLLoader fx =new FXMLLoader();
                fx.setLocation(getClass().getResource("/Shini/FXML/SingleOrder.fxml"));
                ap=fx.load();
                SingleOController sn= fx.getController();
                sn.setData(order);
                if(column == 1){
                    column = 0;
                    row++;
                }
                grpane.add(ap,column++,row);
                GridPane.setMargin(ap, new Insets(7));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void markethandle(ActionEvent actionEvent) {
        grpane.getChildren().clear();
        marbut.getStyleClass().remove("but2");
        marbut.getStyleClass().add("but1");
        onlinebut.getStyleClass().remove("but1");
        onlinebut.getStyleClass().add("but2");
        String phone="1234567891";
        DatabaseHelper databaseHelper = new DatabaseHelper();
//        if(l.getPhoneTF().getText()==null){
//            phone=c.getPhoneTF().getText();
//        }else {
//            phone=l.getPhoneTF().getText();
//        }
        List<Order> orders = databaseHelper.getOrders1(phone);
        int column = 0;
        int row = 0;
        try{
            for(Order order : orders){
                FXMLLoader fx =new FXMLLoader();
                fx.setLocation(getClass().getResource("/Shini/FXML/SingleOrder.fxml"));
                ap=fx.load();
                SingleOController sn= fx.getController();
                sn.setData(order);
                if(column == 1){
                    column = 0;
                    row++;
                }
                grpane.add(ap,column++,row);
                GridPane.setMargin(ap, new Insets(7));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onlinehandle(ActionEvent actionEvent) {
        grpane.getChildren().clear();
        onlinebut.getStyleClass().remove("but2");
        onlinebut.getStyleClass().add("but1");
        marbut.getStyleClass().remove("but1");
        marbut.getStyleClass().add("but2");
        String phone="1234567891";
        DatabaseHelper databaseHelper = new DatabaseHelper();
//        if(l.getPhoneTF().getText()==null){
//            phone=c.getPhoneTF().getText();
//        }else {
//            phone=l.getPhoneTF().getText();
//        }
        List<Order> orders = databaseHelper.getOrders(phone);
        int column = 0;
        int row = 0;
        try{
            for(Order order : orders){
                FXMLLoader fx =new FXMLLoader();
                fx.setLocation(getClass().getResource("/Shini/FXML/SingleOrder.fxml"));
                ap=fx.load();
                SingleOController sn= fx.getController();
                sn.setData(order);
                if(column == 1){
                    column = 0;
                    row++;
                }
                grpane.add(ap,column++,row);
                GridPane.setMargin(ap, new Insets(7));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
