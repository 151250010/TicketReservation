package cn.edu.nju.p.ticketreservation.enums;

public enum SeatStatus {

    NOT_BOOKED("This seat is not booked."),
    BOOKED("This seat has been booked.");

    private String info;

    SeatStatus(String info) {
        this.info = info;
    }

    public static SeatStatus resolveDBSeatStatus(int db) {
        assert db >= 0 && db <= 1;
        return values()[db];
    }

    @Override
    public String toString() {
        return info;
    }
}
