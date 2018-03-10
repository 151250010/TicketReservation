package cn.edu.nju.p.ticketreservation.dao.entity;

public class PlanSeatPrice {

    private int planId;
    private int siteId;
    private int seatZ;
    private double price;

    public int getPlanId() {
        return planId;
    }

    public void setPlanId(int planId) {
        this.planId = planId;
    }

    public int getSiteId() {
        return siteId;
    }

    public void setSiteId(int siteId) {
        this.siteId = siteId;
    }

    public int getSeatZ() {
        return seatZ;
    }

    public void setSeatZ(int seatZ) {
        this.seatZ = seatZ;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "PlanSeatPrice{" +
                "planId=" + planId +
                ", siteId=" + siteId +
                ", seatZ=" + seatZ +
                ", price=" + price +
                '}';
    }
}
