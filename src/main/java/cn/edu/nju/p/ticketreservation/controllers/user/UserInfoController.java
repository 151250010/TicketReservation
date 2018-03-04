package cn.edu.nju.p.ticketreservation.controllers.user;

import cn.edu.nju.p.ticketreservation.dao.UserDao;
import cn.edu.nju.p.ticketreservation.po.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserInfoController {

    private final UserDao userDao;

    @Autowired
    public UserInfoController(UserDao userDao) {
        this.userDao = userDao;
    }

    @GetMapping("/email")
    public UserInfo getUserEmail(@RequestParam("userName") String userName) {
        return new UserInfo(userDao.getEmailByUsername(userName));
    }
}
