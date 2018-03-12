package cn.edu.nju.p.ticketreservation.service;

import cn.edu.nju.p.ticketreservation.interact.display.SeatDisplay;

import java.util.List;

public interface SeatService {

    List<SeatDisplay> getAllSeatsOfPlan(int planId);
}
