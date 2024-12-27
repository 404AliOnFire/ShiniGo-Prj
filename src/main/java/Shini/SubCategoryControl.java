package Shini;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import javax.swing.text.html.StyleSheet;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;



public class SubCategoryControl implements Initializable {
    @FXML
    private VBox account;

    @FXML
    private VBox cart;

    @FXML
    private VBox main;

    @FXML
    private VBox order;

    @FXML
    private HBox subHB;

    static int subCategoryCount = 0;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        subCategoryCount = subHB.getChildren().size();


        String css = getClass().getResource("/Shini/Styles/redLine.css").toExternalForm();


        for (int i = 0; i < subCategoryCount; i++) {
            VBox vboxSubCategory = (VBox) subHB.getChildren().get(i);
            vboxSubCategory.getStylesheets().add(css);

            vboxSubCategory.setOnMouseClicked(event -> {
                for (int j = 0; j < subCategoryCount; j++) {
                    VBox otherVBox = (VBox) subHB.getChildren().get(j);

                    if (otherVBox == vboxSubCategory) {
                        otherVBox.getStyleClass().clear();
                        otherVBox.getStyleClass().add("vbox-red-line");
                    } else {
                        otherVBox.getStyleClass().clear();
                    }
                }
            });
        }
    }
}
