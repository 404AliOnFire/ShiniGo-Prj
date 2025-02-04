package Shini;

import Shini.Admin.Employee;
import javafx.scene.chart.PieChart;

import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

public class DatabaseHelper {

    public static int getCustomerId(String phone, String password) {
        String query = "SELECT Customer_ID FROM customer WHERE Phone =? AND Password =?";

        try (Connection con = DatabaseConnection.getConnection()) {
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, phone);
            pstmt.setString(2, password);
            ResultSet resultSet = pstmt.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("Customer_ID");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return -1;
    }

    public static void sendOrder(Date requestDate, Date arrivalDate, String status, double totalPrice, String destination, int numOfProducts, String receving, Integer ssn, int customerId) {


        String query = "INSERT INTO orderr (requestdate, arrivaldate, status, totalprice, destination, numofproducts,receving, SSN, Customer_ID) " + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(query)) {


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


    public static boolean addEmployee(Employee employee) {
        String query = "INSERT INTO employee (name, address, email, birthdayDate, hireDate, salary, role, phone, workshiftTime, advisor, gender) " + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {

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

            if (rowsInserted > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int ssn = generatedKeys.getInt(1);
                        employee.setSsn(ssn);
                    }
                }
            }
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean deleteEmployee(int ssn) {
        String query = "DELETE FROM employee WHERE ssn = ?";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, ssn);

            int rowsDeleted = stmt.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean updateEmployee(Employee employee) {
        String query = "UPDATE employee SET name = ?, address = ?, email = ?, birthdayDate = ?, hireDate = ?, salary = ?, " + "role = ?, phone = ?, workshiftTime = ?, advisor = ?, gender = ? WHERE ssn = ?";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

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
            stmt.setInt(12, employee.getSsn());

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

        try (Connection connection = DatabaseConnection.getConnection(); PreparedStatement statement = connection.prepareStatement(query); ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Employee employee = new Employee(resultSet.getInt("ssn"), resultSet.getString("name"), resultSet.getString("address"), resultSet.getString("email"), resultSet.getDate("birthdayDate"), resultSet.getDate("hireDate"), resultSet.getDouble("salary"), resultSet.getString("role"), resultSet.getString("phone"), resultSet.getInt("workshifttime"), resultSet.getInt("advisor"), resultSet.getString("gender"));
                employees.add(employee);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }

    public static List<Product> getProductsInCart(int customerId) {
        List<Product> products = new ArrayList<>();
        String query = "SELECT p.* FROM product p " + "JOIN shoppingcart2product sp ON p.barcode = sp.barcode " + "JOIN shoppingcart sc ON sc.Cart_ID = sp.Cart_ID " + "WHERE sc.Customer_ID = ?";

        try (Connection connection = DatabaseConnection.getConnection(); PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, customerId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Product product = new Product(resultSet.getInt("barcode"), resultSet.getString("name"), resultSet.getString("type"), resultSet.getDate("endD"), resultSet.getDate("startD"), resultSet.getDouble("price"), resultSet.getInt("calories"), resultSet.getString("size"), resultSet.getString("description"), resultSet.getBoolean("boycott"), resultSet.getBoolean("isEdible"), resultSet.getInt("Subcategory_ID"), resultSet.getObject("Offer_ID", Integer.class), resultSet.getString("image_path"));
                    products.add(product);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    public static boolean isCartNotEmpty(int customerId) {
        String query = "SELECT COUNT(*) AS product_count FROM shoppingcart2product sp " + "JOIN shoppingcart sc ON sp.Cart_ID = sc.Cart_ID " + "WHERE sc.Customer_ID = ?";

        try (Connection connection = DatabaseConnection.getConnection(); PreparedStatement statement = connection.prepareStatement(query)) {

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

    public static void initializeCart(int customerId, Controller controller) {
        List<Product> productsInCart = getProductsInCart(customerId);

        for (Product product : productsInCart) {
            try {
                controller.addProductToCart(product);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    public static Product getProductByBarcode(int barcode) {
        Product product = null;

        String query = "SELECT barcode, name, type, endD, startD, price, calories,size, description, boycott, isEdible, Subcategory_ID, Offer_ID, image_path FROM product WHERE barcode = ?";

        try (Connection connection = DatabaseConnection.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, barcode);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    String name = resultSet.getString("name");
                    String type = resultSet.getString("type");
                    Date endD = resultSet.getDate("endD");
                    Date startD = resultSet.getDate("startD");
                    double price = resultSet.getDouble("price");
                    int calories = resultSet.getInt("calories");
                    String size = resultSet.getString("size");
                    String description = resultSet.getString("description");
                    boolean boycott = resultSet.getBoolean("boycott");
                    boolean isEdible = resultSet.getBoolean("isEdible");
                    int subcategoryID = resultSet.getInt("Subcategory_ID");
                    Integer offerID = resultSet.getObject("Offer_ID") != null ? resultSet.getInt("Offer_ID") : null;
                    String imagePath = resultSet.getString("image_path");

                    product = new Product(barcode, name, type, endD, startD, price, calories, size, description, boycott, isEdible, subcategoryID, offerID, imagePath);
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
        String query = "INSERT INTO customer (Name, Password, Phone, Gender, CouponCode) " + "VALUES (?,?,?,?,NULL)";

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

    public static List<Product> getAllProducts() {
        List<Product> allProducts = new ArrayList<>();
//        List<Map<String, Object>> allProducts = new ArrayList<>();
        String query = "Select * from Product";

        try(Connection con = DatabaseConnection.getConnection(); PreparedStatement pstmt= con.prepareStatement(query)){

            ResultSet rs = pstmt.executeQuery();


            while (rs.next()) {
                allProducts.add(new Product(rs.getInt("barcode"),
                        rs.getString("name"),
                        rs.getString("type"),
                        rs.getDate("endD"),
                        rs.getDate("startD"),
                        rs.getDouble("price"),
                        rs.getInt("calories"),
                        rs.getString("size"),
                        rs.getString("description"),
                        rs.getBoolean("boycott"),
                        rs.getBoolean("isEdible"),
                        rs.getInt("subcategory_ID"),
                        rs.getObject("offer_ID") != null ? rs.getInt("offer_ID") : null, rs.getString("image_path")));
            }


        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return allProducts;
    }

    public static void deleteProduct(int barcode) {
        String query = "DELETE FROM Product WHERE barcode = ?";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, barcode);

            int rowsDeleted = stmt.executeUpdate();
//            return rowsDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
//            return false;
        }
    }

    public static List<Integer> getAllSubCategoriesId() {
        List<Integer> subCategoriesId = new ArrayList<>();

        String query = "select ID from subCategory";
        try(Connection con = DatabaseConnection.getConnection(); PreparedStatement pstmt = con.prepareStatement(query)) {
            ResultSet rs = pstmt.executeQuery();

            while(rs.next()){
                subCategoriesId.add(rs.getInt("ID"));
            }

        }catch (SQLException e) {
            e.printStackTrace();
        }


        return subCategoriesId;
    }

    public static List<Integer> getAllOfferId() {
        List<Integer> offerId = new ArrayList<>();

        String query = "select offer_ID from Offer";
        try(Connection con = DatabaseConnection.getConnection(); PreparedStatement pstmt = con.prepareStatement(query)) {
            ResultSet rs = pstmt.executeQuery();

            while(rs.next()){
                offerId.add(rs.getInt(1));
            }

        }catch (SQLException e) {
            e.printStackTrace();
        }


        return offerId;
    }

    public static void addProduct(Product newProduct) {
//        (String name, String type, Date endD, Date startD, double price, int calories,
//        String size, String description, boolean boycott, boolean isEdible, int subcategoryId,
//        Integer offerId, String imagePath, boolean shown)
        String query = "INSERT INTO Product (name, type, endD, startD, price, calories, size, description, boycott, isEdible, subcategory_Id, offer_Id, image_Path, shown) " + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?,?)";
        try (Connection con = DatabaseConnection.getConnection(); PreparedStatement pstmt = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, newProduct.getName());
            pstmt.setString(2, newProduct.getType());
            pstmt.setDate(3, newProduct.getEndD());
            pstmt.setDate(4, newProduct.getStartD());
            pstmt.setDouble(5, newProduct.getPrice());
            pstmt.setInt(6, newProduct.getCalories());
            pstmt.setString(7, newProduct.getSize());
            pstmt.setString(8, newProduct.getDescription());
            pstmt.setBoolean(9, newProduct.isBoycott());
            pstmt.setBoolean(10, newProduct.isEdible());
            pstmt.setInt(11, newProduct.getSubcategoryId());
            pstmt.setInt(12, newProduct.getOfferId());
            pstmt.setString(13, newProduct.getImagePath());
            pstmt.setBoolean(14, newProduct.getShown());

            int rowsInserted = pstmt.executeUpdate();

            if (rowsInserted > 0) {
                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int barCode = generatedKeys.getInt(1);
                        newProduct.setBarcode(barCode);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void updateProduct(Product updatedProduct) {
//        (String name, String type, Date endD, Date startD, double price, int calories,
//        String size, String description, boolean boycott, boolean isEdible, int subcategoryId,
//        Integer offerId, String imagePath, boolean shown)
        String query = "UPDATE Product SET name = ?, type = ?, endD = ?, startD = ?, price = ?, calories = ?, " +
                "size = ?, description = ?, boycott = ?, isEdible = ?, subcategory_Id = ?, offer_Id = ? ,image_Path = ? , shown = ? WHERE barcode = ?";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, updatedProduct.getName());
            stmt.setString(2, updatedProduct.getType());
            stmt.setDate(3, updatedProduct.getEndD());
            stmt.setDate(4, updatedProduct.getStartD());
            stmt.setDouble(5, updatedProduct.getPrice());
            stmt.setInt(6, updatedProduct.getCalories());
            stmt.setString(7, updatedProduct.getSize());
            stmt.setString(8, updatedProduct.getDescription());
            stmt.setBoolean(9, updatedProduct.isBoycott());
            stmt.setBoolean(10, updatedProduct.isEdible());
            stmt.setInt(11, updatedProduct.getSubcategoryId());
            stmt.setInt(12, updatedProduct.getOfferId());
            stmt.setString(13, updatedProduct.getImagePath());
            stmt.setBoolean(14, updatedProduct.getShown());
            stmt.setInt(15, updatedProduct.getBarcode());

            stmt.executeUpdate();
            System.out.println("updated successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public List<Order> getOrders(String phone) {
        List<Order> orders = new ArrayList<Order>();
        String query = "SELECT O.ID, O.requestdate, O.arrivaldate, O.status, O.totalprice, O.destination, O.numofproducts,O.receiving, O.SSN, O.Customer_ID FROM orderr O, Customer C " + "WHERE C.Phone LIKE ? " + "AND O.Customer_ID = C.Customer_ID " + "AND O.receiving LIKE 'اونلاين';";
        try (Connection con = DatabaseConnection.getConnection(); PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setString(1, "%" + phone + "%");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                orders.add(new Order(rs.getInt("ID"), rs.getDate("requestdate"), rs.getDate("arrivaldate"), rs.getString("status"), rs.getDouble("totalprice"), rs.getString("destination"), rs.getInt("numofproducts"), rs.getString("receiving"), rs.getInt("SSN"), rs.getInt("Customer_ID")));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return orders;
    }

    public List<Order> getOrders1(String phone) {
        List<Order> orders = new ArrayList<Order>();
        String query = "SELECT O.ID, O.requestdate, O.arrivaldate, O.status, O.totalprice, O.destination, O.numofproducts,O.receiving, O.SSN, O.Customer_ID FROM orderr O, Customer C " + "WHERE C.Phone LIKE ? " + "AND O.Customer_ID = C.Customer_ID " + "AND O.receiving LIKE 'المتجر';";
        try (Connection con = DatabaseConnection.getConnection(); PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setString(1, "%" + phone + "%");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                orders.add(new Order(rs.getInt("ID"), rs.getDate("requestdate"), rs.getDate("arrivaldate"), rs.getString("status"), rs.getDouble("totalprice"), rs.getString("destination"), rs.getInt("numofproducts"), rs.getString("receiving"), rs.getInt("SSN"), rs.getInt("Customer_ID")));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return orders;
    }

    public static List<Category> getAllCategories() {
        List<Category> categories = new ArrayList<Category>();
//        Category(String name, String type, int numOfSubcategory, String imagePath)
        try (Connection con = DatabaseConnection.getConnection(); Statement stmt = con.createStatement();) {
            String query = "select * from category";
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
        String query = "SELECT P.barcode, P.name, P.type, P.endD, P.startD, P.price, P.calories, P.size ,P.description, " + "P.boycott, P.isEdible, P.subcategory_ID, P.offer_ID, P.image_path " + "FROM Category C " + "JOIN SubCategory SC ON C.ID = SC.category_ID " + "JOIN Product P ON SC.ID = P.subcategory_ID " + "WHERE SC.name LIKE ? and P.shown = 1;";

        try (Connection con = DatabaseConnection.getConnection(); PreparedStatement pstmt = con.prepareStatement(query)) {

            pstmt.setString(1, "%" + subCategoryName + "%");
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                products.add(new Product(rs.getInt("barcode"), rs.getString("name"), rs.getString("type"), rs.getDate("endD"), rs.getDate("startD"), rs.getDouble("price"), rs.getInt("calories"), rs.getString("size"), rs.getString("description"), rs.getBoolean("boycott"), rs.getBoolean("isEdible"), rs.getInt("subcategory_ID"), rs.getObject("offer_ID") != null ? rs.getInt("offer_ID") : null, rs.getString("image_path")));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return products;
    }

    public List<Product> getAllProductsOfCategory(String categName) {

        List<Product> products = new ArrayList<Product>();

        if(categName.equals("العروض")){
            products = getAllProductHaveOffer();

            return products;
        }

        String query = """
                SELECT P.barcode, P.name, P.type, P.endD, P.startD, P.price, P.calories, P.size, 
                       P.description, P.boycott, P.isEdible, P.subcategory_ID, P.offer_ID, P.image_path
                FROM Product P
                JOIN Subcategory SC ON P.Subcategory_ID = SC.id
                JOIN Category C ON SC.category_ID = C.ID
                WHERE C.ID = (SELECT ID FROM Category WHERE name = ?)
                and P.shown = 1;
                """;
        try (Connection con = DatabaseConnection.getConnection(); PreparedStatement pstmt = con.prepareStatement(query)) {

            pstmt.setString(1, categName);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                products.add(new Product(rs.getInt("barcode"), rs.getString("name"), rs.getString("type"), rs.getDate("endD"), rs.getDate("startD"), rs.getDouble("price"), rs.getInt("calories"), rs.getString("size"), rs.getString("description"), rs.getBoolean("boycott"), rs.getBoolean("isEdible"), rs.getInt("subcategory_ID"), rs.getObject("offer_ID") != null ? rs.getInt("offer_ID") : null, rs.getString("image_path")));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return products;
    }


    public Offer getDiscountPercentage(int offerNum) {

        Offer offer = new Offer();
        String query = "Select * from offer where Offer_ID = ?;";

//        Offer (Offer_ID, StartDate, EndDate, Percentage)
        try (Connection con = DatabaseConnection.getConnection(); PreparedStatement pstmt = con.prepareStatement(query)) {

            pstmt.setInt(1, offerNum);
            ResultSet rs = pstmt.executeQuery();


            if (rs.next()) {
                offer.setOfferId(rs.getInt("offer_ID"));
                offer.setStartDate(rs.getDate("StartDate"));
                offer.setEndDate(rs.getDate("EndDate"));
                offer.setPercentage(rs.getDouble("Percentage"));
            }


        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return offer;
    }


    public List<String> getSubCategories(String categName) {
        List<String> subCategories = new ArrayList<>();

        String query = "select SC.name \n" + "from Subcategory SC, Category C\n" + "where Sc.category_ID = c.ID\n" + "and c.ID = (select ID from Category where name = ? );";


        try (Connection con = DatabaseConnection.getConnection(); PreparedStatement pstmt = con.prepareStatement(query)) {

            pstmt.setString(1, categName);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                subCategories.add(rs.getString("name"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return subCategories;
    }


    public void insertProductToCart(int customerId, Product product) throws SQLException {
//        Connection connection = null;
        double price = 0;
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;
        customerId = 1;
//        System.out.println("customerId: "+customerId);
//      Step 1: Check if the cart exists for the customer
        String findCustomerCart = "select cart_Id from shoppingCart where customer_Id = ? ;";

        try (Connection connection = DatabaseConnection.getConnection()) {

            pstmt = connection.prepareStatement(findCustomerCart);
            pstmt.setInt(1, customerId);
            resultSet = pstmt.executeQuery();


            int cartID;
            if (resultSet.next()) {
                cartID = resultSet.getInt("cart_id");
            } else {
                // Step 2: Create a new cart for the customer
                String createShoppingCartForCustomer = "INSERT INTO ShoppingCart (Customer_ID) VALUES (?);";
                pstmt = connection.prepareStatement(createShoppingCartForCustomer, Statement.RETURN_GENERATED_KEYS);
                pstmt.setInt(1, customerId);
                pstmt.executeUpdate();
                resultSet = pstmt.getGeneratedKeys();

                if (resultSet.next()) {
                    cartID = resultSet.getInt(1);
//                    System.out.println("cartID: "+cartID);
                } else {
                    throw new SQLException("Failed to create shopping cart.");
                }
            }

            String checkIfProductAdded = "select count(*) from ShoppingCart2Product where cart_ID = ? and barcode = ? ";
            pstmt = connection.prepareStatement(checkIfProductAdded);
            pstmt.setInt(1, cartID);
            pstmt.setInt(2, product.getBarcode());

            resultSet = pstmt.executeQuery();
            if (resultSet.next() && resultSet.getInt("count(*)") == 0) {
                // Step 3: Add the product to ShoppingCart2Product
                String addProduct = "insert into ShoppingCart2Product (cart_ID, barcode) values (?, ?)";
                price = product.getPrice();

                if (product.getOfferId() != null && product.getOfferId() > 0) {
                    LocalDate currentDate = LocalDate.now();
                    Offer offer = getDiscountPercentage(product.getOfferId());

                    if (offer.getOfferId() != 0 && currentDate.isBefore(offer.getEndDate().toLocalDate())) {
                        double prevPrice = product.getPrice();
                        price = price * (1 - offer.getPercentage() / 100);
                    }
                }
                pstmt = connection.prepareStatement(addProduct);
                pstmt.setDouble(1, cartID);
                pstmt.setInt(2, product.getBarcode());

                pstmt.executeUpdate();

            }
            // Step 4: Update the TotalPrice and NumberOfOrders in ShoppingCart
            String updateShoppingCart = "Update ShoppingCart set totalPrice = totalPrice + ?, numberOfOrders = numberOfOrders + 1 where cart_Id = ?; ";

            pstmt = connection.prepareStatement(updateShoppingCart);
            pstmt.setDouble(1, price);
            pstmt.setInt(2, cartID);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        pstmt.close();
        resultSet.close();
    }

    public List<Product> getAllProductHaveOffer() {
        List<Product> productsWithOffer = new ArrayList<>();

        String query = "select * from Product where offer_Id > 0 and P.shown = 1";
        try(Connection con = DatabaseConnection.getConnection(); PreparedStatement pstmt = con.prepareStatement(query)){

            ResultSet rs =  pstmt.executeQuery();
            while (rs.next()) {
                productsWithOffer.add(new Product(rs.getInt("barcode"),
                        rs.getString("name"),
                        rs.getString("type"),
                        rs.getDate("endD"),
                        rs.getDate("startD"),
                        rs.getDouble("price"),
                        rs.getInt("calories"),
                        rs.getString("size"),
                        rs.getString("description"),
                        rs.getBoolean("boycott"),
                        rs.getBoolean("isEdible"),
                        rs.getInt("subcategory_ID"),
                        rs.getObject("offer_ID") != null ? rs.getInt("offer_ID") : null, rs.getString("image_path")));
            }

        }catch (SQLException ex) {
            ex.printStackTrace();
        }


        return productsWithOffer;
    }

    public List<Product> getWantedProduct(String input) {
        List<Product> wantedProducts = new ArrayList<>();
        String query;
        boolean isTextSearch = input.matches("[\\u0600-\\u06FF\\s]+");

        // Choose query based on input type
        if (isTextSearch) {
            query = "SELECT * FROM Product P WHERE (P.name LIKE ? OR P.type LIKE ?) and P.shown = 1";
        } else {
            query = "SELECT * FROM Product P WHERE P.calories = ? and P.shown = 1";
        }

        // Execute query
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement pstmt = con.prepareStatement(query)) {

            if (isTextSearch) {
                pstmt.setString(1, "%" + input + "%");
                pstmt.setString(2, "%" + input + "%");
            } else {
                pstmt.setInt(1, Integer.parseInt(input)); // Parse input to integer for calories
            }

            try (ResultSet rs = pstmt.executeQuery()) { // Use try-with-resources for ResultSet
                while (rs.next()) {
                    wantedProducts.add(new Product(
                            rs.getInt("barcode"),
                            rs.getString("name"),
                            rs.getString("type"),
                            rs.getDate("endD"),
                            rs.getDate("startD"),
                            rs.getDouble("price"),
                            rs.getInt("calories"),
                            rs.getString("size"),
                            rs.getString("description"),
                            rs.getBoolean("boycott"),
                            rs.getBoolean("isEdible"),
                            rs.getInt("subcategory_ID"),
                            rs.getObject("offer_ID") != null ? rs.getInt("offer_ID") : null,
                            rs.getString("image_path")
                    ));
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace(); // Consider logging instead of printing stack trace in production
        } catch (NumberFormatException ex) {
            System.err.println("Invalid numeric input for calories: " + input); // Handle invalid numeric input
        }

        return wantedProducts;
    }



}
