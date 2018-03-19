package cn.edu.nju.p.ticketreservation.dao.entity;

import cn.edu.nju.p.ticketreservation.interact.input.PlanForm;

public class OrderEntity {

    private UserInfo userInfo;
    private Site site;
    private PlanForm planForm;
    private String seats;
    private int orderType;
    private int orderId;
    private double totalMoney;
    private int orderStatus;


    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public Site getSite() {
        return site;
    }

    public void setSite(Site site) {
        this.site = site;
    }

    public PlanForm getPlanForm() {
        return planForm;
    }

    public void setPlanForm(PlanForm planForm) {
        this.planForm = planForm;
    }

    public String getSeats() {
        return seats;
    }

    public void setSeats(String seats) {
        this.seats = seats;
    }

    public int getOrderType() {
        return orderType;
    }

    public void setOrderType(int orderType) {
        this.orderType = orderType;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(double totalMoney) {
        this.totalMoney = totalMoney;
    }

    public int getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
    }
}
