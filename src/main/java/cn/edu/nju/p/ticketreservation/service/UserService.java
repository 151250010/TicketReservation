package cn.edu.nju.p.ticketreservation.service;

import cn.edu.nju.p.ticketreservation.interact.input.UserRegInfo;
import cn.edu.nju.p.ticketreservation.po.UserInfo;

public interface UserService {

    UserInfo getUserInfoByEmail(String email);

    void addUserInfo(UserRegInfo info);
}
