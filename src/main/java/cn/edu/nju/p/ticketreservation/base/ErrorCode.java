package cn.edu.nju.p.ticketreservation.base;

public class ErrorCode {

    public static final int SUCCESS = 0;

    public static final int VERIFY_CODE_EXISTED = 99990001;
    public static final int MAIL_ADDRESS_NOT_EXIST = 99990002;

    public static final int LOGIN_FAILED = 88880001;
    public static final int USER_NOT_REGISTER = 88880002;
    public static final int VIP_SCORE_NOT_AVAILABLE = 88880003;

    public static final int PARAMETER_VALIDATED_FAILED = 77770001;
    public static final int INTEGER_FORMAT_EXCEPTION = 77770002;
    public static final int DATE_NOT_AVAILABLE = 77770003;

    public static final int DAO_DUPLICATE_KEY = 66660001;
    public static final int REDIS_CONNECT_REFUSED = 66660002;


}
