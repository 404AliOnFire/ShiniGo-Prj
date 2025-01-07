package Shini;


import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXRadioButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
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
import java.sql.SQLOutput;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import io.github.palexdev.materialfx.controls.MFXScrollPane;

public class Controller implements Initializable {
    static HashMap<Product, Integer>  myCartHash = new HashMap<Product, Integer>();
    static double totalPriceNum = 0;
    static int countOfProducts = 0;
    int indexOfProduct = 0;
    public final String CURRENCY = "₪ ";
    private final HashMap<String, Pane> panes = new HashMap<>();
    private String currentPane = "main";

    @FXML
    private Text GMUser;

    @FXML
    private AnchorPane topAnchorPane;


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

    @FXML
    private AnchorPane cart1;

    @FXML
    private VBox searchProduct;

    @FXML
    private HBox goodMorningBox;

    private VBox vBox;
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

    @FXML // afnan
    private HBox orderBox;

    @FXML
    private HBox cartLabel;

    @FXML // afnan
    private HBox myacouBox;

    @FXML
    private AnchorPane ordarpane;

    @FXML
    private ScrollPane scpane;
    @FXML
    private Button brn1button;

    @FXML
    private Button brnbutton;

    @FXML
    private Label brnlab;

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
    private AnchorPane myaccountpane;

    @FXML
    private Button myex1button;

    @FXML
    private Button myexbutton;

    @FXML
    private Label myexlab;

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
    private Button backbutton;

    @FXML
    private Button chanpass1button;

    @FXML
    private Button chanpassbutton;

    @FXML
    private Label chanpasslab;

    @FXML
    private Button deleteaccountbutton;

    @FXML
    private Label deleteaccountlab;

    @FXML
    private Button location1button;

    @FXML
    private Button locationbutton;

    @FXML
    private Label locationlab;

    @FXML
    private Label phonenumlab;

    @FXML
    private Button updaitpro1button;

    @FXML
    private Button updaitprobutton;

    @FXML
    private Label updaitprolab;

    @FXML
    private Label usernamelb;

    @FXML
    private AnchorPane userprofilepane;

    @FXML
    private Label userlab;

    @FXML
    private Button back1button;

    @FXML
    private AnchorPane myextrapane;
    @FXML
    private AnchorPane magazinepane;

    @FXML
    private Button backb;

    @FXML
    private GridPane branchgrid;

    @FXML
    private AnchorPane branchpane;

    @FXML
    private MFXScrollPane branchsc;

    @FXML
    private Button back6button;

    @FXML
    private AnchorPane termspane;

    @FXML
    private TextArea texta;

    @FXML
    private Button backbutton9;

    @FXML
    private AnchorPane privacypane;

    @FXML
    private TextArea texta2;

    @FXML
    private Button backbutton10;

    @FXML
    private AnchorPane helppane;

    @FXML
    private TextArea texta3;

    @FXML
    private Button backbutton11;

    @FXML
    private AnchorPane tecpane;
    ToggleGroup g = new ToggleGroup();
    @FXML
    private Button back11button;

    @FXML
    private AnchorPane changenamepane;

    @FXML
    private RadioButton femalerbutton;

    @FXML
    private HBox hbox32;

    @FXML
    private RadioButton malerbutton;

    @FXML
    private TextField namefield;

    @FXML
    private Button savebutton;
    @FXML
    private Button back21button;

    @FXML
    private TextField locationf;

    @FXML
    private AnchorPane loctaionnnpane;

    @FXML
    private Button save1button;

    @FXML
    private Button back31button;

    @FXML
    private AnchorPane changepasswordpane;

    @FXML
    private MFXPasswordField pass1f;

    @FXML
    private MFXPasswordField pass2f;

    @FXML
    private MFXPasswordField pass3f;

    @FXML
    private Label pass4f;

    @FXML
    private Label pass5f;
    @FXML
    private Button cancle;

    @FXML
    private Button confirm7;

    @FXML
    private AnchorPane deletepane;

    @FXML
    private Button save11button;

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
        //afnan
        initializeMyOrder();

        panes.put("main", mainScene);
        panes.put("cart", myCart);
        panes.put("emptyCart", myEmptyCart);
        panes.put("myorders", ordarpane);//afnan
        panes.put("search",searchScene);
        panes.put("myaccount",myaccountpane);
        panes.put("noSearch",noSearch);
        panes.put("subCategory", subCategoryScene);
        panes.put("userprofile",userprofilepane);
        panes.put("myextra",myextrapane);
        panes.put("magazine",magazinepane);
        panes.put("branches",branchpane);
        panes.put("termspane",termspane);
        panes.put("privacpane",privacypane);
        panes.put("helpane",helppane);
        panes.put("tepane",tecpane);
        panes.put("changename",changenamepane);
        panes.put("locationpane",loctaionnnpane);
        panes.put("passwordpane",changepasswordpane);
        panes.put("deletepan",deletepane);
        // Hanadi
        List<Category> categories = databaseHelper.getAllCategories();
        int column = 0;
        int row = 0;
        try{
            for(Category category : categories){
//                URL imageUrl = getClass().getClassLoader().getResource(category.getImagePath());
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
        showHbox("myacouBox");
        showPane("myaccount");
    }

    @FXML
    void cartUI(MouseEvent event) {
        makeAllLight();
        hideAllBold();
        boldCart.setVisible(true);

        showPane("cart");
        showHbox("cartBox");
    }

    private void showHbox(String hbox) {
        for (Node node : topAnchorPane.getChildren()) {
            node.setVisible(node.getId().equals(hbox));
        }
    }

    @FXML
    void mainUI(MouseEvent event) {
        makeAllLight();
        hideAllBold();
        boldMain.setVisible(true);

        showPane("main");
        showHbox("searchVbox");
        showHbox("goodMorningBox");
    }

    @FXML
    void orderUI(MouseEvent event) {
        makeAllLight();
        hideAllBold();
        boldOrder.setVisible(true);
        showHbox("orderBox");
        showPane("myorders");
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
        categoryName.setText(categName);
        List<String> subCategoriesName = databaseHelper.getSubCategories(categName);
        showPane("subCategory");
        showHbox("searchBackBox");
//        searchBox.setVisible(false);
//        cartBox.setVisible(false);
//        mainScene.setVisible(false);
//        GMUser.setVisible(false);
//        categoryGrid.setVisible(false);
//        goodMorningImg.setVisible(false);
//        searchBackBox.setVisible(true);
//        subCategoryScene.setVisible(true);
//        mainScene.setVisible(false);

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

        productsOfSubCategory = databaseHelper.getAllProductsOfCategory(categName);
        loadProduct(productsOfSubCategory, subCategoryGrid);

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
                    productsOfSubCategory = databaseHelper.getAllProductsOfCategory(categName);

                else{
                    productsOfSubCategory= databaseHelper.getProductsOfSubCategory(subCategoryName);
                }
                loadProduct(productsOfSubCategory, subCategoryGrid);
            });
        }
    }

    private void loadProduct(List<Product> productsOfSubCategory, GridPane subCategoryGrid) {
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

                subCategoryGrid.setMargin(pane, new Insets(10));
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
        showPane("main");
        showHbox("goodMorningBox");
//        showHbox("searchBox");
        searchBox.setVisible(true);
//        cartBox.setVisible(false);
//        mainScene.setVisible(true);
//        GMUser.setVisible(true);
//        categoryGrid.setVisible(true);
//        goodMorningImg.setVisible(true);
//        subCategoryScene.setVisible(false);
//        mainScene.setVisible(true);
//        searchBackBox.setVisible(false);

    }

    @FXML
    void searchOnProduct(MouseEvent event) {
        searchFeild.clear();
        showPane("noSearch");
        writeToFind.setVisible(true);
        searchProduct.setVisible(true);
        searchBox.setVisible(false);
        goodMorningBox.setVisible(false);
        searchBackBox.setVisible(false);

//        showHbox("searchLabel");

    }

    @FXML
    void searchingOn(ActionEvent event) {
        productFoundGrid.getChildren().clear();
        List<Product> products =  databaseHelper.getWantedProduct(searchFeild.getText().trim());
        if(products.size() > 0){
            showPane("search");
            loadProduct(products, productFoundGrid);
        }else{
            showPane("noSearch");
            writeToFind.setVisible(false);
            notProductFound.setVisible(true);
        }

    }
//(Afnan Work)
    AnchorPane ap;
    public void markethandle(ActionEvent actionEvent) { // for market button in orderpane (Afnan)
        grpane.getChildren().clear();
        marbut.getStyleClass().remove("but2");
        marbut.getStyleClass().add("but1");
        onlinebut.getStyleClass().remove("but1");
        onlinebut.getStyleClass().add("but2");
        String phone = "1234567891";
        DatabaseHelper databaseHelper = new DatabaseHelper();
//        if(l.getPhoneTF().getText()==null){
//            phone=c.getPhoneTF().getText();
//        }else {
//            phone=l.getPhoneTF().getText();
//        }
        List<Order> orders = databaseHelper.getOrders1(phone);
        int column = 0;
        int row = 0;
        try {
            for (Order order : orders) {
                FXMLLoader fx = new FXMLLoader();
                fx.setLocation(getClass().getResource("/Shini/FXML/SingleOrder.fxml"));
                ap = fx.load();
                SingleOController sn = fx.getController();
                sn.setData(order);
                if (column == 1) {
                    column = 0;
                    row++;
                }
                grpane.add(ap, column++, row);
                GridPane.setMargin(ap, new Insets(7));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onlinehandle(ActionEvent actionEvent) {// for online button in orderpane (Afnan)
        grpane.getChildren().clear();
        onlinebut.getStyleClass().remove("but2");
        onlinebut.getStyleClass().add("but1");
        marbut.getStyleClass().remove("but1");
        marbut.getStyleClass().add("but2");
        String phone = "1234567891";
        DatabaseHelper databaseHelper = new DatabaseHelper();
//        if(l.getPhoneTF().getText()==null){
//            phone=c.getPhoneTF().getText();
//        }else {
//            phone=l.getPhoneTF().getText();
//        }
        List<Order> orders = databaseHelper.getOrders(phone);
        int column = 0;
        int row = 0;
        try {
            for (Order order : orders) {
                FXMLLoader fx = new FXMLLoader();
                fx.setLocation(getClass().getResource("/Shini/FXML/SingleOrder.fxml"));
                ap = fx.load();
                SingleOController sn = fx.getController();
                sn.setData(order);
                if (column == 1) {
                    column = 0;
                    row++;
                }
                grpane.add(ap, column++, row);
                GridPane.setMargin(ap, new Insets(7));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void initializeMyOrder() {
        String phone = "1234567891";
        DatabaseHelper databaseHelper = new DatabaseHelper();
//        if(l.getPhoneTF().getText()==null){
//            phone=c.getPhoneTF().getText();
//        }else {
//            phone=l.getPhoneTF().getText();
//        }
        List<Order> orders = databaseHelper.getOrders(phone);
        int column = 0;
        int row = 0;
        try {
            for (Order order : orders) {
                FXMLLoader fx = new FXMLLoader();
                fx.setLocation(getClass().getResource("/Shini/FXML/SingleOrder.fxml"));
                ap = fx.load();
                SingleOController sn = fx.getController();
                sn.setData(order);
                if (column == 1) {
                    column = 0;
                    row++;
                }
                grpane.add(ap, column++, row);
                GridPane.setMargin(ap, new Insets(7));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    void brnacchandle(ActionEvent event) {
        showPane("branches");
        cartBox.setVisible(false);
        myacouBox.setVisible(false);
        orderBox.setVisible(false);
        goodMorningBox.setVisible(false);
        searchBox.setVisible(false);
        searchBackBox.setVisible(false);
        searchProduct.setVisible(false);
        cartLabel.setVisible(false);
        initializebranch();
    }

    @FXML
    void helacchandle(ActionEvent event) {
        showPane("helpane");
        cartBox.setVisible(false);
        myacouBox.setVisible(false);
        orderBox.setVisible(false);
        goodMorningBox.setVisible(false);
        searchBox.setVisible(false);
        searchBackBox.setVisible(false);
        searchProduct.setVisible(false);
        cartLabel.setVisible(false);
        texta3.setText("1 - تطبيق الشني آون لاين\n" +
                "المنتجات المتاجة واش الطلبية وتوص بالوست المطلوب لنلية كافة\n" +
                "احتياجات المنزل.\n" +
                "قسم الأون لا يبيرة بجامنب شركة غرغور التجارية ماديت الشفي ومقردة\n" +
                "2 - إنشاء طلبية\n" +
                "تسجيل الدخول من خلال التطبيق باسمك ورقمك لتصلك رسالة تفعيل تتيح لك تصفح المنتجات المتاحة بطريقة سهلة وومتعة -تجميعها في سلتك وتأكيدها ليتم تواصل أحد موظفينا وتأكيد الطلبية وإيصالها في الوقت المطلوب.\n" +
                "3 - سياسة التعديل على الطلبية بعد تأكيدها.\n" +
                "حيث ان هذا الخيار غير متوفر على التطبيق حاليا، ف بإمكانك إجراء التعديل المطلوب على طلبيتك بعد 40 دقيقة من طلبها من خلال الاتصال هاتفيا على الرقم 0562200539\n" +
                "4 - سياسة التعديل على الطلبية بعد تأكيدها\n" +
                "يتم تسليم الطلبية للعنوان المسجل لدينا - وبالإمكان استلامها من قبل أي شخص اخر يتم تسجيل اسمه في خانة الملاحظات بعد تاكيد الطلبية\n" +
                "5 - سياسة الإرجاع والتبديل\n" +
                "يتي حال تم توصيل صنف مختلف على ان يكون المنتج غير مفتوح وغيرو\n" +
                "مستخدم وبغلافه الاصلي. وذلك خلال ساعتين من استلام الطلبية .\n" +
                "6 - سياسة الأصناف غير المتوفرة على التطبيق\n" +
                "بإمكانك اقتراح أي صنف تم البحث عنه ولم تظهر لك نتائج ليتم العمل على إضافته لقائمتنا بأقرب وقت ممكن\n" +
                "7 - سياسة إلغاء الحساب\n" +
                "في حال الرغبة بإلغاء الحساب يرجى تواصل مع دعم الفني عن طريق الضغط على أيقونة الدعم الفني في واجهة \" حسابي\n" +
                "8 - الأسعار على التطبيق والأسعار داخل الفرع\n" +
                "اسعار كافة الاصناف داخل التطبيق وداخل الفرع ثابتة وسياسة الطلب والتوصيل تتيح لك المجال للاستفادة من كافة العروض المتاحة داخل الفرع في حال تم الاعلان عن شمولية العرض ب تطبيق شني اون لاين.\n" +
                "9 - الحد الأدنى لقيمة الطلبية\n" +
                "100 شيقل هو الحد الأدنى للطلب والتوصيل ضمن السياسة الموضحة\n" +
                "10 - رسوم التوصيل\n" +
                "رسوم التوصل الخاص بمناطق رام اللّٰه و البيرة إبداء من 12 شيقل، أما رسوم التوصيل لمناطق العيزرية وابوديس إبداءً من 10 شيقل.\n" +
                "11 - مدة توصيل الطلبية\n" +
                "تتراوح مدة توصيل الطلبية من ساعتين إلى 3 ساعات\n" +
                "12 - التوصيل السريع\n" +
                "مدة التوصيل السريع \" ساعة - ساعة ونصف \" برسوم قيمتها \"15\"\n" +
                "13 - المناطق المتاحة والتابعة لسياسة التوصيل\n" +
                "المعتمدة\n" +
                "- ينارع القدس عين منجد - بيتونيا - بطن الحهوا بلرام اللّٰه التحتا -\n" +
                "الصاصيون\n" +
                "- آم الشرايط - وسط المدينة - الطيرة - كفر عقب\n" +
                "را الستان المشروالع العيزريو ديس - السوادرة - الشيخ سعد - الإسكان -\n" +
                "14 - التوصيل للمناطق غير المتاحة على خارطتنا\n" +
                "الاستلام من أحد موظفينا الرام- سردا- أبوقش - رزيتل - الجريد موعد\n" +
                "الدبلوماسي - الريحان\n" +
                "15 - ساعات العمل للتوصيل\n" +
                "من الساما صاح حتى الساحة للسام لتم تاكيدها من خلال أحد موظهينا\n" +
                "16 - طرق الدفع المتاحة\n" +
                "-الدفع النقدي \" كاش \"\n" +
                "-الدفع عن طريق الفيزا الالكترونية.\n" +
                "17 - طرق استلام الطلبية المتاحة\n" +
                "استلام الطلبية من خلال التوصيل للعنوان المدرج في التطبيق أو الاستلام عن داخل فرع الأون لاين الموضح عنوانه\n" +
                "18 - الطلبيات كبيرة الحجم\n" +
                "بإمكانك الآن إنشاء طلبية كبيرة الحجم بالكميات المطلوبة ليتم تواصل أحد موظفينا وتاكيد الطلبية وتحديد موعد مناسب لاستلامها");
    }

    @FXML
    void logoutacchandle(ActionEvent event) {

    }

    @FXML
    void magacchandle(ActionEvent event) {
        showPane("magazine");
        cartBox.setVisible(false);
        myacouBox.setVisible(false);
        orderBox.setVisible(false);
        goodMorningBox.setVisible(false);
        searchBox.setVisible(false);
        searchBackBox.setVisible(false);
        searchProduct.setVisible(false);
        cartLabel.setVisible(false);
    }

    @FXML
    void myexacchandle(ActionEvent event) {
        showPane("myextra");
        cartBox.setVisible(false);
        myacouBox.setVisible(false);
        orderBox.setVisible(false);
        goodMorningBox.setVisible(false);
        searchBox.setVisible(false);
        searchBackBox.setVisible(false);
        searchProduct.setVisible(false);
        cartLabel.setVisible(false);

    }

    @FXML
    void priacchandle(ActionEvent event) {
        showPane("privacpane");
        cartBox.setVisible(false);
        myacouBox.setVisible(false);
        orderBox.setVisible(false);
        goodMorningBox.setVisible(false);
        searchBox.setVisible(false);
        searchBackBox.setVisible(false);
        searchProduct.setVisible(false);
        cartLabel.setVisible(false);
        texta2.setText("معلوماتك الشخصية\n" +
                "المسجلة لدينا\n" +
                "بمجرد تحميلك للتطبيق، فإنك تسمح ل\n" +
                "\"الشني أون لاين \"بالتواصل معك باستخدام عنو ان بريدك\n" +
                "الإلكتروني وأرقام الهاتف وغيرها من البيانات\n" +
                "الشخصية المماثلة.\n" +
                "يمع معاملات وستعد الألبيانات التي سعة تعامل\n" +
                "مسبقاً باستخدامها ، وبتحميلك\n" +
                "للتطبيق - فذلك يعني قبولك لتلك السياسة نقوم باستخدام برامج حديثة لمتابعة و تحليل حركة مرورك على الموقع من أجل تقديم خدمات إعلانية وترويجية ، أطر افتحارجية تلكن بدون العملا عن حويشا العملاء مم أو\n" +
                "المستخدمين. حماية البيانات\n" +
                "عتبر حماية البيانات من أولويتنا القصوى، لذلك تخذ جميع الخطوات الاحترازية لحماية بياناتك وخصوصيتك، حيث تتم حماية معلوماتك وعمليات الدفع\n" +
                "المحتملة وفقًا\n" +
                "لأعلى مستوى ممكن من الأمان عبر الإنترنت، ونسعى دائماً إلى استخدام أفضل الطرق والأساليب لضمان أمان شامل وكامل لجميع التفاصيل والبيانات\n" +
                "الحساسة الخاصة بك.\n" +
                "حقوقك في التصرف\n" +
                "بالبيانات والمعلومات\n" +
                "المسجلة لدينا\n" +
                "في أي وقت، يمكنك الانسحاب من الاشتراك بالتطبيق، بالإضافة إلى امكانية الحصول على معلومات حول أي مما يلي عن طريق ترك رسالة على عنوان بريدنا\n" +
                "الإلكتروني :\n" +
                "• ماهية البيانات التي لدينا عنك\n" +
                ": طلب حلف أي بيانات لدينا عنذل\n" +
                "• إبلاغنا بأي مخاوف لديك او مقترحات\n" +
                "بشأن محتوى التطبيق\n" +
                "الإشعارات الإعلانية\n" +
                "والتسويقية\n" +
                "قد نطلب جمع معلومات شخصية (مثل الاسم والعمر وغيرها) عن طريق الاستبيانات والمسابقات، نقوم جمع تلك\n" +
                "المعلومات واستخدامها لتحسين محتوي\n" +
                "الإعالام تاك الإشعارات المر الات التسويقية أ عدم\n" +
                "المشاركة في تلك المسابقات، يمكنك ببساطة اختيار تنشيط أو إيقاف تلك التنبيهات عن طريق تغيير الوضعيات الموجودة ب \" الملف الشخصي\" . تحديث سياسة\n" +
                "الخصوصية\n" +
                "الصناعة يوالجودها نومي بأن يعد كل ماستخدم قديم أو\n" +
                "محتمل بهذا الإشعار .\n" +
                "عبر التطبيق.\n" +
                "إذا كنت تشعر أننا لا نلتزم بسياسة الخصوصية هذه ، يرجى الاتصال بنا على الفور على بريدنا الدلكتروني أو عن طريق الضغط على زر \" اتصل بنا \" .");
    }

    @FXML
    void tecacchandle(ActionEvent event) {
        showPane("tepane");
        cartBox.setVisible(false);
        myacouBox.setVisible(false);
        orderBox.setVisible(false);
        goodMorningBox.setVisible(false);
        searchBox.setVisible(false);
        searchBackBox.setVisible(false);
        searchProduct.setVisible(false);
        cartLabel.setVisible(false);
    }

    @FXML
    void teracchandle(ActionEvent event) {
        showPane("termspane");
        cartBox.setVisible(false);
        myacouBox.setVisible(false);
        orderBox.setVisible(false);
        goodMorningBox.setVisible(false);
        searchBox.setVisible(false);
        searchBackBox.setVisible(false);
        searchProduct.setVisible(false);
        cartLabel.setVisible(false);
        texta.setText("عزيزي مستخدم تطبيق شني اكسترا اونلاين اهلا بك-الرجاء عدم الاستمرار في استخدام التطبيق وا أو الخدمات المتاحة في حال عدم موافقتك على الشروط والاحكام . يعتبر استمرارك باستخدام التطبيق وااو الخدمات المتاحة وااو إجراء عمليات الشراء والدفع الالكتروني موافقة من قبلك على الشروط والأحكام .\n" +
                "الحساب الشخصي\n" +
                "عند استخدام تطبيق شني اكسترا أونلاين، يتطلب منك إنشاء حساب شخصي حسابل وتدافظ كلمة المرور بأمان. أنت المسؤول الوحيد عن جميع الاشمع التي تتم باستخدام حسابك.\n" +
                "استخدام التطبيق\n" +
                "يجب استخدام تطبيق شني اكسترا أونلاين فقط لأغراض قانونية ومشروعة. يُمنع استخدام التطبيق بطرق تنتهك حقوق الأخرين أو نسبب ضررا للتطبيق أو لأي طرف آخر، وفي هذه الحالة يتم مباشرة إنهاء الاشتراك بالتطبيق وحظره في حال مخالفة شروط الاستخدام، ومن ضمنها:\n" +
                "بيع أو نسخ أو تأجير أي معلومات متوفرة على التطبيق لأي طرف آخر تعطيل أو إتلاف أو إعاقة تشغيل هذه المنصة أو محاولة الدخول غير المصرح به بها أو الشبكات المتصلة معها ، عن طريق القرصنة أو الانتحال أو غيرها من الوسائل\n" +
                "-تغيير أو تعديل أو المساس بحقوق الملكية الفكرية الخاصة بالشركة\n" +
                "المنتجات والخدمات المقدمة\n" +
                "هذا التطبيق متاح ل طلب مستلزمات البيت \\ المكتب \\ الشركة الكترونيا وهذا يشمل كافة الأصناف المتوفرة داخل فروعنا \" الشني اكسترا \" مع خدمة التوصيل للعنوان المحدد والمذكورة شروطها في خانة مركز المساعدة ، كما يتيح لك الاستفادة من التطبيق من خلال:\n" +
                "الاحتفاظ بنسخة الكترونية من فاتورتك عند التسوق سواء من داخل أحد فروعنا أو التسوق الكترونيا عبر التطبيق.\n" +
                "الطلب والتوصيل\n" +
                "ععند اجراء طلبية من خلال التطبيق يجب عليك توفير معلومات صحيحة ومحدثة بشأن العنوان وطريقة الدفع وتفصيل الطلب. يجب عليك أن تكون مسؤولا عن توفير معلومات صحيحة وتواجدك في المكان المحدد لتسليم الطلب، بالإضافة إلى الأخذ بعين الاعتبار أوقات إنشاء الطلبية والأوقات المتاحة لدينا لاستقبالها \" مرفق التفاصيل بمركز\n" +
                "المساعدة \"\n" +
                "الدفع الإلكتروني\n" +
                "تطبيق شني اكسترا أونلاين يوفر خدمة الدفع الإلكتروني لتسهيل عملية الشراء. يجب عليك تقديم معلومات الدفع الصحيحة والمحدثة.\n" +
                "نحن نسعى جاهدين لتوفير بيئة امنة للدفع الإلكتروني, ولكن لسنا مسؤولين عن أي خسارة أو ضرر قد ينتج عن استخدامك للخدمة.\n" +
                "طرق الدفع\n" +
                "- الدفع النقدي \" كاش \"\n" +
                "- الدفع عن طريق ماكينة الفيزا اليدوية\n" +
                "- الدفع عن طريق المحفظة الالكترونية \" مارد \\ مدفوعاتكم \"\n" +
                "- الدفع عن طريق بطاقة الفيزا الالكترونية \" أون لاين\".\n" +
                "خصوصية المعلومات\n" +
                "نحن نهتم بخصوصية المعلومات الشخصية التي تقدمها عند استخدام التطبيق وعمليات الدفع الإلكتروني. يرجي الاطلاع على سياسة الخصوصية الخاصة بنا لفهم كيفية جمعنا واستخدامنا وحماية معلوماتك الشخصية، مع العلم أن الموافقة على شروط وأحكام استخدام التطبيق هو موافقة على سياسة الخصوصية أيضا التعديلات والإلغاءات\n" +
                "قد يكون هناك تعديلات أو إلغاءات للطلبات التي تقدمها عبر التطبيق يُرجى الالترام بسياسة التعديل والإلغاء المحددة في التطبيق واتباع الإرشادات المقدمة\n" +
                "المسؤولية\n" +
                "على المعلومات القدمة من خلال. يجب عليل استخدام التطبيق على مسووليتل الخاصة.");
    }

    @FXML
    void useracchandle(ActionEvent event) {
        showPane("userprofile");
        initializeUserInfo();
        cartBox.setVisible(false);
        myacouBox.setVisible(false);
        orderBox.setVisible(false);
        goodMorningBox.setVisible(false);
        searchBox.setVisible(false);
        searchBackBox.setVisible(false);
        searchProduct.setVisible(false);
        cartLabel.setVisible(false);
    }

    @FXML
    void deleteaccounthandle(ActionEvent event) throws IOException {
        showPane("deletepan");
        cartBox.setVisible(false);
        myacouBox.setVisible(false);
        orderBox.setVisible(false);
        goodMorningBox.setVisible(false);
        searchBox.setVisible(false);
        searchBackBox.setVisible(false);
        searchProduct.setVisible(false);
        cartLabel.setVisible(false);
    }

    @FXML
    void updatelocation(ActionEvent event) {
        showPane("locationpane");
        cartBox.setVisible(false);
        myacouBox.setVisible(false);
        orderBox.setVisible(false);
        goodMorningBox.setVisible(false);
        searchBox.setVisible(false);
        searchBackBox.setVisible(false);
        searchProduct.setVisible(false);
        cartLabel.setVisible(false);
    }

    @FXML
    void updatenamehandle(ActionEvent event) {
        showPane("changename");
        cartBox.setVisible(false);
        myacouBox.setVisible(false);
        orderBox.setVisible(false);
        goodMorningBox.setVisible(false);
        searchBox.setVisible(false);
        searchBackBox.setVisible(false);
        searchProduct.setVisible(false);
        cartLabel.setVisible(false);
    }

    @FXML
    void updatepasshandle(ActionEvent event) {
        showPane("passwordpane");
        cartBox.setVisible(false);
        myacouBox.setVisible(false);
        orderBox.setVisible(false);
        goodMorningBox.setVisible(false);
        searchBox.setVisible(false);
        searchBackBox.setVisible(false);
        searchProduct.setVisible(false);
        cartLabel.setVisible(false);
    }
    @FXML
    void backtoacc(ActionEvent event) {
        showPane("myaccount");
        showHbox("myacouBox");
    }
    public void initializebranch() {
        DatabaseHelper databaseHelper = new DatabaseHelper();
        List<Branch> branches = databaseHelper.getbranches();
        int column = 0;
        int row = 0;
        try{
            for(Branch branch : branches){
                FXMLLoader fx =new FXMLLoader(getClass().getResource("/Shini/FXML/SingleLocation.fxml"));
                ap=fx.load();
                SingleLocationController sn=fx.getController();
                sn.setData(branch);
                if(column == 2){
                    column = 0;
                    ++row;
                }
                branchgrid.add(ap,column++,row);
                branchgrid.setHgap(200);
                GridPane.setMargin(ap, new Insets(7));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void initializeUserInfo() {
        String phone="1234567891";
        DatabaseHelper databaseHelper = new DatabaseHelper();
        Customer customer = databaseHelper.getCustomer(phone);
        usernamelb.setText(customer.getName());
        System.out.println(customer.getName());
        phonenumlab.setText(customer.getPhone());
        malerbutton=new RadioButton("M");
        femalerbutton=new RadioButton("F");
        malerbutton.setToggleGroup(g);
        femalerbutton.setToggleGroup(g);
    }
    @FXML
    void backkk(ActionEvent event) {
        showPane("userprofile");
        initializeUserInfo();
        cartBox.setVisible(false);
        myacouBox.setVisible(false);
        orderBox.setVisible(false);
        goodMorningBox.setVisible(false);
        searchBox.setVisible(false);
        searchBackBox.setVisible(false);
        searchProduct.setVisible(false);
        cartLabel.setVisible(false);
    }

    @FXML
    void savechanges(ActionEvent event) {
        String phone="1234567891";
        malerbutton.setToggleGroup(g);
        femalerbutton.setToggleGroup(g);
        DatabaseHelper databaseHelper = new DatabaseHelper();
        Customer c=databaseHelper.getCustomer(phone);
         if(!namefield.getText().equals("")){
            System.out.println("aaaaaaaaaaaaa"+namefield.getText()+"aaaaaaaaaaaaa");
            databaseHelper.updatename(namefield.getText(),phone);
        }if(malerbutton.isSelected()){
             databaseHelper.updategender("M",phone);}
         else if (femalerbutton.isSelected()) {
                databaseHelper.updategender("F",phone);
         }
         if (!pass1f.getText().equals("") && !pass2f.getText().equals("") && !pass3f.getText().equals("")) {
            if(c.getPassword().equals(pass1f.getText()) && pass2f.getText().equals(pass3f.getText())){
                databaseHelper.updatepassword(pass2f.getText(),phone);
            } else if (!c.getPassword().equals(pass1f.getText())) {
                pass4f.setText("كلمة السر خاطئة");
            } else if (!pass2f.getText().equals(pass3f.getText())) {
                pass5f.setText("التأكيد غير متطابق");
            }
        } if(!locationf.getText().equals("")){
            databaseHelper.updatelocation(locationf.getText(),phone);
        }
    }
    @FXML
    void cancel(ActionEvent event) {
        showPane("userprofile");
        cartBox.setVisible(false);
        myacouBox.setVisible(false);
        orderBox.setVisible(false);
        goodMorningBox.setVisible(false);
        searchBox.setVisible(false);
        searchBackBox.setVisible(false);
        searchProduct.setVisible(false);
        cartLabel.setVisible(false);
    }

    @FXML
    void confirmm7(ActionEvent event) {
        String phone="1234567891";
        databaseHelper.delete(phone);
        showPane("userprofile");
        cartBox.setVisible(false);
        myacouBox.setVisible(false);
        orderBox.setVisible(false);
        goodMorningBox.setVisible(false);
        searchBox.setVisible(false);
        searchBackBox.setVisible(false);
        searchProduct.setVisible(false);
        cartLabel.setVisible(false);
    }
}