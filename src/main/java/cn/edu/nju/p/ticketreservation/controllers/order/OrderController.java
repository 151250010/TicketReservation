package cn.edu.nju.p.ticketreservation.controllers.order;

import cn.edu.nju.p.ticketreservation.base.BaseResult;
import cn.edu.nju.p.ticketreservation.base.ErrorCode;
import cn.edu.nju.p.ticketreservation.enums.OrderStatus;
import cn.edu.nju.p.ticketreservation.exception.SeatNotEnoughException;
import cn.edu.nju.p.ticketreservation.exception.UserNotRegisterException;
import cn.edu.nju.p.ticketreservation.interact.display.OrderDisplay;
import cn.edu.nju.p.ticketreservation.interact.display.SiteDisplay;
import cn.edu.nju.p.ticketreservation.interact.display.UserInfo;
import cn.edu.nju.p.ticketreservation.interact.input.PlanForm;
import cn.edu.nju.p.ticketreservation.interact.input.RandomSelectionOrder;
import cn.edu.nju.p.ticketreservation.interact.input.SeatSelectionOrder;
import cn.edu.nju.p.ticketreservation.service.OrderService;
import cn.edu.nju.p.ticketreservation.service.PlanService;
import cn.edu.nju.p.ticketreservation.service.SiteService;
import cn.edu.nju.p.ticketreservation.service.UserService;
import cn.edu.nju.p.ticketreservation.service.impl.OrderServiceImpl;
import cn.edu.nju.p.ticketreservation.utils.RedisCacheUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private RedisCacheUtil cacheUtil;

    @Autowired
    private UserService userService;

    @Autowired
    private PlanService planService;

    @Autowired
    private SiteService siteService;

    @Autowired
    private OrderService orderService;

    @PostMapping("/selection")
    public BaseResult insertSeatSelectionOrder(@RequestBody @Validated SeatSelectionOrder order) throws UserNotRegisterException {

        OrderDisplay orderDisplay = orderService.addOrder(order);

        String email = order.getUserEmail();
        UserInfo userInfo = userService.getUserInfoByEmail(email);

        int planId = order.getPlanId();
        PlanForm planForm = planService.getPlan(planId);

        String siteId = order.getSiteId();
        SiteDisplay siteDisplay = siteService.getSiteInfo(siteId);

        orderDisplay.setUserInfo(userInfo);
        orderDisplay.setPlanForm(planForm);
        orderDisplay.setSiteDisplay(siteDisplay);
        orderDisplay.setOrderStatus(OrderStatus.NOT_PAYED);

        cacheUtil.putCacheWithExpireTime(orderDisplay.getOrderId() + OrderServiceImpl.CACHE_ORDER_POSTFIX, orderDisplay, 60 * 30);
        return new BaseResult<>(orderDisplay, ErrorCode.SUCCESS);
    }

    @PostMapping("/random")
    public BaseResult insertRandomSelectionOrder(@RequestBody @Validated RandomSelectionOrder order) throws SeatNotEnoughException, UserNotRegisterException {

        OrderDisplay orderDisplay = orderService.addOrder(order);
        String email = order.getEmail();
        UserInfo userInfo = userService.getUserInfoByEmail(email);

        int planId = order.getPlanId();
        PlanForm planForm = planService.getPlan(planId);

        String siteId = order.getSiteId();
        SiteDisplay siteDisplay = siteService.getSiteInfo(siteId);

        orderDisplay.setUserInfo(userInfo);
        orderDisplay.setPlanForm(planForm);
        orderDisplay.setSiteDisplay(siteDisplay);
        orderDisplay.setOrderStatus(OrderStatus.NOT_PAYED);

        cacheUtil.putCacheWithExpireTime(orderDisplay.getOrderId() + OrderServiceImpl.CACHE_ORDER_POSTFIX, orderDisplay, 60 * 30);
        return new BaseResult<>(orderDisplay, ErrorCode.SUCCESS);
    }

    @GetMapping("/unsubscribe")
    public BaseResult unsubscribeOrder(@RequestParam("orderId") int orderId) {


        return null;
    }

}
