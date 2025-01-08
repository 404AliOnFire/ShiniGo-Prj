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

    ProductInCart(Product product, int index){
        this.product = product;
        indexOfProductInCart = index;
    }

    public void setData() throws IOException {
        //loader = new FXMLLoader(getClass().getResource("/Shini/FXML/productInCart.fxml"));
        productImage.setImage(new Image(product.getImagePath()));
        price.setText(CURRENCY + product.getPrice());
        productName.setText(product.getName());
    }

    public HBox getProductHBox(){
        return productHbox;
    }
    @FXML
    void addButton(ActionEvent event) {

    }

    @FXML
    void subButton(ActionEvent event) {

    }


}
