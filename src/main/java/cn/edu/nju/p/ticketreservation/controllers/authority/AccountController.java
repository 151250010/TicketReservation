package cn.edu.nju.p.ticketreservation.controllers.authority;

import cn.edu.nju.p.ticketreservation.base.BaseResult;
import cn.edu.nju.p.ticketreservation.base.ErrorCode;
import cn.edu.nju.p.ticketreservation.dao.entity.Account;
import cn.edu.nju.p.ticketreservation.interact.display.SiteDisplay;
import cn.edu.nju.p.ticketreservation.service.AccountService;
import cn.edu.nju.p.ticketreservation.service.SiteService;
import cn.edu.nju.p.ticketreservation.service.impl.SiteServiceImpl;
import cn.edu.nju.p.ticketreservation.utils.RedisCacheUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private SiteService siteService;

    @Autowired
    private RedisCacheUtil cacheUtil;

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
        String siteId = account.getId();
        SiteDisplay siteDisplay = siteService.getSiteInfo(siteId);
        if (checkPass) {
            return new BaseResult<>(siteDisplay, ErrorCode.SUCCESS);
        } else {
            cacheUtil.putCacheWithExpireTime(siteId + SiteServiceImpl.CACHE_POSTFIX, siteDisplay, 60 * 30);
            return new BaseResult<>("Site  Login Failed!", ErrorCode.LOGIN_FAILED);
        }
    }
}
