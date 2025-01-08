package Shini;


import java.sql.Date;

public class Product {
    private int barcode;
    private String name;
    private String type;
    private Date endD; // Use java.util.Date or java.time.LocalDate if needed
    private Date srtartD; // Use java.util.Date or java.time.LocalDate if needed
    private double price;
    private int calories;
    private String description;
    private boolean boycott;
    private boolean isEdible;
    private int subcategoryID;
    private Integer offerID; // Nullable if no offer is present
    private String imagePath;


    // Parameterized constructor
    public Product(int barcode, String name, String type, Date endD, Date srtartD, double price, int calories,
                   String description, boolean boycott, boolean isEdible, int subcategoryID, Integer offerID, String imagePath) {
        this.barcode = barcode;
        this.name = name;
        this.type = type;
        this.endD = endD;
        this.srtartD = srtartD;
        this.price = price;
        this.calories = calories;
        this.description = description;
        this.boycott = boycott;
        this.isEdible = isEdible;
        this.subcategoryID = subcategoryID;
        this.offerID = offerID;
        this.imagePath = imagePath;
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

    public Date getSrtartD() {
        return srtartD;
    }

    public void setSrtartD(Date srtartD) {
        this.srtartD = srtartD;
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

    public void setEdible(boolean isEdible) {
        this.isEdible = isEdible;
    }

    public int getSubcategoryID() {
        return subcategoryID;
    }

    public void setSubcategoryID(int subcategoryID) {
        this.subcategoryID = subcategoryID;
    }

    public Integer getOfferID() {
        return offerID;
    }

    public void setOfferID(Integer offerID) {
        this.offerID = offerID;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    @Override
    public String toString() {
        return "Product{" +
                "barcode=" + barcode +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", endD='" + endD + '\'' +
                ", srtartD='" + srtartD + '\'' +
                ", price=" + price +
                ", calories=" + calories +
                ", description='" + description + '\'' +
                ", boycott=" + boycott +
                ", isEdible=" + isEdible +
                ", subcategoryID=" + subcategoryID +
                ", offerID=" + offerID +
                ", imagePath='" + imagePath + '\'' +
                '}';
    }
}
