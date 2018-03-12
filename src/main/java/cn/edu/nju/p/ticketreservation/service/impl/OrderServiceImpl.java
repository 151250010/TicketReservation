package cn.edu.nju.p.ticketreservation.service.impl;

import cn.edu.nju.p.ticketreservation.dao.OrderDao;
import cn.edu.nju.p.ticketreservation.dao.SeatDao;
import cn.edu.nju.p.ticketreservation.interact.display.OrderDisplay;
import cn.edu.nju.p.ticketreservation.interact.input.SeatSelectionOrder;
import cn.edu.nju.p.ticketreservation.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

        orderDao.addSeatSelectionOrder(order);
        int orderId = order.getOrderId();
        OrderDisplay orderDisplay = new OrderDisplay();
        orderDisplay.setOrderId(orderId);
        orderDisplay.setSeatForms(order.getSeatForms());

        seatDao.bookSeats(order.getPlanId(), order.getSeatForms());
        return orderDisplay;
    }
}
