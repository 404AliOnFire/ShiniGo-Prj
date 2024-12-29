package Shini;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
                System.out.println("Helk");
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

                System.out.println(subCategoryName);
                List<Product> productsOfSubCategory = databaseHelper.getProductsOfSubCategory(subCategoryName);

                int column = 0;
                int row = 0;
                try{
//                    for(Product product : productsOfSubCategory){
//
//                        ImageView productImg = new ImageView(new Image(product.getImagePath()));
//                        productImg.setFitHeight(200);
//                        productImg.setFitWidth(200);
//                        Text productDesc = new Text(product.getDescription());
//                        productDesc.setTextAlignment(TextAlignment.RIGHT);
//                        productDesc.setFont(Font.font("Cairo SemiBold", 15));
//
//                        Text isBoycott = new Text();
//                        isBoycott.setTextAlignment(TextAlignment.RIGHT);
//                        isBoycott.setFont(Font.font("Cairo SemiBold"));
//                        isBoycott.setStyle("-fx-text-fill: darkred ");
//                        isBoycott.setFont(Font.font("Cairo SemiBold", 17));
//
//                        if (product.isBoycott())
//                            isBoycott.setText("مقاطع");
//
//                        Text numOfCalories = new Text( product.getCalories()+" cal");
//                        numOfCalories.setTextAlignment(TextAlignment.RIGHT);
//                        numOfCalories.setFont(Font.font("Cairo SemiBold"));
//                        numOfCalories.setFont(Font.font("Cairo SemiBold", 16));
//
//                        Text price = new Text(product.getPrice()+" ILS");
//                        price.setTextAlignment(TextAlignment.RIGHT);
//                        price.setFont(Font.font("Cairo SemiBold", 20));
//
//                        ImageView addIcon = new ImageView(new Image("C:\\Users\\Hanad\\IdeaProjects\\ShiniGo-Prj\\src\\main\\resources\\Shini\\Images\\icons8-add-50.png"));
//                        addIcon.setFitHeight(30);
//                        addIcon.setFitWidth(30);
//
//                        HBox hBox = new HBox(100, price, addIcon);
//
////                        if(product.getOfferID() != null){
////                            Text discountPrice = new Text(product.getPrice()+" ILS");
////                            discountPrice.setTextAlignment(TextAlignment.RIGHT);
////                            discountPrice.setFont(Font.font("Cairo SemiBold", 20));
////                        }
//
//
//
//                        VBox vBox = new VBox(5);
////                        vBox.getStylesheets().add(style);
//                        vBox.setAlignment(Pos.CENTER);
//                        vBox.getChildren().addAll(productImg, productDesc,numOfCalories, isBoycott, hBox);
//
//
//                        if(column == 2){
//                            column = 0;
//                            ++row;
//                        }
//                        subCatGrid.add(vBox, column++, row);
//                        System.out.println("added");
//                        subCatGrid.setMargin(vBox, new Insets(30));
//                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            });
        }


    }
}
