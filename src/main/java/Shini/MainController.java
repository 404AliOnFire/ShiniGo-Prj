package Shini;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    HashMap<Product, Integer> myCartHash = new HashMap<Product, Integer>();
    double totalPriceNum = 0;
    int countOfProducts = 0;
    int indexOfProduct = 0;
    public final String CURRENCY = "₪ ";

    @FXML
    private VBox account;

    @FXML
    private VBox account1;

    @FXML
    private VBox listProductVbox;

    @FXML
    private VBox cart;

    @FXML
    private VBox cart1;

//    @FXML
//    private VBox main;
//
//    @FXML
//    private VBox main1;

    @FXML
    private Text numberOfProduct;

    @FXML
    private VBox order;

    @FXML
    private VBox order1;

    @FXML
    private Text price;

    @FXML
    private HBox productHbox;

    @FXML
    private Text productName;

    @FXML
    private Text totalPrice;

    @FXML
    private ImageView productImage;

    @FXML
    private Label countProductLabel;

    @FXML
    private AnchorPane myCart;

    @FXML
    private AnchorPane myEmptyCart;

    @FXML
    private AnchorPane mainScPane;


    @FXML
    private HBox searchBackBox;

    @FXML
    private HBox searchBox;

    @FXML
    private HBox cartBox;

    private VBox vBox;

    @FXML
    private GridPane categoryGrid;

    @FXML
    private Text categoryName;

    @FXML
    private Text GMUser;

    @FXML
    private AnchorPane mainScene;

    @FXML
    private AnchorPane subCategoryScene;

    @FXML
    private ImageView goodMorningImg;


    @FXML
    private HBox subCategoryBox;

    @FXML
    private GridPane subCategoryGrid;

    private List<Product> productsOfSubCategory;

    private DatabaseHelper databaseHelper = new DatabaseHelper();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        myEmptyCart.setVisible(false);
        myCart.setVisible(false);


        List<Category> categories = databaseHelper.getAllCategories();
        int column = 0;
        int row = 0;
        try{
            for(Category category : categories){

                ImageView categoryImg = new ImageView(new Image(category.getImagePath()));
                categoryImg.setFitHeight(150);
                categoryImg.setFitWidth(150);
                Text categName = new Text(category.getName());
                categName.setTextAlignment(TextAlignment.CENTER);
                categName.setFont(Font.font("Cairo SemiBold"));

                vBox = new VBox(5);
                vBox.setAlignment(Pos.CENTER);
                vBox.getChildren().addAll(categoryImg, categName);


                categoryImg.setOnMouseClicked(event -> handleImageClick(event, category.getName()));



                if(column == 3){
                    column = 0;
                    ++row;
                }
                categoryGrid.add(vBox, column++, row);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void handleImageClick(MouseEvent event, String categName){
        System.out.println(categName);
        categoryName.setText(categName);

        List<String> subCategoriesName = databaseHelper.getSubCategories(categName);

        searchBox.setVisible(false);
        cartBox.setVisible(false);
        mainScene.setVisible(false);
        GMUser.setVisible(false);
        categoryGrid.setVisible(false);
        goodMorningImg.setVisible(false);
        searchBackBox.setVisible(true);
        subCategoryScene.setVisible(true);
        mainScene.setVisible(false);

        String css = getClass().getResource("/Shini/Styles/redLine.css").toExternalForm();
        String style = getClass().getResource("/Shini/Styles/style.css").toExternalForm();

        subCategoryBox.getChildren().clear();

        Text text = new Text();

        VBox vbox2 = new VBox();
        vbox2.getStylesheets().add(css);
        vbox2.setStyle("-fx-background-color:  #ececec");
        vbox2.setAlignment(Pos.CENTER);


        subCategoryBox.setSpacing(10);

        for(int i =0; i <  subCategoriesName.size(); i++){
            text = new Text(subCategoriesName.get(i));
            text.setFont(Font.font("Cairo", FontWeight.BOLD, 20));
            System.out.println(text);
            vbox2 = new VBox(text);
            subCategoryBox.getChildren().add(vbox2);
        }

        text = new Text("الجميع");
        text.setFont(Font.font("Cairo", FontWeight.BOLD, 20));
        vbox2 = new VBox(text);
        subCategoryBox.getChildren().add(vbox2);

        DatabaseHelper databaseHelper = new DatabaseHelper();
             productsOfSubCategory = databaseHelper.getAllProductsOfSubCategory(categName);
            loadProduct(productsOfSubCategory);

        for (int i = 0; i < (subCategoriesName.size() + 1); i++) {
            VBox vboxSubCategory = (VBox) subCategoryBox.getChildren().get(i);
            vboxSubCategory.getStylesheets().add(css);




            vboxSubCategory.setOnMouseClicked(e -> {
                subCategoryGrid.getChildren().clear();
                for (int j = 0; j < (subCategoriesName.size() + 1); j++) {
                    VBox otherVBox = (VBox) subCategoryBox.getChildren().get(j);

                    if (otherVBox == vboxSubCategory) {
                        otherVBox.getStyleClass().clear();
                        otherVBox.getStyleClass().add("vbox-red-line");
                    } else {
                        otherVBox.getStyleClass().clear();
                    }
                }

                // Hanadi

                String subCategoryName = ((Text)vboxSubCategory.getChildren().get(0)).getText();


                if(subCategoryName.equals("الجميع"))
                    productsOfSubCategory = databaseHelper.getAllProductsOfSubCategory(categName);

                else{
                    productsOfSubCategory= databaseHelper.getProductsOfSubCategory(subCategoryName);
                }


                loadProduct(productsOfSubCategory);




            });
        }


    }

    private void loadProduct(List<Product> productsOfSubCategory) {
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
                subCategoryGrid.add(pane, column++, row);

                subCategoryGrid.setMargin(pane, new Insets(20));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void addProducts() {


    }

    @FXML
    void backToMain(MouseEvent event) {
        searchBox.setVisible(true);
        cartBox.setVisible(false);
        mainScene.setVisible(true);
        GMUser.setVisible(true);
        categoryGrid.setVisible(true);
        goodMorningImg.setVisible(true);
        subCategoryScene.setVisible(false);
        mainScene.setVisible(true);
        searchBackBox.setVisible(false);

    }

    public void deleteAllVbox(MouseEvent mouseEvent) {
        listProductVbox.getChildren().clear();
        myCartHash.clear();
        totalPriceNum = 0;
        totalPrice.setText(CURRENCY + 0);

    }

    public void addButton(ActionEvent actionEvent) {
        numberOfProduct.setText(String.valueOf(Integer.parseInt(numberOfProduct.toString()) + 1));
        // addProductToCart(product);
    }

    public void subButton(ActionEvent actionEvent) {
        // deleteProductFromCart(product);
    }

    public void confirmButton(ActionEvent actionEvent) {
    }

//    @FXML
//    void showSubCategoryField(MouseEvent event) {
//
//        System.out.println("Hiiiiiiiiiiiiiiiiiiii");
//    }





}
