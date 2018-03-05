package cn.edu.nju.p.ticketreservation.service.impl;

import cn.edu.nju.p.ticketreservation.exception.VerifyCodeHasExistedException;
import cn.edu.nju.p.ticketreservation.service.LoginService;
import cn.edu.nju.p.ticketreservation.utils.RedisCacheUtil;
import cn.edu.nju.p.ticketreservation.utils.TokenUtil;
import cn.edu.nju.p.ticketreservation.utils.VerifyCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private RedisCacheUtil redisCacheUtil;

    @Override
    public String createVerifyCode(String email) throws VerifyCodeHasExistedException {
        if (redisCacheUtil.cacheExist(email)) {
            throw new VerifyCodeHasExistedException("A verify code has sent to your email!");
        }
        String verifyCode = VerifyCodeUtil.generateVerifyCodes();
        redisCacheUtil.putCache(email, verifyCode);
        return verifyCode;
    }

    @Override
    public boolean checkVerifyCode(String email, String inputCode) {
        String trueCode = redisCacheUtil.getCache(email);
        return trueCode != null && trueCode.equals(inputCode);
    }

    @Override
    public String createLoginToken(String email) {
        return TokenUtil.createToken(email);
    }


}
