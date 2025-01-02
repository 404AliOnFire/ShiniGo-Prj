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
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import javax.swing.text.html.StyleSheet;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;



public class SubCategoryControl implements Initializable {
    @FXML
    private VBox account;

    @FXML
    private VBox cart;

    @FXML
    private VBox main;

    @FXML
    private VBox order;

    @FXML
    private HBox subHB;

    @FXML
    private GridPane subCatGrid;

    static int subCategoryCount = 0;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        subCategoryCount = subHB.getChildren().size();

        String css = getClass().getResource("/Shini/Styles/redLine.css").toExternalForm();
        String style = getClass().getResource("/Shini/Styles/style.css").toExternalForm();


        for (int i = 0; i < subCategoryCount; i++) {
            VBox vboxSubCategory = (VBox) subHB.getChildren().get(i);
            vboxSubCategory.getStylesheets().add(css);

            vboxSubCategory.setOnMouseClicked(event -> {
                subCatGrid.getChildren().clear();
                for (int j = 0; j < subCategoryCount; j++) {
                    VBox otherVBox = (VBox) subHB.getChildren().get(j);

                    if (otherVBox == vboxSubCategory) {
                        otherVBox.getStyleClass().clear();
                        otherVBox.getStyleClass().add("vbox-red-line");
                    } else {
                        otherVBox.getStyleClass().clear();
                    }
                }

                // Hanadi
                DatabaseHelper databaseHelper = new DatabaseHelper();
                String subCategoryName = ((Text)vboxSubCategory.getChildren().get(0)).getText();


                List<Product> productsOfSubCategory;
                    productsOfSubCategory= databaseHelper.getProductsOfSubCategory(subCategoryName);

                int column = 0;
                int row = 0;
                try{
                    for(Product product : productsOfSubCategory){

                        FXMLLoader loader = new FXMLLoader();
                        loader.setLocation(getClass().getResource("/Shini/FXML/productCard.fxml"));
                        AnchorPane pane = loader.load();
                        ProductController productController = loader.getController();

                        productController.setData(product);

                        if(column == 2){
                            column = 0;
                            ++row;
                        }
                        subCatGrid.add(pane, column++, row);

                        subCatGrid.setMargin(pane, new Insets(20));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            });
        }


    }
}
