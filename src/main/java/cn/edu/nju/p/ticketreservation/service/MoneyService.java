package cn.edu.nju.p.ticketreservation.service;

import cn.edu.nju.p.ticketreservation.exception.MoneyNotEnoughException;
import cn.edu.nju.p.ticketreservation.exception.OrderCancelledException;
import cn.edu.nju.p.ticketreservation.interact.display.SiteMoneyDisplay;
import cn.edu.nju.p.ticketreservation.interact.input.MoneyPay;

public interface MoneyService {

    void payMoney(MoneyPay moneyPay) throws MoneyNotEnoughException, OrderCancelledException;

    SiteMoneyDisplay getSiteMoney(String siteId);
}
