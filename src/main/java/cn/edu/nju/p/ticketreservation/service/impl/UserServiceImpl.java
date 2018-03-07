package cn.edu.nju.p.ticketreservation.service.impl;

import cn.edu.nju.p.ticketreservation.dao.UserDao;
import cn.edu.nju.p.ticketreservation.interact.input.UserRegInfo;
import cn.edu.nju.p.ticketreservation.po.UserInfo;
import cn.edu.nju.p.ticketreservation.service.UserService;
import cn.edu.nju.p.ticketreservation.utils.RedisCacheUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RedisCacheUtil cacheUtil;

    public static final String CACHE_INFO_POSTFIX = "_user_info";

    @Override
    public UserInfo getUserInfoByEmail(String email) {

        if (cacheUtil.cacheExist(email + CACHE_INFO_POSTFIX)) {
            return cacheUtil.getCache(email + CACHE_INFO_POSTFIX, UserInfo.class);
        }
        Optional<cn.edu.nju.p.ticketreservation.dao.entity.UserInfo> userInfoOptional = Optional.ofNullable(userDao.getUserInfoByEmail(email));
        return userInfoOptional.map(UserInfo::new).orElse(null);
    }

    @Override
    public void addUserInfo(UserRegInfo info) {

        cn.edu.nju.p.ticketreservation.dao.entity.UserInfo userInfo = new cn.edu.nju.p.ticketreservation.dao.entity.UserInfo(info);
        userDao.addUser(userInfo);
    }
}
