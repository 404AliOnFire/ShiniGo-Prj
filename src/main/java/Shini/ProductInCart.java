package Shini;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

import java.io.IOException;


public class ProductInCart {
    private Controller controller;

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
        controller.setTotalPrice(totalPrice);
        setData();
    }

    public ProductInCart(Product product, int indexOfProduct, Controller controller) {
        this.product = product;
        this.indexOfProductInCart = indexOfProduct;
        this.controller = controller;
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

        currentTotal = Controller.getTotalPrice();
        totalPrice = product.getPrice() * count;

        controller.setTotalPrice(currentTotal + product.getPrice());
        Controller.myCartHash.put(this.product, count);
        Controller.countOfProducts++;
    }

    @FXML
    void subButton(ActionEvent event) {
        int count = Integer.parseInt(numberOfProduct.getText());
        if (count > 1) {
            count--;
            numberOfProduct.setText(String.valueOf(count));
            price.setText(CURRENCY + (product.getPrice() * count));

            currentTotal = Controller.getTotalPrice();
            totalPrice = product.getPrice() * count;

            controller.setTotalPrice(currentTotal - product.getPrice());
            Controller.myCartHash.put(this.product, count);
            Controller.countOfProducts--;
        } else {
            currentTotal = Controller.getTotalPrice();
            controller.setTotalPrice((double) (currentTotal - product.getPrice()));
            Controller.myCartHash.remove(this.product);
            Controller.countOfProducts--;

            if(Controller.countOfProducts == 0) {
                controller.myEmptyCart.setVisible(true);
                controller.myCart.setVisible(false);
            }

            controller.listProductVbox.getChildren().remove(indexOfProductInCart);
        }
    }
}
