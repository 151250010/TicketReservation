package cn.edu.nju.p.ticketreservation.dao.entity;

public class SeatPrice {

    private int siteId;
    private int siteZ;
    private double price;

    public int getSiteId() {
        return siteId;
    }

    public void setSiteId(int siteId) {
        this.siteId = siteId;
    }

    public int getSiteZ() {
        return siteZ;
    }

    public void setSiteZ(int siteZ) {
        this.siteZ = siteZ;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "SeatPrice{" +
                "siteId=" + siteId +
                ", siteZ=" + siteZ +
                ", price=" + price +
                '}';
    }
}
