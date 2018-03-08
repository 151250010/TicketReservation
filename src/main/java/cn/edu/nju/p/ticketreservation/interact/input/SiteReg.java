package cn.edu.nju.p.ticketreservation.interact.input;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class SiteReg {

    private String id = "0000000";

    @NotBlank
    @NotNull
    private String province;

    @NotBlank
    @NotNull
    private String city;

    @NotBlank
    @NotNull
    private String avenue;

    @NotBlank
    @NotNull
    private String name;

    @Min(1)
    private int seatCountX;

    @Min(1)
    private int seatCountY;

    @Min(1)
    private int seatCountZ;

    private boolean checkPass;

    public SiteReg() {

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isCheckPass() {
        return checkPass;
    }

    public void setCheckPass(boolean checkPass) {
        this.checkPass = checkPass;
    }

    @Override
    public String toString() {
        return "SiteReg{" +
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
