package cn.edu.nju.p.ticketreservation.service;

import cn.edu.nju.p.ticketreservation.exception.VerifyCodeHasExistedException;

public interface LoginService {

    String createVerifyCode(String email) throws VerifyCodeHasExistedException;

    boolean checkVerifyCode(String email, String inputCode);

    String createLoginToken(String email);

}
