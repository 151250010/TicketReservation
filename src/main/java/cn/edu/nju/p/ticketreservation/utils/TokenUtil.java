package cn.edu.nju.p.ticketreservation.utils;

import org.apache.commons.codec.binary.Hex;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class TokenUtil {

    public static String createToken(String email) {
        return encrypt(email);
    }

    public static boolean checkToken(String email, String token) {
        return token.equals(encrypt(email));
    }

    public static String encrypt(String source) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] bytes = messageDigest.digest(source.getBytes("UTF-8"));
            return Hex.encodeHexString(bytes);
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            return null;
        }
    }

}
