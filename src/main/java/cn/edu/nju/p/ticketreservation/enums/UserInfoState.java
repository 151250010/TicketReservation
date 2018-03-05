package cn.edu.nju.p.ticketreservation.enums;

public enum UserInfoState {

    USER_NORMAL(0),
    USER_CANCELLED(1);

    private int userState;

    UserInfoState(int userState) {
        this.userState = userState;
    }

    public int getUserState() {
        return userState;
    }

    public static UserInfoState stateOf(int userState) {
        for (UserInfoState state : values()) {
            if (state.getUserState() == userState) {
                return state;
            }
        }
        return null;
    }
}
