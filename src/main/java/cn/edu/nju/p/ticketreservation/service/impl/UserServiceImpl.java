package cn.edu.nju.p.ticketreservation.service.impl;

import cn.edu.nju.p.ticketreservation.dao.UserDao;
import cn.edu.nju.p.ticketreservation.interact.input.UserRegInfo;
import cn.edu.nju.p.ticketreservation.po.UserInfo;
import cn.edu.nju.p.ticketreservation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public UserInfo getUserInfoByEmail(String email) {

        Optional<cn.edu.nju.p.ticketreservation.dao.entity.UserInfo> userInfoOptional = Optional.ofNullable(userDao.getUserInfoByEmail(email));
        cn.edu.nju.p.ticketreservation.dao.entity.UserInfo defaultNullUser = new cn.edu.nju.p.ticketreservation.dao.entity.UserInfo("", "", -1, 1, 0);
        return new UserInfo(userInfoOptional.orElse(defaultNullUser));
    }

    @Override
    public void addUserInfo(UserRegInfo info) {

        cn.edu.nju.p.ticketreservation.dao.entity.UserInfo userInfo = new cn.edu.nju.p.ticketreservation.dao.entity.UserInfo(info);
        userDao.addUser(userInfo);
    }
}
