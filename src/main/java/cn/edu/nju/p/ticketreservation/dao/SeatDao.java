package cn.edu.nju.p.ticketreservation.dao;

import cn.edu.nju.p.ticketreservation.interact.display.SeatDisplay;
import cn.edu.nju.p.ticketreservation.interact.input.SeatForm;
import cn.edu.nju.p.ticketreservation.utils.mybatis.provider.SeatBookProvider;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface SeatDao {

    @UpdateProvider(type = SeatBookProvider.class, method = "bookSeats")
    void bookSeats(@Param("planId") int planId, @Param("seats") List<SeatForm> seatForms);

    @Select("select * from plan_seats where planId=#{planId}")
    @Results({
            @Result(property = "x", column = "seat_x"),
            @Result(property = "y", column = "seat_y"),
            @Result(property = "z", column = "seat_z"),
            @Result(property = "price", column = "price"),
            @Result(property = "seatStatus", column = "status")
    })
    List<SeatDisplay> getAllSeatsOfPlan(@Param("planId") int planId);
}
