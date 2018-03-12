package cn.edu.nju.p.ticketreservation.service.impl;

import cn.edu.nju.p.ticketreservation.dao.SeatDao;
import cn.edu.nju.p.ticketreservation.interact.display.SeatDisplay;
import cn.edu.nju.p.ticketreservation.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeatServiceImpl implements SeatService {

    @Autowired
    private SeatDao seatDao;

    @Override
    public List<SeatDisplay> getAllSeatsOfPlan(int planId) {
        return seatDao.getAllSeatsOfPlan(planId);
    }
}
