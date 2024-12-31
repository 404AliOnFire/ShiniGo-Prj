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
    public int indexOfProductInCart;

    public Product product;

    public FXMLLoader loader;

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

    public final String CURRENCY = "₪ ";

    @FXML
    private void initialize() throws IOException {
        setData();
    }

    public ProductInCart(Product product, int index) {
        this.product = product;
        this.indexOfProductInCart = index;

        // Load FXML and initialize
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Shini/FXML/productInCart.fxml"));
        loader.setController(this);
        try {
            productHbox = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setData() {
        productImage.setImage(new Image(product.getImagePath()));
        price.setText(CURRENCY + product.getPrice());
        productName.setText(product.getName());
        numberOfProduct.setText("1"); // Default count
    }

    public HBox getProductHBox(){
        return productHbox;
    }
    @FXML
    void addButton(ActionEvent event) {
        int count = Integer.parseInt(numberOfProduct.getText());
        count++;
        numberOfProduct.setText(String.valueOf(count));
        price.setText(CURRENCY + (product.getPrice() * count));

        // Update the product in the cart
        CartController.myCartHash.put(this.product, count);
        CartController.countOfProducts++;
    }

    @FXML
    void subButton(ActionEvent event) {
        int count = Integer.parseInt(numberOfProduct.getText());

        if(count > 1){
            count--;
            numberOfProduct.setText(String.valueOf(count));
            price.setText(CURRENCY + (product.getPrice() * count));

            // Update the product in the cart
            CartController.myCartHash.put(this.product, count);
            CartController.countOfProducts--;
        }
    }


}
