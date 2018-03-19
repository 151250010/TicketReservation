package cn.edu.nju.p.ticketreservation.service.impl;

import cn.edu.nju.p.ticketreservation.dao.OrderDao;
import cn.edu.nju.p.ticketreservation.dao.SeatDao;
import cn.edu.nju.p.ticketreservation.exception.SeatNotEnoughException;
import cn.edu.nju.p.ticketreservation.interact.display.OrderDisplay;
import cn.edu.nju.p.ticketreservation.interact.display.SeatDisplay;
import cn.edu.nju.p.ticketreservation.interact.input.RandomSelectionOrder;
import cn.edu.nju.p.ticketreservation.interact.input.SeatForm;
import cn.edu.nju.p.ticketreservation.interact.input.SeatNums;
import cn.edu.nju.p.ticketreservation.interact.input.SeatSelectionOrder;
import cn.edu.nju.p.ticketreservation.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

        order.setOrderTime(getCurrentTime());

        orderDao.addSeatSelectionOrder(order);
        int orderId = order.getOrderId();
        OrderDisplay orderDisplay = new OrderDisplay();
        orderDisplay.setOrderId(orderId);
        orderDisplay.setSeatForms(order.getSeatForms());

        seatDao.bookSeats(order.getPlanId(), order.getSeatForms());
        return orderDisplay;
    }

    @Override
    @Transactional
    public OrderDisplay addOrder(RandomSelectionOrder order) throws SeatNotEnoughException {

        order.setOrderTime(getCurrentTime());

        int planId = order.getPlanId();
        List<SeatDisplay> seatDisplays = seatDao.getAllNotBookedSeatsOfPlan(planId);
        List<SeatNums> seatNums = order.getSeatNums();
        List<SeatDisplay> doBookSeats = doBookSeats(seatDisplays, seatNums);
        List<SeatForm> toBeBookedSeats = doBookSeats.stream().map(seatDisplay -> new SeatForm(seatDisplay.getX(), seatDisplay.getY(), seatDisplay.getZ(), seatDisplay.getPrice())).collect(Collectors.toList());

        orderDao.addRandomOrder(order, doBookSeats);
        int orderId = order.getOrderId();
        OrderDisplay orderDisplay = new OrderDisplay();
        orderDisplay.setOrderType(1);
        orderDisplay.setOrderId(orderId);
        orderDisplay.setSeatForms(toBeBookedSeats);

        seatDao.bookSeats(order.getPlanId(), toBeBookedSeats);
        return orderDisplay;
    }

    private List<SeatDisplay> doBookSeats(List<SeatDisplay> seatDisplays, List<SeatNums> seatNums) throws SeatNotEnoughException {

        if (seatNums == null || seatNums.size() == 0) {
            throw new SeatNotEnoughException("Seats Not Enough!");
        }

        List<SeatDisplay> result = new ArrayList<>();
        // get all z seats
        Map<Integer, List<SeatDisplay>> zSeats = seatDisplays.stream().collect(Collectors.groupingBy(SeatDisplay::getZ));

        for (SeatNums seatNum : seatNums) {
            int seatz = seatNum.getSeatz();
            int num = seatNum.getSeatNumber();
            List<SeatDisplay> allzSeats = zSeats.get(seatz);
            if (allzSeats == null || allzSeats.size() < num) {
                throw new SeatNotEnoughException("Seats of zone " + seatz + " Not Enough!");
            } else {
                result.addAll(allzSeats.subList(0, num));
            }
        }

        return result;
    }

    private String getCurrentTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return formatter.format(LocalDateTime.now());
    }
}
