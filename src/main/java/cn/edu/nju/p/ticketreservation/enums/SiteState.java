package cn.edu.nju.p.ticketreservation.enums;

public enum SiteState {

    WAITING_REG_PASS("Waiting for Registering Request to Pass"),
    REG_PASSED("Registering Request has Passed"),
    WAITING_UPDATE_PASS("Waiting for Updating Request to Pass"),
    UPDATE_PASSED("Updating Request has Passed");

    private String info;

    SiteState(String info) {
        this.info = info;
    }

    public static SiteState getCorrespondingSiteState(int index) {
        assert index >= 0 && index < 4;
        return values()[index];
    }

    @Override
    public String toString() {
        return info;
    }
}
