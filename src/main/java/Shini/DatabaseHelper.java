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
        String query = "SELECT P.barcode, P.name, P.type, P.endD, P.startD, P.price, P.calories, P.size,P.description, " +
                "P.boycott, P.isEdible, P.subcategory_ID, P.offer_ID, P.image_path " +
                "FROM Category C " +
                "JOIN SubCategory SC ON C.ID = SC.category_ID " +
                "JOIN Product P ON SC.ID = P.subcategory_ID " +
                "WHERE SC.name LIKE ?;";

        try(Connection con = DatabaseConnection.getConnection(); PreparedStatement pstmt = con.prepareStatement(query)){

            pstmt.setString(1, "%" + subCategoryName + "%");
            ResultSet rs = pstmt.executeQuery();

//            (String barcode, String name, String type, String endD, String startD, double price, int calories,
//            String size, String description, boolean boycott, boolean isEdible, int subcategoryId,
//            int offerId, String imagePath)

            while (rs.next()) {
                products.add(new Product(
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

        }catch(SQLException ex){
            ex.printStackTrace();
        }

        return products;
    }

    public List<Product> getAllProductsOfSubCategory(String categName){
        System.out.println(categName);
        List<Product> products = new ArrayList<Product>();
//        Product(int barcode, String name, String type, String endD, String srtartD, double price, int calories,
//                   String description, boolean boycott, boolean isEdible, int subcategoryID, Integer offerID, String imagePath)
        String query = """
            SELECT P.barcode, P.name, P.type, P.endD, P.startD, P.price, P.calories, P.size, 
                   P.description, P.boycott, P.isEdible, P.subcategory_ID, P.offer_ID, P.image_path
            FROM Product P
            JOIN Subcategory SC ON P.Subcategory_ID = SC.id
            JOIN Category C ON SC.category_ID = C.ID
            WHERE C.ID = (SELECT ID FROM Category WHERE name = ?);
            """;
        try(Connection con = DatabaseConnection.getConnection(); PreparedStatement pstmt = con.prepareStatement(query)){

            pstmt.setString(1, categName);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                products.add(new Product(
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

        }catch(SQLException ex){
            ex.printStackTrace();
        }

        return products;
    }


    public Offer getDiscountPercentage(int offerNum){

        Offer offer = new Offer();
        String query = "Select * from offer where Offer_ID = ?;";

//        Offer (Offer_ID, StartDate, EndDate, Percentage)
        try(Connection con = DatabaseConnection.getConnection();
            PreparedStatement pstmt = con.prepareStatement(query)){

            pstmt.setInt(1, offerNum);
            ResultSet rs = pstmt.executeQuery();


                if(rs.next()){
                    offer.setOfferId(rs.getInt("offer_ID"));
                    offer.setStartDate(rs.getDate("StartDate"));
                    offer.setEndDate(rs.getDate("EndDate"));
                    offer.setPercentage(rs.getDouble("Percentage"));
                }


        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return offer;
    }


    public List<String> getSubCategories(String categName) {
        List<String> subCategories = new ArrayList<>();

        String query = "select SC.name \n" +
                "from Subcategory SC, Category C\n" +
                "where Sc.category_ID = c.ID\n" +
                "and c.ID = (select ID from Category where name = ? );";


        try(Connection con = DatabaseConnection.getConnection(); PreparedStatement pstmt = con.prepareStatement(query)){

            pstmt.setString(1, categName);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                subCategories.add(rs.getString("name"));
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }

        return subCategories;
    }
}
