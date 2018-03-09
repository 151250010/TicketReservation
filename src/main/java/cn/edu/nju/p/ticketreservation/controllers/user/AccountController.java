package cn.edu.nju.p.ticketreservation.controllers.user;

import cn.edu.nju.p.ticketreservation.base.BaseResult;
import cn.edu.nju.p.ticketreservation.base.ErrorCode;
import cn.edu.nju.p.ticketreservation.dao.entity.Account;
import cn.edu.nju.p.ticketreservation.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping
    public BaseResult insertAccount(@RequestBody @Validated Account account) {
        accountService.addAccount(account);
        return new BaseResult<>("Account : " + account.getId() + " has already set correctly!", ErrorCode.SUCCESS);
    }

    @PutMapping
    public BaseResult updateAccount(@RequestBody @Validated Account account) {
        accountService.updateAccount(account);
        return new BaseResult<>("Account : " + account.getId() + " has updated successfully!", ErrorCode.SUCCESS);
    }

    @RequestMapping(value = "/check", method = RequestMethod.POST)
    public BaseResult checkAccount(@RequestBody @Validated Account account) {
        boolean checkPass = accountService.checkPassword(account);
        return checkPass ? new BaseResult<>("Site Login Successfully!", ErrorCode.SUCCESS)
                : new BaseResult<>("Site  Login Failed!", ErrorCode.LOGIN_FAILED);
    }
}
