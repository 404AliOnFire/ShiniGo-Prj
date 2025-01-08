package Shini;

import java.sql.Date;

public class Offer {

    private int offerId;
    private Date startDate;
    private Date endDate;
    private double percentage;

    // Constructor
    public Offer(int offerId, Date startDate, Date endDate, double percentage) {
        this.offerId = offerId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.percentage = percentage;
    }

    // Default Constructor
    public Offer() {
    }

    // Getters and Setters
    public int getOfferId() {
        return offerId;
    }

    public void setOfferId(int offerId) {
        this.offerId = offerId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }

    // toString Method
    @Override
    public String toString() {
        return "Offer{" +
                "offerId=" + offerId +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", percentage=" + percentage +
                '}';
    }
}