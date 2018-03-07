package cn.edu.nju.p.ticketreservation.controllers.user;

import cn.edu.nju.p.ticketreservation.base.BaseResult;
import cn.edu.nju.p.ticketreservation.base.ErrorCode;
import cn.edu.nju.p.ticketreservation.interact.input.UserRegInfo;
import cn.edu.nju.p.ticketreservation.interact.display.UserInfo;
import cn.edu.nju.p.ticketreservation.service.UserService;
import cn.edu.nju.p.ticketreservation.service.impl.UserServiceImpl;
import cn.edu.nju.p.ticketreservation.utils.RedisCacheUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Email;

@RestController
@RequestMapping("/user")
@Validated
public class UserInfoController {

    @Autowired
    private UserService service;

    @Autowired
    private RedisCacheUtil cacheUtil;

    @GetMapping
    public UserInfo getUserInfoByEmail(@RequestParam("email") String email) {
        return service.getUserInfoByEmail(email);
    }

    @PostMapping
    public BaseResult register(@RequestBody @Validated UserRegInfo info) {
        service.addUserInfo(info);
        return new BaseResult<>("Add a user successfully!", 0);
    }

    @GetMapping("/cancel")
    public BaseResult cancelUser(@RequestParam("email") @Email String email) {

        // 判断email是否已经注册，将取出的UserInfo缓存起来
        UserInfo userInfo = service.getUserInfoByEmail(email);
        if (userInfo == null) {
            return new BaseResult<>("User " + email + " has not registered.", ErrorCode.USER_NOT_REGISTER);
        } else {
            cacheUtil.putCacheWithExpireTime(email + UserServiceImpl.CACHE_INFO_POSTFIX, userInfo, 60 * 30);
        }

        service.cancelAUser(email);
        return new BaseResult<>("Cancel user " + email + " successfully!", 0);
    }
}
