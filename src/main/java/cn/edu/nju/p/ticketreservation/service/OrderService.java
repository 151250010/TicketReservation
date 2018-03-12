package cn.edu.nju.p.ticketreservation.service;

import cn.edu.nju.p.ticketreservation.interact.display.OrderDisplay;
import cn.edu.nju.p.ticketreservation.interact.input.SeatSelectionOrder;

public interface OrderService {

    OrderDisplay addOrder(SeatSelectionOrder order);

}
