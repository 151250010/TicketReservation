package cn.edu.nju.p.ticketreservation.dao.entity;

import cn.edu.nju.p.ticketreservation.interact.input.SiteReg;

public class Site {

    private int id;
    private String province;
    private String city;
    private String avenue;
    private String name;
    private int seatCountX;
    private int seatCountY;
    private int seatCountZ;
    private boolean checkPass;

    public Site(SiteReg siteReg) {
        this.province = siteReg.getProvince();
        this.city = siteReg.getCity();
        this.avenue = siteReg.getAvenue();
        this.name = siteReg.getName();
        this.seatCountX = siteReg.getSeatCountX();
        this.seatCountY = siteReg.getSeatCountY();
        this.seatCountZ = siteReg.getSeatCountZ();
        this.id = Integer.valueOf(siteReg.getId());
        this.checkPass = siteReg.isCheckPass();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
        return "Site{" +
                "id=" + id +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", avenue='" + avenue + '\'' +
                ", name='" + name + '\'' +
                ", seatCountX=" + seatCountX +
                ", seatCountY=" + seatCountY +
                ", seatCountZ=" + seatCountZ +
                '}';
    }
}
