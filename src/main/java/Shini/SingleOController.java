package Shini;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

public class SingleOController{
    @FXML
    private Label arrivallab;

    @FXML
    private Label numofprolab;

    @FXML
    private Label reqdbut;

    @FXML
    private Button statusbut;

    @FXML
    private Label totpricbut;

    private Order order;
    public void setData(Order order){
        this.order=order;
        SimpleDateFormat ss= new SimpleDateFormat("yyyy-MM-dd");
        String s1= ss.format(order.getRequestd());
        numofprolab.setText(Integer.toString(order.getNumofproduct()) + "عنصر");
        reqdbut.setText(s1);
        statusbut.setText(order.getStatus());
        if(order.getStatus().equals("تمت الموافقة") || order.getStatus() == "تمت الموافقة"){
            statusbut.getStyleClass().remove("but3");
            statusbut.getStyleClass().add("but6");
        }else if(order.getStatus().equals("قيد التحضير") || order.getStatus() == "قيد التحضير"){
            statusbut.getStyleClass().add("but3");
        }else{
            statusbut.getStyleClass().remove("but3");
            statusbut.getStyleClass().add("but5");
        }
        totpricbut.setText(Double.toString(order.getTotalprice()) +"₪");
    }
    @FXML
    public void arrddisplay(javafx.event.ActionEvent actionEvent) {
        SimpleDateFormat ss= new SimpleDateFormat("yyyy-MM-dd");
        String s= ss.format(order.getArrivald());
        arrivallab.setText(s);
    }


//    @FXML
//    private void handleb(ActionEvent event){
//        if(order.getStatus().equals("جاهز") || order.getStatus() == "جاهز"){
//            statusbut.setDisable(false);
//        }
//
//    }

}
