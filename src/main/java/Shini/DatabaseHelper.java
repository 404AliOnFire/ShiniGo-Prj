package Shini;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper {
    public static boolean checkUserExists(String phone, String password) {
        boolean exists = false;
        String query = "SELECT * FROM customer WHERE Phone =? AND Password =?";

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

         try(Connection con = DatabaseConnection.getConnection()) {
             PreparedStatement pstmt = con.prepareStatement(query);
             pstmt.setString(1, phone);
             pstmt.setString(2, password);
             ResultSet resultSet = pstmt.executeQuery();
             if(resultSet.next()) {
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
    public static void createAccount(String name, String phone, String gender, String password){
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

    public List<Category> getAllCategories(){
        List<Category> categories = new ArrayList<Category>();
//        Category(String name, String type, int numOfSubcategory, String imagePath)
        try(Connection con = DatabaseConnection.getConnection()){
            String query = "select * from category";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while(rs.next()){
                categories.add(new Category(rs.getString("name"), rs.getString("type"), rs.getInt("num_Of_Subcategory"), rs.getString("image_path")));
            }

        }catch(SQLException ex){
            ex.printStackTrace();
        }

        return categories;
    }

    public List<Product> getProductsOfSubCategory(String subCategoryName){
        List<Product> products = new ArrayList<Product>();
//        Product(int barcode, String name, String type, String endD, String srtartD, double price, int calories,
//                   String description, boolean boycott, boolean isEdible, int subcategoryID, Integer offerID, String imagePath)
        String query = "SELECT P.barcode, P.name, P.type, P.endD, P.srtartD, P.price, P.calories, P.description, " +
                "P.boycott, P.isEdible, P.subcategory_ID, P.offer_ID, P.image_path " +
                "FROM Category C " +
                "JOIN SubCategory SC ON C.ID = SC.category_ID " +
                "JOIN Product P ON SC.ID = P.subcategory_ID " +
                "WHERE SC.name LIKE ?;";

        try(Connection con = DatabaseConnection.getConnection(); PreparedStatement pstmt = con.prepareStatement(query)){

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

        }catch(SQLException ex){
            ex.printStackTrace();
        }

        return products;
    }



}
