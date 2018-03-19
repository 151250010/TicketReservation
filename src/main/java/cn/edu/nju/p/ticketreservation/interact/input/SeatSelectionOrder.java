package cn.edu.nju.p.ticketreservation.interact.input;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.io.Serializable;
import java.util.List;

public class SeatSelectionOrder implements Serializable {

    private int orderId = 0;

    @Length(min = 7, max = 7)
    private String siteId;

    private int planId;

    @Email
    private String userEmail;

    @NotNull
    private List<SeatForm> seatForms;

    @Null
    private String orderTime;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getSiteId() {
        return siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }

    public int getPlanId() {
        return planId;
    }

    public void setPlanId(int planId) {
        this.planId = planId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public List<SeatForm> getSeatForms() {
        return seatForms;
    }

    public void setSeatForms(List<SeatForm> seatForms) {
        this.seatForms = seatForms;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }
}
