package cn.edu.nju.p.ticketreservation.dao;

import cn.edu.nju.p.ticketreservation.dao.entity.OrderEntity;
import cn.edu.nju.p.ticketreservation.interact.display.OrderDisplay;
import cn.edu.nju.p.ticketreservation.interact.display.SeatDisplay;
import cn.edu.nju.p.ticketreservation.interact.input.RandomSelectionOrder;
import cn.edu.nju.p.ticketreservation.interact.input.SeatSelectionOrder;
import cn.edu.nju.p.ticketreservation.utils.mybatis.provider.OrderProvider;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface OrderDao{

    @InsertProvider(type = OrderProvider.class,method = "insertOrder")
    @Options(keyProperty = "order.orderId", keyColumn = "orderId", useGeneratedKeys = true)
    void addSeatSelectionOrder(@Param("order") SeatSelectionOrder order);

    @Update("update t_order set orderStatus=#{status} where orderId=#{orderId}")
    void changeOrderStatus(@Param("orderId") int orderId, @Param("status") int status);

    @Select("select orderStatus from t_order where orderId=#{orderId}")
    int getOrderStatus(@Param("orderId") int orderId);

    @InsertProvider(type = OrderProvider.class,method = "insertRandomOrder")
    @Options(keyProperty = "order.orderId", keyColumn = "orderId", useGeneratedKeys = true)
    void addRandomOrder(@Param("order") RandomSelectionOrder order, @Param("seats") List<SeatDisplay> seats);

    @Select("select * from t_order where orderId=#{orderId}")
    @Results({
            @Result(property = "orderId",column = "orderId"),
            @Result(property = "totalMoney",column = "totalMoney"),
            @Result(property = "seats",column = "seats"),
            @Result(property = "orderType",column = "orderType"),
            @Result(property = "orderStatus",column = "orderStatus"),
            @Result(property = "userInfo",column = "email",one = @One(select = "cn.edu.nju.p.ticketreservation.dao.UserDao.getUserInfoByEmail")),
            @Result(property = "planForm",column = "planId",one = @One(select = "cn.edu.nju.p.ticketreservation.dao.PlanDao.getPlanForm")),
            @Result(property = "site",column = "siteId",one = @One(select = "cn.edu.nju.p.ticketreservation.dao.SiteDao.getSite"))
    })
    OrderEntity getOrder(@Param("orderId") int orderId);

    @Select("select * from t_order where email=#{email}")
    @Results({
            @Result(property = "orderId",column = "orderId"),
            @Result(property = "totalMoney",column = "totalMoney"),
            @Result(property = "seats",column = "seats"),
            @Result(property = "orderType",column = "orderType"),
            @Result(property = "orderStatus",column = "orderStatus"),
            @Result(property = "userInfo",column = "email",one = @One(select = "cn.edu.nju.p.ticketreservation.dao.UserDao.getUserInfoByEmail")),
            @Result(property = "planForm",column = "planId",one = @One(select = "cn.edu.nju.p.ticketreservation.dao.PlanDao.getPlanForm")),
            @Result(property = "site",column = "siteId",one = @One(select = "cn.edu.nju.p.ticketreservation.dao.SiteDao.getSite"))
    })
    List<OrderEntity> getAllUserOrders(@Param("email") String email);

    @Select("select * from t_order where siteId=#{siteId}")
    @Results({
            @Result(property = "orderId",column = "orderId"),
            @Result(property = "totalMoney",column = "totalMoney"),
            @Result(property = "seats",column = "seats"),
            @Result(property = "orderType",column = "orderType"),
            @Result(property = "orderStatus",column = "orderStatus"),
            @Result(property = "userInfo",column = "email",one = @One(select = "cn.edu.nju.p.ticketreservation.dao.UserDao.getUserInfoByEmail")),
            @Result(property = "planForm",column = "planId",one = @One(select = "cn.edu.nju.p.ticketreservation.dao.PlanDao.getPlanForm")),
            @Result(property = "site",column = "siteId",one = @One(select = "cn.edu.nju.p.ticketreservation.dao.SiteDao.getSite"))
    })
    List<OrderEntity> getAllSiteOrders(@Param("siteId") int siteId);
}
