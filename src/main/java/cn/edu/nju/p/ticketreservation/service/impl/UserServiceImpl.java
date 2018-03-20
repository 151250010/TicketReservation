package cn.edu.nju.p.ticketreservation.service.impl;

import cn.edu.nju.p.ticketreservation.dao.MoneyDao;
import cn.edu.nju.p.ticketreservation.dao.UserDao;
import cn.edu.nju.p.ticketreservation.exception.UserNotRegisterException;
import cn.edu.nju.p.ticketreservation.interact.input.UserRegInfo;
import cn.edu.nju.p.ticketreservation.interact.display.UserInfo;
import cn.edu.nju.p.ticketreservation.service.UserService;
import cn.edu.nju.p.ticketreservation.utils.RedisCacheUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private MoneyDao moneyDao;

    @Autowired
    private RedisCacheUtil cacheUtil;

    public static final String CACHE_INFO_POSTFIX = "_user_info";

    @Override
    public UserInfo getUserInfoByEmail(String email) throws UserNotRegisterException {

        if (cacheUtil.cacheExist(email + CACHE_INFO_POSTFIX)) {
            return cacheUtil.getCache(email + CACHE_INFO_POSTFIX, UserInfo.class);
        } else {
            Optional<cn.edu.nju.p.ticketreservation.dao.entity.UserInfo> userInfoOptional = Optional.ofNullable(userDao.getUserInfoByEmail(email));
            if (!userInfoOptional.isPresent()) {
                throw new UserNotRegisterException("User of " + email + " does not exist!");
            } else {
                UserInfo result = userInfoOptional.map(UserInfo::new).orElse(null);
                cacheUtil.putCacheWithExpireTime(email + UserServiceImpl.CACHE_INFO_POSTFIX, result, 60 * 30);
                return result;
            }
        }
    }

    @Override
    @Transactional
    public void addUserInfo(UserRegInfo info) {
        cn.edu.nju.p.ticketreservation.dao.entity.UserInfo userInfo = new cn.edu.nju.p.ticketreservation.dao.entity.UserInfo(info);
        moneyDao.addMoneyAccount(info.getEmail());
        userDao.addUser(userInfo);
    }

    @Override
    public void cancelAUser(String email) {
        userDao.cancelUser(email);
    }

    @Override
    public List<UserInfo> getAllUsers() {
        List<cn.edu.nju.p.ticketreservation.dao.entity.UserInfo> userInfos = userDao.getAllUsers();
        return userInfos.stream().map(UserInfo::new).collect(Collectors.toList());
    }

}
