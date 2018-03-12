package cn.edu.nju.p.ticketreservation.service.impl;

import cn.edu.nju.p.ticketreservation.dao.PlanDao;
import cn.edu.nju.p.ticketreservation.dao.SeatPriceDao;
import cn.edu.nju.p.ticketreservation.exception.DateNotAvailableException;
import cn.edu.nju.p.ticketreservation.exception.PlanNotExistException;
import cn.edu.nju.p.ticketreservation.interact.display.SiteDisplay;
import cn.edu.nju.p.ticketreservation.interact.input.PlanForm;
import cn.edu.nju.p.ticketreservation.interact.input.PlanSeatPriceForm;
import cn.edu.nju.p.ticketreservation.service.PlanService;
import cn.edu.nju.p.ticketreservation.utils.RedisCacheUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class PlanServiceImpl implements PlanService {

    @Autowired
    private PlanDao planDao;

    @Autowired
    private RedisCacheUtil cacheUtil;

    public static final String PLAN_CACHE_POSTFIX = "_plan_info";

    @Override
    @Transactional
    public PlanForm addPlan(PlanForm planForm, SiteDisplay siteDisplay) {

        checkDate(planForm);

        planDao.addPlan(planForm);

        List<PlanSeatPriceForm> seatPriceForms = planForm.getPriceList();
        int size_x = siteDisplay.getSeatCountX();
        int size_y = siteDisplay.getSeatCountY();
        int planId = planForm.getPlanId();

        seatPriceForms.forEach(seatPriceForm -> {
            int z = seatPriceForm.getSeatZ();
            double price = seatPriceForm.getPrice();
            planDao.addPlanSeats(planId, size_x, size_y, z, price);
        });

        // cache the plan
        cacheUtil.putCacheWithExpireTime(planForm + PLAN_CACHE_POSTFIX, planForm, 60 * 30);

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
        String key = planId + PLAN_CACHE_POSTFIX;
        if (cacheUtil.cacheExist(key)) {
            return cacheUtil.getCache(key, PlanForm.class);
        } else {
            PlanForm result = planDao.getPlanForm(planId);
            if (result == null) {
                throw new PlanNotExistException("Plan of " + planId + " Not exist!");
            }
            cacheUtil.putCacheWithExpireTime(key, result, 60 * 30);
            return result;
        }
    }

    @Override
    public List<PlanForm> getAllPlan(String siteId) {

        return planDao.getAllCurrentPlans(Integer.valueOf(siteId));
    }

}
