package Shini;

import javafx.scene.control.DatePicker;

import java.sql.Date;

public class Product {
    private int barcode;
    private String name;
    private String type;
    private Date endD;
    private Date startD;
    private double price;
    private int calories;
    private String size;
    private String description;
    private boolean boycott;
    private boolean isEdible;
    private int subcategoryId;
    private Integer offerId;
    private String imagePath;
    private  boolean shown = true;

    // Constructor
    public Product(int barcode, String name, String type, Date endD, Date startD, double price, int calories,
                   String size, String description, boolean boycott, boolean isEdible, int subcategoryId,
                   Integer offerId, String imagePath) {
        this.barcode = barcode;
        this.name = name;
        this.type = type;
        this.endD = endD;
        this.startD = startD;
        this.price = price;
        this.calories = calories;
        this.size = size;
        this.description = description;
        this.boycott = boycott;
        this.isEdible = isEdible;
        this.subcategoryId = subcategoryId;
        this.offerId = offerId;
        this.imagePath = imagePath;
    }

    public Product(String name, String type, Date endD, Date startD, double price, int calories,
                   String size, String description, boolean boycott, boolean isEdible, int subcategoryId,
                   Integer offerId, String imagePath, boolean shown) {
        this.name = name;
        this.type = type;
        this.endD = endD;
        this.startD = startD;
        this.price = price;
        this.calories = calories;
        this.size = size;
        this.description = description;
        this.boycott = boycott;
        this.isEdible = isEdible;
        this.subcategoryId = subcategoryId;
        this.offerId = offerId;
        this.imagePath = imagePath;
        this.shown = shown;
    }

    // Default constructor
    public Product() {
    }



    // Getters and Setters
    public int getBarcode() {
        return barcode;
    }

    public void setBarcode(int barcode) {
        this.barcode = barcode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getEndD() {
        return endD;
    }

    public void setEndD(Date endD) {
        this.endD = endD;
    }

    public Date getStartD() {
        return startD;
    }

    public void setStartD(Date startD) {
        this.startD = startD;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isBoycott() {
        return boycott;
    }

    public void setBoycott(boolean boycott) {
        this.boycott = boycott;
    }

    public boolean isEdible() {
        return isEdible;
    }

    public void setEdible(boolean edible) {
        isEdible = edible;
    }

    public int getSubcategoryId() {
        return subcategoryId;
    }

    public void setSubcategoryId(int subcategoryId) {
        this.subcategoryId = subcategoryId;
    }

    public Integer getOfferId() {
        return offerId;
    }

    public void setOfferId(Integer offerId) {
        this.offerId = offerId;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public boolean getShown() {
        return shown;
    }

    public void setShown(boolean shown) {
        this.shown = shown;
    }

    // toString Method
    @Override
    public String toString() {
        return "Product{" +
                "barcode='" + barcode + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", endD='" + endD + '\'' +
                ", startD='" + startD + '\'' +
                ", price=" + price +
                ", calories=" + calories +
                ", size='" + size + '\'' +
                ", description='" + description + '\'' +
                ", boycott=" + boycott +
                ", isEdible=" + isEdible +
                ", subcategoryId=" + subcategoryId +
                ", offerId=" + offerId +
                ", imagePath='" + imagePath + '\'' +
                '}';
    }
}
