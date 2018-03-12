package cn.edu.nju.p.ticketreservation.controllers.site;

import cn.edu.nju.p.ticketreservation.base.BaseResult;
import cn.edu.nju.p.ticketreservation.base.ErrorCode;
import cn.edu.nju.p.ticketreservation.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/seat")
public class SeatController {

    @Autowired
    private SeatService seatService;

    @GetMapping("/all")
    public BaseResult getAllSeats(@RequestParam("planId") int planId) {
        return new BaseResult<>(seatService.getAllSeatsOfPlan(planId), ErrorCode.SUCCESS);
    }
}
