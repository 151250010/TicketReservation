package cn.edu.nju.p.ticketreservation.service.impl;

import cn.edu.nju.p.ticketreservation.dao.MoneyDao;
import cn.edu.nju.p.ticketreservation.dao.OrderDao;
import cn.edu.nju.p.ticketreservation.dao.SeatDao;
import cn.edu.nju.p.ticketreservation.dao.UserDao;
import cn.edu.nju.p.ticketreservation.dao.entity.OrderEntity;
import cn.edu.nju.p.ticketreservation.enums.OrderStatus;
import cn.edu.nju.p.ticketreservation.exception.SeatNotEnoughException;
import cn.edu.nju.p.ticketreservation.interact.display.OrderDisplay;
import cn.edu.nju.p.ticketreservation.interact.display.SeatDisplay;
import cn.edu.nju.p.ticketreservation.interact.input.RandomSelectionOrder;
import cn.edu.nju.p.ticketreservation.interact.input.SeatForm;
import cn.edu.nju.p.ticketreservation.interact.input.SeatNums;
import cn.edu.nju.p.ticketreservation.interact.input.SeatSelectionOrder;
import cn.edu.nju.p.ticketreservation.service.OrderService;
import cn.edu.nju.p.ticketreservation.utils.RedisCacheUtil;
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

    @Autowired
    private UserDao userDao;

    @Autowired
    private MoneyDao moneyDao;

    @Autowired
    private RedisCacheUtil cacheUtil;

    public static final String CACHE_ORDER_POSTFIX = "_order_info";

    @Override
    @Transactional
    public OrderDisplay addOrder(SeatSelectionOrder order) {

        order.setOrderTime(getCurrentTime());

        orderDao.addSeatSelectionOrder(order);
        int orderId = order.getOrderId();
        OrderDisplay orderDisplay = new OrderDisplay();
        orderDisplay.setOrderId(orderId);
        orderDisplay.setTotalMoney(order.getMoney());
        orderDisplay.setSeatForms(order.getSeatForms());

        seatDao.bookSeats(order.getPlanId(), order.getSeatForms());

        userDao.updateUserScore(order.getUserEmail(), order.getMoney());
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
        orderDisplay.setTotalMoney(order.getMoney());
        orderDisplay.setSeatForms(toBeBookedSeats);

        seatDao.bookSeats(order.getPlanId(), toBeBookedSeats);

        userDao.updateUserScore(order.getEmail(), order.getMoney());
        return orderDisplay;
    }

    @Override
    public OrderDisplay getOrder(int orderId) {

        String key = orderId + CACHE_ORDER_POSTFIX;
        /*if (cacheUtil.cacheExist(key)) {
            return cacheUtil.getCache(key, OrderDisplay.class);
        }*/

        OrderEntity orderEntity = orderDao.getOrder(orderId);
        OrderDisplay orderDisplay = new OrderDisplay(orderEntity);

        cacheUtil.putCacheWithExpireTime(key, orderDisplay, 60 * 30);
        return orderDisplay;
    }

    @Override
    @Transactional
    public void unsubscribe(int orderId) {

        OrderDisplay orderDisplay = getOrder(orderId);

        OrderStatus status = orderDisplay.getOrderStatus();
        switch (status) {
            case CANCELLED:
                break;
            case NOT_PAYED:
                // booked seats released
                seatDao.releaseBookedSeats(orderDisplay.getPlanForm().getPlanId(), orderDisplay.getSeatForms());

                // cancel order
                orderDao.changeOrderStatus(orderId, OrderStatus.CANCELLED.ordinal());
                break;
            case PAYED: {
                // booked seats released
                seatDao.releaseBookedSeats(orderDisplay.getPlanForm().getPlanId(), orderDisplay.getSeatForms());

                // cancel order
                orderDao.changeOrderStatus(orderId, OrderStatus.CANCELLED.ordinal());

                double totalMoney = orderDisplay.getTotalMoney();
                String email = orderDisplay.getUserInfo().getEmail();

                // return money
                moneyDao.plusMoney(email, totalMoney);
                moneyDao.minusMoney("administrator", totalMoney);

                // return score and consumption
                userDao.backUserScore(email, totalMoney);
                break;
            }
            case SETTLED: {
                // booked seats released
                seatDao.releaseBookedSeats(orderDisplay.getPlanForm().getPlanId(), orderDisplay.getSeatForms());

                // cancel order
                orderDao.changeOrderStatus(orderId, OrderStatus.CANCELLED.ordinal());

                double totalMoney = orderDisplay.getTotalMoney();
                String email = orderDisplay.getUserInfo().getEmail();

                // return money
                moneyDao.plusMoney(email, totalMoney);
                moneyDao.minusMoney(orderDisplay.getSiteDisplay().getId(), totalMoney);

                // return score and consumption
                userDao.backUserScore(email, totalMoney);
                break;
            }
        }

    }

    @Override
    public List<OrderDisplay> getAllOrders(String email) {
        List<OrderEntity> orderEntities = orderDao.getAllUserOrders(email);
        return orderEntities.stream().map(OrderDisplay::new).collect(Collectors.toList());
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
