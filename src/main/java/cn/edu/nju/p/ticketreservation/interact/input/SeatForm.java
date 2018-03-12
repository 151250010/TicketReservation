package cn.edu.nju.p.ticketreservation.interact.input;

import java.io.Serializable;

public class SeatForm implements Serializable{

    private String siteId;
    private int x;
    private int y;
    private int z;

    public String getSiteId() {
        return siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getZ() {
        return z;
    }

    public void setZ(int z) {
        this.z = z;
    }

    @Override
    public String toString() {
//        return "asd";
        return "(" + x + ',' + y + ',' + z + ")";
    }
}
