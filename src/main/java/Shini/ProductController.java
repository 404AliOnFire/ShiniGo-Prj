package Shini;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ProductController {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private ImageView btAddProduct;

    @FXML
    private HBox discountBox;

    @FXML
    private Text txtIsBoycott;

    @FXML
    private ImageView productImg;

    @FXML
    private Text txtBarCode;

    @FXML
    private Text txtCalorie;

    @FXML
    private Text txtDesc;

    @FXML
    private Text txtDiscountPer;

    @FXML
    private Text txtDiscountPrice;

    @FXML
    private Text txtPrice;

    @FXML
    private Text txtSize;

    private boolean added = false;
    private Controller controller = new Controller();

    public void setData(Product product) {

        DatabaseHelper databaseHelper = new DatabaseHelper();

        productImg.setImage(new Image(product.getImagePath()));
        txtCalorie.setText(product.getCalories() + " cal");

        txtDesc.setText(product.getDescription());

        txtSize.setText(product.getSize());

        txtBarCode.setText(product.getBarcode() + "");
        if (product.isBoycott()) txtIsBoycott.setText("مقاطع");


        txtPrice.setText(product.getPrice() + " ILS");


        if (product.getOfferId() != null && product.getOfferId() > 0) {
            LocalDate currentDate = LocalDate.now();
            Offer offer = databaseHelper.getDiscountPercentage(product.getOfferId());

            if (offer.getOfferId() != 0 && currentDate.isBefore(offer.getEndDate().toLocalDate())) {
                double prevPrice = product.getPrice();
                double newPrice = product.getPrice() * (1 - offer.getPercentage() / 100);

                txtPrice.setStyle("-fx-fill: #7a0808; -fx-strikethrough: true;");

                txtDiscountPer.setText(offer.getPercentage() + " %");
                txtDiscountPrice.setText(String.format("%.2f", newPrice) + " ILS");

            }
        } else discountBox.setVisible(false);


    }

    @FXML
    void addProductToMyCart(MouseEvent event) {
        if (added) {
//            System.out.println("added before");
            return;
        }
        int barcode = Integer.parseInt(txtBarCode.getText());

        DatabaseHelper databaseHelper = new DatabaseHelper();
        Product product = databaseHelper.getProductByBarcode(barcode);

        if (controller != null && product != null) {
            try {
                // Ali here in your method there is something wrong return check it out
//                controller.addProductToCart(product);
//                System.out.println("product added");
                databaseHelper.insertProductToCart(LoginController.customerId, product);
                added = true;
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }
    }


}
