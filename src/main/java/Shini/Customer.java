package Shini;

public class Customer {
    private int Customer_ID;
    private int NumberOfOrders;
    private String Name;
    private String Password;
    private String Phone;
    private String location;
    private String gender;
    private int Login_ID;
    private String CouponCode;

    public Customer() {
    }

    public Customer(int Customer_ID, int NumberOfOrders, String Name, String Password, String Phone, String location,String gender, int Login_ID, String CouponCode) {
        this.Customer_ID = Customer_ID;
        this.NumberOfOrders = NumberOfOrders;
        this.Name = Name;
        this.Password = Password;
        this.Phone = Phone;
        this.location = location;
        this.gender = gender;
        this.Login_ID = Login_ID;
        this.CouponCode = CouponCode;
    }

    public int getCustomer_ID() {
        return Customer_ID;
    }

    public void setCustomer_ID(int customer_ID) {
        Customer_ID = customer_ID;
    }

    public int getNumberOfOrders() {
        return NumberOfOrders;
    }

    public void setNumberOfOrders(int numberOfOrders) {
        NumberOfOrders = numberOfOrders;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getLogin_ID() {
        return Login_ID;
    }

    public void setLogin_ID(int login_ID) {
        Login_ID = login_ID;
    }

    public String getCouponCode() {
        return CouponCode;
    }

    public void setCouponCode(String couponCode) {
        CouponCode = couponCode;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
