package Shini.Admin;

public class Subcategory {

    private int id;
    private String name;
    private int categoryId;

    // Default constructor
    public Subcategory() {
    }

    // Parameterized constructor
    public Subcategory(int id, String name, int categoryId) {
        this.id = id;
        this.name = name;
        this.categoryId = categoryId;
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

    // Getter and Setter for Category ID
    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    // toString method
    @Override
    public String toString() {
        return "Subcategory{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", categoryId=" + categoryId +
                '}';
    }
}
