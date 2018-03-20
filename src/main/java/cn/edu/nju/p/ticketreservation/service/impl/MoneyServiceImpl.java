package cn.edu.nju.p.ticketreservation.service.impl;

import cn.edu.nju.p.ticketreservation.dao.MoneyDao;
import cn.edu.nju.p.ticketreservation.dao.OrderDao;
import cn.edu.nju.p.ticketreservation.dao.entity.MoneyCount;
import cn.edu.nju.p.ticketreservation.dao.entity.SiteMoney;
import cn.edu.nju.p.ticketreservation.enums.OrderStatus;
import cn.edu.nju.p.ticketreservation.exception.MoneyNotEnoughException;
import cn.edu.nju.p.ticketreservation.exception.OrderCancelledException;
import cn.edu.nju.p.ticketreservation.interact.display.SiteMoneyDisplay;
import cn.edu.nju.p.ticketreservation.interact.input.MoneyPay;
import cn.edu.nju.p.ticketreservation.service.MoneyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MoneyServiceImpl implements MoneyService {

    @Autowired
    private MoneyDao moneyDao;

    @Autowired
    private OrderDao orderDao;

    @Override
    @Transactional
    public void payMoney(MoneyPay moneyPay) throws MoneyNotEnoughException, OrderCancelledException {

        int orderId = moneyPay.getOrderId();

        if (!moneyEnough(moneyPay.getEmail(), moneyPay.getTotalMoney())) {
            throw new MoneyNotEnoughException("Money Not Enough to Pay!");
        }

        moneyDao.plusMoney("administrator", moneyPay.getTotalMoney());
        moneyDao.minusMoney(moneyPay.getEmail(), moneyPay.getTotalMoney());

        if (orderNotPayed(orderId)) {
            orderDao.changeOrderStatus(orderId, OrderStatus.PAYED.ordinal());
        } else {
            throw new OrderCancelledException("Order of " + orderId + " has already cancelled!");
        }

    }

    @Override
    public SiteMoneyDisplay getSiteMoney(String siteId) {

        List<SiteMoney> moneyList = moneyDao.getSiteMoney(Integer.valueOf(siteId));
        SiteMoneyDisplay result = new SiteMoneyDisplay(siteId, 0, 0, 0);

        if (moneyList.size() == 0) {
            return result;
        } else if (moneyList.size() == 1) {
            SiteMoney siteMoney = moneyList.get(0);
            if (siteMoney.getOrderStatus() == 2) {
                result.setNotSettledMoney(siteMoney.getAllMoney());
                result.setTotalMoney(siteMoney.getAllMoney());
                result.setSettledMoney(0);
                return result;
            } else if (siteMoney.getOrderStatus() == 3) {
                result.setSettledMoney(siteMoney.getAllMoney());
                result.setTotalMoney(siteMoney.getAllMoney());
                result.setNotSettledMoney(0);
                return result;
            }
        } else if (moneyList.size() == 2) {
            SiteMoney siteMoney1 = moneyList.get(0);
            SiteMoney siteMoney2 = moneyList.get(1);
            if (siteMoney1.getOrderStatus() == 2) {
                result.setNotSettledMoney(siteMoney1.getAllMoney());
                result.setSettledMoney(siteMoney2.getAllMoney());
                result.setTotalMoney(siteMoney1.getAllMoney() + siteMoney2.getAllMoney());
            } else {
                result.setNotSettledMoney(siteMoney2.getAllMoney());
                result.setSettledMoney(siteMoney1.getAllMoney());
                result.setTotalMoney(siteMoney1.getAllMoney() + siteMoney2.getAllMoney());
            }
            return result;
        }

        return result;
    }

    @Override
    public void settle(double rate) {
        moneyDao.settle(rate);
    }

    @Override
    public List<MoneyCount> getAllMoney() {
        return moneyDao.getAllMoneyCount();
    }

    private boolean moneyEnough(String email, double totalMoney) {

        double balance = moneyDao.getBalance(email);
        return balance >= totalMoney;
    }

    private boolean orderNotPayed(int orderId) {
        int statusNumber = orderDao.getOrderStatus(orderId);
        OrderStatus status = OrderStatus.changeInt2Status(statusNumber);
        return status == OrderStatus.NOT_PAYED;
    }
}
