package Shini.Admin;

public class Customer {

    private int customerId;
    private int numberOfOrders;
    private String name;
    private String password;
    private String phone;
    private String email;
    private Integer loginId;
    private String couponCode;

    // Default constructor
    public Customer() {
    }

    // Parameterized constructor
    public Customer(int customerId, int numberOfOrders, String name, String password, String phone, String email, Integer loginId, String couponCode) {
        this.customerId = customerId;
        this.numberOfOrders = numberOfOrders;
        this.name = name;
        this.password = password;
        this.phone = phone;
        this.email = email;
        this.loginId = loginId;
        this.couponCode = couponCode;
    }

    // Getter and Setter for Customer ID
    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    // Getter and Setter for Number of Orders
    public int getNumberOfOrders() {
        return numberOfOrders;
    }

    public void setNumberOfOrders(int numberOfOrders) {
        this.numberOfOrders = numberOfOrders;
    }

    // Getter and Setter for Name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Getter and Setter for Password
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Getter and Setter for Phone
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    // Getter and Setter for Email
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Getter and Setter for Login ID
    public Integer getLoginId() {
        return loginId;
    }

    public void setLoginId(Integer loginId) {
        this.loginId = loginId;
    }

    // Getter and Setter for Coupon Code
    public String getCouponCode() {
        return couponCode;
    }

    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }

    // toString method
    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", numberOfOrders=" + numberOfOrders +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", loginId=" + loginId +
                ", couponCode='" + couponCode + '\'' +
                '}';
    }
}

