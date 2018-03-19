package cn.edu.nju.p.ticketreservation.interact.display;

import cn.edu.nju.p.ticketreservation.dao.entity.OrderEntity;
import cn.edu.nju.p.ticketreservation.dao.entity.Site;
import cn.edu.nju.p.ticketreservation.enums.OrderStatus;
import cn.edu.nju.p.ticketreservation.interact.input.PlanForm;
import cn.edu.nju.p.ticketreservation.interact.input.SeatForm;

import java.util.ArrayList;
import java.util.List;

public class OrderDisplay {

    private int orderId;
    private UserInfo userInfo;
    private PlanForm planForm;
    private SiteDisplay siteDisplay;
    private List<SeatForm> seatForms;
    private String orderType = "Seat Selection";
    private double totalMoney;
    private OrderStatus orderStatus;

    public OrderDisplay(){}

    public OrderDisplay(OrderEntity orderEntity) {
        this.orderId = orderEntity.getOrderId();
        this.userInfo = new UserInfo(orderEntity.getUserInfo());
        this.planForm = orderEntity.getPlanForm();
        this.siteDisplay = new SiteDisplay(orderEntity.getSite());
        setOrderType(orderEntity.getOrderType());
        this.totalMoney = orderEntity.getTotalMoney();
        this.orderStatus = OrderStatus.values()[orderEntity.getOrderStatus()];
        resolveSeats(orderEntity.getSeats());
    }

    private void resolveSeats(String seats) {
        this.seatForms = new ArrayList<>();
        String[] seatsSplit = seats.split(";");
        for (String s : seatsSplit) {
            String actualString = s.substring(1, s.length() - 1);
            String[] numbers = actualString.split(",");
            if (numbers.length == 3) {
                this.seatForms.add(new SeatForm(Integer.valueOf(numbers[0]), Integer.valueOf(numbers[1]), Integer.valueOf(numbers[2])));
            }
        }
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(cn.edu.nju.p.ticketreservation.dao.entity.UserInfo userInfo) {
        this.userInfo = new UserInfo(userInfo);
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public PlanForm getPlanForm() {
        return planForm;
    }

    public void setPlanForm(PlanForm planForm) {
        this.planForm = planForm;
    }

    public SiteDisplay getSiteDisplay() {
        return siteDisplay;
    }

    public void setSiteDisplay(SiteDisplay siteDisplay) {
        this.siteDisplay = siteDisplay;
    }

    public void setSiteDisplay(Site site) {
        this.siteDisplay = new SiteDisplay(site);
    }

    public List<SeatForm> getSeatForms() {
        return seatForms;
    }

    public void setSeatForms(List<SeatForm> seatForms) {
        this.seatForms = seatForms;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(int orderType) {
        this.orderType = orderType == 0 ? "Seat Selection" : "Random Selection";
    }

    public double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(double totalMoney) {
        this.totalMoney = totalMoney;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    @Override
    public String toString() {
        return "OrderDisplay{" +
                "orderId=" + orderId +
                ", userInfo=" + userInfo +
                ", planForm=" + planForm +
                ", siteDisplay=" + siteDisplay +
                ", seatForms=" + seatForms +
                ", orderType='" + orderType + '\'' +
                ", totalMoney=" + totalMoney +
                ", orderStatus=" + orderStatus +
                '}';
    }
}
