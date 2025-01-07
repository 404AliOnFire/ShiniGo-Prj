package Shini;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

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
        DatabaseHelper databaseHelper = new DatabaseHelper();
        List<Category> categories = databaseHelper.getAllCategories();
        int column = 0;
        int row = 0;
        try{
            for(Category category : categories){

                ImageView categoryImg = new ImageView(new Image(category.getImagePath()));
                categoryImg.setFitHeight(150);
                categoryImg.setFitWidth(150);
                Text categoryName = new Text(category.getName());
                categoryName.setTextAlignment(TextAlignment.CENTER);
                categoryName.setFont(Font.font("Cairo SemiBold"));

                VBox vBox = new VBox(5);
                vBox.setAlignment(Pos.CENTER);
                vBox.getChildren().addAll(categoryImg, categoryName);


                if(column == 3){
                    column = 0;
                    ++row;
                }
                categoryGrid.add(vBox, column++, row);
//                GridPane.setMargin(vBox, new Insets(20));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}