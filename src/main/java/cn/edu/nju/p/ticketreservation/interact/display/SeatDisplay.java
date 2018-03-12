package cn.edu.nju.p.ticketreservation.interact.display;

import cn.edu.nju.p.ticketreservation.enums.SeatStatus;

public class SeatDisplay {

    private int x;
    private int y;
    private int z;
    private double price;
    private SeatStatus seatStatus;


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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public SeatStatus getSeatStatus() {
        return seatStatus;
    }

    public void setSeatStatus(int seatStatus) {
        this.seatStatus = SeatStatus.resolveDBSeatStatus(seatStatus);
    }
}
