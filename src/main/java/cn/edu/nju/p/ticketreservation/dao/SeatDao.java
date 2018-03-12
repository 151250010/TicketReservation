package cn.edu.nju.p.ticketreservation.dao;

import cn.edu.nju.p.ticketreservation.interact.input.SeatForm;
import cn.edu.nju.p.ticketreservation.utils.mybatis.provider.SeatBookProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.UpdateProvider;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface SeatDao {

    @UpdateProvider(type = SeatBookProvider.class, method = "bookSeats")
    void bookSeats(@Param("planId") int planId, @Param("seats") List<SeatForm> seatForms);

}
