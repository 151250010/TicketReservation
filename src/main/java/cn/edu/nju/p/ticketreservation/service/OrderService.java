package cn.edu.nju.p.ticketreservation.service;

import cn.edu.nju.p.ticketreservation.exception.SeatNotEnoughException;
import cn.edu.nju.p.ticketreservation.interact.display.OrderDisplay;
import cn.edu.nju.p.ticketreservation.interact.input.RandomSelectionOrder;
import cn.edu.nju.p.ticketreservation.interact.input.SeatSelectionOrder;

import java.util.List;

public interface OrderService {

    OrderDisplay addOrder(SeatSelectionOrder order);

    OrderDisplay addOrder(RandomSelectionOrder order) throws SeatNotEnoughException;

    OrderDisplay getOrder(int orderId);

    void unsubscribe(int orderId);

    List<OrderDisplay> getAllOrders(String email);

    List<OrderDisplay> getAllSiteOrders(String siteId);

}
