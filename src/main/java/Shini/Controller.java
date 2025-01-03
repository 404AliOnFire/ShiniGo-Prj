package Shini;


import io.github.palexdev.materialfx.controls.MFXRadioButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;


public class Controller implements Initializable {
    static HashMap<Product, Integer>  myCartHash = new HashMap<Product, Integer>();
    static double totalPriceNum = 0;
    static int countOfProducts = 0;
    int indexOfProduct = 0;
    public final String CURRENCY = "₪ ";
    private final HashMap<String, Pane> panes = new HashMap<>();
    private String currentPane = "emptyCart";

    @FXML
    private Text GMUser;

    @FXML
    private VBox account1;

    @FXML
    private MFXTextField addressTextField;

    @FXML
    private ImageView backBt;

    @FXML
    private ImageView backMain;

    @FXML
    private ImageView boldAccount;

    @FXML
    private ImageView boldCart;

    @FXML
    private ImageView boldMain;

    @FXML
    private ImageView boldOrder;

    @FXML
    private HBox cartBox;

    @FXML
    private GridPane categoryGrid;

    @FXML
    private Text categoryName;

    @FXML
    private MFXTextField cityTextField;

    @FXML
    private VBox confirmOrderVBox;

    @FXML
    private Label countProductLabel;

    @FXML
    private Text failedSendOrder;

    @FXML
    private ImageView goodMorningImg;

    @FXML
    private VBox itemsInCartVBox;

    @FXML
    private ImageView lightAccount;

    @FXML
    private ImageView lightCart;

    @FXML
    private ImageView lightMain;

    @FXML
    private ImageView lightOrder;

    @FXML
     VBox listProductVbox;

    @FXML
    private VBox main1;

    @FXML
    private AnchorPane mainScene;

    @FXML
     AnchorPane myCart;

    @FXML
     AnchorPane myEmptyCart;

    @FXML
    private AnchorPane noSearch;

    @FXML
    private ImageView notProductFound;

    @FXML
    private MFXRadioButton onlineButton;

    @FXML
    private VBox order1;

    @FXML
    private GridPane productFoundGrid;

    @FXML
    private HBox searchBackBox;

    @FXML
    private HBox searchBox;

    @FXML
    private HBox searchBox2;

    @FXML
    private TextField searchFeild;

    @FXML
    private ImageView searchIcon;

    @FXML
    private HBox searchLabel;

    @FXML
    private AnchorPane searchScene;

    @FXML
    private MFXRadioButton shopButton;

    @FXML
    private HBox subCategoryBox;

    @FXML
    private GridPane subCategoryGrid;

    @FXML
    private AnchorPane subCategoryScene;

    @FXML
    private Text textID;

    @FXML
    private ToggleGroup toggleButtonsSO;

    @FXML
    private Text totalPrice;

    @FXML
    private Text totalPrice1;

    @FXML
    private ImageView writeToFind;

    private VBox vBox;

    public Stage createAccountStage = new Stage();

    private List<Product> productsOfSubCategory;

    private DatabaseHelper databaseHelper = new DatabaseHelper();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // Ali
        List<Product> productsInCart = DatabaseHelper.getProductsInCart(LoginController.customerId);

        for (Product product : productsInCart) {

            try {
                addProductToCart(product);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        panes.put("cart", myCart);
        panes.put("emptyCart", myEmptyCart);

        // Hanadi
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

    // Ali
    public void showPane(String paneName) {
        if (!paneName.equals(currentPane)) {
            panes.get(currentPane).setVisible(false);
            panes.get(paneName).setVisible(true);
            currentPane = paneName;
        }
    }

    public void addProductToCart(Product product) throws IOException {
        ProductInCart productInCart = new ProductInCart(product, indexOfProduct, this);

        myCartHash.put(product, 1);
        countOfProducts++;
        indexOfProduct++;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Shini/FXML/productInCart.fxml"));
        loader.setController(productInCart);
        HBox hboxProduct = loader.load();

        listProductVbox.getChildren().add(hboxProduct);
        listProductVbox.setPrefHeight(listProductVbox.getPrefHeight() + 100);

        countProductLabel.setText(countOfProducts + "");
        countProductLabel.setVisible(true);


//        this.product = product;
//        int countOfProduct = myCartHash.getOrDefault(product, 1);
//        myCartHash.put(product, countOfProduct + 1);
//        countOfProducts++;
//
//        if(countOfProduct == 1) {
//            productName.setText(product.getName());
//            price.setText(CURRENCY + product.getPrice());
//            productImage.setImage(new Image(product.getImagePath()));
//            listProductVbox.setPrefHeight(listProductVbox.getPrefHeight() + 100);
//            listProductVbox.getChildren().add(productHbox);
//        } else {
//            price.setText(product.getPrice() * countOfProduct + "");
//        }
//        countProductLabel.setText(String.valueOf(countOfProducts));
//        countProductLabel.setVisible(true);
//        totalPriceNum += product.getPrice();
//        totalPrice.setText(CURRENCY + totalPriceNum);
//    }
//
//    public void deleteProductFromCart(Product product) {
//        int countOfProduct = myCartHash.get(product);
//        if(countOfProduct == 1) {
//            myCartHash.remove(product);
//            countOfProducts--;
//            listProductVbox.setPrefHeight(listProductVbox.getPrefHeight()  - 100);
//            listProductVbox.getChildren().remove(productHbox);
//
//            myEmptyCart.setVisible(true);
//            myCart.setVisible(false);
//            countProductLabel.setVisible(false);
//        } else {
//            myCartHash.put(product, countOfProduct - 1);
//        }
//        countProductLabel.setText(String.valueOf(countOfProducts));
//        totalPriceNum -= product.getPrice();
//        totalPrice.setText(CURRENCY + totalPriceNum);
//        if(countOfProducts == 0) {
//            countProductLabel.setVisible(false);
//        }
    }

    public void deleteAllVbox(MouseEvent mouseEvent) {
        listProductVbox.getChildren().clear();
        myCartHash.clear();
        totalPriceNum = 0;
        totalPrice.setText(CURRENCY + 0);
        myCart.setVisible(false);
        myEmptyCart.setVisible(true);
    }

    public void setTotalPrice(double totalPrice){
        totalPriceNum = totalPrice;
        this.totalPrice.setText(CURRENCY + totalPriceNum);
    }
    public static double getTotalPrice(){
        return totalPriceNum;
    }

    @FXML
    void accountUI(MouseEvent event) {
        makeAllLight();
        hideAllBold();
        boldAccount.setVisible(true);

        // showPane("account");
    }

    @FXML
    void cartUI(MouseEvent event) {
        makeAllLight();
        hideAllBold();
        boldCart.setVisible(true);

        showPane("cart");
    }

    @FXML
    void mainUI(MouseEvent event) {
        makeAllLight();
        hideAllBold();
        boldMain.setVisible(true);

        //showPane("main");
    }

    @FXML
    void orderUI(MouseEvent event) {
        makeAllLight();
        hideAllBold();
        boldOrder.setVisible(true);

        //showPane("order");
    }

    @FXML
    void confirmButton(ActionEvent event) {
        itemsInCartVBox.setVisible(false);
        confirmOrderVBox.setVisible(true);
        totalPrice1.setText(totalPrice.getText());
        textID.setText("تاكيد الطلب");
    }

    @FXML
    void sendOrder(MouseEvent event) {
        //check
        if(!toggleButtonsSO.getSelectedToggle().isSelected()
                || cityTextField.getText().isEmpty()
                || addressTextField.getText().isEmpty()){
            failedSendOrder.setText("الرجاء إملاء البيانات");
            failedSendOrder.setVisible(true);
            return;
        }
        boolean online = false;
        online = toggleButtonsSO.getSelectedToggle().equals(onlineButton);

        String city = cityTextField.getText();
        String address = addressTextField.getText();
        String receiving = online ? "اونلاين" : "المتجر";
        Date requestDate = new Date(System.currentTimeMillis()); // Initialize request date at the button press
        Date arrivalDate = calculateArrivalDate(requestDate); // Calculate arrival date (optional logic)
        String status = "تمت الموافقة"; // Example status


        DatabaseHelper.sendOrder(requestDate, arrivalDate, status, getTotalPrice(), city + ", " + address, countOfProducts, receiving, null, LoginController.customerId);

    }

    void makeAllLight() {
        lightAccount.setVisible(true);
        lightCart.setVisible(true);
        lightMain.setVisible(true);
        lightOrder.setVisible(true);
    }

    void hideAllBold() {
        boldAccount.setVisible(false);
        boldCart.setVisible(false);
        boldMain.setVisible(false);
        boldOrder.setVisible(false);
    }

    private static Date calculateArrivalDate(Date requestDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(requestDate);
        calendar.add(Calendar.DAY_OF_MONTH, 3); // Add 3 days
        return new Date(calendar.getTimeInMillis());
    }



    // Hanadi
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




    public void CreateAccountUI() throws IOException {
        // Implement logic to create account UI
        Driver.loginStage.close();

        // Delete the Fonts Codes imported, because they are useless here
        // _____________________________________________________________
        FXMLLoader fxmlLoader = new FXMLLoader(Driver.class.getResource("/Shini/FXML/CreateAccount.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 606, 600);
        createAccountStage.setTitle("Create Account");
        createAccountStage.setScene(scene);
        createAccountStage.show();
    }

    public void BackToLoginText() {
        returnToLogin();
    }

    public void BackToLoginArrow() {
        returnToLogin();
    }
    public void returnToLogin(){
        createAccountStage.close();
        Driver.loginStage.show();
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


}
