package cn.edu.nju.p.ticketreservation.service.impl;

import cn.edu.nju.p.ticketreservation.dao.PlanDao;
import cn.edu.nju.p.ticketreservation.dao.SeatPriceDao;
import cn.edu.nju.p.ticketreservation.interact.input.PlanForm;
import cn.edu.nju.p.ticketreservation.interact.input.PlanSeatPriceForm;
import cn.edu.nju.p.ticketreservation.service.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        List<PlanSeatPriceForm> seatPriceForms = planForm.getPriceList();
        planDao.addPlan(planForm);
        int planId = planForm.getPlanId();
        String siteId = planForm.getSiteId();
        seatPriceDao.addPlanSeatPrice(seatPriceForms, planId, Integer.valueOf(siteId));
        seatPriceForms.forEach(seatPriceForm -> seatPriceForm.setSiteId(siteId));
        return planForm;
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

}
