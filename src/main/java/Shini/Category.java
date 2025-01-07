package Shini;

public class Category {
    private String name;
    private String type;
    private int numOfSubcategory;
    private String imagePath;

    public Category(String name, String type, int numOfSubcategory, String imagePath) {
        this.name = name;
        this.type = type;
        this.numOfSubcategory = numOfSubcategory;
        this.imagePath = imagePath;
    }

    // Getter and Setter methods
    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public int getNumOfSubcategory() {
        return numOfSubcategory;
    }

    public String getImagePath() {
        return imagePath;
    }
}