package Shini;

import Shini.Admin.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper {
     public static boolean checkUserExists(String phone, String password) {
         boolean exists = false;
         String query = " SELECT * FROM customer WHERE Phone =? AND Password =?";

         try (Connection con = DatabaseConnection.getConnection()) {
             PreparedStatement pstmt = con.prepareStatement(query);
             pstmt.setString(1, phone);
             pstmt.setString(2, password);
             ResultSet resultSet = pstmt.executeQuery();
             exists = resultSet.next();

         } catch (SQLException e) {
             throw new RuntimeException(e);
         }

         return exists;
     }
    public static boolean addEmployee(Employee employee) {
        String query = "INSERT INTO employee ( name, address, email, birthdayDate, hireDate, salary, role, phone, workshiftTime, advisor, gender) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, employee.getName());
            stmt.setString(2, employee.getAddress());
            stmt.setString(3, employee.getEmail());
            stmt.setDate(4, employee.getBirthdayDate());
            stmt.setDate(5, employee.getHireDate());
            stmt.setDouble(6, employee.getSalary());
            stmt.setString(7, employee.getRole());
            stmt.setString(8, employee.getPhone());
            stmt.setInt(9, employee.getWorkShiftTime());
            stmt.setInt(10, employee.getAdvisor());
            stmt.setString(11, employee.getGender());

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public static boolean deleteEmployee(String ssn) {
        String query = "DELETE FROM employee WHERE ssn = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, ssn);

            int rowsDeleted = stmt.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public static boolean updateEmployee(Employee employee) {
        String query = "UPDATE employee SET name = ?, address = ?, email = ?, birthdayDate = ?, hireDate = ?, salary = ?, " +
                "role = ?, phone = ?, workshiftTime = ?, advisor = ?, gender = ? WHERE ssn = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, employee.getName());
            stmt.setString(2, employee.getAddress());
            stmt.setString(3, employee.getEmail());
            stmt.setDate(4, employee.getBirthdayDate());
            stmt.setDate(5, employee.getHireDate());
            stmt.setDouble(6, employee.getSalary());
            stmt.setString(7, employee.getRole());
            stmt.setString(8, employee.getPhone());
            stmt.setInt(9, employee.getWorkShiftTime());
            stmt.setInt(10, employee.getAdvisor());
            stmt.setString(11, employee.getGender());
            stmt.setString(12, employee.getSsn());

            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public static List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        String query = "SELECT * FROM employee";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Employee employee = new Employee(
                        resultSet.getString("ssn"),
                        resultSet.getString("name"),
                        resultSet.getString("address"),
                        resultSet.getString("email"),
                        resultSet.getDate("birthdayDate"),
                        resultSet.getDate("hireDate"),
                        resultSet.getDouble("salary"),
                        resultSet.getString("role"),
                        resultSet.getString("phone"),
                        resultSet.getInt("workshifttime"),
                        resultSet.getInt("advisor"),
                        resultSet.getString("gender")
                );
                employees.add(employee);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }


    public static List<Product> getProductsInCart(int customerId) {
        List<Product> products = new ArrayList<>();
        String query = "SELECT p.* FROM product p " +
                "JOIN shoppingcart2product sp ON p.barcode = sp.barcode " +
                "JOIN shoppingcart sc ON sc.Cart_ID = sp.Cart_ID " +
                "WHERE sc.Customer_ID = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, customerId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Product product = new Product(
                            resultSet.getInt("barcode"),
                            resultSet.getString("name"),
                            resultSet.getString("type"),
                            resultSet.getDate("endD"),
                            resultSet.getDate("srtartD"),
                            resultSet.getDouble("price"),
                            resultSet.getInt("calories"),
                            resultSet.getString("description"),
                            resultSet.getBoolean("boycott"),
                            resultSet.getBoolean("isEdible"),
                            resultSet.getInt("Subcategory_ID"),
                            resultSet.getObject("Offer_ID", Integer.class),
                            resultSet.getString("image_path")
                    );
                    products.add(product);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }
    public static boolean isCartNotEmpty(int customerId) {
        String query = "SELECT COUNT(*) AS product_count FROM shoppingcart2product sp " +
                "JOIN shoppingcart sc ON sp.Cart_ID = sc.Cart_ID " +
                "WHERE sc.Customer_ID = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, customerId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("product_count") > 0;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void initializeCart(int customerId, CartController cartController) {
        List<Product> productsInCart = getProductsInCart(customerId);

        for (Product product : productsInCart) {
            try {
                cartController.addProductToCart(product);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    public static Product getProductByBarcode(int barcode) {
        Product product = null;

        String query = "SELECT barcode, name, type, endD, srtartD, price, calories, description, boycott, isEdible, Subcategory_ID, Offer_ID, image_path FROM product WHERE barcode = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, barcode);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    String name = resultSet.getString("name");
                    String type = resultSet.getString("type");
                    Date endD = resultSet.getDate("endD");
                    Date srtartD = resultSet.getDate("srtartD");
                    double price = resultSet.getDouble("price");
                    int calories = resultSet.getInt("calories");
                    String description = resultSet.getString("description");
                    boolean boycott = resultSet.getBoolean("boycott");
                    boolean isEdible = resultSet.getBoolean("isEdible");
                    int subcategoryID = resultSet.getInt("Subcategory_ID");
                    Integer offerID = resultSet.getObject("Offer_ID") != null ? resultSet.getInt("Offer_ID") : null;
                    String imagePath = resultSet.getString("image_path");

                    product = new Product(barcode, name, type, endD, srtartD, price, calories, description, boycott, isEdible, subcategoryID, offerID, imagePath);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return product;
    }

    public static String getUserName(String phone, String password) {
        String userName = "";
        String query = "SELECT Name FROM customer WHERE Phone = ? AND Password = ?";

        try (Connection con = DatabaseConnection.getConnection()) {
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, phone);
            pstmt.setString(2, password);
            ResultSet resultSet = pstmt.executeQuery();
            if (resultSet.next()) {
                userName = resultSet.getString("Name");
            }
            return userName;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

     public static boolean checkPhoneExists(String phone) {
         boolean exists = false;
         String query = "SELECT * FROM customer WHERE Phone =?";

         try (Connection con = DatabaseConnection.getConnection()) {
             PreparedStatement pstmt = con.prepareStatement(query);
             pstmt.setString(1, phone);
             ResultSet resultSet = pstmt.executeQuery();
             exists = resultSet.next();
         } catch (SQLException e) {
             throw new RuntimeException(e);
         }
         return exists;
     }

    public static void createAccount(String name, String phone, String gender, String password) {
        String query = "INSERT INTO customer (Name, Password, Phone, Gender, CouponCode) " +
                "VALUES (?,?,?,?,NULL)";

        try (Connection con = DatabaseConnection.getConnection()) {
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, name);
            pstmt.setString(2, password);
            pstmt.setString(3, phone);
            pstmt.setString(4, gender);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

     }

    public static int getCustomerId(String phone, String password) {
        String query = "SELECT Customer_ID FROM customer WHERE Phone =? AND Password =?";

        try (Connection con = DatabaseConnection.getConnection()) {
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, phone);
            pstmt.setString(2, password);
            ResultSet resultSet = pstmt.executeQuery();
            if(resultSet.next()) {
                return resultSet.getInt("Customer_ID");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return -1;
        }

    public static void sendOrder(Date requestDate, Date arrivalDate, String status, double totalPrice,
                                 String destination, int numOfProducts,String receving, Integer ssn, int customerId) {


        String query = "INSERT INTO orderr (requestdate, arrivaldate, status, totalprice, destination, numofproducts,receving, SSN, Customer_ID) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {


            pstmt.setDate(1, new java.sql.Date(requestDate.getTime()));
            pstmt.setDate(2, new java.sql.Date(arrivalDate.getTime()));
            pstmt.setString(3, status);
            pstmt.setDouble(4, totalPrice);
            pstmt.setString(5, destination);
            pstmt.setInt(6, numOfProducts);
            pstmt.setString(7, receving);


            if (ssn == null) {
                pstmt.setNull(8, java.sql.Types.INTEGER);
            } else {
                pstmt.setInt(8, ssn);
            }

            pstmt.setInt(9, customerId);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Failed to insert order into the database.");
        }
    }


    public List<Category> getAllCategories() {
        List<Category> categories = new ArrayList<Category>();
//        Category(String name, String type, int numOfSubcategory, String imagePath)
        try (Connection con = DatabaseConnection.getConnection()) {
            String query = "select * from category";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                categories.add(new Category(rs.getString("name"), rs.getString("type"), rs.getInt("num_Of_Subcategory"), rs.getString("image_path")));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return categories;
    }

    public List<Product> getProductsOfSubCategory(String subCategoryName) {
        List<Product> products = new ArrayList<Product>();
//        Product(int barcode, String name, String type, String endD, String srtartD, double price, int calories,
//                   String description, boolean boycott, boolean isEdible, int subcategoryID, Integer offerID, String imagePath)
        String query = "SELECT P.barcode, P.name, P.type, P.endD, P.srtartD, P.price, P.calories, P.description, " +
                "P.boycott, P.isEdible, P.subcategory_ID, P.offer_ID, P.image_path " +
                "FROM Category C " +
                "JOIN SubCategory SC ON C.ID = SC.category_ID " +
                "JOIN Product P ON SC.ID = P.subcategory_ID " +
                "WHERE SC.name LIKE ?;";

        try (Connection con = DatabaseConnection.getConnection(); PreparedStatement pstmt = con.prepareStatement(query)) {

            pstmt.setString(1, "%" + subCategoryName + "%");
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                products.add(new Product(
                        rs.getInt("barcode"),
                        rs.getString("name"),
                        rs.getString("type"),
                        rs.getDate("endD"),
                        rs.getDate("srtartD"),
                        rs.getDouble("price"),
                        rs.getInt("calories"),
                        rs.getString("description"),
                        rs.getBoolean("boycott"),
                        rs.getBoolean("isEdible"),
                        rs.getInt("subcategory_ID"),
                        rs.getObject("offer_ID") != null ? rs.getInt("offer_ID") : null,
                        rs.getString("image_path")
                ));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return products;
    }


}
