package cn.edu.nju.p.ticketreservation.dao;

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
}
