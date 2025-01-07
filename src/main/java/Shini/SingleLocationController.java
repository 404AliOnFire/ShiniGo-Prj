package Shini;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.text.SimpleDateFormat;

public class SingleLocationController {
    @FXML
    private Label Locationnlabel;

    private Branch branch;
    public void setData(Branch branch){
        this.branch=branch;
        Locationnlabel.setText(branch.getLocation());
    }
}
