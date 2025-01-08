package Shini.Admin;

public class Category {

    private int id;
    private String name;
    private String type;
    private int numOfSubcategory;
    private String imagePath;

    // Default constructor
    public Category() {
    }

    // Parameterized constructor
    public Category(int id, String name, String type, int numOfSubcategory, String imagePath) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.numOfSubcategory = numOfSubcategory;
        this.imagePath = imagePath;
    }

    // Getter and Setter for ID
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Getter and Setter for Name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Getter and Setter for Type
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    // Getter and Setter for Number of Subcategories
    public int getNumOfSubcategory() {
        return numOfSubcategory;
    }

    public void setNumOfSubcategory(int numOfSubcategory) {
        this.numOfSubcategory = numOfSubcategory;
    }

    // Getter and Setter for Image Path
    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    // toString method
    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", numOfSubcategory=" + numOfSubcategory +
                ", imagePath='" + imagePath + '\'' +
                '}';
    }
}
