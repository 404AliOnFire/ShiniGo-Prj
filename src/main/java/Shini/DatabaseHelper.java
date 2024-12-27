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

//    private Connection connection;

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
}
