package cn.edu.nju.p.ticketreservation.interact.display;

public class SiteMoneyDisplay {

    private String siteId;
    private double totalMoney;
    private double settledMoney;
    private double notSettledMoney;

    public SiteMoneyDisplay(String siteId, int totalMoney, int settledMoney, int notSettledMoney) {
        this.siteId = siteId;
        this.totalMoney = totalMoney;
        this.settledMoney = settledMoney;
        this.notSettledMoney = notSettledMoney;
    }

    public SiteMoneyDisplay() {
    }

    public String getSiteId() {
        return siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }

    public double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(double totalMoney) {
        this.totalMoney = totalMoney;
    }

    public double getSettledMoney() {
        return settledMoney;
    }

    public void setSettledMoney(double settledMoney) {
        this.settledMoney = settledMoney;
    }

    public double getNotSettledMoney() {
        return notSettledMoney;
    }

    public void setNotSettledMoney(double notSettledMoney) {
        this.notSettledMoney = notSettledMoney;
    }
}
