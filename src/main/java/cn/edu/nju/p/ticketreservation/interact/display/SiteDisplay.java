package cn.edu.nju.p.ticketreservation.interact.display;

import cn.edu.nju.p.ticketreservation.dao.entity.Site;

import java.text.DecimalFormat;

public class SiteDisplay {

    private String id;
    private String province;
    private String city;
    private String avenue;
    private String name;
    private int seatCountX;
    private int seatCountY;
    private int seatCountZ;
    private boolean checkPass;

    public SiteDisplay() {
    }

    public SiteDisplay(Site site) {

        this.id = resolveId(site.getId());
        this.province = site.getProvince();
        this.city = site.getCity();
        this.avenue = site.getAvenue();
        this.name = site.getName();
        this.seatCountX = site.getSeatCountX();
        this.seatCountY = site.getSeatCountY();
        this.seatCountZ = site.getSeatCountZ();
        this.checkPass = site.isCheckPass();
    }

    private String resolveId(int id) {
        DecimalFormat decimalFormat = new DecimalFormat("0000000");
        return decimalFormat.format(id);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAvenue() {
        return avenue;
    }

    public void setAvenue(String avenue) {
        this.avenue = avenue;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSeatCountX() {
        return seatCountX;
    }

    public void setSeatCountX(int seatCountX) {
        this.seatCountX = seatCountX;
    }

    public int getSeatCountY() {
        return seatCountY;
    }

    public void setSeatCountY(int seatCountY) {
        this.seatCountY = seatCountY;
    }

    public int getSeatCountZ() {
        return seatCountZ;
    }

    public void setSeatCountZ(int seatCountZ) {
        this.seatCountZ = seatCountZ;
    }

    public boolean isCheckPass() {
        return checkPass;
    }

    public void setCheckPass(boolean checkPass) {
        this.checkPass = checkPass;
    }

    @Override
    public String toString() {
        return "SiteDisplay{" +
                "id='" + id + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", avenue='" + avenue + '\'' +
                ", name='" + name + '\'' +
                ", seatCountX=" + seatCountX +
                ", seatCountY=" + seatCountY +
                ", seatCountZ=" + seatCountZ +
                ", checkPass=" + checkPass +
                '}';
    }
}
