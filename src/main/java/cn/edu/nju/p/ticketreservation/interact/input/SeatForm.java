package cn.edu.nju.p.ticketreservation.interact.input;

import java.io.Serializable;

public class SeatForm implements Serializable{

    private int x;
    private int y;
    private int z;
    private double price;

    public SeatForm(){}

    public SeatForm(int x, int y, int z, double price) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.price = price;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "(" + x + ',' + y + ',' + z + ")";
    }
}
