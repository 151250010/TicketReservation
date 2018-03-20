package cn.edu.nju.p.ticketreservation.controllers.order;

import cn.edu.nju.p.ticketreservation.base.BaseResult;
import cn.edu.nju.p.ticketreservation.base.ErrorCode;
import cn.edu.nju.p.ticketreservation.exception.MoneyNotEnoughException;
import cn.edu.nju.p.ticketreservation.exception.OrderCancelledException;
import cn.edu.nju.p.ticketreservation.interact.input.MoneyPay;
import cn.edu.nju.p.ticketreservation.service.MoneyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/money")
public class MoneyController {

    @Autowired
    private MoneyService moneyService;

    @PostMapping("/pay")
    public BaseResult payMoney(@RequestBody @Validated MoneyPay moneyPay) throws MoneyNotEnoughException, OrderCancelledException {
        moneyService.payMoney(moneyPay);
        return new BaseResult<>("Money has already payed for the order!", ErrorCode.SUCCESS);
    }

    @GetMapping("/site")
    public BaseResult getAllSiteMoney(@RequestParam("siteId") String siteId) {
        return new BaseResult<>(moneyService.getSiteMoney(siteId), ErrorCode.SUCCESS);
    }

}
