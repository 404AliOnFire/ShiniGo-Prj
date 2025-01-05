package Shini.Admin;

import Shini.DatabaseHelper;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
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

    @FXML
    private MFXButton updateEmp;

    private ObservableList<Employee> employeeObservableList;

    private HashMap<String, TableColumn<Employee, ?>> allColumns;

    @FXML
    void initialize() throws IOException {
        loadEmployees();
        setupColumns();
        setupTableView();
        setupTableViewListener();
        loadEmployeesInTableView();
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

            return new Employee(name, address,email,birthdayDate,hireDate,salary
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
}
