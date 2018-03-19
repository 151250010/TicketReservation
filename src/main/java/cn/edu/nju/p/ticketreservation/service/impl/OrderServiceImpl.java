package cn.edu.nju.p.ticketreservation.service.impl;

import cn.edu.nju.p.ticketreservation.dao.OrderDao;
import cn.edu.nju.p.ticketreservation.dao.SeatDao;
import cn.edu.nju.p.ticketreservation.interact.display.OrderDisplay;
import cn.edu.nju.p.ticketreservation.interact.input.SeatSelectionOrder;
import cn.edu.nju.p.ticketreservation.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private SeatDao seatDao;

    public static final String CACHE_ORDER_POSTFIX = "_order_info";

    @Override
    @Transactional
    public OrderDisplay addOrder(SeatSelectionOrder order) {

        updateDate(order);

        orderDao.addSeatSelectionOrder(order);
        int orderId = order.getOrderId();
        OrderDisplay orderDisplay = new OrderDisplay();
        orderDisplay.setOrderId(orderId);
        orderDisplay.setSeatForms(order.getSeatForms());

        seatDao.bookSeats(order.getPlanId(), order.getSeatForms());
        return orderDisplay;
    }

    private void updateDate(SeatSelectionOrder order) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String currentTime = formatter.format(LocalDateTime.now());
        order.setOrderTime(currentTime);
    }

    /*private void checkDate(String orderTime) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime orderDateTime = LocalDateTime.parse(orderTime, dateTimeFormatter);
    }*/
}
