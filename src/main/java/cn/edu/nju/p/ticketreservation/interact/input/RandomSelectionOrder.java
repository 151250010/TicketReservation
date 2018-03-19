package cn.edu.nju.p.ticketreservation.interact.input;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.List;

public class RandomSelectionOrder {

    private int orderId;

    @Null
    private String orderTime;

    @Null
    private String orderType;

    @Email
    private String email;

    @NotNull
    private List<SeatNums> seatNums;

    private int planId;

    @Length(min = 7, max = 7)
    private String siteId;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<SeatNums> getSeatNums() {
        return seatNums;
    }

    public void setSeatNums(List<SeatNums> seatNums) {
        this.seatNums = seatNums;
    }

    public int getPlanId() {
        return planId;
    }

    public void setPlanId(int planId) {
        this.planId = planId;
    }

    public String getSiteId() {
        return siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }
}
