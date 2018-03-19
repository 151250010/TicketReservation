package cn.edu.nju.p.ticketreservation.enums;

public enum OrderStatus {

    NOT_PAYED, CANCELLED, PAYED, SETTLED;

    public static OrderStatus changeInt2Status(int number) {
        assert number >= 0 && number <= 3 : "OrderStatus number not valid!";
        return values()[number];
    }
}
