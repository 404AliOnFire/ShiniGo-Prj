package Shini.Admin;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class EmployeeInPane {

    @FXML
    private ImageView image;

    @FXML
    private Text name;

    @FXML
    private Text role;

    @FXML
    private Text salary;

    Employee employee;
    @FXML
    void initialize(){
        setData();
    }

    private void setData() {
        name.setText(employee.getName());
        role.setText(employee.getRole());
        salary.setText(employee.getSalary() + "");
    }

    EmployeeInPane(Employee employee){
        this.employee = employee;
    }
}