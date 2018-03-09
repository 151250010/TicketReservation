package cn.edu.nju.p.ticketreservation.service.impl;

import cn.edu.nju.p.ticketreservation.dao.AccountDao;
import cn.edu.nju.p.ticketreservation.dao.entity.Account;
import cn.edu.nju.p.ticketreservation.service.AccountService;
import cn.edu.nju.p.ticketreservation.utils.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountDao accountDao;

    @Override
    public void addAccount(Account account) {
        String password = account.getPassword();
        String encryptPassword = TokenUtil.encrypt(password);
        account.setPassword(encryptPassword);
        accountDao.addAccount(account);
    }

    @Override
    @Transactional
    public void updateAccount(Account account) {
        String newPassword = TokenUtil.encrypt(account.getPassword());
        accountDao.deleteAccount(account.getId());
        account.setPassword(newPassword);
        accountDao.addAccount(account);
    }

    @Override
    public boolean checkPassword(Account account) {
        String actualPassword = accountDao.getPassword(account.getId());
        String encryptPassword = TokenUtil.encrypt(account.getPassword());
        return actualPassword.equals(encryptPassword);
    }
}
