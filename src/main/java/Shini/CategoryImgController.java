package Shini;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class CategoryImgController {
    @FXML
    private VBox categoryBox;

    @FXML
    private ImageView categoryImg;

    @FXML
    private Text categoryName;


    public void setData(Category category) {
        categoryImg.setImage(new Image(category.getImagePath()));
        categoryName.setText(category.getName());
    }
}
