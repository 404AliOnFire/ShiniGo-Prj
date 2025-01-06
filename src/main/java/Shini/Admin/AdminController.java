package Shini.Admin;

import Shini.DatabaseConnection;
import Shini.DatabaseHelper;
import Shini.Offer;
import Shini.Product;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AdminController {

    @FXML
    private VBox listEmployee;

    @FXML
    private Text employeeCountv1;

    List<Employee> employees;

    @FXML
    private Group EmployeesPane;

    @FXML
    private MFXButton addEmp;

    @FXML
    private Group addEmployee;

    @FXML
    private Group iloveyou;

    @FXML
    private MFXTextField addressEmp;

    @FXML
    private MFXTextField advisor;

    @FXML
    private MFXButton deleteEmp;

    @FXML
    private MFXTextField emailEmp;

    @FXML
    private TableView<Employee> tableViewEmp;

    @FXML
    private MFXComboBox<String> genderEmp;

    @FXML
    private MFXTextField nameEmp;

    @FXML
    private MFXTextField phoneEmp;

    @FXML
    private MFXTextField roleEmp;

    @FXML
    private DatePicker bodEmp;

    @FXML
    private MFXTextField salaryEmp;

    @FXML
    private DatePicker hireDateEmp;

    @FXML
    private MFXTextField shiftEmp;

//    @FXML
//    private TableView<Map<String, Object>> tableViewProd;

    @FXML
    private TableView<Product> tableViewProd;

    @FXML
    private MFXButton updateEmp;

    @FXML
    private Tab CategoryTap;

    @FXML
    private Tab OfferTab;


    @FXML
    private MFXButton addProd;


    @FXML
    private ImageView backToLife;


    @FXML
    private MFXComboBox<Boolean> cbxBoycottProd;

    @FXML
    private MFXComboBox<Boolean> cbxIsEdibleProd;

    @FXML
    private MFXComboBox<Integer> cbxOfferIDProd;

    @FXML
    private MFXComboBox<Integer> cbxSubCID;

    @FXML
    private MFXComboBox<Boolean> cbxVisibility;

    @FXML
    private MFXButton customerAdmin;

    @FXML
    private MFXButton deleteProd;

    @FXML
    private DatePicker endD;

    @FXML
    private Text imgChooser;


    @FXML
    private Group productM;

    @FXML
    private Tab productTab;

    @FXML
    private TabPane productTabPane;

    @FXML
    private MFXButton productsAdmin;


    @FXML
    private HBox searchBox2;

    @FXML
    private TextField searchFeild;

    @FXML
    private DatePicker startD;

    @FXML
    private Tab subCategoryTab;

    @FXML
    private MFXTextField tfCaloriesProd;

    @FXML
    private MFXTextField tfDescProd;

    @FXML
    private MFXTextField tfImgPath;

    @FXML
    private MFXTextField tfNameProd;

    @FXML
    private MFXTextField tfPriceProd;

    @FXML
    private MFXTextField tfSizeProd;

    @FXML
    private MFXTextField tfTypeProd;

    @FXML
    private MFXButton updateProd;

    @FXML
    private BarChart<String, Number> barChartProduct;

    private ObservableList<Employee> employeeObservableList;
    private ObservableList<Product> productObservableList;
    private ObservableList<Category> categoryObservableList;
    private ObservableList<Subcategory> subCategoryObservableList;
    private ObservableList<Offer> offerObservableList;
    private ObservableList<Customer> customerObservableList;

    private GenericTableView<?> genaricTableProduct = new GenericTableView<>();
    private HashMap<String, TableColumn<Employee, ?>> allColumns;

    @FXML
    void initialize() throws IOException {
        loadProductInTableView();
        setupProductTableViewListener();
        setBarChartProductDataFromWhere();
//        searchListener();
//        loadEmployees();
//        setupColumns();
//        setupTableView();
//        setupTableViewListener();
//        loadEmployeesInTableView();

    }





    private void loadProductInTableView() {
        productObservableList = FXCollections.observableArrayList(DatabaseHelper.getAllProducts());
        if (genaricTableProduct.getColumns().size() == 0) {
            genaricTableProduct.createTableView(productObservableList);
        } else
            genaricTableProduct.setItems(productObservableList);
//        String query = "Select P.name,P.price,O.Percentage from Product P , Offer O where P.offer_Id = o.offer_ID";
//        String query = "select * from Product";
//        TableView<Map<String, Object>> dynamicTable = DynamicTableView.createDynamicTable(query);
//
//        tableViewProd.getColumns().clear();
//        tableViewProd.getColumns().addAll(dynamicTable.getColumns());
//        tableViewProd.setItems(dynamicTable.getItems());

        tableViewProd.getColumns().clear();
        tableViewProd.getColumns().addAll(genaricTableProduct.getColumns());
        tableViewProd.setItems(genaricTableProduct.getItems());


        cbxSubCID.clear();
        cbxOfferIDProd.clear();
        cbxIsEdibleProd.clear();
        cbxBoycottProd.clear();
        cbxVisibility.clear();

        List<Integer> subCategoryID = DatabaseHelper.getAllSubCategoriesId();
        List<Integer> offerIds = DatabaseHelper.getAllOfferId();
        List<Boolean> trueOrFalse = new ArrayList<>();
        trueOrFalse.add(true);
        trueOrFalse.add(false);
        cbxSubCID.getItems().addAll(subCategoryID);
        cbxOfferIDProd.getItems().addAll(offerIds);
        cbxIsEdibleProd.getItems().addAll(trueOrFalse);
        cbxBoycottProd.getItems().addAll(trueOrFalse);
        cbxVisibility.getItems().addAll(trueOrFalse);

    }

    @FXML
    void addEmplHandle(ActionEvent event) throws IOException {
        Employee newEmployee = getEmployeeFromFields();
        if (newEmployee != null) {
            DatabaseHelper.addEmployee(newEmployee);
            employeeObservableList.add(newEmployee);
            clearFields();

        }

    }

    @FXML
    void deleteEmpHandle(ActionEvent event) throws IOException {
        Employee selectedEmployee = tableViewEmp.getSelectionModel().getSelectedItem();
        if (selectedEmployee != null) {
            DatabaseHelper.deleteEmployee(selectedEmployee.getSsn()); // Assuming this method exists in DatabaseHelper
            employeeObservableList.remove(selectedEmployee);
            clearFields();
        }
    }

    @FXML
    void updateEmpHandle(ActionEvent event) throws IOException {
        Employee selectedEmployee = tableViewEmp.getSelectionModel().getSelectedItem();
        if (selectedEmployee != null) {
            Employee updatedEmployee = getEmployeeFromFields();
            if (updatedEmployee != null) {
                DatabaseHelper.updateEmployee(updatedEmployee);
                employeeObservableList.set(employeeObservableList.indexOf(selectedEmployee), updatedEmployee);
                tableViewEmp.refresh();
                clearFields();
            }
        }
    }

    @FXML
    void addEmployee(ActionEvent event) {
    }

    @FXML
    void controlEmployee(ActionEvent event) {

    }


    private void updateTableViewColumn(String selectedColumn) {
        tableViewEmp.getColumns().clear();
        TableColumn<Employee, ?> column = allColumns.get(selectedColumn);
        if (column != null) {
            tableViewEmp.getColumns().add(column);
        }
    }

    private void setupColumns() {
        allColumns = new HashMap<>();

        // Define all columns
        TableColumn<Employee, String> ssnColumn = new TableColumn<>("SSN");
        ssnColumn.setCellValueFactory(new PropertyValueFactory<>("ssn"));

        TableColumn<Employee, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Employee, String> addressColumn = new TableColumn<>("Address");
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));

        TableColumn<Employee, String> emailColumn = new TableColumn<>("Email");
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));

        TableColumn<Employee, LocalDate> birthdayColumn = new TableColumn<>("Birthday");
        birthdayColumn.setCellValueFactory(new PropertyValueFactory<>("birthdayDate"));

        TableColumn<Employee, LocalDate> hireDateColumn = new TableColumn<>("Hire Date");
        hireDateColumn.setCellValueFactory(new PropertyValueFactory<>("hireDate"));

        TableColumn<Employee, Double> salaryColumn = new TableColumn<>("Salary");
        salaryColumn.setCellValueFactory(new PropertyValueFactory<>("salary"));

        TableColumn<Employee, String> roleColumn = new TableColumn<>("Role");
        roleColumn.setCellValueFactory(new PropertyValueFactory<>("role"));

        TableColumn<Employee, String> phoneColumn = new TableColumn<>("Phone");
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));

        TableColumn<Employee, Integer> shiftColumn = new TableColumn<>("Work Shift Time");
        shiftColumn.setCellValueFactory(new PropertyValueFactory<>("workShiftTime"));

        TableColumn<Employee, Integer> advisorColumn = new TableColumn<>("Advisor");
        advisorColumn.setCellValueFactory(new PropertyValueFactory<>("advisor"));

        TableColumn<Employee, String> genderColumn = new TableColumn<>("Gender");
        genderColumn.setCellValueFactory(new PropertyValueFactory<>("gender"));

        // Add columns to tableView
        tableViewEmp.getColumns().addAll(ssnColumn, nameColumn, addressColumn, emailColumn, birthdayColumn, hireDateColumn,
                salaryColumn, roleColumn, phoneColumn, shiftColumn, advisorColumn, genderColumn);

        // Add all columns to the map
        allColumns.put("SSN", ssnColumn);
        allColumns.put("Name", nameColumn);
        allColumns.put("Address", addressColumn);
        allColumns.put("Email", emailColumn);
        allColumns.put("Birthday", birthdayColumn);
        allColumns.put("Hire Date", hireDateColumn);
        allColumns.put("Salary", salaryColumn);
        allColumns.put("Role", roleColumn);
        allColumns.put("Phone", phoneColumn);
        allColumns.put("Work Shift Time", shiftColumn);
        allColumns.put("Advisor", advisorColumn);
        allColumns.put("Gender", genderColumn);
    }

    private Employee getEmployeeFromFields() {
        try {
            String name = nameEmp.getText();
            String address = addressEmp.getText();
            String email = emailEmp.getText();
            String phone = phoneEmp.getText();
            double salary = Double.parseDouble(salaryEmp.getText());
            String role = roleEmp.getText();
            int workshiftTime = Integer.parseInt(shiftEmp.getText());
            int advisorId = Integer.parseInt(advisor.getText());
            Date birthdayDate = Date.valueOf(bodEmp.getValue());
            Date hireDate = Date.valueOf(hireDateEmp.getValue());
            String gender = genderEmp.getValue();

            return new Employee(name, address, email, birthdayDate, hireDate, salary
                    , role, phone, workshiftTime, advisorId, gender);

        } catch (Exception e) {
            return null;
        }
    }

    private void clearFields() {
        nameEmp.clear();
        addressEmp.clear();
        emailEmp.clear();
        phoneEmp.clear();
        salaryEmp.clear();
        roleEmp.clear();
        shiftEmp.clear();
        advisor.clear();
        bodEmp.setValue(null);
        hireDateEmp.setValue(null);
        genderEmp.setValue(null);
    }


    private void loadEmployees() throws IOException {
        listEmployee.getChildren().clear();
        employees = DatabaseHelper.getAllEmployees();

        for (Employee employee : employees) {
            addEmployee(employee);
        }
        employeeCountv1.setText(employees.size() + "");
    }

    public void addEmployee(Employee employee) throws IOException {
        EmployeeInPane employeeInPane = new EmployeeInPane(employee);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Shini/FXML/Employee.fxml"));
        loader.setController(employeeInPane);
        AnchorPane anchorPane = loader.load();

        listEmployee.getChildren().add(anchorPane);
        listEmployee.setPrefHeight(listEmployee.getPrefHeight() + 20);

    }

    private void setupTableView() {
        tableViewEmp.setItems(FXCollections.observableArrayList());
    }

    private void loadEmployeesInTableView() throws IOException {
        employeeObservableList = FXCollections.observableArrayList(DatabaseHelper.getAllEmployees());
        tableViewEmp.setItems(employeeObservableList);
        employeeCountv1.setText(String.valueOf(employeeObservableList.size()));
    }

    private void setupTableViewListener() {
        tableViewEmp.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                populateFields(newSelection);
            }
        });
    }

    private void setupProductTableViewListener() {
        tableViewProd.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                populateProductFields(newSelection);
            }
        });
    }

    private void populateProductFields(Product product) {
        tfNameProd.setText(product.getName());
        tfTypeProd.setText(product.getType());
        tfPriceProd.setText(product.getPrice()+"");
        startD.setValue(product.getStartD().toLocalDate());
        endD.setValue(product.getEndD().toLocalDate());
        tfCaloriesProd.setText(product.getCalories()+"");
        tfSizeProd.setText(product.getSize()+"");
        tfDescProd.setText(product.getDescription());
        cbxIsEdibleProd.setValue(product.isEdible());
        cbxBoycottProd.setValue(product.isBoycott());
        cbxSubCID.setValue(product.getSubcategoryId());
        cbxOfferIDProd.setValue(product.getOfferId());
        tfImgPath.setText(product.getImagePath());
        cbxVisibility.setValue(product.getShown());
    }

    @FXML
    void backToAli(MouseEvent event) throws IOException {
        addEmployee.setVisible(false);
        EmployeesPane.setVisible(true);
        refreshEmployees();
    }

    private void populateFields(Employee employee) {
        nameEmp.setText(employee.getName());
        addressEmp.setText(employee.getAddress());
        emailEmp.setText(employee.getEmail());
        phoneEmp.setText(employee.getPhone());
        salaryEmp.setText(String.valueOf(employee.getSalary()));
        roleEmp.setText(employee.getRole());
        shiftEmp.setText(String.valueOf(employee.getWorkShiftTime()));
        advisor.setText(String.valueOf(employee.getAdvisor()));
        bodEmp.setValue(employee.getBirthdayDate().toLocalDate());
        hireDateEmp.setValue(employee.getHireDate().toLocalDate());
        genderEmp.setValue(employee.getGender());
    }

    public void refreshEmployees() throws IOException {
        loadEmployees();
    }

    @FXML
    void updateProdHandle(ActionEvent event) {
        Product selectedProduct = tableViewProd.getSelectionModel().getSelectedItem();
        if (selectedProduct != null) {
            Product updatedProduct= getProductFromFields();
            System.out.println("updated Prod"+ updatedProduct);
            if (updatedProduct != null) {
                DatabaseHelper.updateProduct(updatedProduct);
                productObservableList.set(productObservableList.indexOf(selectedProduct), updatedProduct);
//                tableViewProd.refresh();
                loadProductInTableView();
                clearProdFields();
            }
        }

    }

    @FXML
    void deleteProdHandle(ActionEvent event) {
        Product selectedProduct = (Product) tableViewProd.getSelectionModel().getSelectedItem();
        if (selectedProduct != null) {
            DatabaseHelper.deleteProduct(selectedProduct.getBarcode()); // Assuming this method exists in DatabaseHelper
//            productObservableList.remove(selectedProduct);
            loadProductInTableView();
//            clearProdFields();
        }
    }

    private void clearProdFields() {
        tfNameProd.clear();
        tfTypeProd.clear();
        tfPriceProd.clear();
        startD.setValue(null);
        endD.setValue(null);
        tfCaloriesProd.clear();
        tfSizeProd.clear();
        tfDescProd.clear();
        cbxIsEdibleProd.setValue(null);
        cbxBoycottProd.setValue(null);
        cbxSubCID.setValue(null);
        cbxOfferIDProd.setValue(null);
        tfImgPath.clear();
        cbxVisibility.setValue(null);
    }

    private Product getProductFromFields() {
        try {
            String name = tfNameProd.getText().trim();
            String type = tfTypeProd.getText().trim();
            double price = Double.parseDouble(tfPriceProd.getText().trim());
            Date startDate = Date.valueOf(startD.getValue());
            Date endDate = Date.valueOf(endD.getValue());
            int cal = Integer.parseInt(tfCaloriesProd.getText().trim());
            String size = tfSizeProd.getText().trim();
            boolean isEdible = cbxIsEdibleProd.getValue();
            boolean isBoycott = cbxBoycottProd.getValue();
            String desc = tfDescProd.getText().trim();
            int subCategID = cbxSubCID.getValue();
            Integer offerID = cbxOfferIDProd.getValue();
            String imgPath = tfImgPath.getText().trim();
            boolean visible = cbxVisibility.getValue();
            return new Product(name, type, endDate, startDate, price, cal, size, desc, isBoycott, isEdible, subCategID, offerID, imgPath, visible);

        } catch (Exception e) {
            return null;
        }
    }

    @FXML
    void addProdlHandle(ActionEvent event) {
        Product newProduct = getProductFromFields();
        if (newProduct != null) {
            DatabaseHelper.addProduct(newProduct);
            productObservableList.add(newProduct);
            loadProductInTableView();
            clearProdFields();
        }

    }

    @FXML
    void chooseImg(MouseEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose a Photo");

        // Set file extension filters (e.g., JPEG, PNG)
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.jpeg", "*.png", "*.gif"),
                new FileChooser.ExtensionFilter("All Files", "*.*")
        );

        Stage currentStage = new Stage();

        // Open the FileChooser and get the selected file
        File selectedFile = fileChooser.showOpenDialog(currentStage);

        if (selectedFile != null) {
            // Set the file path in the TextField
            tfImgPath.setText(selectedFile.getAbsolutePath());
        } else {
            // Clear the TextField if no file was chosen
            tfImgPath.setText("");
        }
    }



    @FXML
    void showProductsManagment(MouseEvent event) {
        productTabPane.setVisible(true);
        EmployeesPane.setVisible(false);
        iloveyou.setVisible(false);
        addEmployee.setVisible(false);
    }

    private void searchListener() {
        FilteredList<Product> filteredData = new FilteredList<>(productObservableList, p -> true);

        // Bind the search field to filter the data
        searchFeild.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(product -> {
                // If the search field is empty, show all rows
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                // Compare name and barcode fields with the filter text
                return product.getName().toLowerCase().contains(lowerCaseFilter) ||
                        String.valueOf(product.getBarcode()).contains(lowerCaseFilter);
            });
        });
    }


    void setBarChartProductDataFromWhere() {



        // Configure the axes
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Product Source");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Number of Products");

        // Configure the BarChart (you don't need to create a new one)
        barChartProduct.getXAxis().setLabel(xAxis.getLabel());
        barChartProduct.getYAxis().setLabel(yAxis.getLabel());

        // Create a data series
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Product Count");

        try (Connection con = DatabaseConnection.getConnection();
             Statement statement = con.createStatement()) {

            // Execute the query
            ResultSet resultSet = statement.executeQuery(
                    "SELECT type, COUNT(*) AS numOfProductAtSource FROM Product GROUP BY type"
            );

            // Populate the data series
            while (resultSet.next()) {
                String type = resultSet.getString("type");
                int count = resultSet.getInt("numOfProductAtSource");
                System.out.println(type+"" +count);// Correct alias
                series.getData().add(new XYChart.Data<>(type, count));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        // Add the series to the chart
        barChartProduct.getData().add(series);


    }

}