package cn.edu.nju.p.ticketreservation.service;

import cn.edu.nju.p.ticketreservation.interact.input.PlanForm;

public interface PlanService {

    PlanForm addPlan(PlanForm planForm);

    void updatePlan(PlanForm planForm);

    void deletePlan(int planId);

    PlanForm getPlan(int planId);
}
