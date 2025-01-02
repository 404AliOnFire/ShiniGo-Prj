package Shini;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

import java.sql.Connection;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ProductController {

    @FXML
    private ImageView addProduct;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Text calorie;

    @FXML
    private Text desc;

    @FXML
    private Text size;

    @FXML
    private Text discountPer;

    @FXML
    private Text discountPrice;

    @FXML
    private Text isBoycott;

    @FXML
    private Text price;

    @FXML
    private ImageView productImg;

    @FXML
    private HBox discountBox;

    public void setData(Product product) {

        DatabaseHelper databaseHelper = new DatabaseHelper();

        productImg.setImage(new Image(product.getImagePath()));
        calorie.setText(product.getCalories()+" cal");

        desc.setText(product.getDescription());

        size.setText(product.getSize());

        if(product.isBoycott())
            isBoycott.setText("مقاطع");


        price.setText(product.getPrice()+" ILS");

        if(product.getOfferId() != null && product.getOfferId() > 0){
            LocalDate currentDate = LocalDate.now();
            Offer offer = databaseHelper.getDiscountPercentage(product.getOfferId());

            if(offer.getOfferId() != 0 && currentDate.isBefore(offer.getEndDate().toLocalDate())){
                double prevPrice = product.getPrice();
                double newPrice = product.getPrice() *  (1- offer.getPercentage()/100);

                price.setStyle("-fx-fill: #7a0808; -fx-strikethrough: true;");

                discountPer.setText(offer.getPercentage()+" %");
                discountPrice.setText(String.format("%.2f", newPrice)+" ILS");

            }
        }else
            discountBox.setVisible(false);


    }

    @FXML
    void addProductToMyCart(MouseEvent event) {
        Product product = (Product) event.getSource();
        System.out.println(product);
        DatabaseHelper databaseHelper = new DatabaseHelper();

        System.out.println("Helloooooooo");

//        try(Connection con = databaseHelper.getCon)



    }


}
