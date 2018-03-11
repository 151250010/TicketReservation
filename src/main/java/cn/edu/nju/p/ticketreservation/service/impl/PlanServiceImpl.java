package cn.edu.nju.p.ticketreservation.service.impl;

import cn.edu.nju.p.ticketreservation.dao.PlanDao;
import cn.edu.nju.p.ticketreservation.dao.SeatPriceDao;
import cn.edu.nju.p.ticketreservation.exception.DateNotAvailableException;
import cn.edu.nju.p.ticketreservation.interact.input.PlanForm;
import cn.edu.nju.p.ticketreservation.interact.input.PlanSeatPriceForm;
import cn.edu.nju.p.ticketreservation.service.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class PlanServiceImpl implements PlanService {

    @Autowired
    private PlanDao planDao;

    @Autowired
    private SeatPriceDao seatPriceDao;

    @Override
    @Transactional
    public PlanForm addPlan(PlanForm planForm) {

        checkDate(planForm);

        planDao.addPlan(planForm);

        List<PlanSeatPriceForm> seatPriceForms = planForm.getPriceList();
        int planId = planForm.getPlanId();
        String siteId = planForm.getSiteId();

        seatPriceDao.addPlanSeatPrice(seatPriceForms, planId, Integer.valueOf(siteId));
        seatPriceForms.forEach(seatPriceForm -> seatPriceForm.setSiteId(siteId));
        return planForm;
    }

    private void checkDate(PlanForm planForm) {
        String start = planForm.getStart();
        String end = planForm.getEnd();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime startDateTime = LocalDateTime.parse(start, formatter);
        LocalDateTime endDateTime = LocalDateTime.parse(end, formatter);
        if (startDateTime.isBefore(LocalDateTime.now()) || endDateTime.isBefore(LocalDateTime.now())) {
            throw new DateNotAvailableException("The start date or end date input is before current time!");
        }
        if (startDateTime.isAfter(endDateTime)) {
            throw new DateNotAvailableException("The start date is after the end date!");
        }
    }

    @Override
    public void updatePlan(PlanForm planForm) {

    }

    @Override
    public void deletePlan(int planId) {

    }

    @Override
    public PlanForm getPlan(int planId) {
        return planDao.getPlanForm(planId);
    }

    @Override
    public List<PlanForm> getAllPlan(String siteId) {

        return planDao.getAllCurrentPlans(Integer.valueOf(siteId));
    }

}
