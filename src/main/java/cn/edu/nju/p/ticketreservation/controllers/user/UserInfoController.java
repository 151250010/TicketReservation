package cn.edu.nju.p.ticketreservation.controllers.user;

import cn.edu.nju.p.ticketreservation.base.BaseResult;
import cn.edu.nju.p.ticketreservation.interact.input.UserRegInfo;
import cn.edu.nju.p.ticketreservation.interact.display.UserInfo;
import cn.edu.nju.p.ticketreservation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserInfoController {

    @Autowired
    private UserService service;

    @GetMapping
    public UserInfo getUserInfoByEmail(@RequestParam("email") String email) {
        return service.getUserInfoByEmail(email);
    }

    @PostMapping
    public BaseResult register(@RequestBody @Validated UserRegInfo info) {
        service.addUserInfo(info);
        return new BaseResult<>("Add a user successfully!", 0);
    }

//    public BaseResult
}
