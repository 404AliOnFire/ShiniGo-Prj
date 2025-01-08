package Shini;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.HashMap;

public class CartController {
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

    public void addProductToCart(Product product) {

            ProductInCart productInCart = new ProductInCart(product, indexOfProduct);
            myCartHash.put(product, 1);
            countOfProducts++;
            indexOfProduct++;

            HBox hboxProduct = productInCart.getProductHBox();
            listProductVbox.setPrefHeight(listProductVbox.getPrefHeight() + 100);
            listProductVbox.getChildren().add(hboxProduct);

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
}
