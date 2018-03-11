package cn.edu.nju.p.ticketreservation.service;

import cn.edu.nju.p.ticketreservation.interact.display.SiteDisplay;
import cn.edu.nju.p.ticketreservation.interact.input.PlanForm;

import java.util.List;

public interface PlanService {

    PlanForm addPlan(PlanForm planForm, SiteDisplay siteDisplay);

    void updatePlan(PlanForm planForm);

    void deletePlan(int planId);

    PlanForm getPlan(int planId);

    List<PlanForm> getAllPlan(String siteId);
}
