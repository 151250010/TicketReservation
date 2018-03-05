package cn.edu.nju.p.ticketreservation.controllers.mail;

import cn.edu.nju.p.ticketreservation.base.BaseResult;
import cn.edu.nju.p.ticketreservation.base.ErrorCode;
import cn.edu.nju.p.ticketreservation.exception.VerifyCodeHasExistedException;
import cn.edu.nju.p.ticketreservation.service.LoginService;
import cn.edu.nju.p.ticketreservation.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mail")
public class MailController {

    @Autowired
    private MailService mailService;

    @Autowired
    private LoginService loginService;

    @GetMapping("/verified")
    public BaseResult sendAMailForVerifying(@RequestParam("email")String email) throws VerifyCodeHasExistedException {
        String verifyCode = loginService.createVerifyCode(email);
        String emailSubject = "[Ticker Reservation] Verify For Login";
        String emailContent = "Here is your verify code to login : \r\n" + verifyCode;
        mailService.sendSimpleMail(email, emailSubject, emailContent);
        return new BaseResult<>("OK");
    }

    @GetMapping("/check")
    public BaseResult checkVerifiedCode(@RequestParam("email") String email, @RequestParam("code") String code) {
        boolean success = loginService.checkVerifyCode(email, code);
        if (success) {
            String loginToken = loginService.createLoginToken(email);
            return new BaseResult<>(loginToken);
        } else {
            return new BaseResult<>("login failed", ErrorCode.LOGIN_FAILED);
        }
    }
}
