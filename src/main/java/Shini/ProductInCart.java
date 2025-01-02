package Shini;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

import java.io.IOException;


public class ProductInCart {
    private CartController cartController;

    public double currentTotal;

    public int indexOfProductInCart;

    public double totalPrice;

    public Product product;

    @FXML
    private Text numberOfProduct;

    @FXML
    private Text price;

    @FXML
    private HBox productHbox;

    @FXML
    private ImageView productImage;

    @FXML
    private Text productName;

    @FXML
    private Text calories;

    public final String CURRENCY = "₪ ";

    @FXML
    private void initialize() throws IOException {
        totalPrice += product.getPrice();
        cartController.setTotalPrice(totalPrice);
        setData();
    }

    public ProductInCart(Product product, int indexOfProduct, CartController cartController) {
        this.product = product;
        this.indexOfProductInCart = indexOfProduct;
        this.cartController = cartController;
    }


    private void setData() {
        productImage.setImage(new Image(product.getImagePath()));
        price.setText(CURRENCY + product.getPrice());
        productName.setText(product.getName());
        calories.setText("cal " + product.getCalories() + " ");
        numberOfProduct.setText("1"); // Default count
    }

    public HBox getProductHBox() {
        return productHbox;
    }
    @FXML
    void addButton(ActionEvent event) {
        int count = Integer.parseInt(numberOfProduct.getText());
        count++;
        numberOfProduct.setText(String.valueOf(count));
        price.setText(CURRENCY + product.getPrice() * count);

        currentTotal = CartController.getTotalPrice();
        totalPrice = product.getPrice() * count;

        cartController.setTotalPrice(currentTotal + product.getPrice());
        CartController.myCartHash.put(this.product, count);
        CartController.countOfProducts++;
    }

    @FXML
    void subButton(ActionEvent event) {
        int count = Integer.parseInt(numberOfProduct.getText());
        if (count > 1) {
            count--;
            numberOfProduct.setText(String.valueOf(count));
            price.setText(CURRENCY + (product.getPrice() * count));

            currentTotal = CartController.getTotalPrice();
            totalPrice = product.getPrice() * count;

            cartController.setTotalPrice(currentTotal - product.getPrice());
            CartController.myCartHash.put(this.product, count);
            CartController.countOfProducts--;
        } else {
            currentTotal = CartController.getTotalPrice();
            cartController.setTotalPrice(currentTotal - product.getPrice());
            CartController.myCartHash.remove(this.product);
            CartController.countOfProducts--;

            if(CartController.countOfProducts == 0) {
                cartController.myEmptyCart.setVisible(true);
                cartController.myCart.setVisible(false);
            }

            cartController.listProductVbox.getChildren().remove(indexOfProductInCart);
        }
    }
}
