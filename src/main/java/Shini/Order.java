package Shini;

import java.sql.Date;

public class Order {
    private int id;
    private Date requestd;
    private Date arrivald;
    private String status;
    private double totalprice;
    private String destination;
    private int numofproduct;
    private String receiving;
    private int DEssn;
    private int Cusomer_id;


    public Order(int id,Date requestd,Date arrivald,String status,double totalprice,String destination,int numofproduct,String receiving,int DEssn, int Cusomer_id) {
        this.id = id;
        this.requestd = requestd;
        this.arrivald = arrivald;
        this.status = status;
        this.totalprice = totalprice;
        this.destination = destination;
        this.numofproduct = numofproduct;
        this.receiving=receiving;
        this.DEssn = DEssn;
        this.Cusomer_id = Cusomer_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getRequestd() {
        return requestd;
    }

    public void setRequestd(Date requestd) {
        this.requestd = requestd;
    }

    public Date getArrivald() {
        return arrivald;
    }

    public void setArrivald(Date arrivald) {
        this.arrivald = arrivald;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(double totalprice) {
        this.totalprice = totalprice;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public int getNumofproduct() {
        return numofproduct;
    }

    public void setNumofproduct(int numofproduct) {
        this.numofproduct = numofproduct;
    }

    public int getDEssn() {
        return DEssn;
    }

    public void setDEssn(int DEssn) {
        this.DEssn = DEssn;
    }

    public int getCusomer_id() {
        return Cusomer_id;
    }

    public void setCusomer_id(int cusomer_id) {
        Cusomer_id = cusomer_id;
    }

    public String getReceiving() {
        return receiving;
    }

    public void setReceiving(String receiving) {
        this.receiving = receiving;
    }
}
