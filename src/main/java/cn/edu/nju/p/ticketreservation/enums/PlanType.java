package cn.edu.nju.p.ticketreservation.enums;

public enum PlanType {

    CONCERT,DANCE,DRAMA, COMPETITION;

    public static PlanType convert2PlanType(int db) {
        assert db >= 0 && db <= 3;
        return PlanType.values()[db];
    }
}
