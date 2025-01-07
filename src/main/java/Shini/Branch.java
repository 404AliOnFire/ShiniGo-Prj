package Shini;

public class Branch {
    private int id;
    private String isactive;
    private String location;
    private int phone;
    private int numberofemp;
    private double profits;

    public Branch(int id,String isactive,String location,int phone,int numberofemp, double profits) {
        this.id = id;
        this.isactive = isactive;
        this.location = location;
        this.phone = phone;
        this.numberofemp = numberofemp;
        this.profits = profits;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIsactive() {
        return isactive;
    }

    public void setIsactive(String isactive) {
        this.isactive = isactive;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public int getNumberofemp() {
        return numberofemp;
    }

    public void setNumberofemp(int numberofemp) {
        this.numberofemp = numberofemp;
    }

    public double getProfits() {
        return profits;
    }

    public void setProfits(double profits) {
        this.profits = profits;
    }

}
