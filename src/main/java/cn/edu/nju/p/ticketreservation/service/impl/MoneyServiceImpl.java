package cn.edu.nju.p.ticketreservation.service.impl;

import cn.edu.nju.p.ticketreservation.dao.MoneyDao;
import cn.edu.nju.p.ticketreservation.dao.OrderDao;
import cn.edu.nju.p.ticketreservation.enums.OrderStatus;
import cn.edu.nju.p.ticketreservation.exception.MoneyNotEnoughException;
import cn.edu.nju.p.ticketreservation.exception.OrderCancelledException;
import cn.edu.nju.p.ticketreservation.interact.input.MoneyPay;
import cn.edu.nju.p.ticketreservation.service.MoneyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
            orderDao.changeOrderStatus(orderId, OrderStatus.NOT_SETTLE.ordinal());
        } else {
            throw new OrderCancelledException("Order of " + orderId + " has already cancelled!");
        }

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
