package cn.edu.nju.p.ticketreservation.dao;

import cn.edu.nju.p.ticketreservation.interact.input.SeatSelectionOrder;
import cn.edu.nju.p.ticketreservation.utils.mybatis.provider.OrderProvider;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface OrderDao {

//    @Insert("insert into order (planId,email,orderTime,seats,orderType,orderStatus) values " +
//            "(#{order.planId},#{order.userEmail},#{order.orderTime},'" +
//            "${order.getSeatForms().toString()}" +
//            "',0,0)")
    @InsertProvider(type = OrderProvider.class,method = "insertOrder")
    @Options(keyProperty = "orderId", keyColumn = "orderId", useGeneratedKeys = true)
    void addSeatSelectionOrder(@Param("order") SeatSelectionOrder order);

}
