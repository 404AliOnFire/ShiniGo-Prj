package Shini;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.ResourceBundle;

public class CategoryController implements Initializable {

    @FXML
    private  GridPane  categoryGrid;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try{
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Shini/FXML/CategoryImage.fxml"));
//            AnchorPane anchorPane =  loader.load();
//            VBox vBox = (VBox) anchorPane.getChildren().get(0);
//            ImageView img = (ImageView) vBox.getChildren().get(0);
//            Text txtName = (Text) vBox.getChildren().get(1);
//            img.setImage(new Image("https://raw.githubusercontent.com/404AliOnFire/ShiniGo-Prj/2627b7b35ac0f27ff46c6b57c0ff427074bb4faf/src/main/resources/Shini/Images/%20(2).jpeg"));
//            txtName.setText("ألبان وأجبان");


            DatabaseHelper databaseHelper = new DatabaseHelper();
            List<Category> categories = databaseHelper.getAllCategories();

            int column = 0;
            int row = 0;

            for(Category category : categories){
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Shini/FXML/CategoryImage.fxml"));
                AnchorPane anchorPane =  loader.load();
                VBox vBox = (VBox) anchorPane.getChildren().get(0);
                ImageView img = (ImageView) vBox.getChildren().get(0);
                Text txtName = (Text) vBox.getChildren().get(1);

                img.setImage(new Image(category.getImagePath()));
                txtName.setText(category.getName());

                categoryGrid.add(vBox, column, row);

                column++;
                if(column == 3){
                    column = 0;
                    row++;
                }
            }





        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
