package cn.edu.nju.p.ticketreservation.utils;

import java.util.Random;

public class VerifyCodeUtil {

    private static final String VERIFY_CODES = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public String generateVerifyCodes(int length) {

        Random random = new Random(System.currentTimeMillis());
        int codesLength = VERIFY_CODES.length();
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < length; i++) {
            result.append(VERIFY_CODES.charAt(random.nextInt(codesLength)));
        }
        return result.toString();
    }
}
