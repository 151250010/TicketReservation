package cn.edu.nju.p.ticketreservation.controllers.test;

import cn.edu.nju.p.ticketreservation.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MailController {

    @Autowired
    private MailService mailService;

    @GetMapping("/mail")
    public String sendAMail() {
        mailService.sendSimpleMail("151250010@smail.nju.edu.cn", "Hello", "Xihao is the most handsome man in the world!");
        return "OK";
    }
}
