package cn.edu.nju.p.ticketreservation.dao;

import cn.edu.nju.p.ticketreservation.interact.input.PlanSeatPriceForm;
import cn.edu.nju.p.ticketreservation.utils.mybatis.provider.SeatPricesProvider;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface SeatPriceDao {

    @InsertProvider(type = SeatPricesProvider.class, method = "addPlanSeatPrice")
    void addPlanSeatPrice(@Param("seatPrices") List<PlanSeatPriceForm> seatPriceForms, @Param("planId") int planId, @Param("siteId") int siteId);

    @Select("select * from plan_seatz_price where plan_id=#{planId} and site_id=#{siteId}")
    @Results({
            @Result(property = "siteId", column = "site_id"),
            @Result(property = "seatZ", column = "seat_z"),
            @Result(property = "price", column = "price")
    })
    PlanSeatPriceForm getPlanSeatPrice(@Param("planId") int planId, @Param("siteId") int siteId);
}
