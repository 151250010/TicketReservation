package cn.edu.nju.p.ticketreservation.service;

import cn.edu.nju.p.ticketreservation.dao.entity.Account;

public interface AccountService {

    void addAccount(Account account);

    void updateAccount(Account account);

    boolean checkPassword(Account account);
}
