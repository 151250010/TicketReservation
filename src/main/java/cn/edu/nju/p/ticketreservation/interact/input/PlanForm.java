package cn.edu.nju.p.ticketreservation.interact.input;

import cn.edu.nju.p.ticketreservation.enums.PlanType;

import javax.validation.constraints.NotNull;
import java.util.List;

public class PlanForm {

    private int planId;

    private List<PlanSeatPriceForm> priceList;

    @NotNull
    private String start;

    @NotNull
    private String end;

    @NotNull
    private String siteId;

    @NotNull
    private PlanType planType;

    @NotNull
    private String introduction;

    public PlanForm() {
    }

    public int getPlanId() {
        return planId;
    }

    public void setPlanId(int planId) {
        this.planId = planId;
    }

    public List<PlanSeatPriceForm> getPriceList() {
        return priceList;
    }

    public void setPriceList(List<PlanSeatPriceForm> priceList) {
        this.priceList = priceList;
    }

    public String getSiteId() {
        return siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public PlanType getPlanType() {
        return planType;
    }

    public void setPlanType(PlanType planType) {
        this.planType = planType;
    }
}
