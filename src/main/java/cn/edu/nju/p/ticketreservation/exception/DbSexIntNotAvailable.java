package cn.edu.nju.p.ticketreservation.exception;

public class DbSexIntNotAvailable extends RuntimeException {

    private String message;

    public DbSexIntNotAvailable(String message) {
        super(message);
    }
}
