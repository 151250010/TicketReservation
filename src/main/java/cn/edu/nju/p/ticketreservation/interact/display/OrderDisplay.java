package cn.edu.nju.p.ticketreservation.interact.display;

import cn.edu.nju.p.ticketreservation.interact.input.PlanForm;
import cn.edu.nju.p.ticketreservation.interact.input.SeatForm;

import java.util.List;

public class OrderDisplay {

    private int orderId;
    private UserInfo userInfo;
    private PlanForm planForm;
    private SiteDisplay siteDisplay;
    private List<SeatForm> seatForms;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public UserInfo getUserInfo() {
        return userInfo;
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

    public List<SeatForm> getSeatForms() {
        return seatForms;
    }

    public void setSeatForms(List<SeatForm> seatForms) {
        this.seatForms = seatForms;
    }

}
