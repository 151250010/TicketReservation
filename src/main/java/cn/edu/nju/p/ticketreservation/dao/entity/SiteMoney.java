package cn.edu.nju.p.ticketreservation.dao.entity;

public class SiteMoney {

    private double allMoney;
    private int orderStatus;

    public double getAllMoney() {
        return allMoney;
    }

    public void setAllMoney(double allMoney) {
        this.allMoney = allMoney;
    }

    public int getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
    }

    @Override
    public String toString() {
        return "SiteMoney{" +
                "allMoney=" + allMoney +
                ", orderStatus=" + orderStatus +
                '}';
    }
}
