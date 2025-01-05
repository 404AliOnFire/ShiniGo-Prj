package Shini.Admin;

import java.sql.Date;

public class Employee {
    private int ssn;
    private String name;
    private String address;
    private String email;
    private Date birthdayDate;
    private Date hireDate;
    private double salary;
    private String role;
    private String phone;
    private int workShiftTime;
    private int advisor;
    private String gender;

    // Constructor
    public Employee(int ssn, String name, String address, String email, Date birthdayDate,
                    Date hireDate ,double salary, String role, String phone,
                    int workShiftTime, int advisor, String gender) {
        this.ssn = ssn;
        this.name = name;
        this.address = address;
        this.email = email;
        this.birthdayDate = birthdayDate;
        this.salary = salary;
        this.role = role;
        this.phone = phone;
        this.workShiftTime = workShiftTime;
        this.advisor = advisor;
        this.gender = gender;
        this.hireDate = hireDate;
    }

    public Employee(String name, String address, String email, Date birthdayDate,
                    Date hireDate ,double salary, String role, String phone,
                    int workShiftTime, int advisor, String gender) {
        this.name = name;
        this.address = address;
        this.email = email;
        this.birthdayDate = birthdayDate;
        this.salary = salary;
        this.role = role;
        this.phone = phone;
        this.workShiftTime = workShiftTime;
        this.advisor = advisor;
        this.gender = gender;
        this.hireDate = hireDate;
    }


    public int getSsn() {
        return ssn;
    }

    public void setSsn(int ssn) {
        this.ssn = ssn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthdayDate() {
        return birthdayDate;
    }

    public void setBirthdayDate(Date birthdayDate) {
        this.birthdayDate = birthdayDate;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public String getGender() {
        return gender;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;

    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getWorkShiftTime() {
        return workShiftTime;
    }

    public void setWorkShiftTime(int workShiftTime) {
        this.workShiftTime = workShiftTime;
    }

    public int getAdvisor() {
        return advisor;
    }

    public void setAdvisor(int advisor) {
        this.advisor = advisor;
    }
}

