package Shini;

import io.github.palexdev.materialfx.controls.MFXRadioButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.io.IOException;
import java.security.cert.PolicyNode;
import java.util.ArrayList;
import java.util.Calendar;
import java.sql.Date;
import java.util.HashMap;

public class CartController {
    public static HashMap<Product, Integer> myCartHash = new HashMap<Product, Integer>();
    static double totalPriceNum = 0;
    public static int countOfProducts = 0;
    int indexOfProduct = 0;
    public final String CURRENCY = "₪ ";

    private static CartController instance = new CartController();
    @FXML
    private VBox account;

    @FXML
    private Text failedSendOrder;

    @FXML
    private VBox account1;

    @FXML
    private Text totalPrice1;

    @FXML
    public  VBox listProductVbox;

    @FXML
    public  VBox itemsInCartVBox;

    @FXML
    private MFXRadioButton onlineButton;

    @FXML
    private MFXRadioButton shopButton;

    @FXML
    private VBox cart;

    @FXML
    private VBox cart1;

    @FXML
    private VBox main;

    @FXML
    private VBox main1;

    @FXML
    private Text numberOfProduct;

    @FXML
    private VBox order;

    @FXML
    private VBox order1;

    @FXML
    private MFXTextField cityTextField;

    @FXML
    private Text textID;

    @FXML
    private ToggleGroup toggleButtonsSO;

    @FXML
    private MFXTextField addressTextField;

    @FXML
    private Text price;

    @FXML
    private HBox productHbox;

    @FXML
    private VBox confirmOrderVBox;

    @FXML
    private Text productName;

    @FXML
    private Text totalPrice;

    @FXML
    private ImageView productImage;

    @FXML
    private Label countProductLabel;

    @FXML
    public AnchorPane myCart;

    @FXML
    public AnchorPane myEmptyCart;

    @FXML
    private ImageView boldAccount;

    @FXML
    private ImageView boldCart;

    @FXML
    private ImageView boldMain;

    @FXML
    private ImageView boldOrder;

    @FXML
    private ImageView lightAccount;

    @FXML
    private ImageView lightCart;

    @FXML
    private ImageView lightMain;

    @FXML
    private ImageView lightOrder;

    private final HashMap<String, Pane> panes = new HashMap<>();
    private String currentPane = "emptyCart";


    @FXML
    public void initialize() throws IOException {
        List<Product> productsInCart = DatabaseHelper.getProductsInCart(customerId);

        // إضافة المنتجات إلى السلة
        for (Product product : productsInCart) {
            addProductToCart(product);
        }

//        panes.put("main", mainPane);
        panes.put("cart", myCart);
        panes.put("emptyCart", myEmptyCart);
//        panes.put("account", accountPane);
//        panes.put("order", orderPane);


//        showPane("main");
    }

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

//    public void addButton(ActionEvent actionEvent) {
//        numberOfProduct.setText(String.valueOf(Integer.parseInt(numberOfProduct.toString()) + 1));
//       // addProductToCart(product);
//    }

//    public void subButton(ActionEvent actionEvent) {
//      // deleteProductFromCart(product);
//    }

}
