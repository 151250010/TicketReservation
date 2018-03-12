package cn.edu.nju.p.ticketreservation.exception;

public class PlanNotExistException extends RuntimeException {
    public PlanNotExistException(String s) {
        super(s);
    }
}
